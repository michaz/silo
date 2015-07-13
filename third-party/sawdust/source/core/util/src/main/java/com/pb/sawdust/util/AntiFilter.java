package com.pb.sawdust.util;

/**
 * The {@code AntiFilter} provides a filter which is the opposite (inverse) of a given filter. That is, the anti-filter
 * will return {@code false} when the source filter returns {@code true}, and vice-versa.
 *
 * @author crf
 *         Started 10/1/11 10:48 AM
 */
public class AntiFilter<I> implements Filter<I>{
    private final Filter<I> filter;

    /**
     * Constructor specifying the source filter.
     *
     * @param filter
     *        The source filter.
     */
    public AntiFilter(Filter<I> filter) {
        this.filter = filter;
    }

    @Override
    public boolean filter(I input) {
        return !filter.filter(input);
    }
}