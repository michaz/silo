package com.pb.sawdust.tensor.alias.scalar.impl;

import com.pb.sawdust.tensor.decorators.primitive.AbstractIntTensor;
import com.pb.sawdust.tensor.decorators.primitive.size.AbstractIntD0Tensor;
import com.pb.sawdust.tensor.decorators.id.primitive.IdIntTensor;
import com.pb.sawdust.tensor.index.Index;
import com.pb.sawdust.tensor.TensorImplUtil;

/**
 * The {@code IntScalarImpl} class provides the default {@code IntScalar} implementation.
 *
 * @author crf <br/>
 *         Started: Jun 16, 2009 8:14:56 PM
 */
public class IntScalarImpl extends AbstractIntD0Tensor {
    private int value;

    /**
     * Constructor placing a default {@code int} as the scalar value.
     */
    public IntScalarImpl() {
    }

    /**
     * Constructor specifying the value of the scalar.
     *
     * @param value
     *        The value to set the scalar to.
     */
    public IntScalarImpl(int value) {
        this.value = value;
    }

    /**
     * Constructor specifying the value and index of the scalar. This constructor should only be used for calls to
     * {@code getReferenceTensor(Index)}.
     *
     * @param value
     *        The value to set the scalar to.
     *
     * @param index
     *        The index to use with this scalar.
     *
     * @throws IllegalArgumentException if {@code index.size() != 0}.
     */
    protected IntScalarImpl(int value, Index<?> index) {
        super(index);
        this.value = value;
    }

    @Override
    public int getCell() {
        return value;
    }

    @Override
    public void setCell(int value) {
        this.value = value;
    }

    @Override
    @SuppressWarnings("unchecked") //idTensorCaster will turn this to an IdIntTensor, for sure
    public <I> IdIntTensor<I> getReferenceTensor(Index<I> index) {
        if (!index.isValidFor(this))
            throw new IllegalArgumentException("Index invalid for this tensor.");
        return (IdIntTensor<I>) TensorImplUtil.idTensorCaster(new ComposedTensor(index));
    }

    private class ComposedTensor extends AbstractIntTensor {

        public ComposedTensor(Index<?> index) {
            super(index);
        }

        @Override
        public int getCell(int... indices) {
            return IntScalarImpl.this.getCell();
        }

        @Override
        public void setCell(int value, int... indices) {
            IntScalarImpl.this.setCell(value);
        }
    }
}