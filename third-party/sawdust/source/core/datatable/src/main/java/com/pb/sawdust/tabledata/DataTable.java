package com.pb.sawdust.tabledata;

import com.pb.sawdust.tabledata.metadata.TableSchema;
import com.pb.sawdust.tabledata.metadata.DataType;


/**
 * The {@code DataTable} interface provides a basic framework for a structure holding similarly structured
 * rows of data. Though this interface is meant to be fairly generic (to avoid restricting implementation
 * details), a few assumptions have been made.
 * <ul>
 *     <li>
 *         Each column in a table will have a specific {@link com.pb.sawdust.tabledata.metadata.DataType} associated with it, which,
 *         as expected, spedifies the type of data that column is to hold. In addition, each column will
 *         have a unique label (name), which will be comprised only of alphanumeric characters and "_" (no
 *         whitespace or special characters).
 *     </li>
 *     <li>
 *         Each row in a table will be comprised of the same number of columns, in the same order.
 *     </li>
 *     <li>
 *         The ordering of rows in the table at any given time will be deterministic, though this ordering is allowed
 *         to change - a single pre-defined ordering need not be guaranteed.
 *     </li>
 *     <li>
 *         All (ordinal) numbering is 0-based. That is, the first row or column in a table has a ordinal index
 *         of 0, the second an index of 1, etc.  This is set up so as to be consistent with Java's arrays and
 *         container classes. Also, allowing for or enforcing 1-based indexing tends to confuse developers, as
 *         they have to switch indexing systems depending on the context. The {@link DataTable#getColumnLabels()}
 *         provides an illustration of this problem: the indexes of the column names are 0-based (as this is a
 *         Java array), but using a 1-based indexing would force the user to add one to these indices to get
 *         the correct column index.
 *     </li>
 *     <li>
 *         Indices may be added to tables, which allow rows to be accessed by one or more column values, as
 *         opposed to row order numbers.
 *     </li>
 *     <li>
 *          A table will have one primary key column, which contains unique values and can be used to (uniquely) access
 *          data rows as opposed to row order numbers.  A key is actually just a type of index containing one column of
 *          unique values, which makes it more efficient than a generic index. If a primary key column is not specified,
 *          then the default/"natural" (0-based) row order numbers will be used. As a note, other key indices may be
 *          used with that table, but they will need to use "{@code index}" (as opposed to "{@code key}") methods.
 *     </li>
 *     <li>
 *         Every table will have an associated {@link com.pb.sawdust.tabledata.metadata.TableSchema}, which describes the metadata for
 *         this table. The schema contains the following information:
 *         <ul>
 *             <li>
 *                 The name, type, and ordinality of each table column.
 *             </li>
 *             <li>
 *                 The table's primary key column.
 *             </li>
 *             <li>
 *                 The relationships (if any) between this table and others (such as index keys).
 *             </li>
 *         </ul>
 *         This last item only has meaning if the other referenced data tables are accessible; such an
 *         accesiblity framework is provided through the {@link DataSet} inteface.
 *     </li>
 * </ul>
 * Though assumptions have been greatly influenced by database (SQL) standards, they are in no way meant to
 * force the use of database engines in actual implementations.
 * <p>
 * Since the {@code DataTable} interface extends {@code Iterable<DataRow>}, a data table is
 * considered to be naturally "row-based," as opposed to being "column-based." That is, iterating through
 * a data table means iterating through its rows, as opposed to its columns. Again, no explicit restrictions
 * are placed on implementations, but internal structures/engines which hold data in rows instead of columns
 * will probably make the {@code DataRow} iterator more efficient.  On the other hand, the {@code get...Column}
 * methods will probably be less efficient in a row-based structure than a column-based one.
 * <p>
 * If desired, data coersion can be enabled, which means that data passed to or from the table that is of a different
 * type than expected will be coerced (if possible) to the correct type. The rules for data coersion between primitive
 * (or equivalent) types is laid out in the {@link com.pb.sawdust.util.Caster} class. To cast to a {@code String},
 * the (boxed) object's {@code toString()} method is called; to cast from a {@code String}, the {@code parseXXX(String)}
 * method is used, where {@code XXX} is the data type.  By default, data coersion should be turned off.
 * <p>
 * Because each {@code DataTable} will have strengths and weaknesses (<i>e.g.</i> the above-mentioned difference
 * between column- and row-based data tables), switching among implementations should be expected. As such, each
 * {@code DataTable} implemenation should have a constructor which takes a {@code DataTable} as an argument and
 * essentially copies the source data table into an instance of the the implmentation; copying includes the actual
 * table data, the table schema, and the table's primary table key.
 * <p>
 * In addition, to ease loading table data from external sources, each {@code DataTable} implmentation should also have
 * a constructor which takes a {@link com.pb.sawdust.tabledata.read.TableReader} as an argument.
 *
 * @author crf <br>
 *         Started: May 6, 2008 8:52:07 PM
 */
