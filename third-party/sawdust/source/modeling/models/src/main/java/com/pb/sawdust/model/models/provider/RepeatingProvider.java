package com.pb.sawdust.model.models.provider;

import com.pb.sawdust.tensor.factory.TensorFactory;
import static com.pb.sawdust.util.Range.*;

import java.util.Set;

/**
 * The {@code RepeatingProvider} ...
 *
 * @author crf
 *         Started 1/23/13 4:06 PM
 */
public class RepeatingProvider extends AbstractDataProvider {
    private final DataProvider baseProvider;
    private final int repeats;

    public RepeatingProvider(TensorFactory factory, DataProvider baseProvider, int repeats) {
        super(factory);
        if (repeats < 1)
            throw new IllegalArgumentException("RepeatingProvider must have at least 1 repeat: " + repeats);
        this.repeats = repeats;
        this.baseProvider = baseProvider;
    }

    public RepeatingProvider(int id, TensorFactory factory, DataProvider baseProvider, int repeats) {
        super(id,factory);
        if (repeats < 1)
            throw new IllegalArgumentException("RepeatingProvider must have at least 1 repeat: " + repeats);
        this.repeats = repeats;
        this.baseProvider = baseProvider;
    }

    @Override
    public int getDataLength() {
        return baseProvider.getDataLength()*repeats;
    }

    @Override
    public boolean hasVariable(String variable) {
        return baseProvider.hasVariable(variable);
    }

    @Override
    public Set<String> getVariables() {
        return baseProvider.getVariables();
    }

    @Override
    public double[] getVariableData(String variable) {
        double[] data = new double[getDataLength()];
        double[] baseData = baseProvider.getVariableData(variable);
        int length = baseData.length;
        for (int i : range(repeats))
            System.arraycopy(baseData,0,data,length*i,length);
        return data;
    }

    @Override
    public double[] getVariableData(String variable, int start, int end) {
        if (end <= start)
            throw new IllegalArgumentException("Data must have a strictly positive range (start=" + start + ", end=" + end + ")");
        if (end > getDataLength()  || start < 0)
            throw new IllegalArgumentException(String.format("Data (start: %d, end: %d) out of bounds for provider of length %d",start,end,getDataLength()));
        int length = end-start;
        double[] baseData = baseProvider.getVariableData(variable);
        double[] data = new double[length];

        int basePoint = start - (start % length)*length;
        int distance = ((start % length) + 1)*length - start; //next largest repeat value above start, subtract off to find difference
        System.arraycopy(baseData,basePoint,data,0,distance);
        int currentDataPoint = 0;
        while ((currentDataPoint + length) < end) {
            System.arraycopy(baseData,0,data,currentDataPoint,length);
            currentDataPoint += length;
        }
        //finish up the remainder
        System.arraycopy(baseData,basePoint,data,currentDataPoint,end-currentDataPoint);

        return data;
    }
}