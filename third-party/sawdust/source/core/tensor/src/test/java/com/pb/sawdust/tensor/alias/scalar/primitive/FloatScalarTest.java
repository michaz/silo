package com.pb.sawdust.tensor.alias.scalar.primitive;

import com.pb.sawdust.tensor.decorators.primitive.size.FloatD0TensorTest;
import com.pb.sawdust.tensor.Tensor;
import org.junit.Test;                                                      
import static org.junit.Assert.*;

/**
 * @author crf <br/>
 *         Started: Jul 5, 2009 9:19:38 AM
 */
public abstract class FloatScalarTest extends FloatD0TensorTest {    
    @Test
    public void testIteratorType() {
        for (Tensor<Float> t : tensor)
            assertTrue(t instanceof FloatScalar);
    }
}