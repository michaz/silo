package com.pb.sawdust.tensor.decorators.primitive.size;

import com.pb.sawdust.tensor.decorators.size.D2TensorShell;
import com.pb.sawdust.tensor.decorators.primitive.FloatTensor;
import com.pb.sawdust.tensor.index.Index;
import com.pb.sawdust.tensor.TensorImplUtil;
import com.pb.sawdust.tensor.decorators.id.primitive.IdFloatTensor;
import com.pb.sawdust.util.abacus.IterableAbacus;
import com.pb.sawdust.util.array.FloatTypeSafeArray;
import com.pb.sawdust.util.array.TypeSafeArrayFactory;
import com.pb.sawdust.tensor.alias.matrix.primitive.FloatMatrix;

/**
 * The {@code FloatD2TensorShell} class is a wrapper which sets a 2-dimensional {@code FloatTensor} as a {@code Matrix} (or,
 * more specifically, a {@code FloatMatrix}).
 *
 * @author crf <br/>
 *         Started: Sat Oct 25 21:35:12 2008
 *         Revised: Dec 14, 2009 12:35:25 PM
 */
public class FloatD2TensorShell extends D2TensorShell<Float> implements FloatMatrix {
    private final FloatTensor tensor;

    /**
     * Constructor specifying tensor to wrap. The tensor must be 2-dimensional or an exception will be thrown.
     *
     * @param tensor
     *        The tensor to wrap.
     *
     * @throws IllegalArgumentException if {@code tensor} is not 2 dimension in size.
     */
    public FloatD2TensorShell(FloatTensor tensor) {
        super(tensor);
        this.tensor = tensor;
    }

    /**
     * {@inheritDoc}
     *
     * This method just calls {@code FloatTensor.getCell(d0index,d1index)}, 
     * and should be overridden if any efficiencies over that method can be made.
     */
    public float getCell(int d0index, int d1index) {
        return tensor.getCell(d0index,d1index);
    }

    public float getCell(int ... indices) {
        if (indices.length != 2)
            throw new IllegalArgumentException("FloatD2Tensor is 2 dimension in size, getValue passed with " + indices.length + " indices.");
        return getCell(indices[0],indices[1]);
    }

    public Float getValue(int ... indices) {
        return getCell(indices);
    }

    public Float getValue(int d0index, int d1index) {
        return getCell(d0index,d1index);
    }

    /**
     * {@inheritDoc}
     *
     * This method just calls {@code FloatTensor.setCell(float,d0index,d1index)}, 
     * and should be overridden if any efficiencies over that method can be made.
     */
    public void setCell(float value, int d0index, int d1index) {
        tensor.setCell(value,d0index,d1index);
    }

    public void setCell(float value, int ... indices) {
        if (indices.length != 2)
            throw new IllegalArgumentException("FloatD2Tensor is 2 dimension in size, getValue passed with " + indices.length + " indices.");
        setCell(value,indices[0],indices[1]);
    }

    public void setValue(Float value, int ... indices) {
        setCell(value,indices);
    }

    public void setValue(Float value, int d0index, int d1index) {
        setCell(value,d0index,d1index);
    }
    
    public FloatTypeSafeArray getTensorValues(Class<Float> type) {
        return getTensorValues();
    }

    public FloatTypeSafeArray getTensorValues() {
       @SuppressWarnings("unchecked") //getType requirements in tensor make this ok
        FloatTypeSafeArray array = TypeSafeArrayFactory.floatTypeSafeArray(getDimensions());
        for (int[] index : IterableAbacus.getIterableAbacus(getDimensions()))
            array.set(getCell(index),index);
        return array;
    }

    public void setTensorValues(FloatTypeSafeArray valuesArray) {
        TensorImplUtil.setTensorValues(this,valuesArray);
    }

    public <I> IdFloatTensor<I> getReferenceTensor(Index<I> index) {
        return (IdFloatTensor<I>) super.getReferenceTensor(index);
    }

    protected FloatTensor getComposedTensor(Index<?> index) {
        return TensorImplUtil.getComposedTensor(this,index); 
    }
}