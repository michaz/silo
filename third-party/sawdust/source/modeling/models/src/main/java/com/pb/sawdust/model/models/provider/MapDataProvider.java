package com.pb.sawdust.model.models.provider;

import com.pb.sawdust.tensor.factory.TensorFactory;
import com.pb.sawdust.util.array.ArrayUtil;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * The {@code MapDataProvider} is a protected class which forms the basis for a couple of data providers. It is a simple
 * data provider holding its data in the form of a mapping from variable name (String) to data (double[]). All of
 * the "get" data methods make defensive copies of the data, but no guarantees can be made about the actual data arrays
 * that instances of this class hold internally.
 *
 * @author crf <br/>
 *         Started Sep 19, 2010 8:53:16 PM
 */
abstract class MapDataProvider extends AbstractDataProvider {
    final Map<String,double[]> data;
    private final int dataLength;

    public MapDataProvider(int dataId, TensorFactory factory, int dataLength, Map<String,double[]> initialData) {
        super(dataId,factory);
        if (dataLength < 1)
            throw new IllegalArgumentException("Data length must be greater than 0: " + dataLength);
        this.dataLength = dataLength;
        data = getDataMap(initialData);
    }

    public MapDataProvider(TensorFactory factory, int dataLength, Map<String,double[]> initialData) {
        super(factory);
        if (dataLength < 1)
            throw new IllegalArgumentException("Data length must be greater than 0: " + dataLength);
        this.dataLength = dataLength;
        data = getDataMap(initialData);
    }

    abstract Map<String,double[]> getDataMap(Map<String,double[]> initialData);

    @Override
    public int getDataLength() {
        return dataLength;
    }

    @Override
    public double[] getVariableData(String variable) {
        if (!hasVariable(variable))
            throw new IllegalArgumentException("Variable not found: " + variable);
        return ArrayUtil.copyArray(data.get(variable)); //defensive copying -
    }

    @Override
    public boolean hasVariable(String variable) {
        return data.containsKey(variable);
    }

    @Override
    public Set<String> getVariables() {
        return data.keySet();
    }

    @Override
    public double[] getVariableData(String variable, int start, int end) {
        if (!hasVariable(variable))
            throw new IllegalArgumentException("Variable not found: " + variable);
        if (end <= start)
            throw new IllegalArgumentException("Subdata must have a strictly positive range (start=" + start + ", end=" + end + ")");
        if (end > getDataLength()  || start < 0)
            throw new IllegalArgumentException(String.format("Subdata (start: %d, end: %d) out of bounds for provider of length %d",start,end,getDataLength()));
        return Arrays.copyOfRange(data.get(variable),start,end);
    }
}