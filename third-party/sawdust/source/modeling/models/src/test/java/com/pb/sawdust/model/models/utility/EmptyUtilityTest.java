package com.pb.sawdust.model.models.utility;

import com.pb.sawdust.model.models.provider.DataProvider;
import com.pb.sawdust.util.collections.ArraySetList;
import com.pb.sawdust.util.collections.SetList;
import com.pb.sawdust.util.test.TestBase;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * The {@code EmptyUtilityTest} ...
 *
 * @author crf <br/>
 *         Started Sep 16, 2010 9:33:09 PM
 */
public class EmptyUtilityTest extends UtilityTest {

    public static void main(String ... args) {
        TestBase.main();
        EmptyLinearUtilityTest.main();
    }

    @Override
    protected Utility getUtility(List<String> variables, double[] coefficients) {
        return EmptyUtility.getEmptyUtility();
    }

    @Override
    protected double[] getUtilityValues(DataProvider data) {
        return new double[data.getDataLength()];
    }

    protected SetList<String> getVariables() {
        return new ArraySetList<String>();
    }

    protected double[] getCoefficients() {
        return new double[1];
    }

    @Ignore
    @Test
    public void testGetUtilitiesMissingVariable() {}

    public static class EmptyLinearUtilityTest extends LinearUtilityTest {
        //multiple inheritence would be nice here...
        private EmptyUtilityTest test = new EmptyUtilityTest();

        @Override
        protected LinearUtility getUtility(SetList<String> variables, double[] coefficients) {
            return  EmptyUtility.getEmptyLinearUtility();
        }

         protected SetList<String> getVariables() {
             return test.getVariables();
         }

        protected double[] getCoefficients() {
             return test.getCoefficients();
         }

        @Ignore
        @Test
        public void testGetUtilitiesMissingVariable() {}

        @Ignore
        @Test
        public void testGetCoefficient() {}

        public static void main() {
            TestBase.main();
        }
    }
}