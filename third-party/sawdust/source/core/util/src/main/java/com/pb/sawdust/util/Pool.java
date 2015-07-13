package com.pb.sawdust.util;

import com.pb.sawdust.util.exceptions.RuntimeInterruptedException;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;

/**
 * The {@code Pool} class is used to provide a container through which objects can be recycled. When an object is
 * requested through the {@code get()} method, a pool item containing an object ({@code T}) is removed from the pool and
 * will be unavailable for further use (through a {@code get()} call) until the pool item's {@code finished()} method is
 * called, returning it too the pool. If an object is requested and the pool is empty, a new object is created using the
 * {@code getNew()} method, otherwise one of the previously created objects will be recycled.
 * <p>
 * Every pool has a limit on how many new objects can be created; once that limit has been reached, {@code get()} calls
 * on an empty pool will either block (if the poll has been set to block) or will return {@code null}.
 * <p>
 * This pool is fairly simple, and does not provide functionality to reclaim stale or otherwise unusable pool items.
 *
 * @param <T>
 *        The type held by this pool.
 *
 * @author crf <br/>
 *         Started: Oct 13, 2008 4:00:13 PM
 */
@ThreadSafe
public abstract class Pool<T> {
    private final BlockingQueue<PoolItem> pool;
    private final int limit;
    @GuardedBy("this") private volatile int createdCount = 0;
    @GuardedBy("this") private volatile boolean poolLimitReached = false;
    private final boolean blocking;

    /**
     * Constructor specifying the pool's size limit and whether it should block or not.
     *
     * @param limit
     *        The maximum size of the pool. This sets the limit on how many objects can <i>ever</i> be created by the
     *        pool.
     *
     * @param blocking
     *        If {@code true}, then this pool will block if {@code get()} is called when it is empty but its limit
     *        on object creation has been reached; otherwise calls to {@code get()} will return {@code null}.
     */
    public Pool(int limit, boolean blocking) {
        pool = new LinkedBlockingQueue<PoolItem>();
        this.limit = limit;
        this.blocking = blocking;
    }

    /**
     * Constructor specifying the pool's size limit. This constructor will create a non-blocking pool
     *
     * @param limit
     *        The maximum size of the pool. This sets the limit on how many objects can <i>ever</i> be created by the
     *        pool.
     */
    public Pool(int limit) {
        this(limit,false);
    }

    /**
     * Get an item from the pool. If the pool is empty and its object creation limit has been reached, then it will
     * either block (if the pool has been set to blocking) or return {@code null}.
     *
     * @return a pool item containing an object for use, or {@code null} if the pool is empty and its object creation
     *         limit has been reached and the pool is non-blocking.
     *
     * @throws RuntimeInterruptedException if the call blocks and is interrupted during that block.
     */
    public PoolItem get() {
        PoolItem pi = pool.poll();
        if (pi == null) {
            synchronized(this) {
                if (!poolLimitReached) {
                    pi = new PoolItem(getNew());
                    poolLimitReached = ++createdCount == limit;
                } else if (blocking) {
                    try {
                        pi = pool.take();
                    } catch (InterruptedException e) {
                        throw new RuntimeInterruptedException(e);
                    }
                }
            }
        }
        return pi;
    }

    /**
     * Get a new object instance for use in this pool. {@code get()} calls on an empty pool (which has not reached its
     * object creation limit) will call this method to create new pool items.
     *
     * @return a new object for use in this pool
     */
    abstract protected T getNew();

    /**
     * The {@code PoolItem} class is used to hold pool objects. It is through instances of this class that pool objects
     * can be retrieved (via {@code get()}) and released (via {@code finished()} for use by others.
     */
    public final class PoolItem {
        private final T item;

        private PoolItem(T item) {
            this.item = item;
        }

        /**
         * Get the object held by this pool item.
         *
         * @return the object held by this pool item.
         */
        public final T get() {
            return item;
        }

        /**
         * Indicate that the object held by this pool item is no longer in use and can be placed back in the pool for
         * use by others.
         */
        public final void finished() {
            pool.add(this);
        }
    }
}