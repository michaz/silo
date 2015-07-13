package com.pb.sawdust.tensor.decorators.id.primitive.size;

import com.pb.sawdust.tensor.decorators.primitive.size.IntD7TensorShell;
import com.pb.sawdust.tensor.decorators.primitive.size.IntD7Tensor;
import com.pb.sawdust.tensor.decorators.primitive.size.IntD6Tensor;
import com.pb.sawdust.tensor.index.Index;
import com.pb.sawdust.tensor.index.BaseIndex;
import com.pb.sawdust.tensor.TensorImplUtil;
import com.pb.sawdust.tensor.Tensor;


import java.util.List;
import java.util.Iterator;

/**
 * The {@code IdIntD7TensorShell} class is a wrapper which sets a {@code IntD7TensorShell} as an {@code IdTensor<I>} (or,
 * more specifically, an {@code IdIntD7Tensor}).
 *
 * @author crf <br/>
 *         Started: Jan 16, 2009 9:47:43 AM
 *         Revised: Dec 14, 2009 12:35:31 PM
 */
public class IdIntD7TensorShell<I> extends IntD7TensorShell implements IdIntD7Tensor<I> {
    private final IntD7Tensor tensor;
    private final Index<I> index;

    IdIntD7TensorShell(IntD7Tensor tensor, Index<I> index) {
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
    public IdIntD7TensorShell(IntD7Tensor tensor) {
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
     * @throws IllegalArgumentException if {@code ids.size() != 7}, if the length of each array in {@code ids} 
     *                                  does not match its respective dimension's size in {@code tensor}, or if any array 
     *                                  in {@code ids} contains repeated elements.
     */
    @SafeVarargs
    @SuppressWarnings({"unchecked","varargs"})
    public IdIntD7TensorShell(IntD7Tensor tensor, I[] ... ids) {
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
     * @throws IllegalArgumentException if {@code ids.size() != 7}, if the length of each array in {@code ids} 
     *                                  does not match its respective dimension's size in {@code tensor}, or if any array 
     *                                  in {@code ids} contains repeated elements.
     */
    public IdIntD7TensorShell(IntD7Tensor tensor, List<List<I>> ids) {
        this(tensor,new BaseIndex<I>(tensor,ids));
    }

    public Index<I> getIndex() {
        return index;
    }

    public int getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
        return tensor.getCell(index.getIndex(0,d0id),index.getIndex(1,d1id),index.getIndex(2,d2id),index.getIndex(3,d3id),index.getIndex(4,d4id),index.getIndex(5,d5id),index.getIndex(6,d6id));
    }

    public void setCellById(int value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
        tensor.setCell(value,index.getIndex(0,d0id),index.getIndex(1,d1id),index.getIndex(2,d2id),index.getIndex(3,d3id),index.getIndex(4,d4id),index.getIndex(5,d5id),index.getIndex(6,d6id));
    }

    @SuppressWarnings({"unchecked", "varargs"})
    public Integer getValueById(I ... ids) {
        TensorImplUtil.checkIndicesLength(this,ids);
        return getCellById(ids[0],ids[1],ids[2],ids[3],ids[4],ids[5],ids[6]);
    }

    @SuppressWarnings({"unchecked", "varargs"})
    public int getCellById(I ... ids) {
        TensorImplUtil.checkIndicesLength(this,ids);
        return getCellById(ids[0],ids[1],ids[2],ids[3],ids[4],ids[5],ids[6]);
    }

    @SuppressWarnings({"unchecked", "varargs"})
    public void setCellById(int value, I ... ids) {
        TensorImplUtil.checkIndicesLength(this,ids);
        setCellById(value,ids[0],ids[1],ids[2],ids[3],ids[4],ids[5],ids[6]);
    }

    @SuppressWarnings({"unchecked", "varargs"})
    public void setValueById(Integer value, I ... ids) {
        setCellById(value,ids);
    }

    public Integer getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
        return getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id);
    }

    public void setValueById(Integer value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
        setCellById(value,d0id,d1id,d2id,d3id,d4id,d5id,d6id);
    }

    public Iterator<Tensor<Integer>> iterator() {
        return new Iterator<Tensor<Integer>>() {
            private final Iterator<Tensor<Integer>> iterator = IdIntD7TensorShell.super.iterator();
            
            public boolean hasNext() {
                return iterator.hasNext();
            }

            public Tensor<Integer> next() {
                return new IdIntD6TensorShell<I>((IntD6Tensor) iterator.next());
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}