public interface DataTable extends Iterable<DataRow>{

    /**
     * Get the label for this data table. Must be identical to {@code getSchema().getTableLabel()}.
     * 
     * @return the label for this table.
     */
    String getLabel();

    /**
     * Get the number of rows in this data table.
     *
     * @return the number of rows in this table.
     */
    int getRowCount();

    /**
     * Get the number of columns in this data table.
     *
     * @return the number of columns in this table.
     */
    int getColumnCount();

    /**
     * Get an array of the column labels, ordered as the columns are in this data table.
     *
     * @return this data table's column labels.
     */
    String[] getColumnLabels();

    /**
     * Determine whether a column is contained in this data table.
     *
     * @param columnLabel
     *        The label of the (potential) column.
     *
     * @return {@code true} if the table contains a column named {@code columnLabel}, {@code false} otherwise.
     */
    boolean hasColumn(String columnLabel);

    /**
     * Get the (0-based) index of the column with the specified column label.
     *
     * @param columnLabel
     *        The label of the column in question.
     *
     * @return the (0-based) index of the column with {@code label}.
     *
     * @throws TableDataException if no column with {@code label} exists in this data table.
     */
    int getColumnNumber(String columnLabel);

    /**
     * Get the label for the column with at the specified (0-based) index.
     *
     * @param columnNumber
     *        The (0-based) index of the column in question.
     *
     * @return the label of the column at {@code columnNumber}.
     *
     * @throws TableDataException if {@code columnNumber} is less than zero or greater than or equal to the number
     *                            of columns in this data table.
     */
    String getColumnLabel(int columnNumber);

    /**
     * Get an array of the column types, ordered as the columns are in this data table.
     *
     * @return this data table's column types.
     */
    DataType[] getColumnTypes();

    /**
     * Get the data type corresponding to the column with the specified label.
     *
     * @param columnLabel
     *        The label of the column in question.
     *
     * @return the data type of the column with {@code label}.
     *
     * @throws TableDataException if no column with {@code label} exists in this data table.
     */
    DataType getColumnDataType(String columnLabel);

    /**
     * Get the data type corresponding to the column at the specified (0-based) index.
     *
     * @param columnNumber
     *        The (0-based) index of the column in question.
     *
     * @return the data type of the column at {@code columnNumber}.
     *
     * @throws TableDataException if {@code columnNumber} is less than zero or greater than or equal to the number
     *                            of columns in this data table.
     */
    DataType getColumnDataType(int columnNumber);

    /**
     * Get the schema describing this data table.
     *
     * @return this data table's schema.
     */
    TableSchema getSchema();

    /**
     * Add a row of data to this data table. This will add the row to the end of the table, in its current ordering.
     * This method is optional, and need not be implemented.
     *
     * @param rowData
     *        The data for the row, its order (and count) corresponding to the column order (and count) in the data
     *        table.
     *
     * @return {@code true} if the row was added successfully, {@code false} otherwise.
     *
     *
     * @throws IllegalArgumentException if data coersion is enabled and any of the values in {@code rowData} cannot be coerced.
     * @throws UnsupportedOperationException if this method is not implemented.
     */
    boolean addRow(Object ... rowData);

    /**
     * Add a row of data to this data table. This will add the row to the end of the table, in its current ordering.
     * This method is optional, and need not be implemented.
     *
     * @param row
     *        The row to add. Consistency checking (column names, types, etc. between row and this data table) may
     *        not be performed.
     *
     * @return {@code true} if the row was added successfully, {@code false} otherwise.
     *
     * @throws IllegalArgumentException if data coersion is enabled and any of the values in {@code row} cannot be coerced.
     * @throws UnsupportedOperationException if this method is not implemented.
     */
    boolean addRow(DataRow row);

