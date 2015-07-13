package com.pb.sawdust.model.models.provider;

import com.pb.sawdust.calculator.NumericFunctionN;
import com.pb.sawdust.calculator.NumericFunctions;
import com.pb.sawdust.tensor.factory.TensorFactory;

import java.util.*;

/**
 * The {@code CalculationDataProvider} class is a basic mutable {@code CalculationProvider} implementation. Beyond the base
 * interface functionality, it also allows calculated variables to be added after construction; this allows it to be a simple
 * way to dynamically build a set of calculated variables.
 *
 * @author crf <br/>
 *         Started 2/4/11 11:41 AM
 */
public class SimpleCalculationDataProvider extends AbstractDataProvider implements CalculationProvider {
    private final DataProvider baseProvider;
    private final Map<String,VariableCalculation> calculations;

    /**
     * Constructor specifying the data source identifier, the base provider, and tensor factory used to build data results.
     * This constructor should only be called if the data provider is equivalent to another (already constructed) provider,
     * and that the equivalence needs to be recognized through the data identifier.
     *
     * @param dataId
     *        The identifier for this provider.
     *
     * @param baseProvider
     *        The base provider that will supply the data used in the variable calculations.
     *
     * @param factory
     *        The tensor factory used to build data results.
     *
     * @throws IllegalArgumentException if {@code id} has not already been allocated via {@code AbstractIdData}.
     */
    public SimpleCalculationDataProvider(int dataId, DataProvider baseProvider, TensorFactory factory) {
        super(dataId,factory);
        this.baseProvider = baseProvider;
        calculations = new LinkedHashMap<String,VariableCalculation>();
    }

    /**
     * Constructor specifying the base provider and tensor factory used to build data results.
     *
     * @param baseProvider
     *        The base provider that will supply the data used in the variable calculations.
     *
     * @param factory
     *        The tensor factory used to build data results.
     */
    public SimpleCalculationDataProvider(DataProvider baseProvider, TensorFactory factory) {
        super(factory);
        this.baseProvider = baseProvider;
        calculations = new LinkedHashMap<String,VariableCalculation>();
    }

    @Override
    public int getDataLength() {
        return baseProvider.getDataLength();
    }

    @Override
    public boolean hasVariable(String variable) {
        return baseProvider.hasVariable(variable) || calculations.containsKey(variable);
    }

    @Override
    public Set<String> getVariables() {
        Set<String> variables = new HashSet<String>(baseProvider.getVariables());
        variables.addAll(calculations.keySet());
        return variables;
    }

    @Override
    public double[] getVariableData(String variable) {
        if (calculations.containsKey(variable))
            return getVariableData(calculations.get(variable));
        else
            return baseProvider.getVariableData(variable);
    }

    @Override
    public double[] getVariableData(String variable, int start, int end) {
        if (calculations.containsKey(variable)) {
            return getVariableData(calculations.get(variable),start,end);
        } else {
            return baseProvider.getVariableData(variable,start,end);
        }
    }

    @Override
    public DataProvider getSubData(int start, int end) {
        return new SimpleCalculationSubDataProvider(start,end);
    }

    /**
     * Get the calculated variable values for a given {@code VariableCalculation}. By default, this method will evaluate the
     * calculation at every call; it may be overridden if a more efficient way of getting the values can be performed.
     *
     * @param calculation
     *        The calculation to evaluate.
     *
     * @return the calculated values of {@code calculation} on this provider.
     */
    protected double[] getVariableData(VariableCalculation calculation) {
        return getVariableData(calculation,-1,-1);
    }

    private double[] getVariableData(VariableCalculation calculation, int start, int end) {
        boolean range = start >= 0;
        List<double[]> data = new LinkedList<double[]>();
        NumericFunctionN function = calculation.getFunction();
        for (String argument : calculation.getArguments())
            data.add(range ? getVariableData(argument,start,end) : getVariableData(argument));
        double[] values = new double[function.getArgumentCount()];
        double[] result = new double[range ? end - start : getDataLength()];
        int counter;
        for (int i = 0; i < result.length; i++) {
            counter = 0;
            for (double[] d : data)
                values[counter++] = d[i];
            result[i] = function.applyDouble(values);
        }
        return result;
    }

    /**
     * Add a calculated variable to this provider.
     *
     * @param variable
     *        The name of the variable to add.
     *
     * @param function
     *        The function used to calculate the variable values.
     *
     * @param args
     *        The names of the variables to use for the arguments in {@code function}.
     *
     * @throws IllegalArgumentException if a variable with the name {@code variable}  already exists in this provider,
     *                                  if this provider does not have a variable with the same name as every argument in
     *                                  {@code args}, or if the number of arguments in {@code function} does not equal the
     *                                  size of {@code args}.
     */
    public void addCalculatedVariable(String variable,NumericFunctionN function, List<String> args) {
        addCalculatedVariable(new VariableCalculation(variable, function, args));
    }

