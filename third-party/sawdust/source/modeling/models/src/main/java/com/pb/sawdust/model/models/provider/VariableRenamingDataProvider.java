package com.pb.sawdust.model.models.provider;

import com.pb.sawdust.model.models.trace.CalculationTrace;
import com.pb.sawdust.tensor.alias.matrix.primitive.DoubleMatrix;
import com.pb.sawdust.tensor.factory.TensorFactory;

import java.util.*;

/**
 * The {@code VariableRenamingDataProvider} is a data provider which wraps another provider and renames its variables.
 * This class can be useful when a single base provider needs to be extended for different contexts and then all of those
 * contexts composited together (a single data provider cannot have more than one variable with a given name).
 *
 * @author crf
 *         Started 5/23/12 4:11 PM
 */
public class VariableRenamingDataProvider extends WrappedDataProvider {
    private final DataProvider provider;
    private Map<String,String> variableMapping;

    private static void checkMapping(DataProvider provider, Map<String,String> variableMapping) {
        for (String variable : variableMapping.values())
            if (! provider.hasVariable(variable))
                throw new IllegalArgumentException("Variable in mapping not found: " + variable);
    }

    /**
     * Constructor specifying the wrapped data provider and the variable mapping. The variable mapping's keys are the
     * new variable names that will be available through the new provider, and its values correspond to the base provider's
     * variables. All of the variables in the base provider need not be used, but any left out will not be available through
     * this provider.
     *
     * @param provider
     *        The data provider to wrap.
     *
     * @param variableMapping
     *        A mapping from the new variable names to the originals in {@code provider.}
     *
     * @throws IllegalArgumentException if any of the values in {@code variableMapping} are not variables in {@code provider}.
     */
    public VariableRenamingDataProvider(DataProvider provider, Map<String,String> variableMapping) {
        super(provider);
        checkMapping(provider,variableMapping);
        this.provider = provider;
        this.variableMapping = variableMapping;
    }

    /**
     * Constructor specifying the wrapped data provider, the data source identifier, and the variable mapping. The variable
     * mapping's keys are the new variable names that will be available through the new provider, and its values correspond
     * to the base provider's  variables. All of the variables in the base provider need not be used, but any left out will
     * not be available through this provider. This constructor should only be  called if the data provider is equivalent
     * to another (already constructed) provider, and that the equivalence needs to be recognized through the data identifier.
     *
     * @param provider
     *        The data provider to wrap.
     *
     * @param id
     *        The identifier for this provider.
     *
     * @param variableMapping
     *        A mapping from the new variable names to the originals in {@code provider.}
     *
     * @throws IllegalArgumentException if any of the values in {@code variableMapping} are not variables in {@code provider},
     *                                  or if {@code id} has not already been allocated via {@code AbstractIdData}.
     */
    public VariableRenamingDataProvider(DataProvider provider, int id, Map<String,String> variableMapping) {
        super(provider,id);
        checkMapping(provider,variableMapping);
        this.provider = provider;
        this.variableMapping = variableMapping;
    }

    private String getVariable(String variable) {
        if (!variableMapping.containsKey(variable))
            throw new IllegalArgumentException("Variable not found: " + variable);
        return variableMapping.get(variable);
    }

    private List<String> getVariables(List<String> variables) {
        List<String> trueVariables = new LinkedList<>();
        for (String variable : variables)
            trueVariables.add(getVariable(variable));
        return trueVariables;
    }

    @Override
    public boolean hasVariable(String variable) {
        return variableMapping.containsKey(variable);
    }

    @Override
    public Set<String> getVariables() {
        return variableMapping.keySet();
    }

    @Override
    public double[] getVariableData(String variable) {
        return super.getVariableData(getVariable(variable));
    }

    @Override
    public DoubleMatrix getData(List<String> variables) {
        return super.getData(getVariables(variables));
    }

    @Override
    public DoubleMatrix getData(List<String> variables, TensorFactory factory) {
        return super.getData(getVariables(variables),factory);
    }

    @Override
    public double[] getVariableData(String variable, int start, int end) {
        return super.getVariableData(getVariable(variable),start,end);
    }

    @Override
    public CalculationTrace getVariableTrace(String variable, int observation) {
        return super.getVariableTrace(getVariable(variable),observation);
    }

    /**
     * Get a data provider in which all of the variables held by the base provider have been renamed in a standardized fashion.
     * The renaming function will give every variable a new name of the form "[originalVariableName]@[referenceName]".
     *
     * @param baseProvider
     *        The base provider whose variables are to be renamed.
     *
     * @param referenceName
     *        The name to use for the reference.
     *
     * @return a data provider wrapping this one which uses transformed variables specific to {@code referenceName}.
     */
    public static DataProvider getStandardVariableRenamedProvider(DataProvider baseProvider, String referenceName) {
        Map<String,String> variableMapping = new HashMap<>();
        for (String column : baseProvider.getVariables())
            variableMapping.put(column + "@" + referenceName,column);
        return new VariableRenamingDataProvider(baseProvider,variableMapping);
    }
}