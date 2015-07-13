package com.pb.sawdust.tabledata.sql.impl.derby;

import com.pb.sawdust.tabledata.sql.SqlDataColumnTest;
import com.pb.sawdust.tabledata.sql.SqlDataSet;
import com.pb.sawdust.tabledata.sql.SqlTableDataUtil;
import com.pb.sawdust.tabledata.metadata.DataType;
import com.pb.sawdust.tabledata.sql.impl.SqlImplTestUtil;
import com.pb.sawdust.util.test.TestBase;
import org.junit.Before;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author crf <br/>
 *         Started: Dec 7, 2008 3:44:12 PM
 */
public class DerbySqlDataColumnTest <T> extends SqlDataColumnTest<T> {
    public static final String DATA_TYPE_KEY = "data type key";

    protected DataType type;

    public static void main(String ... args) {
        //if should perform finish operations, then this test is being run by itself, so make sure that the derby dir is clear
        if (SqlImplTestUtil.shouldPerformTestFinishOperations(args)) {
            TestBase.classStarted();
            DerbyPackageTests.performTestStartOperations();
        }
        TestBase.main();
        if (SqlImplTestUtil.shouldPerformTestFinishOperations(args))
            TestBase.classFinished(new Runnable() {
                public void run() {
                     DerbyPackageTests.performTestFinishOperations();
                }
            });

    }

    protected Collection<Class<? extends TestBase>> getAdditionalTestClasses() {
        List<Map<String,Object>> context = new LinkedList<Map<String,Object>>();
        for (DataType type : DataType.values()) {
            for (DerbyPackageTests.DerbyDataSetType dataSetType : DerbyPackageTests.DerbyDataSetType.values()) {
                Map<String,Object> ct = buildContext(DATA_TYPE_KEY,type);
                ct.put(DerbyPackageTests.DERBY_DATA_SET_TYPE_KEY,dataSetType);
                context.add(ct);
            }
        }
        addClassRunContext(this.getClass(),context);
        return super.getAdditionalTestClasses();
    }

    @Before
    public void beforeTest() {
        type = (DataType) getTestData(DATA_TYPE_KEY);
        super.beforeTest();
    }

    protected DataType getColumnDataType() {
        return type;
    }

    protected SqlDataSet getSqlDataSet() {
        return DerbyPackageTests.getDataSet((DerbyPackageTests.DerbyDataSetType) getTestData(DerbyPackageTests.DERBY_DATA_SET_TYPE_KEY));
    }
    
    protected void dropTableIfExistsFromDatabase(String tableName, SqlDataSet sqlDataSet) {
        Connection c = null;
        ResultSet r = null;
        try {
            c = sqlDataSet.getConnection();
            r = c.getMetaData().getTables(null,null,tableName,null);
            if (r.next())
                sqlDataSet.executeSqlUpdate("DROP TABLE " + SqlTableDataUtil.formQuotedIdentifier(tableName,sqlDataSet.getIdentifierQuote()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (r != null)
                    r.close();
            } catch (SQLException e) {
                //ignore
            }
            try {
                if (c != null)
                    c.close();
            } catch (SQLException e) {
                //ignore
            }
        }
    }
}