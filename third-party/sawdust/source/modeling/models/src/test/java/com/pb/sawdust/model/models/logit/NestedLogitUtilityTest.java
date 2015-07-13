package com.pb.sawdust.model.models.logit;

import com.pb.sawdust.model.models.utility.SimpleLinearUtilityTest;
import com.pb.sawdust.model.models.utility.Utility;
import com.pb.sawdust.model.models.Choice;
import com.pb.sawdust.model.models.ChoiceUtil;
import com.pb.sawdust.model.models.provider.DataProvider;
import com.pb.sawdust.tensor.ArrayTensor;
import com.pb.sawdust.tensor.alias.vector.primitive.DoubleVector;
import static com.pb.sawdust.util.Range.*;
import static org.junit.Assert.assertEquals;

import com.pb.sawdust.util.array.ArrayUtil;
import com.pb.sawdust.util.collections.LinkedSetList;
import com.pb.sawdust.util.collections.SetList;
import com.pb.sawdust.util.test.TestBase;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * The {@code NestedLogitUtilityTest} ...
 *
 * @author crf <br/>
 *         Started Sep 28, 2010 7:51:46 AM
 */
public class NestedLogitUtilityTest extends SimpleLinearUtilityTest {
    public static final String NESTED_LOGIT_MODE = "nested logit mode";

    public static enum NestedLogitUtilityMode {
        NO_NESTED_MODEL,
        NO_VARIABLES,
        VARIABLES_AND_NESTED_MODEL
    }

    protected NestedLogitUtilityMode mode;

    protected NestedLogitUtility<Choice> nestedUtility;
    protected LogitModel<Choice> nestedModel;
    protected double logsumCoefficient;

    public static void main(String ... args) {
        TestBase.main();
    }

    protected Collection<Class<? extends TestBase>> getAdditionalTestClasses() {
        List<Map<String,Object>> contexts = new LinkedList<Map<String,Object>>();
        for (NestedLogitUtilityMode nlm : NestedLogitUtilityMode.values())
            contexts.add(buildContext(NESTED_LOGIT_MODE,nlm));
        addClassRunContext(this.getClass(),contexts);
        return super.getAdditionalTestClasses();
    }

    protected SetList<String> getVariables() {
        if (mode == NestedLogitUtilityMode.NO_VARIABLES)
            return new LinkedSetList<String>();
        return super.getVariables();
    }

    protected double[] getCoefficients() {
        if (mode == NestedLogitUtilityMode.NO_VARIABLES)
            return new double[0];
        return random.nextDoubles(variables.size());
    }

    @Override
    @SuppressWarnings("unchecked") //EMPTY_NEST can be cast to any parameterization
    protected NestedLogitUtility<Choice> getUtility(SetList<String> variables, double[] coefficients) {
        switch (mode) {
            case NO_VARIABLES : {
                logsumCoefficient = random.nextDouble();
                Set<ChoiceUtil.IntChoice> choices = ChoiceUtil.getChoiceRange(random.nextInt(4,8));
                Map<Choice,Utility> utilityMap = new HashMap<Choice,Utility>();
                for (Choice c : choices) {
                    double[] coeffs = random.nextDoubles(random.nextInt(4,10));
                    SetList<String> vars = new LinkedSetList<String>();
                    for (int i : range(coeffs.length))
                        vars.add(random.nextAsciiString(7));
                    utilityMap.put(c,super.getUtility(vars,coeffs));
                }
                nestedModel = new LogitModel<Choice>(utilityMap,ArrayTensor.getFactory());
                return new NestedLogitUtility<Choice>(nestedModel,logsumCoefficient,ArrayTensor.getFactory());
            }
            case NO_NESTED_MODEL : {
                logsumCoefficient = 0.0;
                nestedModel = (NestedLogitModel<Choice>) NestedLogitModel.EMPTY_NEST;
                return new NestedLogitUtility<Choice>(variables,Arrays.asList(ArrayUtil.toDoubleArray(coefficients)),ArrayTensor.getFactory());
            }
            case VARIABLES_AND_NESTED_MODEL : {
                logsumCoefficient = random.nextDouble();
                Set<ChoiceUtil.IntChoice> choices = ChoiceUtil.getChoiceRange(random.nextInt(4,8));
                Map<Choice,Utility> utilityMap = new HashMap<Choice,Utility>();
                for (Choice c : choices) {
                    double[] coeffs = random.nextDoubles(random.nextInt(4,10));
                    SetList<String> vars = new LinkedSetList<String>();
                    for (int i : range(coeffs.length))
                        vars.add(random.nextAsciiString(7));
                    utilityMap.put(c,super.getUtility(vars,coeffs));
                }
                nestedModel = new LogitModel<Choice>(utilityMap,ArrayTensor.getFactory());
                return new NestedLogitUtility<Choice>(variables,Arrays.asList(ArrayUtil.toDoubleArray(coefficients)),nestedModel,logsumCoefficient,ArrayTensor.getFactory());
            }
        }
        throw new IllegalStateException("Shouldn't be here");
    }

