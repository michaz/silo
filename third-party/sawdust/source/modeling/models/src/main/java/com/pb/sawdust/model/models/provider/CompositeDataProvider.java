package com.pb.sawdust.model.models.provider;

import com.pb.sawdust.model.models.trace.CalculationTrace;
import com.pb.sawdust.tensor.factory.TensorFactory;
import com.pb.sawdust.tensor.alias.matrix.primitive.DoubleMatrix;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * The {@code CompositeDataProvider} class allows multiple data providers to be combined into a single provider.
 * Because some providers can change their data lengths, it is possible that methods called on this class will throw
 * and {@code IllegalStateException} if the data lengths of the composed data providers are inconsistent.
 * <p>
 * This class implements the {@code CalculationProvider} interface, so that if it contains any {@code CalculationProvider}
 * instances that interface contract can be correctly transmitted through this class.
 *
 * @author crf <br/>
 *         Started Aug 15, 2010 1:11:46 PM
 */
public class CompositeDataProvider extends AbstractDataProvider implements CalculationProvider {
    private final List<DataProvider> providers;
    private final Set<CalculationProvider> calculationProviders;

    /**
     * Constructor specifying the tensor factory used to build data results.
     *
     * @param factory
     *        The tensor factory used to build data results.
     */
    public CompositeDataProvider(TensorFactory factory) {
        super(factory);
        providers = new LinkedList<DataProvider>();
        calculationProviders = new HashSet<CalculationProvider>();
    }

    /**
     * Constructor specifying the tensor factory used to build data results and the initial providers for this provider.
     *
     * @param factory
     *        The tensor factory used to build data results.
     *
     * @param providers
     *        The providers which this provider will be (initially) composed with.
     *
     * @throws IllegalArgumentException if the data lengths of the providers in {@code providers} do not all equal each other.
     */
    public CompositeDataProvider(TensorFactory factory, DataProvider ... providers) {
        this(factory);
        for (DataProvider provider : providers)
            addProvider(provider);
    }

    private int checkState() {
        int length = UNINITIALIZED_DATA_LENGTH;
        for (DataProvider provider : providers)
            if (length == UNINITIALIZED_DATA_LENGTH)
                length = provider.getDataLength();
            else if (provider.getDataLength() == UNINITIALIZED_DATA_LENGTH)
                continue; //uninitialized - ignore
            else if (length != provider.getDataLength())
                throw new IllegalStateException(String.format("All providers in composite data provider must have same length (%d vs. %d)",length,provider.getDataLength()));
        return length;
    }

    public void addProvider(DataProvider provider) {
        if (provider == this)
            throw new IllegalArgumentException("Cannot add self to composite provider.");
        int dataLength = getDataLength();
        if (dataLength != UNINITIALIZED_DATA_LENGTH && provider.getDataLength() != dataLength)
            throw new IllegalArgumentException("Data length (" + provider.getDataLength() + ") for provider does not match this provider's length (" + dataLength + ")");
        providers.add(provider);
        if (provider instanceof CalculationProvider)
            calculationProviders.add((CalculationProvider) provider);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if the data providers composed into this data provider do not (still) have equal data lengths.
     */
    @Override
    public int getDataLength() {
        return checkState();
    }

    private DataProvider checkForVariable(String variable, boolean throwException) {
        for (DataProvider provider : providers)
            if (provider.hasVariable(variable))
                return provider;
        if (throwException)
            throw new IllegalArgumentException("Variable not found: " + variable);
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if the data providers composed into this data provider do not (still) have equal data lengths.
     */
    @Override
    public double[] getVariableData(String variable) {
        checkState();
        return checkForVariable(variable,true).getVariableData(variable);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if the data providers composed into this data provider do not (still) have equal data lengths.
     */
    @Override
    public double[] getVariableData(String variable, int start, int end) {
        checkState();
        return checkForVariable(variable,true).getVariableData(variable,start,end);
    }

    @Override
    public boolean hasVariable(String variable) {
        return checkForVariable(variable,false) != null;
    }

    @Override
    public Set<String> getVariables() {
        Set<String> variables = new HashSet<String>();
        for (DataProvider provider : providers)
            variables.addAll(provider.getVariables());
        return variables;
    }


    /**
     * {@inheritDoc}
     *
     * The sub-data provider returned by this method will throw {@code IllegalStateException}s for certain method calls
     * if the data providers composed into this data provider do not (still) have equal data lengths at the time of
     * calling. The methods which will throw this exception for this situation are the same as in this class, except
     * {@code getDataLength()}, which does not refer back to this instance to return its result.
     */
    @Override
    public DataProvider getSubData(int start, int end) {
        return new CompositeCalculationSubDataProvider(start,end);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if the data providers composed into this data provider do not (still) have equal data lengths.
     */
    @Override
    public DoubleMatrix getData(List<String> variables) {
        return super.getData(variables);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if the data providers composed into this data provider do not (still) have equal data lengths.
     */
    @Override
    public DoubleMatrix getData(List<String> variables, TensorFactory factory) {
        return super.getData(variables,factory);
    }

    @Override
    public CalculationTrace getVariableTrace(String variable, int observation) {
        checkState();
        return checkForVariable(variable,true).getVariableTrace(variable,observation);
    }

    @Override
    public VariableCalculation getCalculation(String variable) {
        DataProvider provider = checkForVariable(variable,true);
        return (provider instanceof CalculationProvider) ? ((CalculationProvider) provider).getCalculation(variable) : new VariableCalculation(variable);
    }

    @Override
    public VariableCalculation getResolvedCalculation(String variable) {
        DataProvider provider = checkForVariable(variable,true);
        return (provider instanceof CalculationProvider) ? ((CalculationProvider) provider).getResolvedCalculation(variable) : new VariableCalculation(variable);
    }

    @Override
    public boolean containsCalculatedVariables() {
        for (CalculationProvider cp : calculationProviders)
            if (cp.containsCalculatedVariables())
                return true;
        return false;
    }

    private class CompositeCalculationSubDataProvider extends LazySubDataProvider implements CalculationProvider {

        private CompositeCalculationSubDataProvider(int start, int end) {
            super(CompositeDataProvider.this,CompositeDataProvider.this.factory,start,end);
        }

        public VariableCalculation getCalculation(String variable) {
            return CompositeDataProvider.this.getCalculation(variable);
        }

        public VariableCalculation getResolvedCalculation(String variable) {
            return CompositeDataProvider.this.getResolvedCalculation(variable);
        }

        public boolean containsCalculatedVariables(){
            return CompositeDataProvider.this.containsCalculatedVariables();
        }
    }
}