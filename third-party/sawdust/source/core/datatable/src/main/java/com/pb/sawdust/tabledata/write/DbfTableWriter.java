package com.pb.sawdust.tabledata.write;

import com.pb.sawdust.tabledata.DataTable;
import com.pb.sawdust.tabledata.TableDataException;
import com.pb.sawdust.tabledata.DataRow;
import com.pb.sawdust.tabledata.metadata.DataType;
import com.pb.sawdust.util.exceptions.RuntimeIOException;
import static com.pb.sawdust.util.Range.*;
import com.linuxense.javadbf.DBFWriter;
import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;

import java.util.Map;
import java.util.HashMap;
import java.io.File;

/**
 * The {@code DbfTableWriter} class provides a {@code TableWriter} implementation for writing data tables to the
 * dBase (dbf) format.
 * 
 * @author crf <br/>
 *         Started: Nov 1, 2008 3:10:54 PM
 */
public class DbfTableWriter extends FileTableWriter {

    /**
     * Constructor specifying the path to the output file.
     *
     * @param filePath
     *        The path to the file to which the data table will be written.
     *
     * @throws com.pb.sawdust.util.exceptions.RuntimeIOException if {@code file} is not found or cannot be created.
     */
    public DbfTableWriter(String filePath) {
        super(filePath);
    }

    /**
     * Cosntructor specifying the file to which the table data will be written.
     *
     * @param file
     *        The file to which the data table will be written.
     *
     * @throws com.pb.sawdust.util.exceptions.RuntimeIOException if {@code file} is not found or cannot be created.
     */
    public DbfTableWriter(File file) {
        super(file);
    }

    public void writeTable(DataTable table) {
        if (tableFile.exists())
            if (!tableFile.delete())
                throw new TableDataException("Cannot overwrite file: " + tableFile.getPath());
        try {
            DBFWriter writer = new DBFWriter(tableFile);
            DBFField[] fields = new DBFField[table.getColumnCount()];
            Map<Integer,Integer> stringColumnWidths = new HashMap<Integer,Integer>();
            for (int i : range(fields.length))
                if ((fields[i] = getDbfField(table.getColumnLabel(i),table.getColumnDataType(i))).getDataType() == DBFField.FIELD_TYPE_C)
                    stringColumnWidths.put(i,0);
            int dataSize;
            if (stringColumnWidths.size() > 0)
                for (DataRow row : table)
                    for (int column : stringColumnWidths.keySet())
                        if (stringColumnWidths.get(column) < (dataSize = row.getCellAsString(column).length()))
                            stringColumnWidths.put(column,dataSize);
            for (int column : stringColumnWidths.keySet())
                fields[column].setFieldLength(stringColumnWidths.get(column));
            writer.setFields(fields);
            for (DataRow row : table)
                writer.addRecord(row.getData());
            writer.write();
        } catch (DBFException e) {
            throw new RuntimeIOException(e);
        }
    }

    @SuppressWarnings("fallthrough") //fallthroughs are intended here
    private DBFField getDbfField(String columnName, DataType type) {
        DBFField field = new DBFField();
        field.setName(columnName);
        byte fieldType = -1;
        int fieldLength = 0;
        int decimalCount = -1;
        switch (type) {
            case BOOLEAN:
                fieldType = DBFField.FIELD_TYPE_L;
                fieldLength = 1;
                break;
            case BYTE:
                fieldLength = 4;
            case SHORT:
                if (fieldLength == 0)
                    fieldLength = 6;
            case INT:
                if (fieldLength == 0)
                    fieldLength = 11;
            case LONG:
                if (fieldLength == 0)
                    fieldLength = 20;
                fieldType = DBFField.FIELD_TYPE_N;
                decimalCount = 0;
                break;
            case DOUBLE:
            case FLOAT:
                fieldLength = 20;
                fieldType = DBFField.FIELD_TYPE_F;
                decimalCount = 3;
                break;
            case STRING:
                fieldLength = 30;
                fieldType = DBFField.FIELD_TYPE_C;
                break;
        }
        field.setDataType(fieldType);
        field.setFieldLength(fieldLength);
        if (decimalCount > -1)
            field.setDecimalCount(decimalCount);
        return field;
    }
}