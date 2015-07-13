package com.pb.sawdust.model.models.provider;

import com.pb.sawdust.tensor.ArrayTensor;
import com.pb.sawdust.util.test.TestBase;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

/**
 * The {@code LazySubDataProviderTest} ...
 *
 * @author crf <br/>
 *         Started Sep 26, 2010 1:00:24 PM
 */
public class LazySubDataProviderTest  extends DataProviderTest {
    protected LazySubDataProvider lazyProvider;
    protected DataProvider parent;

    public static void main(String ... args) {
        TestBase.main();
    }

    @Before
    public void beforeTest() {
        super.beforeTest();
        lazyProvider = (LazySubDataProvider) provider;
    }

    @Override
    protected DataProvider getProvider(int id, int dataLength, Map<String, double[]> data) {
        parent = new SimpleDataProvider(id,data, ArrayTensor.getFactory());
        return new LazySubDataProvider(parent,ArrayTensor.getFactory(),0,dataLength);
    }

    @Override
    protected DataProvider getUninitializedProvider(Set<String> variables) {
        return null; //uninitialized lazy provider non existant
    }

    @Override
    @Ignore
    @Test
    public void testGetId() {
        //no way to set id
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorEndLessThanStart() {
        new LazySubDataProvider(parent,ArrayTensor.getFactory(),subDataEnd,subDataStart);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorZeroLength() {
        new LazySubDataProvider(parent,ArrayTensor.getFactory(),subDataStart,subDataStart);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorStartTooSmall() {
        new LazySubDataProvider(parent,ArrayTensor.getFactory(),-1,subDataEnd);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorEndTooBig() {
        new LazySubDataProvider(parent,ArrayTensor.getFactory(),subDataStart,dataLength+1);
    }
}