    /**
     * Add a column to this data table. This requires specifying the column label, type, as well as data values for
     * all existing rows in the table.  This will add the column to the end of the current column list. Successfully
     * adding the column will also update the table schema appropriately. This method should allow both object and
     * primitive arrays, so long as they match the column's data type's corresponding object/primitive class. This
     * method is optional, and need not be implemented.
     *
     * @param columnLabel
     *        The label for the new column.
     *
     * @param columnData
     *        The new column data for the existing rows in the data table. This must be a java array (this is
     *        left as {@code Object} to allow for primitive and object arrays), with one entry for each row, matching the
     *        current row ordering of the data table.
     *
     * @param type
     *        The data type of the new column.
     *
     * @return {@code true} if the column was added successfully, {@code false} otherwise.
     *
     * @throws IllegalArgumentException if {@code columnData} is not an array, or if data coersion is enabled and the
     *                                  values in {@code columnData} cannot be coerced.
     *
     * @throws TableDataException if {@code columnData} does not match {@code type}'s corresponding object/primitive class,
     *                            if a column with {@code columnLabel} already exists in this data table, or if the
     *                            length of the data column does not equal the number of rows in the data table
     * .
     * @throws UnsupportedOperationException if this method is not implemented.
     */
    boolean addColumn(String columnLabel, Object columnData, DataType type);

    /**
     * Add a column to this data table. This will add the column to the end of the current column list. Successfully
     * adding the column will also update the table schema appropriately. This method is optional, and need not be
     * implemented.
     *
     * @param column
     *        The column to add.
     *
     * @return {@code true} if the column was added successfully, {@code false} otherwise.
     *
     * @throws IllegalArgumentException if data coersion is enabled and the values in {@code column} cannot be coerced.
     *
     * @throws TableDataException if a column with the column's label already exists in this data table.
     * 
     * @throws UnsupportedOperationException if this method is not implemented.
     */
    boolean addColumn(DataColumn column);

    /**
     * Add column-based data to this data table. Column-based means the data is organized first into a series of
     * columns, and then into a series of row entries. Each {@code Object} in {@code data} is to be an array,
     * corresponding to a column in the data set, and whose component type is determined by the respective column
     * data type. This method should allow both object and primitive arrays, so long as they match the column's data
     * type's corresponding object/primitive class. This method is optional, and need not be implemented.
     *
     * @param data
     *        An array of arrays holding the new data by columns.
     *
     * @return {@code true} if the data was added successfully, {@code false} otherwise.
     *
     * @throws IllegalArgumentException if one or more of the elements in {@code data} is not an array, if the length
     *                                  of each array in in {@code data} is not the same, or if data coersion is enabled
     *                                  and any of the values in {@code data} cannot be coerced.
     *
     * @throws TableDataException if an array in {@code data} does not match the corresponding column's data type
     *                            object/primitive class, or if the length of {@code data} does not equal the number of
     *                            columns in the data.
     *
     * @throws UnsupportedOperationException if this method is not implemented.
     */
    boolean addDataByColumn(Object[] data);

    /**
     * Add row-based data to this data table Row-based means the data is organized first into a series of rows,
     * and then into a series of column entries. This method is optional, and need not be implemented.
     *
     * @param data
     *        An array of row-based data to add to this data table. Its first dimension is the row, and second
     *        dimension the column entries. Each row array must have a length equal to the number of columns in
     *        this data table.
     *
     * @return {@code true} if the data was added successfully, {@code false} otherwise.
     *
     * @throws IllegalArgumentException if data coersion is enabled and any of the values in {@code data} cannot be coerced.
     *
     * @throws UnsupportedOperationException if this method is not implemented.
     */
    boolean addDataByRow(Object[][] data);

    /**
     * Set the data in the specified row. A variety of input and data checking is performed, however, no checks are made
     * to ensure the the uniqueness of keys on the table are maintained. This method is optional, and need not be
     * implemented.
     *
     * @param rowNumber
     *        The row whose data will be set.
     *
     * @param values
     *        The new row values, their order (and count) corresponding to the column order (and count) in the data table.
     *
     * @return {@code true} if the row was updated succesfully, {@code false} otherwise.
     *
     * @throws IllegalArgumentException if data coersion is enabled and any of the values in {@code values} cannot be coerced.
     *
     * @throws TableDataException if the row number is less than 0 or greater than {@code (1-getRowCount()}, if the
     *                            number of {@code values} does not equal the number of columns in the table, or if the
     *                            values cannot be coerced into the respective data types of the columns.
     *
     * @throws UnsupportedOperationException if this method is not implemented.
     */
    boolean setRowData(int rowNumber, Object ... values);

