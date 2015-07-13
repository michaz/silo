package com.pb.sawdust.tabledata.sql.impl.derby;

import com.pb.sawdust.tabledata.sql.SqlDataSetTest;
import com.pb.sawdust.tabledata.sql.SqlDataSet;
import com.pb.sawdust.tabledata.sql.impl.SqlImplTestUtil;
import com.pb.sawdust.util.test.TestBase;
import com.pb.sawdust.util.Filter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author crf <br/>
 *         Started: Dec 6, 2008 2:28:19 PM
 */
public class DerbySqlDataSetTest extends SqlDataSetTest {

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
        for (DerbyPackageTests.DerbyDataSetType dataSetType : DerbyPackageTests.DerbyDataSetType.values())
            context.add(buildContext(DerbyPackageTests.DERBY_DATA_SET_TYPE_KEY,dataSetType));
        addClassRunContext(this.getClass(),context);
        return super.getAdditionalTestClasses();
    }

    protected SqlDataSet getDistinctSqlDataSet() {
        return new DerbyFileDataSet(SqlImplTestUtil.getDatabasePath("derby_distinct").getPath());
    }

     protected Filter<String> getTableDropFilter() {
        return new Filter<String>() {
            public boolean filter(String input) {
                return input.indexOf("SYS") != 0;
            }
        };
    }

    protected SqlDataSet getDataSet() {
        return DerbyPackageTests.getDataSet((DerbyPackageTests.DerbyDataSetType) getTestData(DerbyPackageTests.DERBY_DATA_SET_TYPE_KEY));
    }
}