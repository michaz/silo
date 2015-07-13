package com.pb.sawdust.tensor.decorators.primitive.size;

import com.pb.sawdust.tensor.decorators.primitive.BooleanTensor;
import com.pb.sawdust.tensor.decorators.size.D0Tensor;
import com.pb.sawdust.tensor.decorators.id.primitive.IdBooleanTensor;
import com.pb.sawdust.tensor.Tensor;
import com.pb.sawdust.tensor.index.Index;

import java.util.Iterator;

/**
 * The {@code BooleanD0Tensor} interface combines the {@code BooleanTensor} and {@code D0Tensor} interfaces. The notes and caveats
 * specified in those interfaces apply here as well.
 *
 * @see com.pb.sawdust.tensor.alias.scalar.primitive.BooleanScalar
 *
 * @author crf <br/>
 *         Started: Sat Oct 25 21:35:12 2008
 *         Revised: Jun 16, 2009 3:17:18 PM
 */
public interface BooleanD0Tensor extends BooleanTensor, D0Tensor<Boolean> {
    /**
     * Get the value of this tensor.
     *
     * @return the tensor value at the specified location.
     */
    boolean getCell();
    
    /**
     * Set the value of this tensor.
     *
     * @param value
     *        The value to set the tensor to.
     */
    void setCell(boolean value);

    @Override
    <I> IdBooleanTensor<I> getReferenceTensor(Index<I> index);
    
    /**
     * {@inheritDoc}
     * 
     * The returned iterator will iterate exactly once, returning this tensor.
     * 
     */
    Iterator<Tensor<Boolean>> iterator();
}