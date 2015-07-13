package com.pb.sawdust.tensor.index;

/**
 * The {@code IdlessIndex} interface specifies an index which does not specify any ids. That is, the ids used by this
 * index are the object ({@code Integer}) equivalents of its indices. This interface is intended to be used as
 * an indicator that the ids may (effectively) be ignored for this index.
 *
 * @author crf <br/>
 *         Started: Dec 15, 2009 8:48:19 AM
 */
public interface IdlessIndex extends Index<Integer> {
}