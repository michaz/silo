package com.pb.sawdust.tensor.alias.vector.id;

import com.pb.sawdust.tensor.alias.vector.primitive.ShortVector;
import com.pb.sawdust.tensor.decorators.id.primitive.size.IdShortD1Tensor;
import com.pb.sawdust.tensor.Tensor;

import java.util.Iterator;

/**
 * The {@code IdShortVector} interface provides an alternate name for 1-dimensional id tensors holding {@code short}s.
 *
 * @author crf <br/>
 *         Started: Jun 16, 2009 9:22:03 AM
 */
public interface IdShortVector<I> extends IdShortD1Tensor<I>,ShortVector {
    
    /**
     * {@inheritDoc}
     * 
     * The tensors this iterator loops over are guaranteed to be {@code IdShortScalar}s.
     * 
     */
    Iterator<Tensor<Short>> iterator();
}