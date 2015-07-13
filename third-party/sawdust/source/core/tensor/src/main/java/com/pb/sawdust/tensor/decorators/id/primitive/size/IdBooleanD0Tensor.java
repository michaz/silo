package com.pb.sawdust.tensor.decorators.id.primitive.size;

import com.pb.sawdust.tensor.decorators.primitive.size.BooleanD0Tensor;
import com.pb.sawdust.tensor.decorators.id.primitive.IdBooleanTensor;
import com.pb.sawdust.tensor.decorators.id.size.IdD0Tensor;
import com.pb.sawdust.tensor.Tensor;
import com.pb.sawdust.tensor.index.Index;

import java.util.Iterator;

/**
 * The {@code IdBooleanD0Tensor} class combines the {@code Boolean0Tensor} and {@code IdTensor} interfaces. Because it is
 * of rank 0, ids are meangless/unused; nonetheless, this interface is specified as a complement to the other {@code Id...}
 * interfaces.
 *
 * @param <I>
 *        The type of id used to reference dimensional indices.
 *
 * @see com.pb.sawdust.tensor.alias.scalar.id.IdBooleanScalar
 *
 * @author crf <br/>
 *         Started: Jan 14, 2009 11:00:16 PM
 *         Revised: Jun 16, 2009 3:17:18 PM
 */
public interface IdBooleanD0Tensor<I> extends BooleanD0Tensor,IdBooleanTensor<I>,IdD0Tensor<Boolean,I> {

    /**
     * Get the value held in this tensor.
     *
     * @return the tensor value.
     */
    boolean getCellById();

    /**
     * Set the value this tensor.
     *
     * @param value
     *        The value to set this tensor to.
     */
    void setCellById(boolean value);

    @Override
    <J> IdBooleanTensor<J> getReferenceTensor(Index<J> index);

    /**
     * {@inheritDoc}
     *
     * The returned iterator will iterate exactly once, returning this tensor.
     *
     */
    Iterator<Tensor<Boolean>> iterator();
}