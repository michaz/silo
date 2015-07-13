package com.pb.sawdust.calculator.tensor.la.mm;

import com.pb.sawdust.calculator.tensor.TensorTestUtil;
import com.pb.sawdust.tensor.ArrayTensor;
import com.pb.sawdust.tensor.alias.matrix.primitive.*;
import com.pb.sawdust.tensor.alias.vector.primitive.*;
import com.pb.sawdust.tensor.factory.TensorFactory;
import com.pb.sawdust.util.JavaType;
import com.pb.sawdust.util.test.TestBase;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.pb.sawdust.util.Range.range;

/**
 * The {@code MatrixVectorMultiplicationTest} ...
 *
 * @author crf <br/>
 *         Started Dec 17, 2010 11:31:57 PM
 */
public abstract class MatrixVectorMultiplicationTest extends TestBase {

    public static enum MultiplyType {
        MATRIX_VECTOR,
        VECTOR_MATRIX
    }
    public static final String MULTIPLY_TYPE_KEY = "MultiplyType";

    protected MatrixVectorMultiplication mm;
    protected TensorFactory factory;
    protected TensorTestUtil ttUtil;
    protected MultiplyType type;

    abstract protected MatrixVectorMultiplication getMatrixVectorMultiplication(TensorFactory factory);

    protected TensorFactory getFactory() {
        return ArrayTensor.getFactory();
    }

    protected Collection<Class<? extends TestBase>> getAdditionalTestClasses() {
        List<Map<String,Object>> contexts = new LinkedList<Map<String,Object>>();
        for (MultiplyType t : MultiplyType.values())
            contexts.add(buildContext(MULTIPLY_TYPE_KEY,t));
        addClassRunContext(this.getClass(),contexts);
        return super.getAdditionalTestClasses();
    }

    @Before
    public void beforeTest() {
        factory = getFactory();
        mm = getMatrixVectorMultiplication(factory);
        ttUtil  = new TensorTestUtil(factory,random);
        ttUtil.setPrintFailedMatrices(true);
        try {
            type = (MultiplyType) getTestData(MULTIPLY_TYPE_KEY);
        } catch (NullPointerException e) {
            type = MultiplyType.MATRIX_VECTOR;
        }
    }

    private int[][] getDims(boolean transpose) {
        int maxSize = 5; //if too big, then overflows sometimes happen - not deterministic
        int match = random.nextInt(1,maxSize);      
        int[] d1 = new int[] {match}; 
        int d2s = random.nextInt(1,maxSize);
        int[] d2 = new int[] {transpose ? d2s : match,transpose ? match : d2s};
        switch (type) {
            case VECTOR_MATRIX : d2 = new int[] {transpose ? d2s : match,transpose ? match : d2s}; break; 
            case MATRIX_VECTOR : d2 = new int[] {transpose ? match : d2s,transpose ? d2s : match}; break;
        }
        return new int[][] {d1,d2};
    }

    protected void testByteByte(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);
        if (misaligned) 
            dims[0][0]++; //increase vector
        ByteVector v = (ByteVector) ttUtil.getRandomTensor(JavaType.BYTE,dims[0]);
        ByteMatrix m = (ByteMatrix) ttUtil.getRandomTensor(JavaType.BYTE,dims[1]);  
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        ByteVector check = factory.byteVector(output[0]);
        for (int i : range(output[0])) {
            byte result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }

    protected void testByteShort(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);     
        if (misaligned) 
            dims[0][0]++;
        ByteVector v = (ByteVector) ttUtil.getRandomTensor(JavaType.BYTE,dims[0]);
        ShortMatrix m = (ShortMatrix) ttUtil.getRandomTensor(JavaType.SHORT,dims[1]);  
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        ShortVector check = factory.shortVector(output[0]);
        for (int i : range(output[0])) {
            short result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }

    protected void testShortByte(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);    
        if (misaligned) 
            dims[0][0]++;
        ShortVector v = (ShortVector) ttUtil.getRandomTensor(JavaType.SHORT,dims[0]);
        ByteMatrix m = (ByteMatrix) ttUtil.getRandomTensor(JavaType.BYTE,dims[1]); 
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        ShortVector check = factory.shortVector(output[0]);
        for (int i : range(output[0])) {
            short result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }   

