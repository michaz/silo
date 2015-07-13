package com.pb.sawdust.tensor.decorators.id.primitive.size;

import com.pb.sawdust.tensor.decorators.primitive.size.LongD3TensorShell;
import com.pb.sawdust.tensor.decorators.primitive.size.LongD3Tensor;
import com.pb.sawdust.tensor.decorators.primitive.size.LongD2Tensor;
import com.pb.sawdust.tensor.index.Index;
import com.pb.sawdust.tensor.index.BaseIndex;
import com.pb.sawdust.tensor.TensorImplUtil;
import com.pb.sawdust.tensor.Tensor;


import java.util.List;
import java.util.Iterator;

/**
 * The {@code IdLongD3TensorShell} class is a wrapper which sets a {@code LongD3TensorShell} as an {@code IdTensor<I>} (or,
 * more specifically, an {@code IdLongD3Tensor}).
 *
 * @author crf <br/>
 *         Started: Jan 16, 2009 9:47:43 AM
 *         Revised: Dec 14, 2009 12:35:26 PM
 */
public class IdLongD3TensorShell<I> extends LongD3TensorShell implements IdLongD3Tensor<I> {
    private final LongD3Tensor tensor;
    private final Index<I> index;

    IdLongD3TensorShell(LongD3Tensor tensor, Index<I> index) {
        super(tensor);
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
    public IdLongD3TensorShell(LongD3Tensor tensor) {
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
     * @throws IllegalArgumentException if {@code ids.size() != 3}, if the length of each array in {@code ids} 
     *                                  does not match its respective dimension's size in {@code tensor}, or if any array 
     *                                  in {@code ids} contains repeated elements.
     */
    @SafeVarargs
    @SuppressWarnings({"unchecked","varargs"})
    public IdLongD3TensorShell(LongD3Tensor tensor, I[] ... ids) {
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
     * @throws IllegalArgumentException if {@code ids.size() != 3}, if the length of each array in {@code ids} 
     *                                  does not match its respective dimension's size in {@code tensor}, or if any array 
     *                                  in {@code ids} contains repeated elements.
     */
    public IdLongD3TensorShell(LongD3Tensor tensor, List<List<I>> ids) {
        this(tensor,new BaseIndex<I>(tensor,ids));
    }

    public Index<I> getIndex() {
        return index;
    }

    public long getCellById(I d0id, I d1id, I d2id) {
        return tensor.getCell(index.getIndex(0,d0id),index.getIndex(1,d1id),index.getIndex(2,d2id));
    }

    public void setCellById(long value, I d0id, I d1id, I d2id) {
        tensor.setCell(value,index.getIndex(0,d0id),index.getIndex(1,d1id),index.getIndex(2,d2id));
    }

    @SuppressWarnings({"unchecked", "varargs"})
    public Long getValueById(I ... ids) {
        TensorImplUtil.checkIndicesLength(this,ids);
        return getCellById(ids[0],ids[1],ids[2]);
    }

    @SuppressWarnings({"unchecked", "varargs"})
    public long getCellById(I ... ids) {
        TensorImplUtil.checkIndicesLength(this,ids);
        return getCellById(ids[0],ids[1],ids[2]);
    }

    @SuppressWarnings({"unchecked", "varargs"})
    public void setCellById(long value, I ... ids) {
        TensorImplUtil.checkIndicesLength(this,ids);
        setCellById(value,ids[0],ids[1],ids[2]);
    }

    @SuppressWarnings({"unchecked", "varargs"})
    public void setValueById(Long value, I ... ids) {
        setCellById(value,ids);
    }

    public Long getValueById(I d0id, I d1id, I d2id) {
        return getCellById(d0id,d1id,d2id);
    }

    public void setValueById(Long value, I d0id, I d1id, I d2id) {
        setCellById(value,d0id,d1id,d2id);
    }

    public Iterator<Tensor<Long>> iterator() {
        return new Iterator<Tensor<Long>>() {
            private final Iterator<Tensor<Long>> iterator = IdLongD3TensorShell.super.iterator();
            
            public boolean hasNext() {
                return iterator.hasNext();
            }

            public Tensor<Long> next() {
                return new IdLongD2TensorShell<I>((LongD2Tensor) iterator.next());
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}