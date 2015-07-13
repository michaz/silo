package com.pb.sawdust.tensor.decorators.primitive.size;

import com.pb.sawdust.tensor.decorators.size.D6TensorShell;
import com.pb.sawdust.tensor.decorators.primitive.LongTensor;
import com.pb.sawdust.tensor.index.Index;
import com.pb.sawdust.tensor.TensorImplUtil;
import com.pb.sawdust.tensor.decorators.id.primitive.IdLongTensor;
import com.pb.sawdust.util.abacus.IterableAbacus;
import com.pb.sawdust.util.array.LongTypeSafeArray;
import com.pb.sawdust.util.array.TypeSafeArrayFactory;


/**
 * The {@code LongD6TensorShell} class is a wrapper which sets a 6-dimensional {@code LongTensor} as a {@code D6Tensor} (or,
 * more specifically, a {@code LongD6Tensor}).
 *
 * @author crf <br/>
 *         Started: Sat Oct 25 21:35:12 2008
 *         Revised: Dec 14, 2009 12:35:30 PM
 */
public class LongD6TensorShell extends D6TensorShell<Long> implements LongD6Tensor {
    private final LongTensor tensor;

    /**
     * Constructor specifying tensor to wrap. The tensor must be 6-dimensional or an exception will be thrown.
     *
     * @param tensor
     *        The tensor to wrap.
     *
     * @throws IllegalArgumentException if {@code tensor} is not 6 dimension in size.
     */
    public LongD6TensorShell(LongTensor tensor) {
        super(tensor);
        this.tensor = tensor;
    }

    /**
     * {@inheritDoc}
     *
     * This method just calls {@code LongTensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index)}, 
     * and should be overridden if any efficiencies over that method can be made.
     */
    public long getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
        return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index);
    }

    public long getCell(int ... indices) {
        if (indices.length != 6)
            throw new IllegalArgumentException("LongD6Tensor is 6 dimension in size, getValue passed with " + indices.length + " indices.");
        return getCell(indices[0],indices[1],indices[2],indices[3],indices[4],indices[5]);
    }

    public Long getValue(int ... indices) {
        return getCell(indices);
    }

    public Long getValue(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
        return getCell(d0index,d1index,d2index,d3index,d4index,d5index);
    }

    /**
     * {@inheritDoc}
     *
     * This method just calls {@code LongTensor.setCell(long,d0index,d1index,d2index,d3index,d4index,d5index)}, 
     * and should be overridden if any efficiencies over that method can be made.
     */
    public void setCell(long value, int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
        tensor.setCell(value,d0index,d1index,d2index,d3index,d4index,d5index);
    }

    public void setCell(long value, int ... indices) {
        if (indices.length != 6)
            throw new IllegalArgumentException("LongD6Tensor is 6 dimension in size, getValue passed with " + indices.length + " indices.");
        setCell(value,indices[0],indices[1],indices[2],indices[3],indices[4],indices[5]);
    }

    public void setValue(Long value, int ... indices) {
        setCell(value,indices);
    }

    public void setValue(Long value, int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
        setCell(value,d0index,d1index,d2index,d3index,d4index,d5index);
    }
    
    public LongTypeSafeArray getTensorValues(Class<Long> type) {
        return getTensorValues();
    }

    public LongTypeSafeArray getTensorValues() {
       @SuppressWarnings("unchecked") //getType requirements in tensor make this ok
        LongTypeSafeArray array = TypeSafeArrayFactory.longTypeSafeArray(getDimensions());
        for (int[] index : IterableAbacus.getIterableAbacus(getDimensions()))
            array.set(getCell(index),index);
        return array;
    }

    public void setTensorValues(LongTypeSafeArray valuesArray) {
        TensorImplUtil.setTensorValues(this,valuesArray);
    }

    public <I> IdLongTensor<I> getReferenceTensor(Index<I> index) {
        return (IdLongTensor<I>) super.getReferenceTensor(index);
    }

    protected LongTensor getComposedTensor(Index<?> index) {
        return TensorImplUtil.getComposedTensor(this,index); 
    }
}