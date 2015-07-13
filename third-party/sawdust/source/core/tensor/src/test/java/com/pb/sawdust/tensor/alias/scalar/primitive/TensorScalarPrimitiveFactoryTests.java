package com.pb.sawdust.tensor.alias.scalar.primitive;

import com.pb.sawdust.tensor.TensorFactoryTests;
import com.pb.sawdust.tensor.factory.TensorFactoryTest;
import com.pb.sawdust.tensor.factory.TensorFactory;
import com.pb.sawdust.util.array.*;
import com.pb.sawdust.util.test.TestBase;
import org.junit.Before;

import java.util.LinkedList;
import java.util.List;

/**
 * The {@code ScalarPrimitivePackageFactoryTests} ...
 *
 * @author crf <br/>
 *         Started Dec 24, 2010 11:02:45 AM
 */
public class TensorScalarPrimitiveFactoryTests {
    public static List<Class<? extends TestBase>> TEST_CLASSES = new LinkedList<Class<? extends TestBase>>();
    static {
        TEST_CLASSES.add(TensorFactoryBooleanScalarTest.class);
        TEST_CLASSES.add(TensorFactoryCharScalarTest.class);
        TEST_CLASSES.add(TensorFactoryByteScalarTest.class);
        TEST_CLASSES.add(TensorFactoryShortScalarTest.class);
        TEST_CLASSES.add(TensorFactoryIntScalarTest.class);
        TEST_CLASSES.add(TensorFactoryLongScalarTest.class);
        TEST_CLASSES.add(TensorFactoryFloatScalarTest.class);
        TEST_CLASSES.add(TensorFactoryDoubleScalarTest.class);
    }

    public static class TensorFactoryBooleanScalarTest extends BooleanScalarTest {

        protected TensorFactory factory;

        public static void main(String ... args) {
            TestBase.main();
        }

        @Before
        public void beforeTest() {
            factory = (TensorFactory) getTestData(getCallingContextInstance().getClass(),TensorFactoryTest.TENSOR_FACTORY_KEY);
            super.beforeTest();
        }

        protected BooleanTypeSafeArray getData() {
            return TensorFactoryTests.booleanData(new int[] {1},random);
        }

        protected Boolean getRandomElement() {
            return TensorFactoryTests.randomBoolean(random);
        }

        protected BooleanScalar getTensor(TypeSafeArray<Boolean> data) {
            BooleanScalar tensor = (BooleanScalar) factory.booleanTensor();
            tensor.setTensorValues(data);
            return tensor;
        }
    }

    public static class TensorFactoryCharScalarTest extends CharScalarTest {

        protected TensorFactory factory;

        public static void main(String ... args) {
            TestBase.main();
        }

        @Before
        public void beforeTest() {
            factory = (TensorFactory) getTestData(getCallingContextInstance().getClass(),TensorFactoryTest.TENSOR_FACTORY_KEY);
            super.beforeTest();
        }

        protected CharTypeSafeArray getData() {
            return TensorFactoryTests.charData(new int[] {1},random);
        }

        protected Character getRandomElement() {
            return TensorFactoryTests.randomChar(random);
        }

        protected CharScalar getTensor(TypeSafeArray<Character> data) {
            CharScalar tensor = (CharScalar) factory.charTensor();
            tensor.setTensorValues(data);
            return tensor;
        }
    }

    public static class TensorFactoryByteScalarTest extends ByteScalarTest {

        protected TensorFactory factory;

        public static void main(String ... args) {
            TestBase.main();
        }

        @Before
        public void beforeTest() {
            factory = (TensorFactory) getTestData(getCallingContextInstance().getClass(),TensorFactoryTest.TENSOR_FACTORY_KEY);
            super.beforeTest();
        }

        protected ByteTypeSafeArray getData() {
            return TensorFactoryTests.byteData(new int[] {1},random);
        }

        protected Byte getRandomElement() {
            return TensorFactoryTests.randomByte(random);
        }

        protected ByteScalar getTensor(TypeSafeArray<Byte> data) {
            ByteScalar tensor = (ByteScalar) factory.byteTensor();
            tensor.setTensorValues(data);
            return tensor;
        }
    }

