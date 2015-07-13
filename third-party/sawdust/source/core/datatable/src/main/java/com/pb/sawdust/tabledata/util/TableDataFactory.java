package com.pb.sawdust.tabledata.util;

import com.pb.sawdust.tabledata.basic.*;
import com.pb.sawdust.tabledata.metadata.TableSchema;
import com.pb.sawdust.tabledata.metadata.DataType;
import com.pb.sawdust.tabledata.*;

/**
 * <p>
 * The {@code TableDataFactory} provides a series of static factory methods through which implementations of
 * {@code TableData} interfaces can be accessed. The intentions of these methods is to make the implementation
 * "invisible" to the user; as such, these should be used to decouple classes from specific implementations and
 * no implementation-specific details (such as efficiencies and limitations) should be expected nor taken for granted.
 * </p>
 * @author crf <br/>
 *         Started: May 28, 2008 10:00:41 AM
 */
public class TableDataFactory {

    /**
     * Get a {@code DataColumn} instance. This method is parametrized by the data column's type and its key type.
     *
     * @param columnData
     *        The column's data.
     *
     * @param columnLabel
     *        The column's label.
     *
     * @param type
     *        The column's data type.
     *
     * @param primaryKey
     *        The primary key to use for the column.
     *
     * @return a new {@code DataColumn} instance.
     */
    public static <T,K> DataColumn<T> getDataColumn(T[] columnData, String columnLabel, DataType type, TableKey<K> primaryKey) {
        return new BasicDataColumn<T>(columnLabel,columnData,type,primaryKey);
    }

    /**
     * Get a {@code DataRow} instance.
     *
     * @param rowData
     *        The row's data.
     *
     * @param columnLabels
     *        The labels for each column in this data row, ordered as they are in the row.
     *
     * @param columnTypes
     *        The data types of each column in this data row, ordered as they are in the row.
     *
     * @param coercionOn
     *        Indicator for whether this data row will coerce data or not.
     *
     * @return a new {@code DataRow} instance.
     */
    public static DataRow getDataRow(Object[] rowData, String[] columnLabels, DataType[] columnTypes, boolean coercionOn) {
        return new BasicDataRow(rowData,columnLabels,columnTypes,coercionOn);
    }

    /**
     * Get an empty (no data) {@code DataTable} instance..
     *
     * @param schema
     *        A schema describing the table.
     *
     * @return a new {@code DataTable} instance.
     */
    public static DataTable getDataTable(TableSchema schema) {
        return new ListDataTable(schema);
    }

    /**
     * Get a {@code DataTable} instance filled with data.
     *
     * @param schema
     *        A schema describing the table.
     *
     * @param data
     *        The data to fill the table with. This data should be organized as required by the
     *        {@link DataTable#addDataByRow(Object[][])} method - its first dimension is the row, and second
     *        dimension the column entries.
     *
     * @return a new {@code DataTable} instance filled with {@code data}.
     */
    public static DataTable getDataTable(TableSchema schema, Object[][] data) {
        DataTable table = getDataTable(schema);
        table.addDataByRow(data);
        return table;
    }

    /**
     * Get a {@code DataSet} instance.
     *
     * @return a new {@code DataSet} instance.
     */
    public static DataSet<DataTable> getDataSet() {
        return new BasicDataSet();
    }

    /**
     * Get a {@code TableKey} instance.
     *
     * @param table
     *        The table against which the key is to be built.
     *
     * @param columnLabel
     *        The label for the column to build the key on.
     *
     * @param <T>
     *        The type of the table key.
     *
     * @return a new {@code TableKey} instance.
     */
    public static <T> TableKey<T> getTableKey(DataTable table, String columnLabel) {
        return new BasicTableKey<T>(table,columnLabel);
    }

}