package com.pb.sawdust.model.models.provider.filter;

import com.pb.sawdust.model.models.provider.DataProvider;
import com.pb.sawdust.tensor.alias.vector.primitive.DoubleVector;
import com.pb.sawdust.tensor.factory.TensorFactory;

/**
 * The {@code FixedDoubleDataFilter} ...
 *
 * @author crf <br/>
 *         Started 3/8/11 6:57 AM
 */
public class FixedDoubleDataFilter extends FixedDataFilter {
    public FixedDoubleDataFilter(TensorFactory factory, double[] filter) {
        super(DoubleDataFilter.getFilter(factory,filter));
    }

    public FixedDoubleDataFilter(TensorFactory factory, DoubleVector filter) {
        this(factory,(double[]) filter.getTensorValues().getArray());
    }

    public FixedDoubleDataFilter(TensorFactory factory, DataProvider provider, String variable) {
        this(factory,provider.getVariableData(variable));
    }
}