package com.pb.sawdust.tensor.decorators.primitive.size;

import com.pb.sawdust.tensor.decorators.primitive.ShortTensor;
import com.pb.sawdust.tensor.decorators.id.primitive.IdShortTensor;
import com.pb.sawdust.tensor.index.Index;

/**
 * The {@code ShortD0TensorShell} class is a wrapper which sets a rank 0 (scalar) {@code ShortTensor} as a
 * {@code D0Tensor} (or, more specifically, a {@code ShortD0Tensor}).
 *
 * @author crf <br/>
 *         Started: Jun 25, 2009 2:16:12 PM
 */
public class ShortD0TensorShell extends AbstractShortD0Tensor {
    private final ShortTensor tensor;

    /**
     * Constructor specifying tensor to wrap.
     *
     * @param tensor
     *        The tensor to wrap.
     *
     * @throws IllegalArgumentException if {@code tensor.size() != 0}.
     */
    public ShortD0TensorShell(ShortTensor tensor) {
        if (tensor.size() != 0)
            throw new IllegalArgumentException("Wrapped tensor must be of rank 0.");
        this.tensor = tensor;
    }

    public short getCell() {
        return tensor.getCell();
    }

    public void setCell(short value) {
        tensor.setCell(value);
    }

    public <I> IdShortTensor<I> getReferenceTensor(Index<I> index) {
        return tensor.getReferenceTensor(index);
    }
}