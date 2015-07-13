package com.pb.sawdust.tensor.index;

import com.pb.sawdust.tensor.Tensor;
import com.pb.sawdust.util.ContainsMetadata;

import java.util.List;

/**
 * The {@code Index} interface provides a structure inside which information about and pertaining to a specific tensor
 * can be held. The index holds 3 primary pieces of information:
 * <ul>
 *     <li>
 *         The size of the tensor: both its dimension count and the size of each dimension.
 *     </li>
 *     <li>
 *         A mapping from the tensor's indices to those of its reference tensor (if the tensor is not a reference,
 *         then this mapping will just pass the indices through).
 *     </li>
 *     <li>
 *         A mapping from ids to indices, where ids are objects (of type {@code I}) which can be used as alternatives
 *         to refer to tensor locations.
 *     </li>
 * </ul>
 * <p>
 * Though ids are not used in the core {@code Tensor} interface, they are intrinsically built into indices to facilitate
 * their use in the {@code IdTensor} interface. For indexes that may not need separate ids (such as the {@code StandardIndex}),
 * the {@code Integer} equivalents of the tensor indices should be used (in such a case the {@link com.pb.sawdust.tensor.index.IdlessIndex}
 * should be implemented). Each id in any given dimension must be unique (distinct from others in that dimension) so that
 * index points can be uniquely mapped using ids.
 * <p>
 * An index need not have the same shape/dimensionality as its reference tensor; however, in such a case, care must be taken
 * when using {@link #getIndex(int, int)} and {@link #getIndices(int...)}, as the former method cannot provide information
 * on the correspondence between the dimensions. Thus, the latter method should always be used when accessing the reference
 * tensor.
 * <p>
 * An index should generally be immutable; once it is created (and attached to a given tensor), it should not ever be
 * modified (which implies a given tensor's internal structure should not be modified). There are instances where
 * exceptions to this rule might be valid (<i>e.g.</i> {@code PermutationIndex}); in such cases, tbe mutability (and its
 * motivation) should be clearly documented.
 *
 * @param <I>
 *        The type of the index's id.
 *
 * @author crf <br/>
 *         Started: Jan 10, 2009 7:44:55 PM
 */
public interface Index<I> extends ContainsMetadata<String> {
    /**
     * Get the number of dimensions in this index.
     *
     * @return this index's dimension count.
     */
    int size();

    /**
     * Get the size of a particular dimension in this index.
     *
     * @param dimension
     *        The (zero-based) dimension in question.
     *
     * @return the size of {@code dimension}.
     *
     * @throws IllegalArgumentException if {@code dimension} is less than zero or greater than one less then the number
     *                                  of dimensions in this index.
     */
    int size(int dimension);

    /**
     * Get the dimensionality (shape) of this index. Each element in the returned array refers to the corresponding
     * dimension in this index, and its value gives the size of the dimension.
     *
     * @return an array describing the dimensionality of this index.
     */
    int[] getDimensions();

    /**
     * Get the collection of ids for this index. Each sublist in the returned list corresponds to a dimension, and each
     * element in the sublist contains the ids corresponding to the indices in that dimension.
     *
     * @return a collection of ids for this index.
     */
    List<List<I>> getIndexIds();

    /**
     * Get the index in a given dimension corresponding to a specified id. The returned index refers to the point in
     * <i>this index</i>, not the reference tensor/index.
     *
     * @param dimension
     *        The (zero-based) dimension in question.
     *
     * @param id
     *        The id in question.
     *
     * @return the index corresponding to {@code id} in {@code dimension}.
     *
     * @throws IllegalArgumentException f {@code dimension} is less than zero or greater than one less then the number
     *                                  of dimensions in this index, or if {@code id} is not a valid id in {@code dimension}
     */
    int getIndex(int dimension, I id);

    /**
     * Get the indices corresponding to a point identified by ids. There must be one id for each dimension. The returned
     * indices refer to the point in <i>this index</i>, not the reference tensor/index.
     *
     * @param ids
     *        The ids describing the index point.
     *
     * @return the index point referred to by {@code ids}.
     *
     * @throws IllegalArgumentException if the size of {@code ids} does not equal the number of dimensions in this tensor,
     *                                  or if any of {@code ids} does not correspond to an index in that particular dimension.
     */
    @SuppressWarnings({"unchecked", "varargs"})
    int[] getIndices(I ... ids);

    /**
     * Get the id for a given dimension and index point.
     *
     * @param dimension
     *        The (zero-based) dimension in question.
     *
     * @param index
     *        The (zero-based) index point.
     *
     * @return the id corresponding to {@code index} in {@code dimension}.
     *
     * @throws IllegalArgumentException if {@code dimension} is less than zero or greater than one less then the number
     *                                  of dimensions in this index, or if {@code index} is less than zero or greater
     *                                  than one less than the size of {@code dimension}.
     */
    I getIndexId(int dimension, int index);

    /**
     * Get the index ids corresponding to a given index point.
     *
     * @param indices
     *        The indices referring to the index point. There must be one index for each dimension.
     *
     * @return a list of ids corresponding to {@code indices}.
     *
     * @throws IllegalArgumentException if the number of indices is not equal to the number of dimensions in this index,
     *                                  or if any value in {@code indices} is less than zero or greater than one less
     *                                  than the size of its corresponding dimension.
     */
    List<I> getIndexIds(int ... indices);

    /**
     * Get the reference index point corresponding to a specified index and dimension.  This method should return the reference
     * index point which corresponds to this index's dimension; if this index has a different shape/dimensionality than
     * its reference, then this method does not provide information about how the dimension number (index) of the point
     * may need to change for the reference. Thus, this method should not be used to directly access the underlying tensor,
     * and {@link #getIndices(int...)} should be used instead.
     *
     * @param dimension
     *        The dimension.
     *
     * @param index
     *        The (zeo-based) index.
     *
     * @return the reference index corresponding to {@code index} in {@code dimension}.
     *
     * @throws IllegalArgumentException if {@code dimension} is less than zero or greater than one less then the number
     *                                  of dimensions in this index.
     * @throws IndexOutOfBoundsException if {@code index} is less than zero or greater than one less than the size of {@code dimension}.
     */
    int getIndex(int dimension, int index);

    /**
     * Get the reference indices corresponding to indices in this index.  This method should always be preferred to
     * {@link #getIndexId(int, int)} to access the associated tensor, since this method will always correctly create a
     * reference array for an index whose dimensionality/shape is different from that of the reference.
     *
     * @param indices
     *        The indices in this index. There should be one index for each dimension.
     *
     * @return the reference indices corresponding to {@code indices}.
     *
     * @throws IllegalArgumentException if the number of indices is not equal to the number of dimensions in this index.
     * @throws IndexOutOfBoundsException if any value in {@code indices} is less than zero or greater than one less
     *                                   than the size of its corresponding dimension.
     */
    int[] getIndices(int ... indices);

    /**
     * Determine whether this index can be used with a given reference tensor. In other words, this method determines
     * whether the specified tensor is a valid reference tensor for use with this index. The tensor is valid if any of
     * the indices that this index maps to using {@code getIndices(int...)} is within the tensor's index bounds.
     *
     * @param tensor
     *        The tensor to test against.
     *
     * @return {@code true} if {@code tensor} is a valid reference tensor for this index, {@code false} if not.
     */
    boolean isValidFor(Tensor tensor);
}