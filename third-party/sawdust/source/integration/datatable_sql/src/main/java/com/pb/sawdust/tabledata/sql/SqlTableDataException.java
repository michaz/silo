package com.pb.sawdust.tabledata.sql;

import com.pb.sawdust.tabledata.TableDataException;

import java.sql.SQLException;

/**
 * The {@code SqlTableDataException} wraps a {@code SQLException} in an unchecked {@code TableDataException}. Methods
 * available in {@code SQLException} to get the sql state and error codes are reflected through this class, so it may
 * be used as if it is a {@code SQLException}. Since it is unchecked, the use of this exception lowers the programmering
 * burden by eliminating the need for {@code throws} statements in method signatures, and/or {@code try...except} blocks
 * in method bodies. Because of this, though, all methods which may throw this exception should clearly state so in the
 * documentation.
 * @author crf <br/>
 *         Started: May 8, 2008 9:10:39 PM
 */
public class SqlTableDataException extends TableDataException {
    private static final long serialVersionUID = 235670245535694522L;

    /**
     * Constructor which wraps a {@code SQLException}.
     *
     * @param sqlException
     *        The source sql exception to wrap.
     */
    public SqlTableDataException(SQLException sqlException) {
        super(sqlException);
    }

    /**
     * Get the source exception.
     *
     * @return the source {@code SQLException}.
     */
    public SQLException getWrappedException() {
        return (SQLException) wrappedException;
    }

    /**
     * Get the sql state related to this exception.
     *
     * @return the sql state value for this exception.
     */
    public String getSQLState() {
        return ((SQLException) wrappedException).getSQLState();
    }

    /**
     * Get the error code related to this exception.
     *
     * @return the error code for this exception.
     */
    public int getErrorCode() {
        return ((SQLException) wrappedException).getErrorCode();
    }

    /**
     * Retrieves the exception chained to this exception by {@code setNextException}. Though the chained exception
     * may be a {@code SQLException}, this method will always wrap it in a {@code SqlTableDataException}.
     *
     * @return the next exception in the chain, or {@code null} if there are none.
     */
    public SqlTableDataException getNextException() {
        return new SqlTableDataException(((SQLException) wrappedException).getNextException());
    }

    /**
     * Adds a {@code SQLException} to the end of the exception chain. Calls to {@code getNextException()} will always
     * return a {@code SqlTableDataException} wrapping the added exception.
     *
     * @param e
     *        the new exception that will be added to the end of this exception's chain
     */
    public void setNextException(SQLException e) {
        ((SQLException) wrappedException).setNextException(e);

    }

    /**
     * Adds an exception to the end of the chain.
     *
     * @param e
     *        the new exception that will be added to the end of this exception's chain
     */
    public void setNextException(SqlTableDataException e) {
        ((SQLException) wrappedException).setNextException(e.getWrappedException()); 
    }
}