    /**
     * Set the data in the specified row as referenced by the row's primary key value. A variety of input and data
     * checking is performed, however, no checks are made to ensure the the uniqueness of keys on the table are
     * maintained. This method is optional, and need not be implemented.
     *
     * @param key
     *        The primary key value corresponding to the row whose data will be set.
     *
     * @param values
     *        The new row values, their order (and count) corresponding to the column order (and count) in the data table.
     *
     * @return {@code true} if the row was updated succesfully, {@code false} otherwise.
     *
     * @throws IllegalArgumentException if data coersion is enabled and any of the values in {@code values} cannot be coerced.
     *
     * @throws TableDataException if the primary key value does not map to a row in the table, if the number of
     *                            {@code values} does not equal the number of columns in the table, or if the values
     *                            cannot be coerced into the respective data types of the columns.
     *
     * @throws UnsupportedOperationException if this method is not implemented.
     */
    <K> boolean setRowDataByKey(K key, Object ... values);

    /**
     * Set the data in the specified column. The input array is typed as an {@code Object} to allow for primitive arrays.
     * This method is optional, and need not be implemented.
     *
     * @param columnIndex
     *        The index of the column which will be updated.
     *
     * @param columnValues
     *        An array of data values to put in the column; they should be in the same order as the current table ordering.
     *
     * @return {@code true} if the column was updated succesfully, {@code false} otherwise.
     *
     * @throws IllegalArgumentException if {@code columnValues} is not an array, or if data coersion is enabled and the
     *                                  values in {@code columnValues} cannot be coerced.
     *
     * @throws TableDataException if the size of {@code columnValues} does not equal the number of rows in this table,
     *                            or if the array type does not match the data type of the column.
     *
     * @throws UnsupportedOperationException if this method is not implemented.
     */
    boolean setColumnData(int columnIndex, Object columnValues);

    /**
     * Set the data in the specified column. The input array is typed as an {@code Object} to allow for primitive arrays.
     * This method is optional, and need not be implemented.
     *
     * @param columnLabel
     *        The label of the column which will be updated.
     *
     * @param columnValues
     *        An array of data values to put in the column; they should be in the same order as the current table ordering.
     *
     * @return {@code true} if the column was updated succesfully, {@code false} otherwise.
     *
     * @throws IllegalArgumentException if {@code columnValues} is not an array, or if data coersion is enabled and any
     *                                  of the values in {@code columnValues} cannot be coerced.
     *
     * @throws TableDataException if the size of {@code columnValues} does not equal the number of rows in this table,
     *                            or if the array type does not match the data type of the column.
     *
     * @throws UnsupportedOperationException if this method is not implemented.
     */
    boolean setColumnData(String columnLabel, Object columnValues);

    /**
     * Set the value of a specified cell. A variety of input and data checking is performed, however, no checks are made
     * to ensure the the uniqueness of keys on the table are maintained. This method is optional, and need not be
     * implemented.
     *
     * @param rowNumber
     *        The row number of the cell to be set.
     *
     * @param columnIndex
     *        The column number of the cell to be set.
     *
     * @param value
     *        The new value for the cell.
     *
     * @return {@code true} if the cell value was set succesfully, {@code false} otherwise.
     *
     * @throws IllegalArgumentException if data coersion is enabled and {@code value} cannot be coerced.
     *
     * @throws TableDataException if the row number is less than 0 or greater than {@code (1-getRowCount()}, if the
     *                            column number is less than 0 or greater than {@code (1-getColumnCount()}, or
     *                            if the value cannot be coerced into the data type of the column.
     *
     * @throws UnsupportedOperationException if this method is not implemented.
     */
    boolean setCellValue(int rowNumber, int columnIndex, Object value);

