package com.pb.sawdust.tensor.decorators.primitive.size;

import com.pb.sawdust.tensor.decorators.primitive.LongTensor;
import com.pb.sawdust.tensor.decorators.id.primitive.IdLongTensor;
import com.pb.sawdust.tensor.index.Index;

/**
 * The {@code LongD0TensorShell} class is a wrapper which sets a rank 0 (scalar) {@code LongTensor} as a
 * {@code D0Tensor} (or, more specifically, a {@code LongD0Tensor}).
 *
 * @author crf <br/>
 *         Started: Jun 25, 2009 2:16:12 PM
 */
public class LongD0TensorShell extends AbstractLongD0Tensor {
    private final LongTensor tensor;

    /**
     * Constructor specifying tensor to wrap.
     *
     * @param tensor
     *        The tensor to wrap.
     *
     * @throws IllegalArgumentException if {@code tensor.size() != 0}.
     */
    public LongD0TensorShell(LongTensor tensor) {
        if (tensor.size() != 0)
            throw new IllegalArgumentException("Wrapped tensor must be of rank 0.");
        this.tensor = tensor;
    }

    public long getCell() {
        return tensor.getCell();
    }

    public void setCell(long value) {
        tensor.setCell(value);
    }

    public <I> IdLongTensor<I> getReferenceTensor(Index<I> index) {
        return tensor.getReferenceTensor(index);
    }
}