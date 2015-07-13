package com.pb.sawdust.tensor.decorators.id.primitive;

import com.pb.sawdust.tensor.decorators.primitive.AbstractBooleanTensor;
import com.pb.sawdust.tensor.decorators.primitive.BooleanTensor;
import com.pb.sawdust.tensor.Tensor;
import com.pb.sawdust.tensor.index.Index;
import com.pb.sawdust.tensor.index.BaseIndex;

import java.util.Iterator;
import java.util.List;

/**
 * The {@code IdBooleanTensorShell} class is a wrapper which sets a {@code BooleanTensor} as an {@code IdTensor<I>} (or,
 * more specifically, an {@code IdBooleanTensor}).
 * 
 * @author crf <br/>
 *         Started: Jan 15, 2009 11:25:11 PM
 *         Revised: Dec 14, 2009 12:35:34 PM
 */
public class IdBooleanTensorShell<I> extends AbstractBooleanTensor implements IdBooleanTensor<I> {
    private final BooleanTensor tensor;
    private final Index<I> index;

    private IdBooleanTensorShell(BooleanTensor tensor, Index<I> index) {
        super(index.getDimensions());
        this.tensor = tensor;
        this.index = index;
    }

    /**
     * Constructor which will wrap a tensor, using its current index for the ids. Note that this constructor is
     * inherently unsafe, as there is no way to check that the type of the tensor's index matches the type declared
     * when this constructor is called. Therefore, it should be used with care or in situations where the index type
     * can be verified.
     * 
     * @param tensor
     *        The tensor to wrap.
     */
    @SuppressWarnings("unchecked") //warning is duly noted and clearly stated in the documentation
    public IdBooleanTensorShell(BooleanTensor tensor) {
        this(tensor,(Index<I>) tensor.getIndex());
    }

    /**
     * Constructor specifying the tensor to wrap, as well as the ids to use to reference the indices.
     * 
     * @param tensor
     *        The tensor to wrap.
     * 
     * @param ids
     *        The ids to reference the indices with.
     *
     * @throws IllegalArgumentException if {@code ids.length != this.size()}, if the length of each array in {@code ids} 
     *                                  does not match its respective dimension's size in {@code tensor}, or if any array 
     *                                  in {@code ids} contains repeated elements.
     */
    @SafeVarargs
    @SuppressWarnings({"unchecked","varargs"})
    public IdBooleanTensorShell(BooleanTensor tensor, I[] ... ids) {
        this(tensor,new BaseIndex<I>(tensor,ids));
    }

    /**
     * Constructor specifying the tensor to wrap, as well as the ids to use to reference the indices.
     * 
     * @param tensor
     *        The tensor to wrap.
     * 
     * @param ids
     *        The ids to reference the indices with.
     *
     * @throws IllegalArgumentException if {@code ids.size() != this.size()}, if the length of each array in {@code ids} 
     *                                  does not match its respective dimension's size in {@code tensor}, or if any array 
     *                                  in {@code ids} contains repeated elements.
     */
    public IdBooleanTensorShell(BooleanTensor tensor, List<List<I>> ids) {
        this(tensor,new BaseIndex<I>(tensor,ids));
    }

    public Index<I> getIndex() {
        return index;
    }

    public boolean getCell(int ... indices) {
        return tensor.getCell(indices);
    }

    public void setCell(boolean value, int ... indices) {
        tensor.setCell(value,indices);
    }

    @SuppressWarnings({"unchecked", "varargs"})
    public boolean getCellById(I ... ids) {
        return getCell(index.getIndices(ids));
    }

    @SuppressWarnings({"unchecked", "varargs"})
    public void setCellById(boolean value, I ... ids) {
        setCell(value,index.getIndices(ids));
    }

    @SuppressWarnings({"unchecked", "varargs"})
    public Boolean getValueById(I... ids) {
        return getCellById(ids);
    }

    @SuppressWarnings({"unchecked", "varargs"})
    public void setValueById(Boolean value, I ... ids) {
        setCellById(value,ids);
    }

    public Iterator<Tensor<Boolean>> iterator() {
        return new Iterator<Tensor<Boolean>>() {
            private final Iterator<Tensor<Boolean>> it = IdBooleanTensorShell.super.iterator();

            public boolean hasNext() {
                return it.hasNext();
            }

            public Tensor<Boolean> next() {
                return new IdBooleanTensorShell<I>((BooleanTensor) it.next());
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}