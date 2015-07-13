package com.pb.sawdust.tabledata;

import com.pb.sawdust.tabledata.metadata.DataType;
import com.pb.sawdust.util.RandomDeluxe;
import com.pb.sawdust.util.collections.InjectiveMap;
import com.pb.sawdust.util.collections.InjectiveHashMap;

import static com.pb.sawdust.util.Range.*;

/**
 * @author crf <br/>
 *         Started: Sep 22, 2008 5:49:19 PM
 */
public class TableDataTestData {
    private static final RandomDeluxe random = new RandomDeluxe();
    
    private static DataType[] columnTypes;    
    private static String[] columnNames;
    
    static {
        columnTypes = DataType.values();
        
        columnNames = new String[columnTypes.length];
        for (int i : range(columnTypes.length))
            columnNames[i] = "column_" + columnTypes[i].toString();
    }

    private static ThreadLocal<Boolean[]> booleanData = new ThreadLocal<Boolean[]>();
    private static ThreadLocal<Byte[]> byteData = new ThreadLocal<Byte[]>();
    private static ThreadLocal<Short[]> shortData = new ThreadLocal<Short[]>();
    private static ThreadLocal<Integer[]> intData = new ThreadLocal<Integer[]>();
    private static ThreadLocal<Long[]> longData = new ThreadLocal<Long[]>();
    private static ThreadLocal<Float[]> floatData = new ThreadLocal<Float[]>();
    private static ThreadLocal<Double[]> doubleData = new ThreadLocal<Double[]>();
    private static ThreadLocal<String[]> stringData = new ThreadLocal<String[]>();
                                                   
    private int unusedKeyInteger = 0;

    public int getColumnOrdinal(DataType type) {
        return type.ordinal();
    }

    public Object[][] getTableData(int numberOfRows) {
        int nTypes = DataType.values().length;
        Object[][] tableData = new Object[numberOfRows][nTypes];
            for (int i : range(numberOfRows))
                for (int j : range(nTypes))
                    tableData[i][j] = getColumnData(columnTypes[j],numberOfRows)[i];
        return tableData;
    }

    public DataType[] getColumnTypes() {
        return columnTypes;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public String getColumnName(DataType type) {
        return getColumnNames()[getColumnOrdinal(type)];
    }

    public Object getRandomData(DataType type) {
        switch (type) {
            case BOOLEAN : return random.nextBoolean();
            case BYTE : return (byte) random.nextInt(Byte.MIN_VALUE,Byte.MAX_VALUE+1);
            case SHORT : return (short) random.nextInt(Short.MIN_VALUE,Short.MAX_VALUE+1);
            case INT : return random.nextInt();
            case LONG : return random.nextLong();
            case FLOAT : return random.nextFloat();
            case DOUBLE : return random.nextDouble();
            case STRING : return random.nextAsciiString(random.nextInt(50));
            default : return null;
        }
    }

    public Object[] getColumnData(DataType type,int numberOfRows) {
        switch (type) {
            case BOOLEAN : return getBooleanColumnData(numberOfRows);
            case BYTE : return getByteColumnData(numberOfRows);
            case SHORT : return getShortColumnData(numberOfRows);
            case INT : return getIntegerColumnData(numberOfRows);
            case LONG : return getLongColumnData(numberOfRows);
            case FLOAT : return getFloatColumnData(numberOfRows);
            case DOUBLE : return getDoubleColumnData(numberOfRows);
            case STRING : return getStringColumnData(numberOfRows);
            default : return null;
        }
    }

    public Boolean[] getBooleanColumnData(int numberOfRows) {
        Boolean[] data = booleanData.get();
        if (data == null || data.length != numberOfRows) {
            data = new Boolean[numberOfRows];
            for(int i = 0; i < numberOfRows; i++)
                data[i] = (Boolean) getRandomData(DataType.BOOLEAN);
            booleanData.set(data);
        }
        return data;
    }

    public Byte[] getByteColumnData(int numberOfRows) {
        Byte[] data = byteData.get();
        if (data == null || data.length != numberOfRows) {
            data = new Byte[numberOfRows];
            for(int i = 0; i < numberOfRows; i++)
                data[i] = (Byte) getRandomData(DataType.BYTE);
            byteData.set(data);
        }
        return data;
    }

    public Short[] getShortColumnData(int numberOfRows) {
        Short[] data = shortData.get();
        if (data == null || data.length != numberOfRows) {
            data = new Short[numberOfRows];
            for(int i = 0; i < numberOfRows; i++)
                data[i] = (Short) getRandomData(DataType.SHORT);
            shortData.set(data);
        }
        return data;
    }

    public Integer[] getIntegerColumnData(int numberOfRows) {
        Integer[] data = intData.get();
        if (data == null || data.length != numberOfRows) {
            data = new Integer[numberOfRows];
            for(int i = 0; i < numberOfRows; i++)
                data[i] = (Integer) getRandomData(DataType.INT);
            intData.set(data);
        }
        return data;
    }

    public Long[] getLongColumnData(int numberOfRows) {
        Long[] data = longData.get();
        if (data == null || data.length != numberOfRows) {
            data = new Long[numberOfRows];
            for(int i = 0; i < numberOfRows; i++)
                data[i] = (Long) getRandomData(DataType.LONG);
            longData.set(data);
        }
        return data;
    }

    public Float[] getFloatColumnData(int numberOfRows) {
        Float[] data = floatData.get();
        if (data == null || data.length != numberOfRows) {
            data = new Float[numberOfRows];
            for(int i = 0; i < numberOfRows; i++)
                data[i] = (Float) getRandomData(DataType.FLOAT);
            floatData.set(data);
        }
        return data;
    }

    public Double[] getDoubleColumnData(int numberOfRows) {
        Double[] data = doubleData.get();
        if (data == null || data.length != numberOfRows) {
            data = new Double[numberOfRows];
            for(int i = 0; i < numberOfRows; i++)
                data[i] = (Double) getRandomData(DataType.DOUBLE);
            doubleData.set(data);
        }
        return data;
    }

    public String[] getStringColumnData(int numberOfRows) {
        String[] data = stringData.get();
        if (data == null || data.length != numberOfRows) {
            data = new String[numberOfRows];
            for(int i = 0; i < numberOfRows; i++)
                data[i] = (String) getRandomData(DataType.STRING);
            stringData.set(data);
        }
        return data;
    }

    public TableKey<Integer> getSimpleKey(final int numberOfRows) {
        TableKey<Integer> key =  new AbstractTableKey<Integer>(getColumnName(DataType.INT),DataType.INT) {
            protected InjectiveMap<Integer,Integer> getKeyIndex() {
                InjectiveMap<Integer,Integer> simpleIndex = new InjectiveHashMap<Integer,Integer>();
                Integer[] columnData = getIntegerColumnData(numberOfRows);
                int max = 0;
                for (int i : range(numberOfRows)) {
                    int data = columnData[i];
                    simpleIndex.put(data,i);
                    if (data > max)
                        max = data;
                }
                unusedKeyInteger = max+1;
                return simpleIndex;
            }
        };
        key.buildIndex();
        return key;
    }

    public int getUnusedKeyInteger() {
        return unusedKeyInteger++;
    }
}