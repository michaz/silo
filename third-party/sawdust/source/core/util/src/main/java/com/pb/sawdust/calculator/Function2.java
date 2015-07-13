package com.pb.sawdust.calculator;

/**
 * The {@code Function2} class represents a two-argument function.
 *
 * @param <X>
 *        The type of the first argument of the function.
 *
 * @param <Y>
 *        The type of the second argument of the function.
 *
 * @param <Z>
 *        The type that the function returns.
 *
 * @author crf <br/>
 *         Started: Jun 12, 2009 11:51:01 AM
 */
public interface Function2<X,Y,Z> {

    /**
     * Apply the function.
     *
     * @param x
     *        The first argument to the function.
     *
     * @param y
     *        The second argument to the function.
     *
     * @return the result of applying the function to {@code x} and {@code y}.
     */
    Z apply(X x, Y y);

    
}