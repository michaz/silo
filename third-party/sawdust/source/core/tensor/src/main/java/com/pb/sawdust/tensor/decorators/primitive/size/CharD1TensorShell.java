package com.pb.sawdust.tensor.decorators.primitive.size;

import com.pb.sawdust.tensor.decorators.size.D1TensorShell;
import com.pb.sawdust.tensor.decorators.primitive.CharTensor;
import com.pb.sawdust.tensor.index.Index;
import com.pb.sawdust.tensor.TensorImplUtil;
import com.pb.sawdust.tensor.decorators.id.primitive.IdCharTensor;
import com.pb.sawdust.util.abacus.IterableAbacus;
import com.pb.sawdust.util.array.CharTypeSafeArray;
import com.pb.sawdust.util.array.TypeSafeArrayFactory;
import com.pb.sawdust.tensor.alias.vector.primitive.CharVector;

/**
 * The {@code CharD1TensorShell} class is a wrapper which sets a 1-dimensional {@code CharTensor} as a {@code Vector} (or,
 * more specifically, a {@code CharVector}).
 *
 * @author crf <br/>
 *         Started: Sat Oct 25 21:35:12 2008
 *         Revised: Dec 14, 2009 12:35:24 PM
 */
public class CharD1TensorShell extends D1TensorShell<Character> implements CharVector {
    private final CharTensor tensor;

    /**
     * Constructor specifying tensor to wrap. The tensor must be 1-dimensional or an exception will be thrown.
     *
     * @param tensor
     *        The tensor to wrap.
     *
     * @throws IllegalArgumentException if {@code tensor} is not 1 dimension in size.
     */
    public CharD1TensorShell(CharTensor tensor) {
        super(tensor);
        this.tensor = tensor;
    }

    /**
     * {@inheritDoc}
     *
     * This method just calls {@code CharTensor.getCell(index)}, 
     * and should be overridden if any efficiencies over that method can be made.
     */
    public char getCell(int index) {
        return tensor.getCell(index);
    }

    public char getCell(int ... indices) {
        if (indices.length != 1)
            throw new IllegalArgumentException("CharD1Tensor is 1 dimension in size, getValue passed with " + indices.length + " indices.");
        return getCell(indices[0]);
    }

    public Character getValue(int ... indices) {
        return getCell(indices);
    }

    public Character getValue(int index) {
        return getCell(index);
    }

    /**
     * {@inheritDoc}
     *
     * This method just calls {@code CharTensor.setCell(char,index)}, 
     * and should be overridden if any efficiencies over that method can be made.
     */
    public void setCell(char value, int index) {
        tensor.setCell(value,index);
    }

    public void setCell(char value, int ... indices) {
        if (indices.length != 1)
            throw new IllegalArgumentException("CharD1Tensor is 1 dimension in size, getValue passed with " + indices.length + " indices.");
        setCell(value,indices[0]);
    }

    public void setValue(Character value, int ... indices) {
        setCell(value,indices);
    }

    public void setValue(Character value, int index) {
        setCell(value,index);
    }
    
    public CharTypeSafeArray getTensorValues(Class<Character> type) {
        return getTensorValues();
    }

    public CharTypeSafeArray getTensorValues() {
       @SuppressWarnings("unchecked") //getType requirements in tensor make this ok
        CharTypeSafeArray array = TypeSafeArrayFactory.charTypeSafeArray(getDimensions());
        for (int[] index : IterableAbacus.getIterableAbacus(getDimensions()))
            array.set(getCell(index),index);
        return array;
    }

    public void setTensorValues(CharTypeSafeArray valuesArray) {
        TensorImplUtil.setTensorValues(this,valuesArray);
    }

    public <I> IdCharTensor<I> getReferenceTensor(Index<I> index) {
        return (IdCharTensor<I>) super.getReferenceTensor(index);
    }

    protected CharTensor getComposedTensor(Index<?> index) {
        return TensorImplUtil.getComposedTensor(this,index); 
    }
}