    /**
     * Add a calculated variable to this provider.
     *
     * @param calculation
     *        The variable calculation to add.
     *
     * @throws IllegalArgumentException if a variable with the same name as that in {@code calculation} already exists
     *                                  in this provider, or if this provider does not have a variable with the same name
     *                                  for every argument in {@code calculation}.
     */
    public void addCalculatedVariable(VariableCalculation calculation) {
        String variable = calculation.getName();
        if (hasVariable(variable))
            throw new IllegalArgumentException("Variable already exists: " + variable);
        for (String arg : calculation.getArguments())
            if (!hasVariable(arg))
                throw new IllegalArgumentException("Argument variable not found: " + arg);
        calculations.put(variable,calculation);
    }

    public VariableCalculation getCalculation(String variable) {
        if (!hasVariable(variable))
            throw new IllegalArgumentException("Variable not found: " + variable);
        if (baseProvider.hasVariable(variable)) {
            if (baseProvider instanceof CalculationProvider)
                return ((CalculationProvider) baseProvider).getCalculation(variable);
            else
                return new VariableCalculation(variable);
        } else {
            return calculations.get(variable);
        }
    }

    public VariableCalculation getResolvedCalculation(String variable) {
        return getResolvedCalculation(this,variable);
    }

    public boolean containsCalculatedVariables(){
        return calculations.size() > 0 ||
               (baseProvider instanceof CalculationProvider &&
                ((CalculationProvider) baseProvider).containsCalculatedVariables());
    }

    /**
     * The {@code SimpleCalculationSubDataProvider} class provides a class which is used to build a sub-data provider for
     * a {@code SimpleCalculationDataProvider}. Extensions of {@code SimpleCalculationDataProvider} which provide enhanced
     * functionality should also extend this class to build its sub-data providers.
     */
    protected class SimpleCalculationSubDataProvider extends LazySubDataProvider implements CalculationProvider {

        /**
         * Constructor specifying the beginning and end of the sub-data provider.
         *
         * @param start
         *        The (inclusive) starting observation number for the provider.
         *
         * @param end
         *        The (exclusive) starting observation number for the provider.
         *
         * @throws IllegalArgumentException if <code>end &lt;= start</code> or if {@code start} and/or {@code end} are out of
         *                                  the parent provider's data bounds (<i>i.e.</i> if either are less than zero or
         *                                  greater than the parent data provider's length).
         */
        SimpleCalculationSubDataProvider(int start, int end) {
            super(SimpleCalculationDataProvider.this,SimpleCalculationDataProvider.this.factory,start,end);
        }

        public VariableCalculation getCalculation(String variable) {
            return SimpleCalculationDataProvider.this.getCalculation(variable);
        }

        public VariableCalculation getResolvedCalculation(String variable) {
            return SimpleCalculationDataProvider.this.getResolvedCalculation(variable);
        }

        public boolean containsCalculatedVariables(){
            return SimpleCalculationDataProvider.this.containsCalculatedVariables();
        }

        @Override
        public double[] getVariableData(String variable) {
            if (calculations.containsKey(variable))
                return getVariableData(calculations.get(variable));
            else
                return super.getVariableData(variable);
        }

        /**
         * Get the calculated variable values for a given {@code VariableCalculation}. By default, this method will evaluate the
         * calculation at every call; it may be overridden if a more efficient way of getting the values can be performed.
         *
         * @param calculation
         *        The calculation to evaluate.
         *
         * @return the calculated values of {@code calculation} on this sub-data provider.
         */
        protected double[] getVariableData(VariableCalculation calculation) {
            return SimpleCalculationDataProvider.this.getVariableData(calculation,start,end);
        }
    }

    /**
     * Get the fully resolved variable calculation for a variable in a {@code CalculationProvider}. This not only returns
     * the direct variable calculation for the variable, but also fully resolves any nested calculations and embeds them
     * into the returned variable calculation.
     *
     * @param provider
     *        The provider holding the variable calculation.
     *
     * @param variable
     *        The calculated variable to resolve.
     *
     * @return the fully resolved variable calculation corresponding to {@code variable} in {@code provider}.
     *
     * @throws IllegalArgumentException if {@code variable} is not held in {@code provider}.
     */
    public static VariableCalculation getResolvedCalculation(CalculationProvider provider, String variable) {
        VariableCalculation calc = provider.getCalculation(variable);
        List<VariableCalculation> subCalcs = new LinkedList<VariableCalculation>();
        for (String arg : calc.getArguments())
            subCalcs.add(getResolvedCalculation(provider,arg));
        boolean unnested = true;
        for (VariableCalculation subCalc : subCalcs)
            unnested &= !subCalc.isCalculated();
        if (unnested) {
            return calc; //no subcalculations, so can just return original
        } else {
            //need to unroll subcalculations into numeric function
            List<NumericFunctionN> functions = new LinkedList<NumericFunctionN>();
            List<String> arguments = new LinkedList<String>();
            for (VariableCalculation subCalc : subCalcs) {
                if (subCalc.isCalculated()) {
                    for (String arg : subCalc.getArguments()) {
                        functions.add(NumericFunctions.PARAMETER);
                        arguments.add(arg);
                    }
                    functions.add(subCalc.getFunction());
                } else {
                    functions.add(NumericFunctions.PARAMETER);
                    arguments.add(subCalc.getName());
                }
            }
            functions.add(calc.getFunction());
            return new VariableCalculation(variable,NumericFunctions.compositeNumericFunction(functions),arguments);
        }
    }


}