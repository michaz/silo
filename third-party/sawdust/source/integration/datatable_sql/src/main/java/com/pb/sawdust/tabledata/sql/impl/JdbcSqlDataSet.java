package com.pb.sawdust.tabledata.sql.impl;

import com.pb.sawdust.tabledata.sql.SqlDataSet;
import com.pb.sawdust.tabledata.sql.SqlTableDataException;
import com.pb.sawdust.tabledata.TableDataException;
import com.pb.sawdust.util.sql.SimpleConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The {@code JdbcSqlDataSet} provides a base class from which {@code SqlDataSet} implementations can inherit, lowering
 * the burden on the programmer. Its requires only that methods to get the JDBC driver class name and the JDBC
 * connection URL be implemented. Internally, it creates a connection pool (based off of
 * {@link com.pb.sawdust.util.sql.SimpleConnectionPool}), which helps to manage and reuse database connections.
 * Because of the pool it uses, certain resource management functionality, such as pool reduction, is not available.
 *
 * @author crf <br/>
 *         Started: Dec 1, 2008 10:43:50 AM
 */
public abstract class JdbcSqlDataSet extends SqlDataSet {
    private final SimpleConnectionPool connectionPool;

    /**
     * Get the JDBC driver class name for JDBC connections made through this data set. An example would be
     * {@code org.apache.derby.jdbc.EmbeddedDriver} .
     *
     * @return the JDBC driver class to use with this data set.
     */
    protected abstract String getJdbcClassName();

    /**
     * Get the JDBC database connection URL which will be used to form connections used by this data set.  An examplw
     * would be {@code jdbc:derby:c:/mydatabase}.
     *
     * @return the database connection URL to creat connections used by this data set.
     */
    protected abstract String formConnectionUrl();

    /**
     * Constructor specifying the maximum number of simultaneous connections allowed to the database. Once the maximum
     * number of connections have  been made, then requests for more connections will require that previously
     * made connections be recycled; if no previously opened connections are available, then an exception may be thrown,
     * or a deadlock may result.
     *
     * @param connectionLimit
     *        The maximum number of simultaneous connections that can be made through this data set.
     */
    public JdbcSqlDataSet(int connectionLimit) {
        super();
        loadJdbcDriverClass();
        connectionPool = getConnectionPool(connectionLimit);
    }

    private void loadJdbcDriverClass() {
        try {
            synchronized(java.sql.DriverManager.class) { //deadlocks can result if more than one thread call this concurrently
                Class.forName(getJdbcClassName());
            }
        } catch (ClassNotFoundException e) {
            throw new TableDataException("JDBC driver not found: " + getJdbcClassName());
        }
    }

    public Connection getConnection() {
        return connectionPool.getConnection();
    }

    /**
     * Get a JDBC connection with a given URL. The default implementation is to call
     * <pre><code>
     *     DriverManager.getConnection(formConnectionUrl());
     * </code></pre>
     * though this method may be overridden as needed.
     *
     * @param connectionUrl
     *        The URL through which the database connection will be made.
     *
     * @return a database connection made with {@code connectionUrl}.
     * 
     * @throws SQLException if a database connection error occurs.
     */
    protected Connection getConnection(String connectionUrl) throws SQLException {
        return DriverManager.getConnection(formConnectionUrl());
    }

    private SimpleConnectionPool getConnectionPool(int connectionLimit) {
        return new SimpleConnectionPool(connectionLimit) {

            protected Connection createConnection() {
                try {
                    return JdbcSqlDataSet.this.getConnection(formConnectionUrl());
                } catch (SQLException e) {
                    throw new SqlTableDataException(e);
                }
            }
        };
    }

}