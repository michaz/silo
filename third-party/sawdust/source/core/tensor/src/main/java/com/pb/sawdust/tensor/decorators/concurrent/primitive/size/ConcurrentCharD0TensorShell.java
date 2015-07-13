package com.pb.sawdust.tensor.decorators.concurrent.primitive.size;

import com.pb.sawdust.tensor.TensorImplUtil;
import com.pb.sawdust.tensor.alias.scalar.primitive.CharScalar;
import com.pb.sawdust.tensor.index.Index;
import com.pb.sawdust.tensor.decorators.concurrent.size.ConcurrentD0TensorShell;
import com.pb.sawdust.tensor.decorators.primitive.CharTensor;
import com.pb.sawdust.tensor.decorators.primitive.size.CharD0Tensor;
import com.pb.sawdust.tensor.decorators.id.primitive.IdCharTensor;
import com.pb.sawdust.util.array.CharTypeSafeArray;

import java.util.concurrent.locks.Lock;

/**
 * The {@code ConcurrentCharD0TensorShell} class provides a wrapper for implementations of the {@code CharScalar} interface
 * with support for concurrent access. The locking policy is set by the {@code D0ConcurrentTensorLocks} implementation used
 * in the class.
 *
 * @author crf <br/>
 *         Started: January 30, 2009 10:47:31 PM
 *         Revised: Jun 16, 2009 3:17:19 PM
 */
public class ConcurrentCharD0TensorShell extends ConcurrentD0TensorShell<Character> implements CharScalar {
    private final CharD0Tensor tensor;

    /**
     * Constructor specifying the tensor to wrap and the concurrency policy used for locking the tensor.
     *
     * @param tensor
     *        The tensor to wrap.
     *
     * @param locks
     *        The {@code ConcurrentD0TensorLocks} instance holding the concurrency policy used when locking the tensor.
     */
    public ConcurrentCharD0TensorShell(CharD0Tensor tensor, ConcurrentD0TensorLocks locks) {
        super(tensor,locks);
        this.tensor = tensor;
    }
    
    public char getCell() {
        Lock lock = locks.getReadLock();
        lock.lock();
        try {
            return tensor.getCell();
        } finally {
            lock.unlock();
        }
    }

    public void setCell(char value) {
        Lock lock = locks.getWriteLock();
        lock.lock();
        try {
            tensor.setCell(value);
        } finally {
            lock.unlock();
        }
    }

    public char getCell(int ... indices) {
        TensorImplUtil.checkIndicesLength(this,indices);
        Lock lock = locks.getReadLock();
        lock.lock();
        try {
            return tensor.getCell();
        } finally {
            lock.unlock();
        }
    }

    public void setCell(char value, int ... indices) {
        TensorImplUtil.checkIndicesLength(this,indices);
        Lock lock = locks.getWriteLock();
        lock.lock();
        try {
            tensor.setCell(value);
        } finally {
            lock.unlock();
        }
    }

    public void setTensorValues(CharTypeSafeArray valuesArray) {
        Lock lock = locks.getWriteLock();
        lock.lock();
        try {
            tensor.setTensorValues(valuesArray);
        } finally {
            lock.unlock();
        }
    }

    public CharTypeSafeArray getTensorValues() {
        Lock lock = locks.getReadLock();
        lock.lock();
        try {
            return tensor.getTensorValues();
        } finally {
            lock.unlock();
        }
    }               

    public CharTypeSafeArray getTensorValues(Class<Character> type) {
        Lock lock = locks.getReadLock();
        lock.lock();
        try {
            return tensor.getTensorValues(type);
        } finally {
            lock.unlock();
        }
    }

    protected CharTensor getComposedTensor(Index index) {
        return TensorImplUtil.getComposedTensor(this,index);
    }

    public <I> IdCharTensor<I> getReferenceTensor(Index<I> index) {
        return (IdCharTensor<I>) super.getReferenceTensor(index);
    }
}