package com.pb.sawdust.tensor.alias.matrix;

import com.pb.sawdust.tensor.decorators.size.D2TensorTest;
import com.pb.sawdust.tensor.alias.vector.Vector;
import com.pb.sawdust.tensor.Tensor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author crf <br/>
 *         Started: Jul 5, 2009 9:15:29 AM
 */
public abstract class MatrixTest<T> extends D2TensorTest<T> {
    @Test
    public void testIteratorType() {
        for (Tensor<T> t : tensor)
            assertTrue(t instanceof Vector);
    }
}