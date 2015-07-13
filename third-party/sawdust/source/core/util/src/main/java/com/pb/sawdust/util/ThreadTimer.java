package com.pb.sawdust.util;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.TimeUnit;

/**
 * The {@code ThreadTimer} class is provides a convenient thread-specific timer. The timer is used to get arbitrary time
 * durations. One {@code ThreadTimer} instance can be used across multiple threads, with each thread getting its own
 * timer. More specifically, any call to this class' methods from a given thread will not affect any timer started/reset
 * from a different thread.
 *
 * @author crf <br/>
 *         Started: Jul 20, 2008 8:19:07 PM
 */
@ThreadSafe
public class ThreadTimer {
    private final ThreadLocal<Long> timeStamp = new ThreadLocal<Long>();
    private final ThreadLocal<Long> timeOffset = new ThreadLocal<Long>() {
        protected Long initialValue() {
            return 0L;
        }
    };
    private final ThreadLocal<Boolean> isPaused = new ThreadLocal<Boolean>() {
        protected Boolean initialValue() {
            return false;
        }
    };
    private final TimeUnit baseTimeUnit = TimeUnit.NANOSECONDS;
    private final TimeUnit reportTimeUnit;

    /**
     * Constructor specifying the time unit to report durations recoreded by this class in.
     *
     * @param timeUnit
     *        The time unit to report the durations in.
     */
    public ThreadTimer(TimeUnit timeUnit) {
        this.reportTimeUnit = timeUnit;
    }

    /**
     * Convenience constructor creating a {@code ThreadTimer} which reports durations in nanoseconds.
     */
    public ThreadTimer() {
        this(TimeUnit.NANOSECONDS);
    }

    /**
     * Start the timer. A timer may only be started if it has not been started yet, or if it has been stopped. A
     * subsequent call (from the same thread) to {@code pollTimer()}, {@code resetTimer()} or {@code endTimer} will
     * return the time duration from the time this method was called.
     *
     * @throws IllegalStateException if the timer (for the calling thread) is currently running.
     */
    public void startTimer() {
        if (isRunning())
            throw new IllegalStateException("Timer already running.");
        timeStamp.set(System.nanoTime());
    }

    /**
     * Stop the timer and get the accumulated duration held by the timer.
     *
     * @return the accumulated time duration held by the timer.
     *
     * @throws IllegalStateException if the timer is not running.
     */
    public long endTimer() {
        long duration = getDuration();
        timeStamp.set(null);
        timeOffset.set(0L);
        return duration;
    }

    /**
     * Poll the timer to get the accumulated duration held by the timer, and reset the timer. Resetting the timer means
     * that subsequent calls (from this thread) to {@code pollTimer()}, {@code resetTimer()}, {@code pauseTimer()} or
     * {@code endTimer} will return the time duration from the time this method was called.
     *
     * @return the accumulated time duration held by the timer.
     *
     * @throws IllegalStateException if the timer is not running.
     */
    public long resetTimer() {
        long duration = getDuration();
        timeStamp.set(System.nanoTime());
        timeOffset.set(0L);
        return duration;
    }

    /**
     * Pause the timer and get the accumulated duration held by the timer.  Subsequent calls to {@code startTimer()} will
     * start the duration count from the accumulated duration returned by this function.
     *
     * @return the accumulated time duration held by the timer.
     *
     * @throws IllegalStateException if the timer is not running or is paused.
     */
    public long pauseTimer() {
        if (isPaused())
            throw new IllegalStateException("Timer is already paused.");
        timeOffset.set(getDuration());
        timeStamp.set(null);
        isPaused.set(true);
        return timeOffset.get();
    }

    /**
     * Poll the timer to get the accumulated duration held by the timer. This method will not reset the timer, so a
     * subsequent call to {@code pollTimer()}, {@code resetTimer()}, {@code pauseTimer()}, or {@code endTimer} will
     * return the time since this method was called <i>plus</i> the duration reported by this method.
     *
     * @return the accumulated time duration held by the timer.
     *
     * @throws IllegalStateException if the timer is not running.
     */
    public long pollTimer() {
        return getDuration();
    }

    /**
     * Determine whether the timer is currently running (in the calling thread) or not.
     *
     * @return {@code true} if the timer is currently running, {@code false} otherwise.
     */
    public boolean isRunning() {
        return timeStamp.get() != null;
    }

    /**
     * Determine whether the timer is currently paused (in the calling thread) or not.
     *
     * @return {@code true} if the timer is currently paused, {@code false} otherwise.
     */
    public boolean isPaused() {
        return isPaused.get();
    }

    private long getDuration() {
        if (!isPaused() && !isRunning())
            throw new IllegalStateException("Timer not started.");
        return reportTimeUnit.convert(System.nanoTime() - timeStamp.get() + timeOffset.get(),baseTimeUnit);
    }

    /**
     * Get the time unit used in this timer.
     *
     * @return this timer's time unit.
     */
    public TimeUnit getTimeUnit() {
        return reportTimeUnit;
    }
}