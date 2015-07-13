package com.pb.sawdust.model.models.provider;

import com.pb.sawdust.model.models.trace.CalculationTrace;
import com.pb.sawdust.model.models.utility.Utility;
import com.pb.sawdust.tensor.factory.TensorFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The {@code UtilityDataProvider} class is a data provider whose variable data comes from {@code Utility}s.  The class
 * holds both a series of utilities, as well as a data provider which is used to calculate the utility values against.
 * The actual utility values are calculated lazily: only when the actual data is requested will the utility calculations
 * be carried out.
 * <p>
 * This provider structure allows utility functional forms to be built that are hybrids without sacrificing the purity
 * of the individual elements nor requiring overly specialized utility implementations. As a basic example, a linear
 * combination of non-linear functions can be created as a series of non-linear utilities embedded in a
 * {@code UtilityDataProvider}, which is then fed into a {@code LinearUtility} to produce the final utilities.
 *
 * @author crf <br/>
 *         Started Sep 17, 2010 4:33:46 PM
 */
public class UtilityDataProvider extends AbstractDataProvider {
    private final DataProvider baseProvider;
    private final Map<String,Utility> utilityMap;

    /**
     * Constructor specifying the base provider and utilities used to create the data, as well as the tensor factory used
     * to build data results.
     *
     * @param baseProvider
     *        The data provider which will be used to calculate the actual utility values.
     *
     * @param utilityMap
     *        A mapping specifying the variable (utility) names and their associated utilities.
     *
     * @param factory
     *        The tensor factory used to build data results.
     *
     * @throws IllegalArgumentException if any of the variables required by the utilities in {@code utilityMap} are not
     *                                  provided by {@code baseProvider}.
     */
    public UtilityDataProvider(DataProvider baseProvider, Map<String,Utility> utilityMap, TensorFactory factory) {
        this(baseProvider,utilityMap,factory,true);
    }

    /**
     * Constructor specifying data identifier, the base provider and utilities used to create the data, as well as the tensor
     * factory used to build data results.     
     *
     * @param dataId
     *        The data id to use for this provider.
     *
     * @param baseProvider
     *        The data provider which will be used to calculate the actual utility values.
     *
     * @param utilityMap
     *        A mapping specifying the variable (utility) names and their associated utilities.
     *
     * @param factory
     *        The tensor factory used to build data results.
     *
     * @throws IllegalArgumentException if any of the variables required by the utilities in {@code utilityMap} are not
     *                                  provided by {@code baseProvider}.
     */
    public UtilityDataProvider(int dataId, DataProvider baseProvider, Map<String,Utility> utilityMap, TensorFactory factory) {
        super(dataId,factory);
        this.baseProvider = baseProvider;
        this.utilityMap = new HashMap<String,Utility>(utilityMap);
        checkValidity();
    }

    private static Map<String,Utility> buildSimpleUtilityMap(String variableName, Utility utility) {
        Map<String,Utility> utilityMap = new HashMap<>();
        utilityMap.put(variableName,utility);
        return utilityMap;
    }


    /**
     * Convenience constructor for a single utility provider.
     *
     * @param baseProvider
     *        The data provider which will be used to calculate the actual utility values.
     *
     * @param variableName
     *        The variable name for the utility.
     *
     * @param utility
     *        The utility.
     *
     * @param factory
     *        The tensor factory used to build data results.
     *
     * @throws IllegalArgumentException if any of the variables required by the utilities in {@code utilityMap} are not
     *                                  provided by {@code baseProvider}.
     */
    public UtilityDataProvider(DataProvider baseProvider, String variableName, Utility utility, TensorFactory factory) {
        this(baseProvider,buildSimpleUtilityMap(variableName,utility),factory);
    }

    /**
     * Convenience constructor for a single utility provider specifying the data identifier.
     *
     * @param dataId
     *        The data id to use for this provider.
     *
     * @param baseProvider
     *        The data provider which will be used to calculate the actual utility values.
     *
     * @param variableName
     *        The variable name for the utility.
     *
     * @param utility
     *        The utility.
     *
     * @param factory
     *        The tensor factory used to build data results.
     *
     * @throws IllegalArgumentException if any of the variables required by the utilities in {@code utilityMap} are not
     *                                  provided by {@code baseProvider}.
     */
    public UtilityDataProvider(int dataId, DataProvider baseProvider, String variableName, Utility utility, TensorFactory factory) {
        this(dataId,baseProvider,buildSimpleUtilityMap(variableName,utility),factory);
    }


    private UtilityDataProvider(DataProvider baseProvider, Map<String,Utility> utilityMap, TensorFactory factory, boolean check) {
        super(factory);
        this.baseProvider = baseProvider;
        this.utilityMap = check ? new HashMap<String,Utility>(utilityMap) : utilityMap;
        if (check)
            checkValidity();
    }

    private void checkValidity() {
        for (String name : this.utilityMap.keySet())
            for (String variable : this.utilityMap.get(name).getVariables())
                if (!baseProvider.hasVariable(variable))
                    throw new IllegalArgumentException("Base provider missing utility variable: " + variable);

    }

    @Override
    public int getDataLength() {
        return baseProvider.getDataLength();
    }

    @Override
    public double[] getVariableData(String variable) {
        if (baseProvider.hasVariable(variable))
            return baseProvider.getVariableData(variable);
        else if (utilityMap.containsKey(variable))
            return (double[]) utilityMap.get(variable).getUtilities(this).getTensorValues().getArray();
        else
            throw new IllegalArgumentException("Variable not found in data provider: " + variable);
    }

    @Override
    public boolean hasVariable(String variable) {
        return baseProvider.hasVariable(variable) || utilityMap.containsKey(variable);
    }

    @Override
    public Set<String> getVariables() {
        Set<String> variables = new HashSet<String>(baseProvider.getVariables());
        variables.addAll(utilityMap.keySet());
        return variables;
    }

    @Override
    public double[] getVariableData(String variable, int start, int end) {
        if (baseProvider.hasVariable(variable))
            return baseProvider.getVariableData(variable,start,end);
        else if (utilityMap.containsKey(variable))
            return (double[]) utilityMap.get(variable).getUtilities(baseProvider.getSubData(start,end)).getTensorValues().getArray();
        else
            throw new IllegalArgumentException("Variable not found in data provider: " + variable);
    }

    public DataProvider getSubData(int start, int end) {
        return new UtilityDataProvider(baseProvider.getSubData(start,end),utilityMap,factory,false);
    }

    public int getAbsoluteStartIndex() {
        return baseProvider.getAbsoluteStartIndex();
    }

    public CalculationTrace getVariableTrace(String variable, int observation) {
        if (baseProvider.hasVariable(variable))
            return baseProvider.getVariableTrace(variable,observation);
        else if (utilityMap.containsKey(variable))
            return utilityMap.get(variable).traceCalculation(baseProvider,observation);
        else
            throw new IllegalArgumentException("Variable not found in data provider: " + variable);
    }
}