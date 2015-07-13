package com.pb.sawdust.tensor.write;

import com.pb.sawdust.tensor.index.Index;

/**
 * The {@code IndexWrite} class provides a framework for writing indices.  In general, an index need not hold any connection
 * to a specific tensor (only to a dimension), so it is generally better to write an index sans any reference to a tensor.
 * Possible exceptions to this rule could be made when intricate reference tensor-index relationships are being preserved,
 * though in such cases this interface is probably not appropriate (in that it would be misleading). In general, most
 * {@code IndexWriter} implementations should have complementing implementations of {@code IndexReader}.
 *
 * @param <I> The type of the ids held by indices written by this writer.
 *
 * @author crf <br/>
 *         Started: Dec 15, 2009 3:02:58 PM
 */
public interface IndexWriter<I> {
    /**
     * Write a given index.
     *
     * @param index
     *        The index to write.
     */
    void writeIndex(Index<? extends I> index);
}