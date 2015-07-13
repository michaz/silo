package com.pb.sawdust.tabledata.transform.row;

import com.pb.sawdust.tabledata.DataRow;
import com.pb.sawdust.tabledata.basic.WrappedDataRow;
import com.pb.sawdust.tabledata.transform.row.ContextDataRow;

/**
 * The {@code DegenerateContextDataRow} class provides a simple context data row which holds itself as a context. This class
 * can be useful for situations where a {@code ContextDataRow} is required, but where the context will never be used.
 *
 * @author crf
 *         Started 1/24/12 5:38 PM
 */
public class DegenerateContextDataRow extends WrappedDataRow implements ContextDataRow<DataRow> {

    /**
     * Constructor specifying the data row.
     *
     * @param row
     *        The data row.
     */
    public DegenerateContextDataRow(DataRow row) {
        super(row);
    }

    @Override
    /**
     * {@inheritDoc}
     *
     * @return this data row.
     */
    public DataRow getContext() {
        return this;
    }
}