    public static class TensorFactoryShortScalarTest extends ShortScalarTest {

        protected TensorFactory factory;

        public static void main(String ... args) {
            TestBase.main();
        }

        @Before
        public void beforeTest() {
            factory = (TensorFactory) getTestData(getCallingContextInstance().getClass(),TensorFactoryTest.TENSOR_FACTORY_KEY);
            super.beforeTest();
        }

        protected ShortTypeSafeArray getData() {
            return TensorFactoryTests.shortData(new int[] {1},random);
        }

        protected Short getRandomElement() {
            return TensorFactoryTests.randomShort(random);
        }

        protected ShortScalar getTensor(TypeSafeArray<Short> data) {
            ShortScalar tensor = (ShortScalar) factory.shortTensor();
            tensor.setTensorValues(data);
            return tensor;
        }
    }

    public static class TensorFactoryIntScalarTest extends IntScalarTest {

        protected TensorFactory factory;

        public static void main(String ... args) {
            TestBase.main();
        }

        @Before
        public void beforeTest() {
            factory = (TensorFactory) getTestData(getCallingContextInstance().getClass(),TensorFactoryTest.TENSOR_FACTORY_KEY);
            super.beforeTest();
        }

        protected IntTypeSafeArray getData() {
            return TensorFactoryTests.intData(new int[] {1},random);
        }

        protected Integer getRandomElement() {
            return TensorFactoryTests.randomInt(random);
        }

        protected IntScalar getTensor(TypeSafeArray<Integer> data) {
            IntScalar tensor = (IntScalar) factory.intTensor();
            tensor.setTensorValues(data);
            return tensor;
        }
    }

    public static class TensorFactoryLongScalarTest extends LongScalarTest {

        protected TensorFactory factory;

        public static void main(String ... args) {
            TestBase.main();
        }

        @Before
        public void beforeTest() {
            factory = (TensorFactory) getTestData(getCallingContextInstance().getClass(),TensorFactoryTest.TENSOR_FACTORY_KEY);
            super.beforeTest();
        }

        protected LongTypeSafeArray getData() {
            return TensorFactoryTests.longData(new int[] {1},random);
        }

        protected Long getRandomElement() {
            return TensorFactoryTests.randomLong(random);
        }

        protected LongScalar getTensor(TypeSafeArray<Long> data) {
            LongScalar tensor = (LongScalar) factory.longTensor();
            tensor.setTensorValues(data);
            return tensor;
        }
    }

    public static class TensorFactoryFloatScalarTest extends FloatScalarTest {

        protected TensorFactory factory;

        public static void main(String ... args) {
            TestBase.main();
        }

        @Before
        public void beforeTest() {
            factory = (TensorFactory) getTestData(getCallingContextInstance().getClass(), TensorFactoryTest.TENSOR_FACTORY_KEY);
            super.beforeTest();
        }

        protected FloatTypeSafeArray getData() {
            return TensorFactoryTests.floatData(new int[] {1},random);
        }

        protected Float getRandomElement() {
            return TensorFactoryTests.randomFloat(random);
        }

        protected FloatScalar getTensor(TypeSafeArray<Float> data) {
            FloatScalar tensor = (FloatScalar) factory.floatTensor();
            tensor.setTensorValues(data);
            return tensor;
        }
    }

    public static class TensorFactoryDoubleScalarTest extends DoubleScalarTest {

        protected TensorFactory factory;

        public static void main(String ... args) {
            TestBase.main();
        }

        @Before
        public void beforeTest() {
            factory = (TensorFactory) getTestData(getCallingContextInstance().getClass(), TensorFactoryTest.TENSOR_FACTORY_KEY);
            super.beforeTest();
        }

        protected DoubleTypeSafeArray getData() {
            return TensorFactoryTests.doubleData(new int[] {1},random);
        }

        protected Double getRandomElement() {
            return TensorFactoryTests.randomDouble(random);
        }

        protected DoubleScalar getTensor(TypeSafeArray<Double> data) {
            DoubleScalar tensor = (DoubleScalar) factory.doubleTensor();
            tensor.setTensorValues(data);
            return tensor;
        }
    }
}