    protected void testByteInt(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);  
        if (misaligned) 
            dims[0][0]++;
        ByteVector v = (ByteVector) ttUtil.getRandomTensor(JavaType.BYTE,dims[0]);
        IntMatrix m = (IntMatrix) ttUtil.getRandomTensor(JavaType.INT,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        IntVector check = factory.intVector(output[0]);
        for (int i : range(output[0])) {
            int result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }

    protected void testIntByte(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);
        if (misaligned) 
            dims[0][0]++;
        IntVector v = (IntVector) ttUtil.getRandomTensor(JavaType.INT,dims[0]);
        ByteMatrix m = (ByteMatrix) ttUtil.getRandomTensor(JavaType.BYTE,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        IntVector check = factory.intVector(output[0]);
        for (int i : range(output[0])) {
            int result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    } 

    protected void testByteLong(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr); 
        if (misaligned) 
            dims[0][0]++;
        ByteVector v = (ByteVector) ttUtil.getRandomTensor(JavaType.BYTE,dims[0]);
        LongMatrix m = (LongMatrix) ttUtil.getRandomTensor(JavaType.LONG,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        LongVector check = factory.longVector(output[0]);
        for (int i : range(output[0])) {
            long result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }

    protected void testLongByte(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr); 
        if (misaligned) 
            dims[0][0]++;
        LongVector v = (LongVector) ttUtil.getRandomTensor(JavaType.LONG,dims[0]);
        ByteMatrix m = (ByteMatrix) ttUtil.getRandomTensor(JavaType.BYTE,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        LongVector check = factory.longVector(output[0]);
        for (int i : range(output[0])) {
            long result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }      

    protected void testByteFloat(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);  
        if (misaligned) 
            dims[0][0]++;
        ByteVector v = (ByteVector) ttUtil.getRandomTensor(JavaType.BYTE,dims[0]);
        FloatMatrix m = (FloatMatrix) ttUtil.getRandomTensor(JavaType.FLOAT,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        FloatVector check = factory.floatVector(output[0]);
        for (int i : range(output[0])) {
            float result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }

    protected void testFloatByte(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);   
        if (misaligned) 
            dims[0][0]++;
        FloatVector v = (FloatVector) ttUtil.getRandomTensor(JavaType.FLOAT,dims[0]);
        ByteMatrix m = (ByteMatrix) ttUtil.getRandomTensor(JavaType.BYTE,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        FloatVector check = factory.floatVector(output[0]);
        for (int i : range(output[0])) {
            float result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    } 

    protected void testByteDouble(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);    
        if (misaligned) 
            dims[0][0]++;
        ByteVector v = (ByteVector) ttUtil.getRandomTensor(JavaType.BYTE,dims[0]);
        DoubleMatrix m = (DoubleMatrix) ttUtil.getRandomTensor(JavaType.DOUBLE,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        DoubleVector check = factory.doubleVector(output[0]);
        for (int i : range(output[0])) {
            double result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }

    protected void testDoubleByte(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);    
        if (misaligned) 
            dims[0][0]++;
        DoubleVector v = (DoubleVector) ttUtil.getRandomTensor(JavaType.DOUBLE,dims[0]);
        ByteMatrix m = (ByteMatrix) ttUtil.getRandomTensor(JavaType.BYTE,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        DoubleVector check = factory.doubleVector(output[0]);
        for (int i : range(output[0])) {
            double result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }
    
    protected void testShortShort(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);
        if (misaligned) 
            dims[0][0]++;
        ShortVector v = (ShortVector) ttUtil.getRandomTensor(JavaType.SHORT,dims[0]);
        ShortMatrix m = (ShortMatrix) ttUtil.getRandomTensor(JavaType.SHORT,dims[1]); 
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        ShortVector check = factory.shortVector(output[0]);
        for (int i : range(output[0])) {
            short result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    } 

    protected void testShortInt(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);  
        if (misaligned) 
            dims[0][0]++;
        ShortVector v = (ShortVector) ttUtil.getRandomTensor(JavaType.SHORT,dims[0]);
        IntMatrix m = (IntMatrix) ttUtil.getRandomTensor(JavaType.INT,dims[1]); 
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        IntVector check = factory.intVector(output[0]);
        for (int i : range(output[0])) {
            int result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }

    protected void testIntShort(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);
        if (misaligned) 
            dims[0][0]++;
        IntVector v = (IntVector) ttUtil.getRandomTensor(JavaType.INT,dims[0]);
        ShortMatrix m = (ShortMatrix) ttUtil.getRandomTensor(JavaType.SHORT,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        IntVector check = factory.intVector(output[0]);
        for (int i : range(output[0])) {
            int result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    } 

    protected void testShortLong(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr); 
        if (misaligned) 
            dims[0][0]++;
        ShortVector v = (ShortVector) ttUtil.getRandomTensor(JavaType.SHORT,dims[0]);
        LongMatrix m = (LongMatrix) ttUtil.getRandomTensor(JavaType.LONG,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        LongVector check = factory.longVector(output[0]);
        for (int i : range(output[0])) {
            long result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }

    protected void testLongShort(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr); 
        if (misaligned) 
            dims[0][0]++;
        LongVector v = (LongVector) ttUtil.getRandomTensor(JavaType.LONG,dims[0]);
        ShortMatrix m = (ShortMatrix) ttUtil.getRandomTensor(JavaType.SHORT,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        LongVector check = factory.longVector(output[0]);
        for (int i : range(output[0])) {
            long result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }      

    protected void testShortFloat(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);  
        if (misaligned) 
            dims[0][0]++;
        ShortVector v = (ShortVector) ttUtil.getRandomTensor(JavaType.SHORT,dims[0]);
        FloatMatrix m = (FloatMatrix) ttUtil.getRandomTensor(JavaType.FLOAT,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        FloatVector check = factory.floatVector(output[0]);
        for (int i : range(output[0])) {
            float result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }

    protected void testFloatShort(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);   
        if (misaligned) 
            dims[0][0]++;
        FloatVector v = (FloatVector) ttUtil.getRandomTensor(JavaType.FLOAT,dims[0]);
        ShortMatrix m = (ShortMatrix) ttUtil.getRandomTensor(JavaType.SHORT,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        FloatVector check = factory.floatVector(output[0]);
        for (int i : range(output[0])) {
            float result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    } 

    protected void testShortDouble(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);    
        if (misaligned) 
            dims[0][0]++;
        ShortVector v = (ShortVector) ttUtil.getRandomTensor(JavaType.SHORT,dims[0]);
        DoubleMatrix m = (DoubleMatrix) ttUtil.getRandomTensor(JavaType.DOUBLE,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        DoubleVector check = factory.doubleVector(output[0]);
        for (int i : range(output[0])) {
            double result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }

    protected void testDoubleShort(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);    
        if (misaligned) 
            dims[0][0]++;
        DoubleVector v = (DoubleVector) ttUtil.getRandomTensor(JavaType.DOUBLE,dims[0]);
        ShortMatrix m = (ShortMatrix) ttUtil.getRandomTensor(JavaType.SHORT,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        DoubleVector check = factory.doubleVector(output[0]);
        for (int i : range(output[0])) {
            double result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    } 
    
    protected void testIntInt(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);
        if (misaligned) 
            dims[0][0]++;
        IntVector v = (IntVector) ttUtil.getRandomTensor(JavaType.INT,dims[0]);
        IntMatrix m = (IntMatrix) ttUtil.getRandomTensor(JavaType.INT,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        IntVector check = factory.intVector(output[0]);
        for (int i : range(output[0])) {
            int result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    } 

    protected void testIntLong(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr); 
        if (misaligned) 
            dims[0][0]++;
        IntVector v = (IntVector) ttUtil.getRandomTensor(JavaType.INT,dims[0]);
        LongMatrix m = (LongMatrix) ttUtil.getRandomTensor(JavaType.LONG,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        LongVector check = factory.longVector(output[0]);
        for (int i : range(output[0])) {
            long result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }

    protected void testLongInt(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr); 
        if (misaligned) 
            dims[0][0]++;
        LongVector v = (LongVector) ttUtil.getRandomTensor(JavaType.LONG,dims[0]);
        IntMatrix m = (IntMatrix) ttUtil.getRandomTensor(JavaType.INT,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        LongVector check = factory.longVector(output[0]);
        for (int i : range(output[0])) {
            long result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }      

    protected void testIntFloat(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);  
        if (misaligned) 
            dims[0][0]++;
        IntVector v = (IntVector) ttUtil.getRandomTensor(JavaType.INT,dims[0]);
        FloatMatrix m = (FloatMatrix) ttUtil.getRandomTensor(JavaType.FLOAT,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        FloatVector check = factory.floatVector(output[0]);
        for (int i : range(output[0])) {
            float result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }

    protected void testFloatInt(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);   
        if (misaligned) 
            dims[0][0]++;
        FloatVector v = (FloatVector) ttUtil.getRandomTensor(JavaType.FLOAT,dims[0]);
        IntMatrix m = (IntMatrix) ttUtil.getRandomTensor(JavaType.INT,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        FloatVector check = factory.floatVector(output[0]);
        for (int i : range(output[0])) {
            float result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    } 

    protected void testIntDouble(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);    
        if (misaligned) 
            dims[0][0]++;
        IntVector v = (IntVector) ttUtil.getRandomTensor(JavaType.INT,dims[0]);
        DoubleMatrix m = (DoubleMatrix) ttUtil.getRandomTensor(JavaType.DOUBLE,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        DoubleVector check = factory.doubleVector(output[0]);
        for (int i : range(output[0])) {
            double result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }

    protected void testDoubleInt(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);    
        if (misaligned) 
            dims[0][0]++;
        DoubleVector v = (DoubleVector) ttUtil.getRandomTensor(JavaType.DOUBLE,dims[0]);
        IntMatrix m = (IntMatrix) ttUtil.getRandomTensor(JavaType.INT,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        DoubleVector check = factory.doubleVector(output[0]);
        for (int i : range(output[0])) {
            double result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }     
    
    protected void testLongLong(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);
        if (misaligned) 
            dims[0][0]++;
        LongVector v = (LongVector) ttUtil.getRandomTensor(JavaType.LONG,dims[0]);
        LongMatrix m = (LongMatrix) ttUtil.getRandomTensor(JavaType.LONG,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        LongVector check = factory.longVector(output[0]);
        for (int i : range(output[0])) {
            long result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }   

    protected void testLongFloat(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);  
        if (misaligned) 
            dims[0][0]++;
        LongVector v = (LongVector) ttUtil.getRandomTensor(JavaType.LONG,dims[0]);
        FloatMatrix m = (FloatMatrix) ttUtil.getRandomTensor(JavaType.FLOAT,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        FloatVector check = factory.floatVector(output[0]);
        for (int i : range(output[0])) {
            float result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }

    protected void testFloatLong(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);   
        if (misaligned) 
            dims[0][0]++;
        FloatVector v = (FloatVector) ttUtil.getRandomTensor(JavaType.FLOAT,dims[0]);
        LongMatrix m = (LongMatrix) ttUtil.getRandomTensor(JavaType.LONG,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        FloatVector check = factory.floatVector(output[0]);
        for (int i : range(output[0])) {
            float result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    } 

    protected void testLongDouble(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);    
        if (misaligned) 
            dims[0][0]++;
        LongVector v = (LongVector) ttUtil.getRandomTensor(JavaType.LONG,dims[0]);
        DoubleMatrix m = (DoubleMatrix) ttUtil.getRandomTensor(JavaType.DOUBLE,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        DoubleVector check = factory.doubleVector(output[0]);
        for (int i : range(output[0])) {
            double result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }

    protected void testDoubleLong(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);    
        if (misaligned) 
            dims[0][0]++;
        DoubleVector v = (DoubleVector) ttUtil.getRandomTensor(JavaType.DOUBLE,dims[0]);
        LongMatrix m = (LongMatrix) ttUtil.getRandomTensor(JavaType.LONG,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        DoubleVector check = factory.doubleVector(output[0]);
        for (int i : range(output[0])) {
            double result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }      
    
    protected void testFloatFloat(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);
        if (misaligned) 
            dims[0][0]++;
        FloatVector v = (FloatVector) ttUtil.getRandomTensor(JavaType.FLOAT,dims[0]);
        FloatMatrix m = (FloatMatrix) ttUtil.getRandomTensor(JavaType.FLOAT,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        FloatVector check = factory.floatVector(output[0]);
        for (int i : range(output[0])) {
            float result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }  

    protected void testFloatDouble(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);    
        if (misaligned) 
            dims[0][0]++;
        FloatVector v = (FloatVector) ttUtil.getRandomTensor(JavaType.FLOAT,dims[0]);
        DoubleMatrix m = (DoubleMatrix) ttUtil.getRandomTensor(JavaType.DOUBLE,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        DoubleVector check = factory.doubleVector(output[0]);
        for (int i : range(output[0])) {
            double result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }

    protected void testDoubleFloat(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);    
        if (misaligned) 
            dims[0][0]++;
        DoubleVector v = (DoubleVector) ttUtil.getRandomTensor(JavaType.DOUBLE,dims[0]);
        FloatMatrix m = (FloatMatrix) ttUtil.getRandomTensor(JavaType.FLOAT,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        DoubleVector check = factory.doubleVector(output[0]);
        for (int i : range(output[0])) {
            double result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }        
    
    protected void testDoubleDouble(boolean tr, boolean misaligned, boolean defaultArgs) {
        int[][] dims = getDims(tr);
        if (misaligned) 
            dims[0][0]++;
        DoubleVector v = (DoubleVector) ttUtil.getRandomTensor(JavaType.DOUBLE,dims[0]);
        DoubleMatrix m = (DoubleMatrix) ttUtil.getRandomTensor(JavaType.DOUBLE,dims[1]);
        int[] output = null;
        switch (type) {
            case VECTOR_MATRIX : output = new int[] {tr ? dims[1][0] : dims[1][1]}; break;
            case MATRIX_VECTOR : output = new int[] {tr ? dims[1][1] : dims[1][0]}; break;
        }
        DoubleVector check = factory.doubleVector(output[0]);
        for (int i : range(output[0])) {
            double result = 0;
            for (int j : range(dims[0][0] - (misaligned ? 1 : 0))) {
                switch (type) {
                    case VECTOR_MATRIX : result += v.getCell(j)*m.getCell(tr ? i : j,tr ? j : i); break;
                    case MATRIX_VECTOR : result += v.getCell(j)*m.getCell(tr ? j : i,tr ? i : j); break;
                }
            }
            check.setCell(result,i);
        }
        switch (type) {
            case VECTOR_MATRIX : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(v,m) : mm.multiply(v,m,tr)); break;
            case MATRIX_VECTOR : ttUtil.assertTensorEquals(check,defaultArgs ? mm.multiply(m,v) : mm.multiply(m,v,tr)); break;
        }
    }

    @Test
    public void testByteByte() {
        testByteByte(false,false,false);
    }

    @Test
    public void testByteByteT() {
        testByteByte(true,false,false);
    }

    @Test
    public void testByteShort() {
        testByteShort(false,false,false);
    }

    @Test
    public void testByteShortT() {
        testByteShort(true,false,false);
    }

    @Test
    public void testShortByte() {
        testShortByte(false,false,false);
    }

    @Test
    public void testShortByteT() {
        testShortByte(true,false,false);
    }

    @Test
    public void testByteInt() {
        testByteInt(false,false,false);
    }

    @Test
    public void testByteIntT() {
        testByteInt(true,false,false);
    }

    @Test
    public void testIntByte() {
        testIntByte(false,false,false);
    }

    @Test
    public void testIntByteT() {
        testIntByte(true,false,false);
    }

    @Test
    public void testByteLong() {
        testByteLong(false,false,false);
    }

    @Test
    public void testByteLongT() {
        testByteLong(true,false,false);
    }

    @Test
    public void testLongByte() {
        testLongByte(false,false,false);
    }

    @Test
    public void testLongByteT() {
        testLongByte(true,false,false);
    }

    @Test
    public void testByteFloat() {
        testByteFloat(false,false,false);
    }

    @Test
    public void testByteFloatT() {
        testByteFloat(true,false,false);
    }

    @Test
    public void testFloatByte() {
        testFloatByte(false,false,false);
    }

    @Test
    public void testFloatByteT() {
        testFloatByte(true,false,false);
    }

    @Test
    public void testByteDouble() {
        testByteDouble(false,false,false);
    }

    @Test
    public void testByteDoubleT() {
        testByteDouble(true,false,false);
    }

    @Test
    public void testDoubleByte() {
        testDoubleByte(false,false,false);
    }

    @Test
    public void testDoubleByteT() {
        testDoubleByte(true,false,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByteByteMisaligned() {
        testByteByte(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByteByteTMisaligned() {
        testByteByte(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByteShortMisaligned() {
        testByteShort(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByteShortTMisaligned() {
        testByteShort(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testShortByteMisaligned() {
        testShortByte(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testShortByteTMisaligned() {
        testShortByte(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByteIntMisaligned() {
        testByteInt(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByteIntTMisaligned() {
        testByteInt(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIntByteMisaligned() {
        testIntByte(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIntByteTMisaligned() {
        testIntByte(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByteLongMisaligned() {
        testByteLong(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByteLongTMisaligned() {
        testByteLong(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLongByteMisaligned() {
        testLongByte(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLongByteTMisaligned() {
        testLongByte(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByteFloatMisaligned() {
        testByteFloat(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByteFloatTMisaligned() {
        testByteFloat(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFloatByteMisaligned() {
        testFloatByte(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFloatByteTMisaligned() {
        testFloatByte(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByteDoubleMisaligned() {
        testByteDouble(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByteDoubleTMisaligned() {
        testByteDouble(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDoubleByteMisaligned() {
        testDoubleByte(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDoubleByteTMisaligned() {
        testDoubleByte(true,true,false);
    }

    @Test
    public void testShortShort() {
        testShortShort(false,false,false);
    }

    @Test
    public void testShortShortT() {
        testShortShort(true,false,false);
    }

    @Test
    public void testShortInt() {
        testShortInt(false,false,false);
    }

    @Test
    public void testShortIntT() {
        testShortInt(true,false,false);
    }

    @Test
    public void testIntShort() {
        testIntShort(false,false,false);
    }

    @Test
    public void testIntShortT() {
        testIntShort(true,false,false);
    }

    @Test
    public void testShortLong() {
        testShortLong(false,false,false);
    }

    @Test
    public void testShortLongT() {
        testShortLong(true,false,false);
    }

    @Test
    public void testLongShort() {
        testLongShort(false,false,false);
    }

    @Test
    public void testLongShortT() {
        testLongShort(true,false,false);
    }

    @Test
    public void testShortFloat() {
        testShortFloat(false,false,false);
    }

    @Test
    public void testShortFloatT() {
        testShortFloat(true,false,false);
    }

    @Test
    public void testFloatShort() {
        testFloatShort(false,false,false);
    }

    @Test
    public void testFloatShortT() {
        testFloatShort(true,false,false);
    }

    @Test
    public void testShortDouble() {
        testShortDouble(false,false,false);
    }

    @Test
    public void testShortDoubleT() {
        testShortDouble(true,false,false);
    }

    @Test
    public void testDoubleShort() {
        testDoubleShort(false,false,false);
    }

    @Test
    public void testDoubleShortT() {
        testDoubleShort(true,false,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testShortShortMisaligned() {
        testShortShort(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testShortShortTMisaligned() {
        testShortShort(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testShortIntMisaligned() {
        testShortInt(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testShortIntTMisaligned() {
        testShortInt(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIntShortMisaligned() {
        testIntShort(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIntShortTMisaligned() {
        testIntShort(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testShortLongMisaligned() {
        testShortLong(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testShortLongTMisaligned() {
        testShortLong(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLongShortMisaligned() {
        testLongShort(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLongShortTMisaligned() {
        testLongShort(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testShortFloatMisaligned() {
        testShortFloat(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testShortFloatTMisaligned() {
        testShortFloat(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFloatShortMisaligned() {
        testFloatShort(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFloatShortTMisaligned() {
        testFloatShort(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testShortDoubleMisaligned() {
        testShortDouble(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testShortDoubleTMisaligned() {
        testShortDouble(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDoubleShortMisaligned() {
        testDoubleShort(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDoubleShortTMisaligned() {
        testDoubleShort(true,true,false);
    }

    @Test
    public void testIntInt() {
        testIntInt(false,false,false);
    }

    @Test
    public void testIntIntT() {
        testIntInt(true,false,false);
    }

    @Test
    public void testIntLong() {
        testIntLong(false,false,false);
    }

    @Test
    public void testIntLongT() {
        testIntLong(true,false,false);
    }

    @Test
    public void testLongInt() {
        testLongInt(false,false,false);
    }

    @Test
    public void testLongIntT() {
        testLongInt(true,false,false);
    }

    @Test
    public void testIntFloat() {
        testIntFloat(false,false,false);
    }

    @Test
    public void testIntFloatT() {
        testIntFloat(true,false,false);
    }

    @Test
    public void testFloatInt() {
        testFloatInt(false,false,false);
    }

    @Test
    public void testFloatIntT() {
        testFloatInt(true,false,false);
    }

    @Test
    public void testIntDouble() {
        testIntDouble(false,false,false);
    }

    @Test
    public void testIntDoubleT() {
        testIntDouble(true,false,false);
    }

    @Test
    public void testDoubleInt() {
        testDoubleInt(false,false,false);
    }

    @Test
    public void testDoubleIntT() {
        testDoubleInt(true,false,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIntIntMisaligned() {
        testIntInt(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIntIntTMisaligned() {
        testIntInt(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIntLongMisaligned() {
        testIntLong(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIntLongTMisaligned() {
        testIntLong(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLongIntMisaligned() {
        testLongInt(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLongIntTMisaligned() {
        testLongInt(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIntFloatMisaligned() {
        testIntFloat(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIntFloatTMisaligned() {
        testIntFloat(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFloatIntMisaligned() {
        testFloatInt(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFloatIntTMisaligned() {
        testFloatInt(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIntDoubleMisaligned() {
        testIntDouble(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIntDoubleTMisaligned() {
        testIntDouble(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDoubleIntMisaligned() {
        testDoubleInt(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDoubleIntTMisaligned() {
        testDoubleInt(true,true,false);
    }

    @Test
    public void testLongLong() {
        testLongLong(false,false,false);
    }

    @Test
    public void testLongLongT() {
        testLongLong(true,false,false);
    }

    @Test
    public void testLongFloat() {
        testLongFloat(false,false,false);
    }

    @Test
    public void testLongFloatT() {
        testLongFloat(true,false,false);
    }

    @Test
    public void testFloatLong() {
        testFloatLong(false,false,false);
    }

    @Test
    public void testFloatLongT() {
        testFloatLong(true,false,false);
    }

    @Test
    public void testLongDouble() {
        testLongDouble(false,false,false);
    }

    @Test
    public void testLongDoubleT() {
        testLongDouble(true,false,false);
    }

    @Test
    public void testDoubleLong() {
        testDoubleLong(false,false,false);
    }

    @Test
    public void testDoubleLongT() {
        testDoubleLong(true,false,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLongLongMisaligned() {
        testLongLong(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLongLongTMisaligned() {
        testLongLong(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLongFloatMisaligned() {
        testLongFloat(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLongFloatTMisaligned() {
        testLongFloat(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFloatLongMisaligned() {
        testFloatLong(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFloatLongTMisaligned() {
        testFloatLong(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLongDoubleMisaligned() {
        testLongDouble(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLongDoubleTMisaligned() {
        testLongDouble(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDoubleLongMisaligned() {
        testDoubleLong(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDoubleLongTMisaligned() {
        testDoubleLong(true,true,false);
    }

    @Test
    public void testFloatFloat() {
        testFloatFloat(false,false,false);
    }

    @Test
    public void testFloatFloatT() {
        testFloatFloat(true,false,false);
    }

    @Test
    public void testFloatDouble() {
        testFloatDouble(false,false,false);
    }

    @Test
    public void testFloatDoubleT() {
        testFloatDouble(true,false,false);
    }

    @Test
    public void testDoubleFloat() {
        testDoubleFloat(false,false,false);
    }

    @Test
    public void testDoubleFloatT() {
        testDoubleFloat(true,false,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFloatFloatMisaligned() {
        testFloatFloat(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFloatFloatTMisaligned() {
        testFloatFloat(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFloatDoubleMisaligned() {
        testFloatDouble(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFloatDoubleTMisaligned() {
        testFloatDouble(true,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDoubleFloatMisaligned() {
        testDoubleFloat(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDoubleFloatTMisaligned() {
        testDoubleFloat(true,true,false);
    }

    @Test
    public void testDoubleDouble() {
        testDoubleDouble(false,false,false);
    }

    @Test
    public void testDoubleDoubleT() {
        testDoubleDouble(true,false,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDoubleDoubleMisaligned() {
        testDoubleDouble(false,true,false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDoubleDoubleTMisaligned() {
        testDoubleDouble(true,true,false);
    }

    @Test
    public void testByteByteDefault() {
        testByteByte(false,false,true);
    }

    @Test
    public void testByteShortDefault() {
        testByteShort(false,false,true);
    }

    @Test
    public void testShortByteDefault() {
        testShortByte(false,false,true);
    }

    @Test
    public void testByteIntDefault() {
        testByteInt(false,false,true);
    }

    @Test
    public void testIntByteDefault() {
        testIntByte(false,false,true);
    }

    @Test
    public void testByteLongDefault() {
        testByteLong(false,false,true);
    }

    @Test
    public void testLongByteDefault() {
        testLongByte(false,false,true);
    }

    @Test
    public void testByteFloatDefault() {
        testByteFloat(false,false,true);
    }

    @Test
    public void testFloatByteDefault() {
        testFloatByte(false,false,true);
    }

    @Test
    public void testByteDoubleDefault() {
        testByteDouble(false,false,true);
    }

    @Test
    public void testDoubleByteDefault() {
        testDoubleByte(false,false,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByteByteMisalignedDefault() {
        testByteByte(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByteShortMisalignedDefault() {
        testByteShort(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testShortByteMisalignedDefault() {
        testShortByte(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByteIntMisalignedDefault() {
        testByteInt(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIntByteMisalignedDefault() {
        testIntByte(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByteLongMisalignedDefault() {
        testByteLong(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLongByteMisalignedDefault() {
        testLongByte(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByteFloatMisalignedDefault() {
        testByteFloat(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFloatByteMisalignedDefault() {
        testFloatByte(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByteDoubleMisalignedDefault() {
        testByteDouble(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDoubleByteMisalignedDefault() {
        testDoubleByte(false,true,true);
    }

    @Test
    public void testShortShortDefault() {
        testShortShort(false,false,true);
    }

    @Test
    public void testShortIntDefault() {
        testShortInt(false,false,true);
    }

    @Test
    public void testIntShortDefault() {
        testIntShort(false,false,true);
    }

    @Test
    public void testShortLongDefault() {
        testShortLong(false,false,true);
    }

    @Test
    public void testLongShortDefault() {
        testLongShort(false,false,true);
    }

    @Test
    public void testShortFloatDefault() {
        testShortFloat(false,false,true);
    }

    @Test
    public void testFloatShortDefault() {
        testFloatShort(false,false,true);
    }

    @Test
    public void testShortDoubleDefault() {
        testShortDouble(false,false,true);
    }

    @Test
    public void testDoubleShortDefault() {
        testDoubleShort(false,false,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testShortShortMisalignedDefault() {
        testShortShort(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testShortIntMisalignedDefault() {
        testShortInt(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIntShortMisalignedDefault() {
        testIntShort(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testShortLongMisalignedDefault() {
        testShortLong(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLongShortMisalignedDefault() {
        testLongShort(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testShortFloatMisalignedDefault() {
        testShortFloat(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFloatShortMisalignedDefault() {
        testFloatShort(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testShortDoubleMisalignedDefault() {
        testShortDouble(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDoubleShortMisalignedDefault() {
        testDoubleShort(false,true,true);
    }

    @Test
    public void testIntIntDefault() {
        testIntInt(false,false,true);
    }

    @Test
    public void testIntLongDefault() {
        testIntLong(false,false,true);
    }

    @Test
    public void testLongIntDefault() {
        testLongInt(false,false,true);
    }

    @Test
    public void testIntFloatDefault() {
        testIntFloat(false,false,true);
    }

    @Test
    public void testFloatIntDefault() {
        testFloatInt(false,false,true);
    }

    @Test
    public void testIntDoubleDefault() {
        testIntDouble(false,false,true);
    }

    @Test
    public void testDoubleIntDefault() {
        testDoubleInt(false,false,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIntIntMisalignedDefault() {
        testIntInt(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIntLongMisalignedDefault() {
        testIntLong(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLongIntMisalignedDefault() {
        testLongInt(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIntFloatMisalignedDefault() {
        testIntFloat(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFloatIntMisalignedDefault() {
        testFloatInt(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIntDoubleMisalignedDefault() {
        testIntDouble(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDoubleIntMisalignedDefault() {
        testDoubleInt(false,true,true);
    }

    @Test
    public void testLongLongDefault() {
        testLongLong(false,false,true);
    }

    @Test
    public void testLongFloatDefault() {
        testLongFloat(false,false,true);
    }

    @Test
    public void testFloatLongDefault() {
        testFloatLong(false,false,true);
    }

    @Test
    public void testLongDoubleDefault() {
        testLongDouble(false,false,true);
    }

    @Test
    public void testDoubleLongDefault() {
        testDoubleLong(false,false,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLongLongMisalignedDefault() {
        testLongLong(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLongFloatMisalignedDefault() {
        testLongFloat(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFloatLongMisalignedDefault() {
        testFloatLong(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLongDoubleMisalignedDefault() {
        testLongDouble(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDoubleLongMisalignedDefault() {
        testDoubleLong(false,true,true);
    }

    @Test
    public void testFloatFloatDefault() {
        testFloatFloat(false,false,true);
    }

    @Test
    public void testFloatDoubleDefault() {
        testFloatDouble(false,false,true);
    }

    @Test
    public void testDoubleFloatDefault() {
        testDoubleFloat(false,false,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFloatFloatMisalignedDefault() {
        testFloatFloat(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFloatDoubleMisalignedDefault() {
        testFloatDouble(false,true,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDoubleFloatMisalignedDefault() {
        testDoubleFloat(false,true,true);
    }

    @Test
    public void testDoubleDoubleDefault() {
        testDoubleDouble(false,false,true);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDoubleDoubleMisalignedDefault() {
        testDoubleDouble(false,true,true);
    }
}