    /**
     * Set the value of a specified cell. A variety of input and data checking is performed, however, no checks are made
     * to ensure the the uniqueness of keys on the table are maintained. This method is optional, and need not be
     * implemented.
     *
     * @param rowNumber
     *        The row number of the cell to be set.
     *
     * @param columnLabel
     *        The column label of the cell to be set.
     *
     * @param value
     *        The new value for the cell.
     *
     * @return {@code true} if the cell value was set succesfully, {@code false} otherwise.
     *
     * @throws IllegalArgumentException if data coersion is enabled and {@code value} cannot be coerced.
     *
     * @throws TableDataException if the row number is less than 0 or greater than {@code (1-getRowCount()}, if the
     *                            column label does not exist in the table, or if the value cannot be coerced into the
     *                            data type of the column.
     *
     * @throws UnsupportedOperationException if this method is not implemented.
     */
    boolean setCellValue(int rowNumber, String columnLabel, Object value);

    /**
     * Set the value of a specified cell identified by a primary key value. A variety of input and data checking is
     * performed, however, no checks are made to ensure the the uniqueness of keys on the table are maintained. This
     * method is optional, and need not be implemented.
     *
     * @param key
     *        The primary key value corresponding to the row whose data will be set.
     *
     * @param columnIndex
     *        The column number of the cell to be set.
     *
     * @param value
     *        The new value for the cell.
     *
     * @return {@code true} if the cell value was set succesfully, {@code false} otherwise.
     *
     * @throws IllegalArgumentException if data coersion is enabled and {@code value} cannot be coerced.
     *
     * @throws TableDataException if the primary key value does not map to a row in the table, if the column number is
     *                            less than 0 or greater than {@code (1-getColumnCount()}, or if the value cannot be
     *                            coerced into the data type of the column.
     *
     * @throws UnsupportedOperationException if this method is not implemented.
     */
    <K> boolean setCellValueByKey(K key, int columnIndex, Object value);

    /**
     * Set the value of a specified cell identified by a primary key value. A variety of input and data checking is
     * performed, however, no checks are made to ensure the the uniqueness of keys on the table are maintained. This
     * method is optional, and need not be implemented.
     *
     * @param key
     *        The primary key value corresponding to the row whose data will be set.
     *
     * @param columnLabel
     *        The column label of the cell to be set.
     *
     * @param value
     *        The new value for the cell.
     *
     * @return {@code true} if the cell value was set succesfully, {@code false} otherwise.
     *
     * @throws IllegalArgumentException if data coersion is enabled and {@code value} cannot be coerced.
     *
     * @throws TableDataException if the primary key value does not map to a row in the table, if the column label does
     *                            not exist in the table, or if the value cannot be coerced into the data type of the column.
     *
     * @throws UnsupportedOperationException if this method is not implemented.
     */
    <K> boolean setCellValueByKey(K key, String columnLabel, Object value);

    /**
     * Delete a row in this data table. All rows subsequent to the deleted one will have their row number decreased by
     * one.
     *
     * @param rowNumber
     *        The (0-based) number of the row to delete.
     *
     * @return the deleted data row.
     *
     * @throws TableDataException if {@code rowNumber} is less than zero or greater than or equal to the number of rows
     *                            in this data table.
     */
    DataRow deleteRow(int rowNumber);

    /**
     * Delete a row in this data table referenced by its primary key value. All rows subsequent to the deleted one will
     * have their row number decreased by one.
     *
     * @param key
     *        The primary key value of the row to delete.
     *
     * @return the deleted data row.
     *
     * @throws TableDataException if {@code key} does not correspond to any row in the table.
     */
    <K> DataRow deleteRowByKey(K key);

    /**
     * Delete a column from this data table. All columns subsequent to the deleted one will have their row number decreased 
     * by one. This method will also modify the table's schema accordingly.
     *
     * @param columnNumber
     *        The (0-based) index of the column to delete.
     *
     * @return the deleted column.
     *
     * @throws TableDataException if {@code columnNumber} is less than zero or greater than or equal to the number of
     *                            columns in this data table.
     */
    <T> DataColumn<T> deleteColumn(int columnNumber);

    /**
     * Delete a column from this data table. All columns subsequent to the deleted one will have their row number decreased
     * by one. This method will also modify the table's schema accordingly.
     *
     * @param columnLabel
     *        The label of the column to delete.
     *
     * @return the deleted column.
     *
     * @throws TableDataException if a column with label {@code columnLabel} does not exist in the data table .
     */
    <T> DataColumn<T> deleteColumn(String columnLabel);

