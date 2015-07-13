package com.pb.sawdust.util.abacus;

import com.pb.sawdust.util.Range;
import com.pb.sawdust.util.test.TestBase;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import static com.pb.sawdust.util.Range.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * The {@code LockableAbacusTest} ...
 *
 * @author crf <br/>
 *         Started 5/8/11 11:44 AM
 */
public class LockableAbacusTest extends AbacusTest {
    public static String LOCKABLE_TEST_STATE = "lockable test state";

    public static enum LockableAbacusTestState {
        UNLOCKED,ONE_LOCKED,MULTIPLE_LOCKED
    }

    public static void main(String ... args) {
        TestBase.main();
    }

    private int[] lockedDimensions;
    private LockableAbacusTestState state;

    protected void addSubDataTest(List<Class<? extends TestBase>> additionalClassContainer) {
        additionalClassContainer.add(LockableAbacusFreshCloneTest.class);
    }

    protected List<Map<String,Object>> getClassContext() {
        List<Map<String,Object>> context = new LinkedList<Map<String,Object>>();
        for (boolean r : new boolean[] {true,false}) {
            for (LockableAbacusTestState s : LockableAbacusTestState.values()) {
                Map<String,Object> c = buildContext(REVERSE_KEY,r);
                c.put(LOCKABLE_TEST_STATE,s);
                context.add(c);
            }
        }
        return context;
    }

    @Override
    @Before
    public void beforeTest() {
        super.beforeTest();
        state = (LockableAbacusTestState) getTestData(LOCKABLE_TEST_STATE);
        lockedDimensions = new int[dimensions.length];
        Arrays.fill(lockedDimensions,-1);
        switch ((LockableAbacusTestState) getTestData(LOCKABLE_TEST_STATE)) {
            case UNLOCKED : break;
            case ONE_LOCKED : {
                int rd = random.nextInt(dimensions.length);
                lockedDimensions[rd] = random.nextInt(dimensions[rd]);
                break;
            }
            case MULTIPLE_LOCKED : {
                for (int i : range(random.nextInt(2,dimensions.length))) {
                    int rd = random.nextInt(dimensions.length);
                    while (lockedDimensions[rd] != -1)
                        rd = random.nextInt(dimensions.length);
                    lockedDimensions[rd] = random.nextInt(dimensions[rd]);
                }
                break;
            }
        }
    }

    protected LockableAbacus getAbacus() {
        LockableAbacus a = new LockableAbacus(reverse,dimensions);
        for (int i : range(lockedDimensions.length))
            if (lockedDimensions[i] > -1)
                a.lockDimension(i,lockedDimensions[i]);
        return a;
    }

    protected int[] getNthStep(int n) {
        int[] step = new int[dimensions.length];
        Range r = reverse ? range(dimensions.length) : range(dimensions.length-1,-1);
        for (int d : r)
            if (lockedDimensions[d] > -1)
                step[d] = lockedDimensions[d];
        for (int i : range(n)) {
            for (int d : r) {
                if (lockedDimensions[d] > -1)
                    continue;
                if (++step[d] == dimensions[d])
                    step[d] = 0;
                 else
                    break;
            }
        }
        return step;
    }

    @Test
    public void testGetLength() {
        long length = 1L;
        for (int i : range(dimensions.length))
            if (lockedDimensions[i] == -1)
                length *= dimensions[i];
        assertEquals(length,getAbacus().getLength());
    }

    @Test
    public void testGetStateCount() {
        long length = 1L;
        for (int i : range(dimensions.length))
            if (lockedDimensions[i] == -1)
                length *= dimensions[i];
        assertEquals(length,getAbacus().getStateCount());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLockDimensionDimensionTooSmall() {
        getAbacus().lockDimension(random.nextInt(-100,0),0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLockDimensionDimensionTooBig() {
        getAbacus().lockDimension(random.nextInt(dimensions.length,100),0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLockDimensionValueTooSmall() {
        int d = random.nextInt(dimensions.length);
        getAbacus().lockDimension(d,random.nextInt(-100,0));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLockDimensionValueTooBig() {
        int d = random.nextInt(dimensions.length);
        getAbacus().lockDimension(d,random.nextInt(dimensions[d],100));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testGetAbacusPointInvalidDimensionLockValue() {
        if (getTestData(LOCKABLE_TEST_STATE) == LockableAbacusTest.LockableAbacusTestState.UNLOCKED)
            throw new IllegalArgumentException();
        Abacus abacus = getAbacus();
        iterateAbacus(abacus,random.nextInt(1,(int) abacus.getLength())); //just to put it  in a new state
        int point = random.nextInt((int) abacus.getLength()-1);
        int[] position = getNthStep(point);
        int lockedDimension = 0;
        for (int i : range(lockedDimensions.length)) {
            if (lockedDimensions[i] > -1) {
                lockedDimension = i;
                break;
            }
        }
        position[lockedDimension]  = (position[lockedDimension]+1)%dimensions[lockedDimension];
        abacus.setAbacusPoint(position);
    }

    public static class LockableAbacusFreshCloneTest extends AbacusFreshCloneTest {
        protected int[] lockedDimensions;

        protected int[] getNthStep(int n) {
            return parent.getNthStep(n);
        }

        @Override
        @Before
        public void beforeTest() {
            super.beforeTest();
            lockedDimensions = ((LockableAbacusTest) parent).lockedDimensions;
        }

        protected List<Map<String,Object>> getClassContext() {
            List<Map<String,Object>> context = new LinkedList<Map<String,Object>>();
            for (LockableAbacusTestState s : LockableAbacusTestState.values()) {
                context.add(buildContext(LOCKABLE_TEST_STATE,s));
            }
            return context;
        }

        @Test
        public void testGetLength() {
            long length = 1L;
            for (int i : range(dimensions.length))
                if (lockedDimensions[i] == -1)
                    length *= dimensions[i];
            assertEquals(length,getAbacus().getLength());
        }

        @Test
        public void testGetStateCount() {
            long length = 1L;
            for (int i : range(dimensions.length))
                if (lockedDimensions[i] == -1)
                    length *= dimensions[i];
            assertEquals(length,getAbacus().getStateCount());
        }
    }
}