package com.pb.sawdust.tensor.decorators.primitive.size;

import com.pb.sawdust.tensor.alias.scalar.primitive.ByteScalar;
import com.pb.sawdust.tensor.index.Index;
import com.pb.sawdust.tensor.decorators.size.AbstractD0Tensor;
import com.pb.sawdust.util.array.ByteTypeSafeArray;
import com.pb.sawdust.util.array.ArrayUtil;
import com.pb.sawdust.util.array.TypeSafeArrayFactory;
import com.pb.sawdust.util.JavaType;

/**
 * The {@code AbstractByteD0Tensor} class provides a skeletal implementation of the {@code ByteD0Tensor} interface.
 *
 * @author crf <br/>
 *         Started: Jun 22, 2009 1:23:58 PM
 */
public abstract class AbstractByteD0Tensor extends AbstractD0Tensor<Byte> implements ByteScalar {

    /**
     * Constructor which will create a scalar using a default index.
     */
    protected AbstractByteD0Tensor() {
    }

    /**
     * Constructor specifying the index to use.
     *
     * @param index
     *        The index to use for the scalar.
     *
     * @throws IllegalArgumentException if {@code index.size() != 0}. 
     */
    protected AbstractByteD0Tensor(Index<?> index) {
        super(index);
    }

    @Override
    public JavaType getType() {
        return JavaType.BYTE;
    }

    @Override
    public Byte getValue() {
        return getCell();
    }

    @Override
    public void setValue(Byte value) {
        setCell(value);
    }

    @Override
    public byte getCell(int... indices) {
        if (indices.length != 0)
            throw new IllegalArgumentException("Scalar has no dimensionality (referred to with " + indices.length + " dimensions).");
        return getValue();
    }

    @Override
    public void setCell(byte value, int... indices) {
        if (indices.length != 0)
            throw new IllegalArgumentException("Scalar has no dimensionality (referred to with " + indices.length + " dimensions).");
        setValue(value);
    }

    @Override
    public void setTensorValues(ByteTypeSafeArray valuesArray) {
        if (!ArrayUtil.isOfDimension(valuesArray.getArray(),new int[] {1}))
            throw new IllegalArgumentException("TypeSafeArray for setting a scalar must contain only one value.");
        setCell(valuesArray.get(0));
    }

    @Override
    public ByteTypeSafeArray getTensorValues(Class<Byte> c) {
        return getTensorValues();
    }

    @Override
    public ByteTypeSafeArray getTensorValues() {
        ByteTypeSafeArray array = TypeSafeArrayFactory.byteTypeSafeArray(1);
        array.set(getCell(),0);
        return array;
    }
}