package com.pb.sawdust.model.integration.transcad;

import com.pb.sawdust.io.ByteOrderDataOutputStream;
import com.pb.sawdust.tabledata.metadata.ColumnSchema;
import static com.pb.sawdust.util.Range.*;

import com.pb.sawdust.tabledata.metadata.TableSchema;
import com.pb.sawdust.util.array.ArrayUtil;
import com.pb.sawdust.util.exceptions.RuntimeIOException;
import com.pb.sawdust.tabledata.DataRow;
import com.pb.sawdust.tabledata.DataTable;
import com.pb.sawdust.tabledata.metadata.DataType;
import com.pb.sawdust.tabledata.write.FileTableWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * The {@code TranscadBinaryTableWriter} class is a data table writer for Transcad fixed-format binary table files.
 *
 * @author crf <br/>
 *         Started Nov 2, 2010 4:04:32 PM
 */
public class TranscadBinaryTableWriter extends FileTableWriter {
    private final String outputFileBase;
    private final TranscadBinaryDataConverter converter;
    private final TranscadTableDictionary dictionaryHints;

    /**
     * Constructor specifying the base output file and a template dictionary.  The file base is the path to the table file, 
     * minus the file extension, and is used to build the binary and dictionary file paths. When the table is written out,
     * the information from the template dictionary hints will be used (where possible) for any fields with the same label.
     *
     * @param outputFileBase
     *        The base file path for the output file.
     *        
     * @param dictionaryHints
     *        The dictionary to use as a template for this writer.
     */
    public TranscadBinaryTableWriter(String outputFileBase, TranscadTableDictionary dictionaryHints) {
        super(outputFileBase + "." + TranscadBinaryTableReader.TRANSCAD_MATRIX_BINARY_FILE_EXTENSION);
        this.outputFileBase = outputFileBase;
        converter = new TranscadBinaryDataConverter();
        this.dictionaryHints = dictionaryHints;
    }                                                     

    /**
     * Constructor specifying the base output file.  The file base is the path to the table file, minus the
     * file extension, and is used to build the binary and dictionary file paths.
     *
     * @param outputFileBase
     *        The base file path for the output file.
     */
    public TranscadBinaryTableWriter(String outputFileBase) {
        this(outputFileBase,null);
    }

    /**
     * Get the data converter used by this reader. The converter returned by this method can be modified before writing
     * the table to modify the conversion behavior.
     *
     * @return the data converter used by this writer.
     */
    public TranscadBinaryDataConverter getConverter() {
        return converter;
    }
    
    private int[] getWidthsForStringFields(DataTable table) {
        int[] widths = new int[table.getColumnCount()];
        Arrays.fill(widths,-1);
        List<Integer> fields = new LinkedList<>();
        int counter = 0;
        for (ColumnSchema schema : table.getSchema()) {
            if (schema.getType() == DataType.STRING)
                fields.add(counter);
            counter++;
        }
        int[] cols = ArrayUtil.toIntArray(fields);
        for (DataRow row : table) 
            for (int col : cols)
                widths[col] = Math.max(widths[col],row.getCellAsString(col).length());
        return widths;
    }
    
    private void applyDictionaryHints(TranscadTableDictionary dictionary) {
        TableSchema baseSchema = dictionary.getSchema();
        TableSchema hintSchema = dictionaryHints.getSchema();
        
        for (String column : hintSchema.getColumnLabels()) {
            if (baseSchema.hasColumn(column)) {
                if (hintSchema.getColumnTypes()[hintSchema.getColumnIndex(column)] == baseSchema.getColumnTypes()[baseSchema.getColumnIndex(column)]) {
                    TranscadTableDictionary.ColumnExtraInformation baseCei = dictionary.getColumnExtraInformation().get(baseSchema.getColumnIndex(column));
                    TranscadTableDictionary.ColumnExtraInformation hintCei = dictionaryHints.getColumnExtraInformation().get(hintSchema.getColumnIndex(column));
                    baseCei.setDisplayWidth(hintCei.getDisplayWidth());
                    baseCei.setWidth(hintCei.getWidth());
                    baseCei.setSplitJoinMethod(hintCei.getSplitJoinMethod());
                    baseCei.setAggregationMethod(hintCei.getAggregationMethod());
                    baseCei.setDecimals(hintCei.getDecimals());
                    baseCei.setDefaultValue(hintCei.getDefaultValue());
                    baseCei.setDescription(hintCei.getDescription());
                    baseCei.setDisplayDecimals(hintCei.getDisplayDecimals());
                    baseCei.setFormat(hintCei.getFormat());
                    baseCei.setDisplayName(hintCei.getDisplayName());
                }
            }
        }
    }

    @Override
    public void writeTable(DataTable table) {
        TranscadTableDictionary dict = new TranscadTableDictionary(table.getSchema(), TranscadTableDictionary.TableType.FFB);
        if (dictionaryHints != null)
            applyDictionaryHints(dict);
        int[] stringColWidths = getWidthsForStringFields(table);
        List<TranscadTableDictionary.ColumnExtraInformation> ceis = dict.getColumnExtraInformation();
        for (int i : range(stringColWidths.length)) {
            int width = stringColWidths[i];
            if (width > -1 && width > ceis.get(i).getWidth()) {
                ceis.get(i).setWidth(width);
                ceis.get(i).setDisplayWidth(width);
            }
        }
        dict.writeDictionary(outputFileBase);
        TranscadBinaryDataConverter converter = new TranscadBinaryDataConverter();
        ByteOrderDataOutputStream writer = null;
        try {
            writer = converter.getBinaryWriter(tableFile);
            DataType[] types = table.getColumnTypes();
            int[] widths = new int[table.getColumnLabels().length];
            int counter = 0;
            for (TranscadTableDictionary.ColumnExtraInformation cei : dict.getColumnExtraInformation())
                widths[counter++] = cei.getWidth(); //for string information
            for (DataRow row : table) {
                Object[] data = row.getData();
                for (int i = 0; i < data.length; i++) {
                    converter.writeValue(data[i],types[i],writer,widths[i]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeIOException(e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (RuntimeIOException e) {
                    //swallow
                }
            }
        }
    }
}