    /**
     * Set the primary key for this data table. If successful, this will replace the current primary key specified
     * on the table. Because there is no technical restriction that the specified key has been built on (or refers to)
     * this data table, it is preferable to use the {@link #setPrimaryKey(String)} method to set the table's primary key.
     * This method is intending for primary key switching convenience and efficiency and, as such, the key index
     * <i>will not</i> be rebuilt when this method is called.
     *
     * @param primaryKey
     *        The key to set as this table's primary key.
     *
     * @return {@code true} if the key was set successfully, {@code false} otherwise.
     */
    <K> boolean setPrimaryKey(TableKey<K> primaryKey);

    /**
     * Set the primary key for this data table by specifying the key column. If successful, this will replace the
     * current primary key specified on the table. If the specified column is not found in the table, then this method
     * will return {@code false}.
     *
     * @param columnLabel
     *        The column to build the key on.
     *
     * @return {@code true} if the key was set successfully, {@code false} otherwise.
     *
     * @throws TableDataException if the specified column does not contain unique values.
     */
    boolean setPrimaryKey(String columnLabel);

    /**
     * Get the {@code TableKey} object corresponding to this data table's primary key. If no primary key has been
     * explicitly set, then the returned object will correspond to the default (0-based) row order numbers.
     *
     * @return this table's primary key.
     */
    <K> TableKey<K> getPrimaryKey();

    /**
     * Get a cell in this data table.
     *
     * @param rowIndex
     *        The (0-based) index of the row containing the cell.
     *
     * @param columnIndex
     *        The (0-based) index of the column containing the cell.
     *
     * @return the cell value corresponding to {@code rowIndex} and {@code columnIndex}.
     *
     * @throws TableDataException if {@code rowIndex}/{@code columnIndex} is less than zero or greater than or equal to the number
     *                            of rows/columns (respectively) in this table.
     */
    Object getCellValue(int rowIndex, int columnIndex);

    /**
     * Get a cell in this data table.
     *
     * @param rowIndex
     *        The (0-based) index of the row the containing the cell.
     *
     * @param columnLabel
     *        The label of the column the containing the cell.
     *
     * @return the cell value corresponding to {@code rowIndex} and {@code columnIndex}.
     *
     * @throws TableDataException if {@code rowIndex} is less than zero or greater than or equal to the number of rows
     *                            in this table, or if {@code columnLabel} does not correspond to any column in this table.
     */
    Object getCellValue(int rowIndex, String columnLabel);

    /**
     * Get a cell in this data table referenced by the table's primary key.
     *
     * @param key
     *        The key value corresponding to the row containing the cell.
     *
     * @param columnIndex
     *        The (0-based) index of the column containing the cell.
     *
     * @return the cell value corresponding to key {@code key} and {@code columnIndex}.
     *
     * @throws TableDataException if {@code columnIndex} is less than zero or greater than or equal to the number of columns
     *                            in this table, or if {@code key} does not correspond to any row in this table.
     */
    <K> Object getCellValueByKey(K key, int columnIndex);

    /**
     * Get a cell in this data table referenced by the table's primary key.
     *
     * @param key
     *        The key value corresponding to the row containing the cell.
     *
     * @param columnLabel
     *        The label of the column the containing the cell.
     *
     * @return the cell value corresponding to key {@code key} and {@code columnIndex}.
     *
     * @throws TableDataException if {@code columnLabel} does not correspond to any column in this table, or if {@code key}
     *                            does not correspond to any row in this table.
     */
    <K> Object getCellValueByKey(K key, String columnLabel);


    /**
     * Get a row of data, ordinally indexed by the current row ordering
     *
     * @param rowIndex
     *        The ordinal number (0-based) of the row to return.
     *
     * @return the row corresonding to index {@code row}.
     *
     * @throws TableDataException if {@code rowIndex} is less than 0 or greater than or equal to the number of rows
     *                            in this table.
     */
    DataRow getRow(int rowIndex);

    /**
     * Get a row of data corresponding to a given key in the table's primary key index. If no primary key column has
     * been specified, then the primary key used will be the default (0-based) row order numbers (essentially an
     * alias for {@link #getRow(int)}).
     *
     * @param key
     *        The primary key value for the row of interest.
     *
     * @return the data row corresponding to {@code key}.
     *
     * @throws TableDataException if the {@code key} does not exist in the table's primary key column.
     */
    <K> DataRow getRowByKey(K key);