    @Override
    protected double[] getUtilityValues(DataProvider data) {
        double[] utValues = super.getUtilityValues(data);
        DoubleVector nestValues = nestedModel.getLogsums(data);
        for (int i : range(utValues.length))
            utValues[i] += nestValues.getCell(i)*logsumCoefficient;
        return utValues;
    }

    @Override
    protected DataProvider getDataProvider(Set<String> variables) {
        return getDataProvider(variables,false);
    }

    protected DataProvider getDataProvider(Set<String> variables, boolean badForNestedModel) {
        Set<String> modelVariables = new HashSet<String>(variables);
        for (Choice c : nestedModel.getUtilities().keySet())
            modelVariables.addAll(nestedModel.getUtilities().get(c).getVariableSet());
        if (badForNestedModel)
            modelVariables.remove(nestedModel.getUtilities().get(nestedModel.getUtilities().keySet().iterator().next()).getVariableSet().iterator().next());
        return super.getDataProvider(modelVariables);
    }

    @Before
    @Override
    @SuppressWarnings("unchecked") //it'll be parameterized correctly
    public void beforeTest() {
        mode = (NestedLogitUtilityMode) getTestData(NESTED_LOGIT_MODE);
        super.beforeTest();
        nestedUtility = (NestedLogitUtility<Choice>) linearUtility;
    }

    @Override
    @Test(expected=IllegalArgumentException.class)
    public void testConstructorMismatchedVariablesAndCoefficients() {
        if (mode != NestedLogitUtilityMode.NO_VARIABLES) {
            super.testConstructorMismatchedVariablesAndCoefficients();
        } else {
            addAdditionalTestInformation(" (skipped)");
            throw new IllegalArgumentException("skipped"); //no variables to miss
        }
    }

    @Override
    @Test(expected=IllegalArgumentException.class)
    public void testConstructorEmptyVariablesAndCoefficients() {
        if (mode == NestedLogitUtilityMode.NO_NESTED_MODEL) {
            super.testConstructorEmptyVariablesAndCoefficients();
        } else {
            addAdditionalTestInformation(" (skipped)");
            throw new IllegalArgumentException("skipped"); //no variables to miss
        }
    }

    @Test
    @Override
    public void testGetVariables() {
        if (mode == NestedLogitUtilityMode.NO_NESTED_MODEL) {
            super.testGetVariables();
        } else {
            SetList<String> vars = new LinkedSetList<String>(variables);
            vars.add(NestedLogitUtility.NESTED_LOGIT_MODEL_NAME);
            assertEquals(vars,utility.getVariables());
        }
    }

    @Test
    @Override
    public void testGetVariableSet() {
        if (mode == NestedLogitUtilityMode.NO_NESTED_MODEL) {
            super.testGetVariableSet();
        } else {
            SetList<String> vars = new LinkedSetList<String>(variables);
            vars.add(NestedLogitUtility.NESTED_LOGIT_MODEL_NAME);
            assertEquals(vars,utility.getVariableSet());
        }
    }

    @Test
    @Override
    public void testGetCoefficients() {
        if (mode == NestedLogitUtilityMode.NO_NESTED_MODEL) {
            super.testGetCoefficients();
        } else {
            double[] coeffs = new double[coefficients.length+1];
            System.arraycopy(coefficients,0,coeffs,0,coefficients.length);
            coeffs[coeffs.length-1] = logsumCoefficient;
            assertArrayAlmostEquals(coeffs,(double[]) utility.getCoefficients().getTensorValues().getArray());
        }
    }

    @Test
    @Override
    public void testGetCoefficient() {
        if (mode == NestedLogitUtilityMode.NO_VARIABLES)
            assertAlmostEquals(logsumCoefficient,linearUtility.getCoefficient(NestedLogitUtility.NESTED_LOGIT_MODEL_NAME));
        else
            super.testGetCoefficient();
    }

    @Override
    @Test(expected=IllegalArgumentException.class)
    public void testGetUtilitiesMissingVariable() {
        if (mode != NestedLogitUtilityMode.NO_VARIABLES) {
            super.testGetUtilitiesMissingVariable();
        } else {
            addAdditionalTestInformation(" (skipped)");
            throw new IllegalArgumentException("skipped"); //no variables to miss
        }
    }

    @Test
    public void testGetNestedModel() {
        assertEquals(nestedModel,nestedUtility.getNestedModel());
    }

    @Test
    public void testGetLogsumParameter() {
        assertAlmostEquals(mode == NestedLogitUtilityMode.NO_NESTED_MODEL ? 0.0 : logsumCoefficient,nestedUtility.getNestedModelLogsumParameter());
    }

}