package com.pb.sawdust.tensor.decorators.primitive.size;

import com.pb.sawdust.tensor.decorators.primitive.IntTensorTest;
import com.pb.sawdust.tensor.Tensor;
import com.pb.sawdust.util.array.TypeSafeArray;
import static com.pb.sawdust.util.Range.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author crf <br/>
 *         Started: Jan 26, 2009 7:42:25 PM
 */
public abstract class IntD9TensorTest extends IntTensorTest {
    IntD9Tensor pdTensor;

    abstract protected IntD9Tensor getTensor(TypeSafeArray<Integer> data);
    
    @Before
    public void beforeTest() {
        super.beforeTest();
        pdTensor = (IntD9Tensor) tensor;
    }
    
    @Test
    public void TestSizeSpecific() {
        assertEquals(9,pdTensor.size());
    }

    @Test
    public void testGetValueD() {
        int[] index = new int[dimensions.length];
        for (int i : range(index.length))
            index[i] = random.nextInt(dimensions[i]);
        assertAlmostEquals(data.getValue(index),pdTensor.getValue(index[0],index[1],index[2],index[3],index[4],index[5],index[6],index[7],index[8]));
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetValueInvalidIndexTooBigD() {
        int[] index = new int[dimensions.length];
        int randomIndex = random.nextInt(dimensions.length);
        index[randomIndex] = dimensions[randomIndex];
        pdTensor.getValue(index[0],index[1],index[2],index[3],index[4],index[5],index[6],index[7],index[8]);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetValueInvalidIndexTooSmallD() {
        int[] index = new int[dimensions.length];
        index[random.nextInt(dimensions.length)] = -1;
        pdTensor.getValue(index[0],index[1],index[2],index[3],index[4],index[5],index[6],index[7],index[8]);
    }

    @Test
    public void testSetValueD() {
        int[] index = new int[dimensions.length];
        for (int i : range(index.length))
            index[i] = random.nextInt(dimensions[i]);
        Integer value = getRandomElement();
        pdTensor.setValue(value,index[0],index[1],index[2],index[3],index[4],index[5],index[6],index[7],index[8]);
        assertAlmostEquals(value,pdTensor.getValue(index));
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testSetValueInvalidIndexTooBigD() {
        int[] index = new int[dimensions.length];
        int randomIndex = random.nextInt(dimensions.length);
        index[randomIndex] = dimensions[randomIndex];
        pdTensor.setValue(getRandomElement(),index[0],index[1],index[2],index[3],index[4],index[5],index[6],index[7],index[8]);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testSetValueInvalidIndexTooSmallD() {
        int[] index = new int[dimensions.length];
        index[random.nextInt(dimensions.length)] = -1;
        pdTensor.setValue(getRandomElement(),index[0],index[1],index[2],index[3],index[4],index[5],index[6],index[7],index[8]);
    }

    @Test
    public void testGetCellD() {
        int[] index = new int[dimensions.length];
        for (int i : range(index.length))
            index[i] = random.nextInt(dimensions[i]);
        assertAlmostEquals(pData.get(index),pdTensor.getCell(index[0],index[1],index[2],index[3],index[4],index[5],index[6],index[7],index[8]));
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetCellInvalidIndexTooBigD() {
        int[] index = new int[dimensions.length];
        int randomIndex = random.nextInt(dimensions.length);
        index[randomIndex] = dimensions[randomIndex];
        pdTensor.getCell(index[0],index[1],index[2],index[3],index[4],index[5],index[6],index[7],index[8]);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetCellInvalidIndexTooSmallD() {
        int[] index = new int[dimensions.length];
        index[random.nextInt(dimensions.length)] = -1;
        pdTensor.getCell(index[0],index[1],index[2],index[3],index[4],index[5],index[6],index[7],index[8]);
    }

    @Test
    public void testSetCellD() {
        int[] index = new int[dimensions.length];
        for (int i : range(index.length))
            index[i] = random.nextInt(dimensions[i]);
        int value = getRandomElement();
        pdTensor.setCell(value,index[0],index[1],index[2],index[3],index[4],index[5],index[6],index[7],index[8]);
        assertAlmostEquals(value,pdTensor.getCell(index));
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testSetCellInvalidIndexTooBigD() {
        int[] index = new int[dimensions.length];
        int randomIndex = random.nextInt(dimensions.length);
        index[randomIndex] = dimensions[randomIndex];
        pdTensor.setCell(getRandomElement(),index[0],index[1],index[2],index[3],index[4],index[5],index[6],index[7],index[8]);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testSetCellInvalidIndexTooSmallD() {
        int[] index = new int[dimensions.length];
        index[random.nextInt(dimensions.length)] = -1;
        pdTensor.setCell(getRandomElement(),index[0],index[1],index[2],index[3],index[4],index[5],index[6],index[7],index[8]);
    }

    @Test
    public void testIteratorType() {
        for (Tensor<Integer> t : tensor)
            assertTrue(t instanceof IntD8Tensor);
    }
}