    /**
     * Get a data table containing rows of data corresponding to a specified index value. Because this is intended
     * to be a view into data rows, the returned data table should be optimized for iterating through the data rows
     * and accessing data by row; other data table operations will probably perform less than optimally.
     *
     * @param index
     *        The index to be used in the data row retrieval.
     *
     * @param indexValues
     *        The index values corresponding to the row(s) of interest
     *
     * @return a data table containing the rows corresponding to {@code indexValues} on {@code index}.
     */
    @SuppressWarnings({"unchecked", "varargs"})
    <K> DataTable getIndexedRows(TableIndex<K> index, K ... indexValues);

    /**
     * Get the column of data corresponding to a given ordinal index as booleans.
     *
     * @param columnIndex
     *        The ordinal index (0-based) of the column to return.
     *
     * @return the column corresponding to index {@code columnIndex}.
     *
     * @throws IllegalArgumentException if data coersion is enabled and the column values cannot be coerced to {@code boolean}s.
     */
    BooleanDataColumn getBooleanColumn(int columnIndex);

    /**
     * Get the column of data corresponding to a given label as booleans.
     *
     * @param columnLabel
     *        The label of the column to return.
     *
     * @return the column corresponding to label {@code columnLabel}.
     *
     * @throws IllegalArgumentException if data coersion is enabled and the column values cannot be coerced to {@code boolean}s.
     */
    BooleanDataColumn getBooleanColumn(String columnLabel);

    /**
     * Get the column of data corresponding to a given ordinal index as bytes.
     *
     * @param columnIndex
     *        The ordinal index (0-based) of the column to return.
     *
     * @return the column corresponding to index {@code columnIndex}.
     *
     * @throws IllegalArgumentException if data coersion is enabled and the column values cannot be coerced to {@code byte}s.
     */
    ByteDataColumn getByteColumn(int columnIndex);

    /**
     * Get the column of data corresponding to a given label as bytes.
     *
     * @param columnLabel
     *        The label of the column to return.
     *
     * @return the column corresponding to label {@code columnLabel}.
     *
     * @throws IllegalArgumentException if data coersion is enabled and the column values cannot be coerced to {@code byte}s.
     */
    ByteDataColumn getByteColumn(String columnLabel);

    /**
     * Get the column of data corresponding to a given ordinal index as shorts.
     *
     * @param columnIndex
     *        The ordinal index (0-based) of the column to return.
     *
     * @return the column corresponding to index {@code columnIndex}.
     *
     * @throws IllegalArgumentException if data coersion is enabled and the column values cannot be coerced to {@code short}s.
     */
    ShortDataColumn getShortColumn(int columnIndex);

    /**
     * Get the column of data corresponding to a given label as shorts.
     *
     * @param columnLabel
     *        The label of the column to return.
     *
     * @return the column corresponding to label {@code columnLabel}.
     *
     * @throws IllegalArgumentException if data coersion is enabled and the column values cannot be coerced to {@code short}s.
     */
    ShortDataColumn getShortColumn(String columnLabel);

    /**
     * Get the column of data corresponding to a given ordinal index as integers.
     *
     * @param columnIndex
     *        The ordinal index (0-based) of the column to return.
     *
     * @return the column corresponding to index {@code columnIndex}.
     *
     * @throws IllegalArgumentException if data coersion is enabled and the column values cannot be coerced to {@code int}s.
     */
    IntegerDataColumn getIntColumn(int columnIndex);

    /**
     * Get the column of data corresponding to a given label as integers.
     *
     * @param columnLabel
     *        The label of the column to return.
     *
     * @return the column corresponding to label {@code columnLabel}.
     *
     * @throws IllegalArgumentException if data coersion is enabled and the column values cannot be coerced to {@code int}s.
     */
    IntegerDataColumn getIntColumn(String columnLabel);

    /**
     * Get the column of data corresponding to a given ordinal index as longs.
     *
     * @param columnIndex
     *        The ordinal index (0-based) of the column to return.
     *
     * @return the column corresponding to index {@code columnIndex}.
     *
     * @throws IllegalArgumentException if data coersion is enabled and the column values cannot be coerced to {@code long}s.
     */
    LongDataColumn getLongColumn(int columnIndex);

    /**
     * Get the column of data corresponding to a given label as longs.
     *
     * @param columnLabel
     *        The label of the column to return.
     *
     * @return the column corresponding to label {@code columnLabel}.
     *
     * @throws IllegalArgumentException if data coersion is enabled and the column values cannot be coerced to {@code long}s.
     */
    LongDataColumn getLongColumn(String columnLabel);

    /**
     * Get the column of data corresponding to a given ordinal index as floats.
     *
     * @param columnIndex
     *        The ordinal index (0-based) of the column to return.
     *
     * @return the column corresponding to index {@code columnIndex}.
     *
     * @throws IllegalArgumentException if data coersion is enabled and the column values cannot be coerced to {@code float}s.
     */
    FloatDataColumn getFloatColumn(int columnIndex);

    /**
     * Get the column of data corresponding to a given label as floats.
     *
     * @param columnLabel
     *        The label of the column to return.
     *
     * @return the column corresponding to label {@code columnLabel}.
     *
     * @throws IllegalArgumentException if data coersion is enabled and the column values cannot be coerced to {@code float}s.
     */
    FloatDataColumn getFloatColumn(String columnLabel);

    /**
     * Get the column of data corresponding to a given ordinal index as doubles.
     *
     * @param columnIndex
     *        The ordinal index (0-based) of the column to return.
     *
     * @return the column corresponding to index {@code columnIndex}.
     *
     * @throws IllegalArgumentException if data coersion is enabled and the column values cannot be coerced to {@code double}s.
     */
    DoubleDataColumn getDoubleColumn(int columnIndex);

    /**
     * Get the column of data corresponding to a given label as doubles.
     *
     * @param columnLabel
     *        The label of the column to return.
     *
     * @return the column corresponding to label {@code columnLabel}.
     *
     * @throws IllegalArgumentException if data coersion is enabled and the column values cannot be coerced to {@code double}s.
     */
    DoubleDataColumn getDoubleColumn(String columnLabel);

    /**
     * Get the column of data corresponding to a given ordinal index as strings.
     *
     * @param columnIndex
     *        The ordinal index (0-based) of the column to return.
     *
     * @return the column corresponding to index {@code columnIndex}.
     */
    StringDataColumn getStringColumn(int columnIndex);

    /**
     * Get the column of data corresponding to a given label as strings.
     *
     * @param columnLabel
     *        The label of the column to return.
     *
     * @return the column corresponding to label {@code columnLabel}.
     */
    StringDataColumn getStringColumn(String columnLabel);

    /**
     * Get the column of data corresponding to a given ordinal index. Though generified, this method offers much less
     * type safety than the {@code get*Column} methods which explicitly state the column type, and should only be used
     * when the type of the data is not important.
     *
     * @param columnIndex
     *        The ordinal index (0-based) of the column to return.
     *
     * @return the column corresponding to index {@code columnIndex}.
     */
    <T> DataColumn<T> getColumn(int columnIndex);

    /**
     * Get the column of data corresponding to a given label. Though generified, this method offers much less
     * type safety than the {@code get*Column} methods which explicitly state the column type, and should only be used
     * when the type of the data is not important.
     *
     * @param columnLabel
     *        The label of the column to return.
     *
     * @return the column corresponding to index {@code columnIndex}.
     */
    <T> DataColumn<T> getColumn(String columnLabel);

    /**
     * Sets whether this data table will attempt to coerce data to the correct types when setting or getting.
     *
     * @param coersionOn
     *        If {@code true}, then data will be coerced, otherwise it will not.
     */
    void setDataCoersion(boolean coersionOn);

    /**
     * Indicates whether this data table will attempt to coerce data to the correct types when setting or getting.
     *
     * @return {@code true} if data will be coerced, {@code false} otherwise.
     */
    boolean willCoerceData();

    /**
     * Get a view of this table corresponding to a range of rows.  The start point of the partition is inclusive, and
     * the end point is exclusive.  The returned {@code DataTable} should be unmodifiable (it cannot itself be modified,
     * nor can it modify the underlying table).  There are no specifications as to whether the returned table is a
     * "snapshot" at the time of the method call, or if it is a dynamic view that will change as the underlying table
     * does.  Because of this, this method should generally be reserved for fixed (unchanging) data tables.
     *
     * @param start
     *        The (0-based) row number to start the partition at.
     *
     * @param end
     *        The (0-based) row number to end the partition at (exclusive).
     *
     * @return a view of this table from row {@code start} up to row {@code end}.
     *
     * @throws IllegalArgumentException if {@code start} or {@code end} are less than zero, greater than
     *                                  the number of rows in the underlying table, or if <code>start >= end</code>.
     *
     */
    DataTable getTablePartition(int start, int end);
}