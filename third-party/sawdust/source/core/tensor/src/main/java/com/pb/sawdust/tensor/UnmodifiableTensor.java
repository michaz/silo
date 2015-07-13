package com.pb.sawdust.tensor;

import com.pb.sawdust.util.JavaType;
import com.pb.sawdust.util.array.*;
import com.pb.sawdust.tensor.decorators.primitive.*;
import com.pb.sawdust.tensor.decorators.primitive.size.*; 
import com.pb.sawdust.tensor.decorators.id.primitive.*;
import com.pb.sawdust.tensor.decorators.id.*;
import com.pb.sawdust.tensor.decorators.id.size.*;
import com.pb.sawdust.tensor.decorators.id.primitive.size.*;
import com.pb.sawdust.tensor.decorators.size.*;
import com.pb.sawdust.tensor.index.Index;
import com.pb.sawdust.tensor.alias.scalar.*;
import com.pb.sawdust.tensor.alias.scalar.primitive.*;
import com.pb.sawdust.tensor.alias.scalar.id.*;
import com.pb.sawdust.tensor.alias.vector.*;
import com.pb.sawdust.tensor.alias.vector.primitive.*;
import com.pb.sawdust.tensor.alias.vector.id.*;
import com.pb.sawdust.tensor.alias.matrix.*;
import com.pb.sawdust.tensor.alias.matrix.primitive.*;
import com.pb.sawdust.tensor.alias.matrix.id.*;

import java.util.Iterator;

/**
 *
 * @author crf <br/>
 *         Started: Jan 22, 2008 10:24:39 PM
 *         Revised: Dec 14, 2009 12:35:35 PM
 */
class UnmodifiableTensor {
    private UnmodifiableTensor() {}
    
    private static class UnmodifiableTensorShell<T> extends ComposableTensor<T> {
        private final Tensor<T> tensor;
        
        private UnmodifiableTensorShell(Tensor<T> tensor) {
            this.tensor = tensor;
        }

        public int[] getDimensions() {
            return tensor.getDimensions();
        }

        public int size(int dimension) {
            return tensor.size(dimension);
        }

        public int size() {
            return tensor.size();
        }

        public JavaType getType() {
            return tensor.getType();
        }

        public T getValue(int ... indices) {
            return tensor.getValue(indices);
        }

        public void setValue(T value, int... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public void setTensorValues(TypeSafeArray<? extends T> typeSafeArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public TypeSafeArray<T> getTensorValues(Class<T> type) {
            return tensor.getTensorValues(type);
        }

        public void setTensorValues(Tensor<? extends T> tensor) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Index<?> getIndex() {
            return tensor.getIndex();
        }
    }

    @SuppressWarnings("unchecked") //it is surely a Tensor<T> if one goes in, so no problems here
    static <T> Tensor<T> unmodifiableTensor(Tensor<T> tensor) {
        if (tensor instanceof ByteTensor) return (Tensor<T>) unmodifiableTensor((ByteTensor) tensor);
        if (tensor instanceof ShortTensor) return (Tensor<T>) unmodifiableTensor((ShortTensor) tensor);
        if (tensor instanceof IntTensor) return (Tensor<T>) unmodifiableTensor((IntTensor) tensor);
        if (tensor instanceof LongTensor) return (Tensor<T>) unmodifiableTensor((LongTensor) tensor);
        if (tensor instanceof FloatTensor) return (Tensor<T>) unmodifiableTensor((FloatTensor) tensor);
        if (tensor instanceof DoubleTensor) return (Tensor<T>) unmodifiableTensor((DoubleTensor) tensor);
        if (tensor instanceof CharTensor) return (Tensor<T>) unmodifiableTensor((CharTensor) tensor);
        if (tensor instanceof BooleanTensor) return (Tensor<T>) unmodifiableTensor((BooleanTensor) tensor);
        if (tensor instanceof D0Tensor) return new D0UnmodifiableTensorShell<T>((D0Tensor) tensor);
        if (tensor instanceof D1Tensor) return new D1UnmodifiableTensorShell<T>((D1Tensor) tensor);
        if (tensor instanceof D2Tensor) return new D2UnmodifiableTensorShell<T>((D2Tensor) tensor);
        if (tensor instanceof D3Tensor) return new D3UnmodifiableTensorShell<T>((D3Tensor) tensor);
        if (tensor instanceof D4Tensor) return new D4UnmodifiableTensorShell<T>((D4Tensor) tensor);
        if (tensor instanceof D5Tensor) return new D5UnmodifiableTensorShell<T>((D5Tensor) tensor);
        if (tensor instanceof D6Tensor) return new D6UnmodifiableTensorShell<T>((D6Tensor) tensor);
        if (tensor instanceof D7Tensor) return new D7UnmodifiableTensorShell<T>((D7Tensor) tensor);
        if (tensor instanceof D8Tensor) return new D8UnmodifiableTensorShell<T>((D8Tensor) tensor);
        if (tensor instanceof D9Tensor) return new D9UnmodifiableTensorShell<T>((D9Tensor) tensor);
        return new UnmodifiableTensorShell<T>(tensor);
    }

    @SuppressWarnings("unchecked") //it is surely a Tensor<T> if one goes in, so no problems here
    static <T,I> IdTensor<T,I> unmodifiableTensor(IdTensor<T,I> tensor) {
        if (tensor instanceof IdByteTensor) return (IdTensor<T,I>) unmodifiableByteTensor((IdByteTensor<I>) tensor);
        if (tensor instanceof IdShortTensor) return (IdTensor<T,I>) unmodifiableShortTensor((IdShortTensor<I>) tensor);
        if (tensor instanceof IdIntTensor) return (IdTensor<T,I>) unmodifiableIntTensor((IdIntTensor<I>) tensor);
        if (tensor instanceof IdLongTensor) return (IdTensor<T,I>) unmodifiableLongTensor((IdLongTensor<I>) tensor);
        if (tensor instanceof IdFloatTensor) return (IdTensor<T,I>) unmodifiableFloatTensor((IdFloatTensor<I>) tensor);
        if (tensor instanceof IdDoubleTensor) return (IdTensor<T,I>) unmodifiableDoubleTensor((IdDoubleTensor<I>) tensor);
        if (tensor instanceof IdCharTensor) return (IdTensor<T,I>) unmodifiableCharTensor((IdCharTensor<I>) tensor);
        if (tensor instanceof IdBooleanTensor) return (IdTensor<T,I>) unmodifiableBooleanTensor((IdBooleanTensor<I>) tensor);
        if (tensor instanceof D0Tensor) return new IdD0UnmodifiableTensorShell<T,I>((IdD0Tensor) tensor);
        if (tensor instanceof D1Tensor) return new IdD1UnmodifiableTensorShell<T,I>((IdD1Tensor) tensor);
        if (tensor instanceof D2Tensor) return new IdD2UnmodifiableTensorShell<T,I>((IdD2Tensor) tensor);
        if (tensor instanceof D3Tensor) return new IdD3UnmodifiableTensorShell<T,I>((IdD3Tensor) tensor);
        if (tensor instanceof D4Tensor) return new IdD4UnmodifiableTensorShell<T,I>((IdD4Tensor) tensor);
        if (tensor instanceof D5Tensor) return new IdD5UnmodifiableTensorShell<T,I>((IdD5Tensor) tensor);
        if (tensor instanceof D6Tensor) return new IdD6UnmodifiableTensorShell<T,I>((IdD6Tensor) tensor);
        if (tensor instanceof D7Tensor) return new IdD7UnmodifiableTensorShell<T,I>((IdD7Tensor) tensor);
        if (tensor instanceof D8Tensor) return new IdD8UnmodifiableTensorShell<T,I>((IdD8Tensor) tensor);
        if (tensor instanceof D9Tensor) return new IdD9UnmodifiableTensorShell<T,I>((IdD9Tensor) tensor);
        return new IdUnmodifiableTensorShell<T,I>(tensor);
    }

    //@SuppressWarnings("unchecked") //it is surely a Tensor<T> if one goes in, so no problems here
    static ByteTensor unmodifiableTensor(ByteTensor tensor) {
        if (tensor instanceof ByteD0Tensor) return new ByteD0UnmodifiableTensorShell((ByteD0Tensor) tensor);
        if (tensor instanceof ByteD1Tensor) return new ByteD1UnmodifiableTensorShell((ByteD1Tensor) tensor);
        if (tensor instanceof ByteD2Tensor) return new ByteD2UnmodifiableTensorShell((ByteD2Tensor) tensor);
        if (tensor instanceof ByteD3Tensor) return new ByteD3UnmodifiableTensorShell((ByteD3Tensor) tensor);
        if (tensor instanceof ByteD4Tensor) return new ByteD4UnmodifiableTensorShell((ByteD4Tensor) tensor);
        if (tensor instanceof ByteD5Tensor) return new ByteD5UnmodifiableTensorShell((ByteD5Tensor) tensor);
        if (tensor instanceof ByteD6Tensor) return new ByteD6UnmodifiableTensorShell((ByteD6Tensor) tensor);
        if (tensor instanceof ByteD7Tensor) return new ByteD7UnmodifiableTensorShell((ByteD7Tensor) tensor);
        if (tensor instanceof ByteD8Tensor) return new ByteD8UnmodifiableTensorShell((ByteD8Tensor) tensor);
        if (tensor instanceof ByteD9Tensor) return new ByteD9UnmodifiableTensorShell((ByteD9Tensor) tensor);
        return new ByteUnmodifiableTensorShell(tensor);
    }

    @SuppressWarnings("unchecked") //it is surely a Tensor<T> if one goes in, so no problems here
    static <I> IdByteTensor<I> unmodifiableByteTensor(IdByteTensor tensor) {
        if (tensor instanceof ByteD0Tensor) return (IdByteTensor<I>) new IdByteD0UnmodifiableTensorShell((IdByteD0Tensor) tensor);
        if (tensor instanceof ByteD1Tensor) return (IdByteTensor<I>) new IdByteD1UnmodifiableTensorShell((IdByteD1Tensor) tensor);
        if (tensor instanceof ByteD2Tensor) return (IdByteTensor<I>) new IdByteD2UnmodifiableTensorShell((IdByteD2Tensor) tensor);
        if (tensor instanceof ByteD3Tensor) return (IdByteTensor<I>) new IdByteD3UnmodifiableTensorShell((IdByteD3Tensor) tensor);
        if (tensor instanceof ByteD4Tensor) return (IdByteTensor<I>) new IdByteD4UnmodifiableTensorShell((IdByteD4Tensor) tensor);
        if (tensor instanceof ByteD5Tensor) return (IdByteTensor<I>) new IdByteD5UnmodifiableTensorShell((IdByteD5Tensor) tensor);
        if (tensor instanceof ByteD6Tensor) return (IdByteTensor<I>) new IdByteD6UnmodifiableTensorShell((IdByteD6Tensor) tensor);
        if (tensor instanceof ByteD7Tensor) return (IdByteTensor<I>) new IdByteD7UnmodifiableTensorShell((IdByteD7Tensor) tensor);
        if (tensor instanceof ByteD8Tensor) return (IdByteTensor<I>) new IdByteD8UnmodifiableTensorShell((IdByteD8Tensor) tensor);
        if (tensor instanceof ByteD9Tensor) return (IdByteTensor<I>) new IdByteD9UnmodifiableTensorShell((IdByteD9Tensor) tensor);
        return (IdByteTensor<I>) new IdByteUnmodifiableTensorShell(tensor);
    }

    //@SuppressWarnings("unchecked") //it is surely a Tensor<T> if one goes in, so no problems here
    static ShortTensor unmodifiableTensor(ShortTensor tensor) {
        if (tensor instanceof ShortD0Tensor) return new ShortD0UnmodifiableTensorShell((ShortD0Tensor) tensor);
        if (tensor instanceof ShortD1Tensor) return new ShortD1UnmodifiableTensorShell((ShortD1Tensor) tensor);
        if (tensor instanceof ShortD2Tensor) return new ShortD2UnmodifiableTensorShell((ShortD2Tensor) tensor);
        if (tensor instanceof ShortD3Tensor) return new ShortD3UnmodifiableTensorShell((ShortD3Tensor) tensor);
        if (tensor instanceof ShortD4Tensor) return new ShortD4UnmodifiableTensorShell((ShortD4Tensor) tensor);
        if (tensor instanceof ShortD5Tensor) return new ShortD5UnmodifiableTensorShell((ShortD5Tensor) tensor);
        if (tensor instanceof ShortD6Tensor) return new ShortD6UnmodifiableTensorShell((ShortD6Tensor) tensor);
        if (tensor instanceof ShortD7Tensor) return new ShortD7UnmodifiableTensorShell((ShortD7Tensor) tensor);
        if (tensor instanceof ShortD8Tensor) return new ShortD8UnmodifiableTensorShell((ShortD8Tensor) tensor);
        if (tensor instanceof ShortD9Tensor) return new ShortD9UnmodifiableTensorShell((ShortD9Tensor) tensor);
        return new ShortUnmodifiableTensorShell(tensor);
    }

    @SuppressWarnings("unchecked") //it is surely a Tensor<T> if one goes in, so no problems here
    static <I> IdShortTensor<I> unmodifiableShortTensor(IdShortTensor tensor) {
        if (tensor instanceof ShortD0Tensor) return (IdShortTensor<I>) new IdShortD0UnmodifiableTensorShell((IdShortD0Tensor) tensor);
        if (tensor instanceof ShortD1Tensor) return (IdShortTensor<I>) new IdShortD1UnmodifiableTensorShell((IdShortD1Tensor) tensor);
        if (tensor instanceof ShortD2Tensor) return (IdShortTensor<I>) new IdShortD2UnmodifiableTensorShell((IdShortD2Tensor) tensor);
        if (tensor instanceof ShortD3Tensor) return (IdShortTensor<I>) new IdShortD3UnmodifiableTensorShell((IdShortD3Tensor) tensor);
        if (tensor instanceof ShortD4Tensor) return (IdShortTensor<I>) new IdShortD4UnmodifiableTensorShell((IdShortD4Tensor) tensor);
        if (tensor instanceof ShortD5Tensor) return (IdShortTensor<I>) new IdShortD5UnmodifiableTensorShell((IdShortD5Tensor) tensor);
        if (tensor instanceof ShortD6Tensor) return (IdShortTensor<I>) new IdShortD6UnmodifiableTensorShell((IdShortD6Tensor) tensor);
        if (tensor instanceof ShortD7Tensor) return (IdShortTensor<I>) new IdShortD7UnmodifiableTensorShell((IdShortD7Tensor) tensor);
        if (tensor instanceof ShortD8Tensor) return (IdShortTensor<I>) new IdShortD8UnmodifiableTensorShell((IdShortD8Tensor) tensor);
        if (tensor instanceof ShortD9Tensor) return (IdShortTensor<I>) new IdShortD9UnmodifiableTensorShell((IdShortD9Tensor) tensor);
        return (IdShortTensor<I>) new IdShortUnmodifiableTensorShell(tensor);
    }

    //@SuppressWarnings("unchecked") //it is surely a Tensor<T> if one goes in, so no problems here
    static IntTensor unmodifiableTensor(IntTensor tensor) {
        if (tensor instanceof IntD0Tensor) return new IntD0UnmodifiableTensorShell((IntD0Tensor) tensor);
        if (tensor instanceof IntD1Tensor) return new IntD1UnmodifiableTensorShell((IntD1Tensor) tensor);
        if (tensor instanceof IntD2Tensor) return new IntD2UnmodifiableTensorShell((IntD2Tensor) tensor);
        if (tensor instanceof IntD3Tensor) return new IntD3UnmodifiableTensorShell((IntD3Tensor) tensor);
        if (tensor instanceof IntD4Tensor) return new IntD4UnmodifiableTensorShell((IntD4Tensor) tensor);
        if (tensor instanceof IntD5Tensor) return new IntD5UnmodifiableTensorShell((IntD5Tensor) tensor);
        if (tensor instanceof IntD6Tensor) return new IntD6UnmodifiableTensorShell((IntD6Tensor) tensor);
        if (tensor instanceof IntD7Tensor) return new IntD7UnmodifiableTensorShell((IntD7Tensor) tensor);
        if (tensor instanceof IntD8Tensor) return new IntD8UnmodifiableTensorShell((IntD8Tensor) tensor);
        if (tensor instanceof IntD9Tensor) return new IntD9UnmodifiableTensorShell((IntD9Tensor) tensor);
        return new IntUnmodifiableTensorShell(tensor);
    }

    @SuppressWarnings("unchecked") //it is surely a Tensor<T> if one goes in, so no problems here
    static <I> IdIntTensor<I> unmodifiableIntTensor(IdIntTensor tensor) {
        if (tensor instanceof IntD0Tensor) return (IdIntTensor<I>) new IdIntD0UnmodifiableTensorShell((IdIntD0Tensor) tensor);
        if (tensor instanceof IntD1Tensor) return (IdIntTensor<I>) new IdIntD1UnmodifiableTensorShell((IdIntD1Tensor) tensor);
        if (tensor instanceof IntD2Tensor) return (IdIntTensor<I>) new IdIntD2UnmodifiableTensorShell((IdIntD2Tensor) tensor);
        if (tensor instanceof IntD3Tensor) return (IdIntTensor<I>) new IdIntD3UnmodifiableTensorShell((IdIntD3Tensor) tensor);
        if (tensor instanceof IntD4Tensor) return (IdIntTensor<I>) new IdIntD4UnmodifiableTensorShell((IdIntD4Tensor) tensor);
        if (tensor instanceof IntD5Tensor) return (IdIntTensor<I>) new IdIntD5UnmodifiableTensorShell((IdIntD5Tensor) tensor);
        if (tensor instanceof IntD6Tensor) return (IdIntTensor<I>) new IdIntD6UnmodifiableTensorShell((IdIntD6Tensor) tensor);
        if (tensor instanceof IntD7Tensor) return (IdIntTensor<I>) new IdIntD7UnmodifiableTensorShell((IdIntD7Tensor) tensor);
        if (tensor instanceof IntD8Tensor) return (IdIntTensor<I>) new IdIntD8UnmodifiableTensorShell((IdIntD8Tensor) tensor);
        if (tensor instanceof IntD9Tensor) return (IdIntTensor<I>) new IdIntD9UnmodifiableTensorShell((IdIntD9Tensor) tensor);
        return (IdIntTensor<I>) new IdIntUnmodifiableTensorShell(tensor);
    }

    //@SuppressWarnings("unchecked") //it is surely a Tensor<T> if one goes in, so no problems here
    static LongTensor unmodifiableTensor(LongTensor tensor) {
        if (tensor instanceof LongD0Tensor) return new LongD0UnmodifiableTensorShell((LongD0Tensor) tensor);
        if (tensor instanceof LongD1Tensor) return new LongD1UnmodifiableTensorShell((LongD1Tensor) tensor);
        if (tensor instanceof LongD2Tensor) return new LongD2UnmodifiableTensorShell((LongD2Tensor) tensor);
        if (tensor instanceof LongD3Tensor) return new LongD3UnmodifiableTensorShell((LongD3Tensor) tensor);
        if (tensor instanceof LongD4Tensor) return new LongD4UnmodifiableTensorShell((LongD4Tensor) tensor);
        if (tensor instanceof LongD5Tensor) return new LongD5UnmodifiableTensorShell((LongD5Tensor) tensor);
        if (tensor instanceof LongD6Tensor) return new LongD6UnmodifiableTensorShell((LongD6Tensor) tensor);
        if (tensor instanceof LongD7Tensor) return new LongD7UnmodifiableTensorShell((LongD7Tensor) tensor);
        if (tensor instanceof LongD8Tensor) return new LongD8UnmodifiableTensorShell((LongD8Tensor) tensor);
        if (tensor instanceof LongD9Tensor) return new LongD9UnmodifiableTensorShell((LongD9Tensor) tensor);
        return new LongUnmodifiableTensorShell(tensor);
    }

    @SuppressWarnings("unchecked") //it is surely a Tensor<T> if one goes in, so no problems here
    static <I> IdLongTensor<I> unmodifiableLongTensor(IdLongTensor tensor) {
        if (tensor instanceof LongD0Tensor) return (IdLongTensor<I>) new IdLongD0UnmodifiableTensorShell((IdLongD0Tensor) tensor);
        if (tensor instanceof LongD1Tensor) return (IdLongTensor<I>) new IdLongD1UnmodifiableTensorShell((IdLongD1Tensor) tensor);
        if (tensor instanceof LongD2Tensor) return (IdLongTensor<I>) new IdLongD2UnmodifiableTensorShell((IdLongD2Tensor) tensor);
        if (tensor instanceof LongD3Tensor) return (IdLongTensor<I>) new IdLongD3UnmodifiableTensorShell((IdLongD3Tensor) tensor);
        if (tensor instanceof LongD4Tensor) return (IdLongTensor<I>) new IdLongD4UnmodifiableTensorShell((IdLongD4Tensor) tensor);
        if (tensor instanceof LongD5Tensor) return (IdLongTensor<I>) new IdLongD5UnmodifiableTensorShell((IdLongD5Tensor) tensor);
        if (tensor instanceof LongD6Tensor) return (IdLongTensor<I>) new IdLongD6UnmodifiableTensorShell((IdLongD6Tensor) tensor);
        if (tensor instanceof LongD7Tensor) return (IdLongTensor<I>) new IdLongD7UnmodifiableTensorShell((IdLongD7Tensor) tensor);
        if (tensor instanceof LongD8Tensor) return (IdLongTensor<I>) new IdLongD8UnmodifiableTensorShell((IdLongD8Tensor) tensor);
        if (tensor instanceof LongD9Tensor) return (IdLongTensor<I>) new IdLongD9UnmodifiableTensorShell((IdLongD9Tensor) tensor);
        return (IdLongTensor<I>) new IdLongUnmodifiableTensorShell(tensor);
    }

    //@SuppressWarnings("unchecked") //it is surely a Tensor<T> if one goes in, so no problems here
    static FloatTensor unmodifiableTensor(FloatTensor tensor) {
        if (tensor instanceof FloatD0Tensor) return new FloatD0UnmodifiableTensorShell((FloatD0Tensor) tensor);
        if (tensor instanceof FloatD1Tensor) return new FloatD1UnmodifiableTensorShell((FloatD1Tensor) tensor);
        if (tensor instanceof FloatD2Tensor) return new FloatD2UnmodifiableTensorShell((FloatD2Tensor) tensor);
        if (tensor instanceof FloatD3Tensor) return new FloatD3UnmodifiableTensorShell((FloatD3Tensor) tensor);
        if (tensor instanceof FloatD4Tensor) return new FloatD4UnmodifiableTensorShell((FloatD4Tensor) tensor);
        if (tensor instanceof FloatD5Tensor) return new FloatD5UnmodifiableTensorShell((FloatD5Tensor) tensor);
        if (tensor instanceof FloatD6Tensor) return new FloatD6UnmodifiableTensorShell((FloatD6Tensor) tensor);
        if (tensor instanceof FloatD7Tensor) return new FloatD7UnmodifiableTensorShell((FloatD7Tensor) tensor);
        if (tensor instanceof FloatD8Tensor) return new FloatD8UnmodifiableTensorShell((FloatD8Tensor) tensor);
        if (tensor instanceof FloatD9Tensor) return new FloatD9UnmodifiableTensorShell((FloatD9Tensor) tensor);
        return new FloatUnmodifiableTensorShell(tensor);
    }

    @SuppressWarnings("unchecked") //it is surely a Tensor<T> if one goes in, so no problems here
    static <I> IdFloatTensor<I> unmodifiableFloatTensor(IdFloatTensor tensor) {
        if (tensor instanceof FloatD0Tensor) return (IdFloatTensor<I>) new IdFloatD0UnmodifiableTensorShell((IdFloatD0Tensor) tensor);
        if (tensor instanceof FloatD1Tensor) return (IdFloatTensor<I>) new IdFloatD1UnmodifiableTensorShell((IdFloatD1Tensor) tensor);
        if (tensor instanceof FloatD2Tensor) return (IdFloatTensor<I>) new IdFloatD2UnmodifiableTensorShell((IdFloatD2Tensor) tensor);
        if (tensor instanceof FloatD3Tensor) return (IdFloatTensor<I>) new IdFloatD3UnmodifiableTensorShell((IdFloatD3Tensor) tensor);
        if (tensor instanceof FloatD4Tensor) return (IdFloatTensor<I>) new IdFloatD4UnmodifiableTensorShell((IdFloatD4Tensor) tensor);
        if (tensor instanceof FloatD5Tensor) return (IdFloatTensor<I>) new IdFloatD5UnmodifiableTensorShell((IdFloatD5Tensor) tensor);
        if (tensor instanceof FloatD6Tensor) return (IdFloatTensor<I>) new IdFloatD6UnmodifiableTensorShell((IdFloatD6Tensor) tensor);
        if (tensor instanceof FloatD7Tensor) return (IdFloatTensor<I>) new IdFloatD7UnmodifiableTensorShell((IdFloatD7Tensor) tensor);
        if (tensor instanceof FloatD8Tensor) return (IdFloatTensor<I>) new IdFloatD8UnmodifiableTensorShell((IdFloatD8Tensor) tensor);
        if (tensor instanceof FloatD9Tensor) return (IdFloatTensor<I>) new IdFloatD9UnmodifiableTensorShell((IdFloatD9Tensor) tensor);
        return (IdFloatTensor<I>) new IdFloatUnmodifiableTensorShell(tensor);
    }

    //@SuppressWarnings("unchecked") //it is surely a Tensor<T> if one goes in, so no problems here
    static DoubleTensor unmodifiableTensor(DoubleTensor tensor) {
        if (tensor instanceof DoubleD0Tensor) return new DoubleD0UnmodifiableTensorShell((DoubleD0Tensor) tensor);
        if (tensor instanceof DoubleD1Tensor) return new DoubleD1UnmodifiableTensorShell((DoubleD1Tensor) tensor);
        if (tensor instanceof DoubleD2Tensor) return new DoubleD2UnmodifiableTensorShell((DoubleD2Tensor) tensor);
        if (tensor instanceof DoubleD3Tensor) return new DoubleD3UnmodifiableTensorShell((DoubleD3Tensor) tensor);
        if (tensor instanceof DoubleD4Tensor) return new DoubleD4UnmodifiableTensorShell((DoubleD4Tensor) tensor);
        if (tensor instanceof DoubleD5Tensor) return new DoubleD5UnmodifiableTensorShell((DoubleD5Tensor) tensor);
        if (tensor instanceof DoubleD6Tensor) return new DoubleD6UnmodifiableTensorShell((DoubleD6Tensor) tensor);
        if (tensor instanceof DoubleD7Tensor) return new DoubleD7UnmodifiableTensorShell((DoubleD7Tensor) tensor);
        if (tensor instanceof DoubleD8Tensor) return new DoubleD8UnmodifiableTensorShell((DoubleD8Tensor) tensor);
        if (tensor instanceof DoubleD9Tensor) return new DoubleD9UnmodifiableTensorShell((DoubleD9Tensor) tensor);
        return new DoubleUnmodifiableTensorShell(tensor);
    }

    @SuppressWarnings("unchecked") //it is surely a Tensor<T> if one goes in, so no problems here
    static <I> IdDoubleTensor<I> unmodifiableDoubleTensor(IdDoubleTensor tensor) {
        if (tensor instanceof DoubleD0Tensor) return (IdDoubleTensor<I>) new IdDoubleD0UnmodifiableTensorShell((IdDoubleD0Tensor) tensor);
        if (tensor instanceof DoubleD1Tensor) return (IdDoubleTensor<I>) new IdDoubleD1UnmodifiableTensorShell((IdDoubleD1Tensor) tensor);
        if (tensor instanceof DoubleD2Tensor) return (IdDoubleTensor<I>) new IdDoubleD2UnmodifiableTensorShell((IdDoubleD2Tensor) tensor);
        if (tensor instanceof DoubleD3Tensor) return (IdDoubleTensor<I>) new IdDoubleD3UnmodifiableTensorShell((IdDoubleD3Tensor) tensor);
        if (tensor instanceof DoubleD4Tensor) return (IdDoubleTensor<I>) new IdDoubleD4UnmodifiableTensorShell((IdDoubleD4Tensor) tensor);
        if (tensor instanceof DoubleD5Tensor) return (IdDoubleTensor<I>) new IdDoubleD5UnmodifiableTensorShell((IdDoubleD5Tensor) tensor);
        if (tensor instanceof DoubleD6Tensor) return (IdDoubleTensor<I>) new IdDoubleD6UnmodifiableTensorShell((IdDoubleD6Tensor) tensor);
        if (tensor instanceof DoubleD7Tensor) return (IdDoubleTensor<I>) new IdDoubleD7UnmodifiableTensorShell((IdDoubleD7Tensor) tensor);
        if (tensor instanceof DoubleD8Tensor) return (IdDoubleTensor<I>) new IdDoubleD8UnmodifiableTensorShell((IdDoubleD8Tensor) tensor);
        if (tensor instanceof DoubleD9Tensor) return (IdDoubleTensor<I>) new IdDoubleD9UnmodifiableTensorShell((IdDoubleD9Tensor) tensor);
        return (IdDoubleTensor<I>) new IdDoubleUnmodifiableTensorShell(tensor);
    }

    //@SuppressWarnings("unchecked") //it is surely a Tensor<T> if one goes in, so no problems here
    static CharTensor unmodifiableTensor(CharTensor tensor) {
        if (tensor instanceof CharD0Tensor) return new CharD0UnmodifiableTensorShell((CharD0Tensor) tensor);
        if (tensor instanceof CharD1Tensor) return new CharD1UnmodifiableTensorShell((CharD1Tensor) tensor);
        if (tensor instanceof CharD2Tensor) return new CharD2UnmodifiableTensorShell((CharD2Tensor) tensor);
        if (tensor instanceof CharD3Tensor) return new CharD3UnmodifiableTensorShell((CharD3Tensor) tensor);
        if (tensor instanceof CharD4Tensor) return new CharD4UnmodifiableTensorShell((CharD4Tensor) tensor);
        if (tensor instanceof CharD5Tensor) return new CharD5UnmodifiableTensorShell((CharD5Tensor) tensor);
        if (tensor instanceof CharD6Tensor) return new CharD6UnmodifiableTensorShell((CharD6Tensor) tensor);
        if (tensor instanceof CharD7Tensor) return new CharD7UnmodifiableTensorShell((CharD7Tensor) tensor);
        if (tensor instanceof CharD8Tensor) return new CharD8UnmodifiableTensorShell((CharD8Tensor) tensor);
        if (tensor instanceof CharD9Tensor) return new CharD9UnmodifiableTensorShell((CharD9Tensor) tensor);
        return new CharUnmodifiableTensorShell(tensor);
    }

    @SuppressWarnings("unchecked") //it is surely a Tensor<T> if one goes in, so no problems here
    static <I> IdCharTensor<I> unmodifiableCharTensor(IdCharTensor tensor) {
        if (tensor instanceof CharD0Tensor) return (IdCharTensor<I>) new IdCharD0UnmodifiableTensorShell((IdCharD0Tensor) tensor);
        if (tensor instanceof CharD1Tensor) return (IdCharTensor<I>) new IdCharD1UnmodifiableTensorShell((IdCharD1Tensor) tensor);
        if (tensor instanceof CharD2Tensor) return (IdCharTensor<I>) new IdCharD2UnmodifiableTensorShell((IdCharD2Tensor) tensor);
        if (tensor instanceof CharD3Tensor) return (IdCharTensor<I>) new IdCharD3UnmodifiableTensorShell((IdCharD3Tensor) tensor);
        if (tensor instanceof CharD4Tensor) return (IdCharTensor<I>) new IdCharD4UnmodifiableTensorShell((IdCharD4Tensor) tensor);
        if (tensor instanceof CharD5Tensor) return (IdCharTensor<I>) new IdCharD5UnmodifiableTensorShell((IdCharD5Tensor) tensor);
        if (tensor instanceof CharD6Tensor) return (IdCharTensor<I>) new IdCharD6UnmodifiableTensorShell((IdCharD6Tensor) tensor);
        if (tensor instanceof CharD7Tensor) return (IdCharTensor<I>) new IdCharD7UnmodifiableTensorShell((IdCharD7Tensor) tensor);
        if (tensor instanceof CharD8Tensor) return (IdCharTensor<I>) new IdCharD8UnmodifiableTensorShell((IdCharD8Tensor) tensor);
        if (tensor instanceof CharD9Tensor) return (IdCharTensor<I>) new IdCharD9UnmodifiableTensorShell((IdCharD9Tensor) tensor);
        return (IdCharTensor<I>) new IdCharUnmodifiableTensorShell(tensor);
    }

    //@SuppressWarnings("unchecked") //it is surely a Tensor<T> if one goes in, so no problems here
    static BooleanTensor unmodifiableTensor(BooleanTensor tensor) {
        if (tensor instanceof BooleanD0Tensor) return new BooleanD0UnmodifiableTensorShell((BooleanD0Tensor) tensor);
        if (tensor instanceof BooleanD1Tensor) return new BooleanD1UnmodifiableTensorShell((BooleanD1Tensor) tensor);
        if (tensor instanceof BooleanD2Tensor) return new BooleanD2UnmodifiableTensorShell((BooleanD2Tensor) tensor);
        if (tensor instanceof BooleanD3Tensor) return new BooleanD3UnmodifiableTensorShell((BooleanD3Tensor) tensor);
        if (tensor instanceof BooleanD4Tensor) return new BooleanD4UnmodifiableTensorShell((BooleanD4Tensor) tensor);
        if (tensor instanceof BooleanD5Tensor) return new BooleanD5UnmodifiableTensorShell((BooleanD5Tensor) tensor);
        if (tensor instanceof BooleanD6Tensor) return new BooleanD6UnmodifiableTensorShell((BooleanD6Tensor) tensor);
        if (tensor instanceof BooleanD7Tensor) return new BooleanD7UnmodifiableTensorShell((BooleanD7Tensor) tensor);
        if (tensor instanceof BooleanD8Tensor) return new BooleanD8UnmodifiableTensorShell((BooleanD8Tensor) tensor);
        if (tensor instanceof BooleanD9Tensor) return new BooleanD9UnmodifiableTensorShell((BooleanD9Tensor) tensor);
        return new BooleanUnmodifiableTensorShell(tensor);
    }

    @SuppressWarnings("unchecked") //it is surely a Tensor<T> if one goes in, so no problems here
    static <I> IdBooleanTensor<I> unmodifiableBooleanTensor(IdBooleanTensor tensor) {
        if (tensor instanceof BooleanD0Tensor) return (IdBooleanTensor<I>) new IdBooleanD0UnmodifiableTensorShell((IdBooleanD0Tensor) tensor);
        if (tensor instanceof BooleanD1Tensor) return (IdBooleanTensor<I>) new IdBooleanD1UnmodifiableTensorShell((IdBooleanD1Tensor) tensor);
        if (tensor instanceof BooleanD2Tensor) return (IdBooleanTensor<I>) new IdBooleanD2UnmodifiableTensorShell((IdBooleanD2Tensor) tensor);
        if (tensor instanceof BooleanD3Tensor) return (IdBooleanTensor<I>) new IdBooleanD3UnmodifiableTensorShell((IdBooleanD3Tensor) tensor);
        if (tensor instanceof BooleanD4Tensor) return (IdBooleanTensor<I>) new IdBooleanD4UnmodifiableTensorShell((IdBooleanD4Tensor) tensor);
        if (tensor instanceof BooleanD5Tensor) return (IdBooleanTensor<I>) new IdBooleanD5UnmodifiableTensorShell((IdBooleanD5Tensor) tensor);
        if (tensor instanceof BooleanD6Tensor) return (IdBooleanTensor<I>) new IdBooleanD6UnmodifiableTensorShell((IdBooleanD6Tensor) tensor);
        if (tensor instanceof BooleanD7Tensor) return (IdBooleanTensor<I>) new IdBooleanD7UnmodifiableTensorShell((IdBooleanD7Tensor) tensor);
        if (tensor instanceof BooleanD8Tensor) return (IdBooleanTensor<I>) new IdBooleanD8UnmodifiableTensorShell((IdBooleanD8Tensor) tensor);
        if (tensor instanceof BooleanD9Tensor) return (IdBooleanTensor<I>) new IdBooleanD9UnmodifiableTensorShell((IdBooleanD9Tensor) tensor);
        return (IdBooleanTensor<I>) new IdBooleanUnmodifiableTensorShell(tensor);
    }

    private static class ByteUnmodifiableTensorShell extends UnmodifiableTensorShell<Byte> implements ByteTensor {
        private final ByteTensor tensor;

        private ByteUnmodifiableTensorShell(ByteTensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }

        public byte getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        public void setCell(byte value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public ByteTypeSafeArray getTensorValues(Class<Byte> type) {
            return tensor.getTensorValues(type);
        }
        
        public ByteTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        public void setTensorValues(ByteTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public <I> IdByteTensor<I> getReferenceTensor(Index<I> index) {
            return (IdByteTensor<I>) super.getReferenceTensor(index);
        }

        protected ByteTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        } 
    }

    private static class ShortUnmodifiableTensorShell extends UnmodifiableTensorShell<Short> implements ShortTensor {
        private final ShortTensor tensor;

        private ShortUnmodifiableTensorShell(ShortTensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }

        public short getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        public void setCell(short value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public ShortTypeSafeArray getTensorValues(Class<Short> type) {
            return tensor.getTensorValues(type);
        }
        
        public ShortTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        public void setTensorValues(ShortTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public <I> IdShortTensor<I> getReferenceTensor(Index<I> index) {
            return (IdShortTensor<I>) super.getReferenceTensor(index);
        }

        protected ShortTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        } 
    }

    private static class IntUnmodifiableTensorShell extends UnmodifiableTensorShell<Integer> implements IntTensor {
        private final IntTensor tensor;

        private IntUnmodifiableTensorShell(IntTensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }

        public int getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        public void setCell(int value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public IntTypeSafeArray getTensorValues(Class<Integer> type) {
            return tensor.getTensorValues(type);
        }
        
        public IntTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        public void setTensorValues(IntTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public <I> IdIntTensor<I> getReferenceTensor(Index<I> index) {
            return (IdIntTensor<I>) super.getReferenceTensor(index);
        }

        protected IntTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        } 
    }

    private static class LongUnmodifiableTensorShell extends UnmodifiableTensorShell<Long> implements LongTensor {
        private final LongTensor tensor;

        private LongUnmodifiableTensorShell(LongTensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }

        public long getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        public void setCell(long value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public LongTypeSafeArray getTensorValues(Class<Long> type) {
            return tensor.getTensorValues(type);
        }
        
        public LongTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        public void setTensorValues(LongTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public <I> IdLongTensor<I> getReferenceTensor(Index<I> index) {
            return (IdLongTensor<I>) super.getReferenceTensor(index);
        }

        protected LongTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        } 
    }

    private static class FloatUnmodifiableTensorShell extends UnmodifiableTensorShell<Float> implements FloatTensor {
        private final FloatTensor tensor;

        private FloatUnmodifiableTensorShell(FloatTensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }

        public float getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        public void setCell(float value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public FloatTypeSafeArray getTensorValues(Class<Float> type) {
            return tensor.getTensorValues(type);
        }
        
        public FloatTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        public void setTensorValues(FloatTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public <I> IdFloatTensor<I> getReferenceTensor(Index<I> index) {
            return (IdFloatTensor<I>) super.getReferenceTensor(index);
        }

        protected FloatTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        } 
    }

    private static class DoubleUnmodifiableTensorShell extends UnmodifiableTensorShell<Double> implements DoubleTensor {
        private final DoubleTensor tensor;

        private DoubleUnmodifiableTensorShell(DoubleTensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }

        public double getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        public void setCell(double value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public DoubleTypeSafeArray getTensorValues(Class<Double> type) {
            return tensor.getTensorValues(type);
        }
        
        public DoubleTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        public void setTensorValues(DoubleTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public <I> IdDoubleTensor<I> getReferenceTensor(Index<I> index) {
            return (IdDoubleTensor<I>) super.getReferenceTensor(index);
        }

        protected DoubleTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        } 
    }

    private static class CharUnmodifiableTensorShell extends UnmodifiableTensorShell<Character> implements CharTensor {
        private final CharTensor tensor;

        private CharUnmodifiableTensorShell(CharTensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }

        public char getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        public void setCell(char value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public CharTypeSafeArray getTensorValues(Class<Character> type) {
            return tensor.getTensorValues(type);
        }
        
        public CharTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        public void setTensorValues(CharTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public <I> IdCharTensor<I> getReferenceTensor(Index<I> index) {
            return (IdCharTensor<I>) super.getReferenceTensor(index);
        }

        protected CharTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        } 
    }

    private static class BooleanUnmodifiableTensorShell extends UnmodifiableTensorShell<Boolean> implements BooleanTensor {
        private final BooleanTensor tensor;

        private BooleanUnmodifiableTensorShell(BooleanTensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }

        public boolean getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        public void setCell(boolean value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public BooleanTypeSafeArray getTensorValues(Class<Boolean> type) {
            return tensor.getTensorValues(type);
        }
        
        public BooleanTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        public void setTensorValues(BooleanTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public <I> IdBooleanTensor<I> getReferenceTensor(Index<I> index) {
            return (IdBooleanTensor<I>) super.getReferenceTensor(index);
        }

        protected BooleanTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        } 
    }
 
    private static class ByteD0UnmodifiableTensorShell extends D0UnmodifiableTensorShell<Byte> implements ByteScalar {
        private final ByteD0Tensor tensor;
        
        private ByteD0UnmodifiableTensorShell(ByteD0Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public byte getCell() {
            return tensor.getCell();
        }
        
        public void setCell(byte value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public byte getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(byte value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ByteTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ByteTypeSafeArray getTensorValues(Class<Byte> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ByteTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdByteTensor<I> getReferenceTensor(Index<I> index) {
            return (IdByteTensor<I>) super.getReferenceTensor(index);
        }

        protected ByteTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class ByteD1UnmodifiableTensorShell extends D1UnmodifiableTensorShell<Byte> implements ByteVector {
        private final ByteD1Tensor tensor;
        
        private ByteD1UnmodifiableTensorShell(ByteD1Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public byte getCell(int index) {
            return tensor.getCell(index);
        }
        
        public void setCell(byte value,int index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public byte getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(byte value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ByteTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ByteTypeSafeArray getTensorValues(Class<Byte> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ByteTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdByteTensor<I> getReferenceTensor(Index<I> index) {
            return (IdByteTensor<I>) super.getReferenceTensor(index);
        }

        protected ByteTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class ShortD0UnmodifiableTensorShell extends D0UnmodifiableTensorShell<Short> implements ShortScalar {
        private final ShortD0Tensor tensor;
        
        private ShortD0UnmodifiableTensorShell(ShortD0Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public short getCell() {
            return tensor.getCell();
        }
        
        public void setCell(short value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public short getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(short value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ShortTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ShortTypeSafeArray getTensorValues(Class<Short> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ShortTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdShortTensor<I> getReferenceTensor(Index<I> index) {
            return (IdShortTensor<I>) super.getReferenceTensor(index);
        }

        protected ShortTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class ShortD1UnmodifiableTensorShell extends D1UnmodifiableTensorShell<Short> implements ShortVector {
        private final ShortD1Tensor tensor;
        
        private ShortD1UnmodifiableTensorShell(ShortD1Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public short getCell(int index) {
            return tensor.getCell(index);
        }
        
        public void setCell(short value,int index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public short getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(short value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ShortTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ShortTypeSafeArray getTensorValues(Class<Short> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ShortTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdShortTensor<I> getReferenceTensor(Index<I> index) {
            return (IdShortTensor<I>) super.getReferenceTensor(index);
        }

        protected ShortTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class IntD0UnmodifiableTensorShell extends D0UnmodifiableTensorShell<Integer> implements IntScalar {
        private final IntD0Tensor tensor;
        
        private IntD0UnmodifiableTensorShell(IntD0Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public int getCell() {
            return tensor.getCell();
        }
        
        public void setCell(int value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public int getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(int value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(IntTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public IntTypeSafeArray getTensorValues(Class<Integer> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public IntTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdIntTensor<I> getReferenceTensor(Index<I> index) {
            return (IdIntTensor<I>) super.getReferenceTensor(index);
        }

        protected IntTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class IntD1UnmodifiableTensorShell extends D1UnmodifiableTensorShell<Integer> implements IntVector {
        private final IntD1Tensor tensor;
        
        private IntD1UnmodifiableTensorShell(IntD1Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public int getCell(int index) {
            return tensor.getCell(index);
        }
        
        public void setCell(int value,int index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public int getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(int value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(IntTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public IntTypeSafeArray getTensorValues(Class<Integer> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public IntTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdIntTensor<I> getReferenceTensor(Index<I> index) {
            return (IdIntTensor<I>) super.getReferenceTensor(index);
        }

        protected IntTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class LongD0UnmodifiableTensorShell extends D0UnmodifiableTensorShell<Long> implements LongScalar {
        private final LongD0Tensor tensor;
        
        private LongD0UnmodifiableTensorShell(LongD0Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public long getCell() {
            return tensor.getCell();
        }
        
        public void setCell(long value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public long getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(long value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(LongTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public LongTypeSafeArray getTensorValues(Class<Long> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public LongTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdLongTensor<I> getReferenceTensor(Index<I> index) {
            return (IdLongTensor<I>) super.getReferenceTensor(index);
        }

        protected LongTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class LongD1UnmodifiableTensorShell extends D1UnmodifiableTensorShell<Long> implements LongVector {
        private final LongD1Tensor tensor;
        
        private LongD1UnmodifiableTensorShell(LongD1Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public long getCell(int index) {
            return tensor.getCell(index);
        }
        
        public void setCell(long value,int index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public long getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(long value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(LongTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public LongTypeSafeArray getTensorValues(Class<Long> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public LongTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdLongTensor<I> getReferenceTensor(Index<I> index) {
            return (IdLongTensor<I>) super.getReferenceTensor(index);
        }

        protected LongTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class FloatD0UnmodifiableTensorShell extends D0UnmodifiableTensorShell<Float> implements FloatScalar {
        private final FloatD0Tensor tensor;
        
        private FloatD0UnmodifiableTensorShell(FloatD0Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public float getCell() {
            return tensor.getCell();
        }
        
        public void setCell(float value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public float getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(float value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(FloatTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public FloatTypeSafeArray getTensorValues(Class<Float> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public FloatTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdFloatTensor<I> getReferenceTensor(Index<I> index) {
            return (IdFloatTensor<I>) super.getReferenceTensor(index);
        }

        protected FloatTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class FloatD1UnmodifiableTensorShell extends D1UnmodifiableTensorShell<Float> implements FloatVector {
        private final FloatD1Tensor tensor;
        
        private FloatD1UnmodifiableTensorShell(FloatD1Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public float getCell(int index) {
            return tensor.getCell(index);
        }
        
        public void setCell(float value,int index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public float getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(float value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(FloatTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public FloatTypeSafeArray getTensorValues(Class<Float> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public FloatTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdFloatTensor<I> getReferenceTensor(Index<I> index) {
            return (IdFloatTensor<I>) super.getReferenceTensor(index);
        }

        protected FloatTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class DoubleD0UnmodifiableTensorShell extends D0UnmodifiableTensorShell<Double> implements DoubleScalar {
        private final DoubleD0Tensor tensor;
        
        private DoubleD0UnmodifiableTensorShell(DoubleD0Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public double getCell() {
            return tensor.getCell();
        }
        
        public void setCell(double value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public double getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(double value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(DoubleTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public DoubleTypeSafeArray getTensorValues(Class<Double> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public DoubleTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdDoubleTensor<I> getReferenceTensor(Index<I> index) {
            return (IdDoubleTensor<I>) super.getReferenceTensor(index);
        }

        protected DoubleTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class DoubleD1UnmodifiableTensorShell extends D1UnmodifiableTensorShell<Double> implements DoubleVector {
        private final DoubleD1Tensor tensor;
        
        private DoubleD1UnmodifiableTensorShell(DoubleD1Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public double getCell(int index) {
            return tensor.getCell(index);
        }
        
        public void setCell(double value,int index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public double getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(double value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(DoubleTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public DoubleTypeSafeArray getTensorValues(Class<Double> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public DoubleTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdDoubleTensor<I> getReferenceTensor(Index<I> index) {
            return (IdDoubleTensor<I>) super.getReferenceTensor(index);
        }

        protected DoubleTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class CharD0UnmodifiableTensorShell extends D0UnmodifiableTensorShell<Character> implements CharScalar {
        private final CharD0Tensor tensor;
        
        private CharD0UnmodifiableTensorShell(CharD0Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public char getCell() {
            return tensor.getCell();
        }
        
        public void setCell(char value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public char getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(char value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(CharTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public CharTypeSafeArray getTensorValues(Class<Character> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public CharTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdCharTensor<I> getReferenceTensor(Index<I> index) {
            return (IdCharTensor<I>) super.getReferenceTensor(index);
        }

        protected CharTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class CharD1UnmodifiableTensorShell extends D1UnmodifiableTensorShell<Character> implements CharVector {
        private final CharD1Tensor tensor;
        
        private CharD1UnmodifiableTensorShell(CharD1Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public char getCell(int index) {
            return tensor.getCell(index);
        }
        
        public void setCell(char value,int index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public char getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(char value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(CharTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public CharTypeSafeArray getTensorValues(Class<Character> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public CharTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdCharTensor<I> getReferenceTensor(Index<I> index) {
            return (IdCharTensor<I>) super.getReferenceTensor(index);
        }

        protected CharTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class BooleanD0UnmodifiableTensorShell extends D0UnmodifiableTensorShell<Boolean> implements BooleanScalar {
        private final BooleanD0Tensor tensor;
        
        private BooleanD0UnmodifiableTensorShell(BooleanD0Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public boolean getCell() {
            return tensor.getCell();
        }
        
        public void setCell(boolean value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public boolean getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(boolean value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(BooleanTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public BooleanTypeSafeArray getTensorValues(Class<Boolean> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public BooleanTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdBooleanTensor<I> getReferenceTensor(Index<I> index) {
            return (IdBooleanTensor<I>) super.getReferenceTensor(index);
        }

        protected BooleanTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class BooleanD1UnmodifiableTensorShell extends D1UnmodifiableTensorShell<Boolean> implements BooleanVector {
        private final BooleanD1Tensor tensor;
        
        private BooleanD1UnmodifiableTensorShell(BooleanD1Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public boolean getCell(int index) {
            return tensor.getCell(index);
        }
        
        public void setCell(boolean value,int index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public boolean getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(boolean value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(BooleanTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public BooleanTypeSafeArray getTensorValues(Class<Boolean> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public BooleanTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdBooleanTensor<I> getReferenceTensor(Index<I> index) {
            return (IdBooleanTensor<I>) super.getReferenceTensor(index);
        }

        protected BooleanTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class ByteD2UnmodifiableTensorShell extends D2UnmodifiableTensorShell<Byte> implements ByteMatrix {
        private final ByteD2Tensor tensor;
        
        private ByteD2UnmodifiableTensorShell(ByteD2Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public byte getCell(int d0index, int d1index) {
            return tensor.getCell(d0index,d1index);
        }
        
        public void setCell(byte value,int d0index, int d1index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public byte getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(byte value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ByteTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ByteTypeSafeArray getTensorValues(Class<Byte> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ByteTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdByteTensor<I> getReferenceTensor(Index<I> index) {
            return (IdByteTensor<I>) super.getReferenceTensor(index);
        }

        protected ByteTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class ShortD2UnmodifiableTensorShell extends D2UnmodifiableTensorShell<Short> implements ShortMatrix {
        private final ShortD2Tensor tensor;
        
        private ShortD2UnmodifiableTensorShell(ShortD2Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public short getCell(int d0index, int d1index) {
            return tensor.getCell(d0index,d1index);
        }
        
        public void setCell(short value,int d0index, int d1index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public short getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(short value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ShortTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ShortTypeSafeArray getTensorValues(Class<Short> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ShortTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdShortTensor<I> getReferenceTensor(Index<I> index) {
            return (IdShortTensor<I>) super.getReferenceTensor(index);
        }

        protected ShortTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class IntD2UnmodifiableTensorShell extends D2UnmodifiableTensorShell<Integer> implements IntMatrix {
        private final IntD2Tensor tensor;
        
        private IntD2UnmodifiableTensorShell(IntD2Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public int getCell(int d0index, int d1index) {
            return tensor.getCell(d0index,d1index);
        }
        
        public void setCell(int value,int d0index, int d1index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public int getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(int value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(IntTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public IntTypeSafeArray getTensorValues(Class<Integer> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public IntTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdIntTensor<I> getReferenceTensor(Index<I> index) {
            return (IdIntTensor<I>) super.getReferenceTensor(index);
        }

        protected IntTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class LongD2UnmodifiableTensorShell extends D2UnmodifiableTensorShell<Long> implements LongMatrix {
        private final LongD2Tensor tensor;
        
        private LongD2UnmodifiableTensorShell(LongD2Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public long getCell(int d0index, int d1index) {
            return tensor.getCell(d0index,d1index);
        }
        
        public void setCell(long value,int d0index, int d1index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public long getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(long value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(LongTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public LongTypeSafeArray getTensorValues(Class<Long> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public LongTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdLongTensor<I> getReferenceTensor(Index<I> index) {
            return (IdLongTensor<I>) super.getReferenceTensor(index);
        }

        protected LongTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class FloatD2UnmodifiableTensorShell extends D2UnmodifiableTensorShell<Float> implements FloatMatrix {
        private final FloatD2Tensor tensor;
        
        private FloatD2UnmodifiableTensorShell(FloatD2Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public float getCell(int d0index, int d1index) {
            return tensor.getCell(d0index,d1index);
        }
        
        public void setCell(float value,int d0index, int d1index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public float getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(float value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(FloatTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public FloatTypeSafeArray getTensorValues(Class<Float> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public FloatTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdFloatTensor<I> getReferenceTensor(Index<I> index) {
            return (IdFloatTensor<I>) super.getReferenceTensor(index);
        }

        protected FloatTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class DoubleD2UnmodifiableTensorShell extends D2UnmodifiableTensorShell<Double> implements DoubleMatrix {
        private final DoubleD2Tensor tensor;
        
        private DoubleD2UnmodifiableTensorShell(DoubleD2Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public double getCell(int d0index, int d1index) {
            return tensor.getCell(d0index,d1index);
        }
        
        public void setCell(double value,int d0index, int d1index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public double getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(double value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(DoubleTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public DoubleTypeSafeArray getTensorValues(Class<Double> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public DoubleTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdDoubleTensor<I> getReferenceTensor(Index<I> index) {
            return (IdDoubleTensor<I>) super.getReferenceTensor(index);
        }

        protected DoubleTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class CharD2UnmodifiableTensorShell extends D2UnmodifiableTensorShell<Character> implements CharMatrix {
        private final CharD2Tensor tensor;
        
        private CharD2UnmodifiableTensorShell(CharD2Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public char getCell(int d0index, int d1index) {
            return tensor.getCell(d0index,d1index);
        }
        
        public void setCell(char value,int d0index, int d1index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public char getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(char value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(CharTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public CharTypeSafeArray getTensorValues(Class<Character> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public CharTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdCharTensor<I> getReferenceTensor(Index<I> index) {
            return (IdCharTensor<I>) super.getReferenceTensor(index);
        }

        protected CharTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class BooleanD2UnmodifiableTensorShell extends D2UnmodifiableTensorShell<Boolean> implements BooleanMatrix {
        private final BooleanD2Tensor tensor;
        
        private BooleanD2UnmodifiableTensorShell(BooleanD2Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public boolean getCell(int d0index, int d1index) {
            return tensor.getCell(d0index,d1index);
        }
        
        public void setCell(boolean value,int d0index, int d1index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public boolean getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(boolean value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(BooleanTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public BooleanTypeSafeArray getTensorValues(Class<Boolean> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public BooleanTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdBooleanTensor<I> getReferenceTensor(Index<I> index) {
            return (IdBooleanTensor<I>) super.getReferenceTensor(index);
        }

        protected BooleanTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class ByteD3UnmodifiableTensorShell extends D3UnmodifiableTensorShell<Byte> implements ByteD3Tensor {
        private final ByteD3Tensor tensor;
        
        private ByteD3UnmodifiableTensorShell(ByteD3Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public byte getCell(int d0index, int d1index, int d2index) {
            return tensor.getCell(d0index,d1index,d2index);
        }
        
        public void setCell(byte value,int d0index, int d1index, int d2index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public byte getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(byte value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ByteTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ByteTypeSafeArray getTensorValues(Class<Byte> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ByteTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdByteTensor<I> getReferenceTensor(Index<I> index) {
            return (IdByteTensor<I>) super.getReferenceTensor(index);
        }

        protected ByteTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class ShortD3UnmodifiableTensorShell extends D3UnmodifiableTensorShell<Short> implements ShortD3Tensor {
        private final ShortD3Tensor tensor;
        
        private ShortD3UnmodifiableTensorShell(ShortD3Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public short getCell(int d0index, int d1index, int d2index) {
            return tensor.getCell(d0index,d1index,d2index);
        }
        
        public void setCell(short value,int d0index, int d1index, int d2index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public short getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(short value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ShortTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ShortTypeSafeArray getTensorValues(Class<Short> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ShortTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdShortTensor<I> getReferenceTensor(Index<I> index) {
            return (IdShortTensor<I>) super.getReferenceTensor(index);
        }

        protected ShortTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class IntD3UnmodifiableTensorShell extends D3UnmodifiableTensorShell<Integer> implements IntD3Tensor {
        private final IntD3Tensor tensor;
        
        private IntD3UnmodifiableTensorShell(IntD3Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public int getCell(int d0index, int d1index, int d2index) {
            return tensor.getCell(d0index,d1index,d2index);
        }
        
        public void setCell(int value,int d0index, int d1index, int d2index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public int getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(int value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(IntTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public IntTypeSafeArray getTensorValues(Class<Integer> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public IntTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdIntTensor<I> getReferenceTensor(Index<I> index) {
            return (IdIntTensor<I>) super.getReferenceTensor(index);
        }

        protected IntTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class LongD3UnmodifiableTensorShell extends D3UnmodifiableTensorShell<Long> implements LongD3Tensor {
        private final LongD3Tensor tensor;
        
        private LongD3UnmodifiableTensorShell(LongD3Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public long getCell(int d0index, int d1index, int d2index) {
            return tensor.getCell(d0index,d1index,d2index);
        }
        
        public void setCell(long value,int d0index, int d1index, int d2index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public long getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(long value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(LongTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public LongTypeSafeArray getTensorValues(Class<Long> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public LongTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdLongTensor<I> getReferenceTensor(Index<I> index) {
            return (IdLongTensor<I>) super.getReferenceTensor(index);
        }

        protected LongTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class FloatD3UnmodifiableTensorShell extends D3UnmodifiableTensorShell<Float> implements FloatD3Tensor {
        private final FloatD3Tensor tensor;
        
        private FloatD3UnmodifiableTensorShell(FloatD3Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public float getCell(int d0index, int d1index, int d2index) {
            return tensor.getCell(d0index,d1index,d2index);
        }
        
        public void setCell(float value,int d0index, int d1index, int d2index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public float getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(float value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(FloatTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public FloatTypeSafeArray getTensorValues(Class<Float> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public FloatTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdFloatTensor<I> getReferenceTensor(Index<I> index) {
            return (IdFloatTensor<I>) super.getReferenceTensor(index);
        }

        protected FloatTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class DoubleD3UnmodifiableTensorShell extends D3UnmodifiableTensorShell<Double> implements DoubleD3Tensor {
        private final DoubleD3Tensor tensor;
        
        private DoubleD3UnmodifiableTensorShell(DoubleD3Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public double getCell(int d0index, int d1index, int d2index) {
            return tensor.getCell(d0index,d1index,d2index);
        }
        
        public void setCell(double value,int d0index, int d1index, int d2index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public double getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(double value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(DoubleTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public DoubleTypeSafeArray getTensorValues(Class<Double> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public DoubleTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdDoubleTensor<I> getReferenceTensor(Index<I> index) {
            return (IdDoubleTensor<I>) super.getReferenceTensor(index);
        }

        protected DoubleTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class CharD3UnmodifiableTensorShell extends D3UnmodifiableTensorShell<Character> implements CharD3Tensor {
        private final CharD3Tensor tensor;
        
        private CharD3UnmodifiableTensorShell(CharD3Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public char getCell(int d0index, int d1index, int d2index) {
            return tensor.getCell(d0index,d1index,d2index);
        }
        
        public void setCell(char value,int d0index, int d1index, int d2index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public char getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(char value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(CharTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public CharTypeSafeArray getTensorValues(Class<Character> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public CharTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdCharTensor<I> getReferenceTensor(Index<I> index) {
            return (IdCharTensor<I>) super.getReferenceTensor(index);
        }

        protected CharTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class BooleanD3UnmodifiableTensorShell extends D3UnmodifiableTensorShell<Boolean> implements BooleanD3Tensor {
        private final BooleanD3Tensor tensor;
        
        private BooleanD3UnmodifiableTensorShell(BooleanD3Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public boolean getCell(int d0index, int d1index, int d2index) {
            return tensor.getCell(d0index,d1index,d2index);
        }
        
        public void setCell(boolean value,int d0index, int d1index, int d2index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public boolean getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(boolean value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(BooleanTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public BooleanTypeSafeArray getTensorValues(Class<Boolean> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public BooleanTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdBooleanTensor<I> getReferenceTensor(Index<I> index) {
            return (IdBooleanTensor<I>) super.getReferenceTensor(index);
        }

        protected BooleanTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class ByteD4UnmodifiableTensorShell extends D4UnmodifiableTensorShell<Byte> implements ByteD4Tensor {
        private final ByteD4Tensor tensor;
        
        private ByteD4UnmodifiableTensorShell(ByteD4Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public byte getCell(int d0index, int d1index, int d2index, int d3index) {
            return tensor.getCell(d0index,d1index,d2index,d3index);
        }
        
        public void setCell(byte value,int d0index, int d1index, int d2index, int d3index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public byte getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(byte value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ByteTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ByteTypeSafeArray getTensorValues(Class<Byte> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ByteTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdByteTensor<I> getReferenceTensor(Index<I> index) {
            return (IdByteTensor<I>) super.getReferenceTensor(index);
        }

        protected ByteTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class ShortD4UnmodifiableTensorShell extends D4UnmodifiableTensorShell<Short> implements ShortD4Tensor {
        private final ShortD4Tensor tensor;
        
        private ShortD4UnmodifiableTensorShell(ShortD4Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public short getCell(int d0index, int d1index, int d2index, int d3index) {
            return tensor.getCell(d0index,d1index,d2index,d3index);
        }
        
        public void setCell(short value,int d0index, int d1index, int d2index, int d3index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public short getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(short value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ShortTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ShortTypeSafeArray getTensorValues(Class<Short> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ShortTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdShortTensor<I> getReferenceTensor(Index<I> index) {
            return (IdShortTensor<I>) super.getReferenceTensor(index);
        }

        protected ShortTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class IntD4UnmodifiableTensorShell extends D4UnmodifiableTensorShell<Integer> implements IntD4Tensor {
        private final IntD4Tensor tensor;
        
        private IntD4UnmodifiableTensorShell(IntD4Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public int getCell(int d0index, int d1index, int d2index, int d3index) {
            return tensor.getCell(d0index,d1index,d2index,d3index);
        }
        
        public void setCell(int value,int d0index, int d1index, int d2index, int d3index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public int getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(int value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(IntTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public IntTypeSafeArray getTensorValues(Class<Integer> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public IntTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdIntTensor<I> getReferenceTensor(Index<I> index) {
            return (IdIntTensor<I>) super.getReferenceTensor(index);
        }

        protected IntTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class LongD4UnmodifiableTensorShell extends D4UnmodifiableTensorShell<Long> implements LongD4Tensor {
        private final LongD4Tensor tensor;
        
        private LongD4UnmodifiableTensorShell(LongD4Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public long getCell(int d0index, int d1index, int d2index, int d3index) {
            return tensor.getCell(d0index,d1index,d2index,d3index);
        }
        
        public void setCell(long value,int d0index, int d1index, int d2index, int d3index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public long getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(long value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(LongTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public LongTypeSafeArray getTensorValues(Class<Long> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public LongTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdLongTensor<I> getReferenceTensor(Index<I> index) {
            return (IdLongTensor<I>) super.getReferenceTensor(index);
        }

        protected LongTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class FloatD4UnmodifiableTensorShell extends D4UnmodifiableTensorShell<Float> implements FloatD4Tensor {
        private final FloatD4Tensor tensor;
        
        private FloatD4UnmodifiableTensorShell(FloatD4Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public float getCell(int d0index, int d1index, int d2index, int d3index) {
            return tensor.getCell(d0index,d1index,d2index,d3index);
        }
        
        public void setCell(float value,int d0index, int d1index, int d2index, int d3index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public float getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(float value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(FloatTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public FloatTypeSafeArray getTensorValues(Class<Float> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public FloatTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdFloatTensor<I> getReferenceTensor(Index<I> index) {
            return (IdFloatTensor<I>) super.getReferenceTensor(index);
        }

        protected FloatTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class DoubleD4UnmodifiableTensorShell extends D4UnmodifiableTensorShell<Double> implements DoubleD4Tensor {
        private final DoubleD4Tensor tensor;
        
        private DoubleD4UnmodifiableTensorShell(DoubleD4Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public double getCell(int d0index, int d1index, int d2index, int d3index) {
            return tensor.getCell(d0index,d1index,d2index,d3index);
        }
        
        public void setCell(double value,int d0index, int d1index, int d2index, int d3index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public double getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(double value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(DoubleTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public DoubleTypeSafeArray getTensorValues(Class<Double> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public DoubleTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdDoubleTensor<I> getReferenceTensor(Index<I> index) {
            return (IdDoubleTensor<I>) super.getReferenceTensor(index);
        }

        protected DoubleTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class CharD4UnmodifiableTensorShell extends D4UnmodifiableTensorShell<Character> implements CharD4Tensor {
        private final CharD4Tensor tensor;
        
        private CharD4UnmodifiableTensorShell(CharD4Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public char getCell(int d0index, int d1index, int d2index, int d3index) {
            return tensor.getCell(d0index,d1index,d2index,d3index);
        }
        
        public void setCell(char value,int d0index, int d1index, int d2index, int d3index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public char getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(char value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(CharTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public CharTypeSafeArray getTensorValues(Class<Character> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public CharTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdCharTensor<I> getReferenceTensor(Index<I> index) {
            return (IdCharTensor<I>) super.getReferenceTensor(index);
        }

        protected CharTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class BooleanD4UnmodifiableTensorShell extends D4UnmodifiableTensorShell<Boolean> implements BooleanD4Tensor {
        private final BooleanD4Tensor tensor;
        
        private BooleanD4UnmodifiableTensorShell(BooleanD4Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public boolean getCell(int d0index, int d1index, int d2index, int d3index) {
            return tensor.getCell(d0index,d1index,d2index,d3index);
        }
        
        public void setCell(boolean value,int d0index, int d1index, int d2index, int d3index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public boolean getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(boolean value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(BooleanTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public BooleanTypeSafeArray getTensorValues(Class<Boolean> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public BooleanTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdBooleanTensor<I> getReferenceTensor(Index<I> index) {
            return (IdBooleanTensor<I>) super.getReferenceTensor(index);
        }

        protected BooleanTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class ByteD5UnmodifiableTensorShell extends D5UnmodifiableTensorShell<Byte> implements ByteD5Tensor {
        private final ByteD5Tensor tensor;
        
        private ByteD5UnmodifiableTensorShell(ByteD5Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public byte getCell(int d0index, int d1index, int d2index, int d3index, int d4index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index);
        }
        
        public void setCell(byte value,int d0index, int d1index, int d2index, int d3index, int d4index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public byte getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(byte value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ByteTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ByteTypeSafeArray getTensorValues(Class<Byte> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ByteTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdByteTensor<I> getReferenceTensor(Index<I> index) {
            return (IdByteTensor<I>) super.getReferenceTensor(index);
        }

        protected ByteTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class ShortD5UnmodifiableTensorShell extends D5UnmodifiableTensorShell<Short> implements ShortD5Tensor {
        private final ShortD5Tensor tensor;
        
        private ShortD5UnmodifiableTensorShell(ShortD5Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public short getCell(int d0index, int d1index, int d2index, int d3index, int d4index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index);
        }
        
        public void setCell(short value,int d0index, int d1index, int d2index, int d3index, int d4index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public short getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(short value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ShortTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ShortTypeSafeArray getTensorValues(Class<Short> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ShortTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdShortTensor<I> getReferenceTensor(Index<I> index) {
            return (IdShortTensor<I>) super.getReferenceTensor(index);
        }

        protected ShortTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class IntD5UnmodifiableTensorShell extends D5UnmodifiableTensorShell<Integer> implements IntD5Tensor {
        private final IntD5Tensor tensor;
        
        private IntD5UnmodifiableTensorShell(IntD5Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public int getCell(int d0index, int d1index, int d2index, int d3index, int d4index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index);
        }
        
        public void setCell(int value,int d0index, int d1index, int d2index, int d3index, int d4index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public int getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(int value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(IntTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public IntTypeSafeArray getTensorValues(Class<Integer> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public IntTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdIntTensor<I> getReferenceTensor(Index<I> index) {
            return (IdIntTensor<I>) super.getReferenceTensor(index);
        }

        protected IntTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class LongD5UnmodifiableTensorShell extends D5UnmodifiableTensorShell<Long> implements LongD5Tensor {
        private final LongD5Tensor tensor;
        
        private LongD5UnmodifiableTensorShell(LongD5Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public long getCell(int d0index, int d1index, int d2index, int d3index, int d4index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index);
        }
        
        public void setCell(long value,int d0index, int d1index, int d2index, int d3index, int d4index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public long getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(long value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(LongTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public LongTypeSafeArray getTensorValues(Class<Long> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public LongTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdLongTensor<I> getReferenceTensor(Index<I> index) {
            return (IdLongTensor<I>) super.getReferenceTensor(index);
        }

        protected LongTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class FloatD5UnmodifiableTensorShell extends D5UnmodifiableTensorShell<Float> implements FloatD5Tensor {
        private final FloatD5Tensor tensor;
        
        private FloatD5UnmodifiableTensorShell(FloatD5Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public float getCell(int d0index, int d1index, int d2index, int d3index, int d4index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index);
        }
        
        public void setCell(float value,int d0index, int d1index, int d2index, int d3index, int d4index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public float getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(float value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(FloatTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public FloatTypeSafeArray getTensorValues(Class<Float> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public FloatTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdFloatTensor<I> getReferenceTensor(Index<I> index) {
            return (IdFloatTensor<I>) super.getReferenceTensor(index);
        }

        protected FloatTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class DoubleD5UnmodifiableTensorShell extends D5UnmodifiableTensorShell<Double> implements DoubleD5Tensor {
        private final DoubleD5Tensor tensor;
        
        private DoubleD5UnmodifiableTensorShell(DoubleD5Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public double getCell(int d0index, int d1index, int d2index, int d3index, int d4index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index);
        }
        
        public void setCell(double value,int d0index, int d1index, int d2index, int d3index, int d4index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public double getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(double value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(DoubleTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public DoubleTypeSafeArray getTensorValues(Class<Double> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public DoubleTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdDoubleTensor<I> getReferenceTensor(Index<I> index) {
            return (IdDoubleTensor<I>) super.getReferenceTensor(index);
        }

        protected DoubleTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class CharD5UnmodifiableTensorShell extends D5UnmodifiableTensorShell<Character> implements CharD5Tensor {
        private final CharD5Tensor tensor;
        
        private CharD5UnmodifiableTensorShell(CharD5Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public char getCell(int d0index, int d1index, int d2index, int d3index, int d4index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index);
        }
        
        public void setCell(char value,int d0index, int d1index, int d2index, int d3index, int d4index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public char getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(char value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(CharTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public CharTypeSafeArray getTensorValues(Class<Character> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public CharTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdCharTensor<I> getReferenceTensor(Index<I> index) {
            return (IdCharTensor<I>) super.getReferenceTensor(index);
        }

        protected CharTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class BooleanD5UnmodifiableTensorShell extends D5UnmodifiableTensorShell<Boolean> implements BooleanD5Tensor {
        private final BooleanD5Tensor tensor;
        
        private BooleanD5UnmodifiableTensorShell(BooleanD5Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public boolean getCell(int d0index, int d1index, int d2index, int d3index, int d4index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index);
        }
        
        public void setCell(boolean value,int d0index, int d1index, int d2index, int d3index, int d4index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public boolean getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(boolean value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(BooleanTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public BooleanTypeSafeArray getTensorValues(Class<Boolean> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public BooleanTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdBooleanTensor<I> getReferenceTensor(Index<I> index) {
            return (IdBooleanTensor<I>) super.getReferenceTensor(index);
        }

        protected BooleanTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class ByteD6UnmodifiableTensorShell extends D6UnmodifiableTensorShell<Byte> implements ByteD6Tensor {
        private final ByteD6Tensor tensor;
        
        private ByteD6UnmodifiableTensorShell(ByteD6Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public byte getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index);
        }
        
        public void setCell(byte value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public byte getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(byte value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ByteTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ByteTypeSafeArray getTensorValues(Class<Byte> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ByteTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdByteTensor<I> getReferenceTensor(Index<I> index) {
            return (IdByteTensor<I>) super.getReferenceTensor(index);
        }

        protected ByteTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class ShortD6UnmodifiableTensorShell extends D6UnmodifiableTensorShell<Short> implements ShortD6Tensor {
        private final ShortD6Tensor tensor;
        
        private ShortD6UnmodifiableTensorShell(ShortD6Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public short getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index);
        }
        
        public void setCell(short value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public short getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(short value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ShortTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ShortTypeSafeArray getTensorValues(Class<Short> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ShortTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdShortTensor<I> getReferenceTensor(Index<I> index) {
            return (IdShortTensor<I>) super.getReferenceTensor(index);
        }

        protected ShortTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class IntD6UnmodifiableTensorShell extends D6UnmodifiableTensorShell<Integer> implements IntD6Tensor {
        private final IntD6Tensor tensor;
        
        private IntD6UnmodifiableTensorShell(IntD6Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public int getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index);
        }
        
        public void setCell(int value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public int getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(int value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(IntTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public IntTypeSafeArray getTensorValues(Class<Integer> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public IntTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdIntTensor<I> getReferenceTensor(Index<I> index) {
            return (IdIntTensor<I>) super.getReferenceTensor(index);
        }

        protected IntTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class LongD6UnmodifiableTensorShell extends D6UnmodifiableTensorShell<Long> implements LongD6Tensor {
        private final LongD6Tensor tensor;
        
        private LongD6UnmodifiableTensorShell(LongD6Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public long getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index);
        }
        
        public void setCell(long value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public long getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(long value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(LongTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public LongTypeSafeArray getTensorValues(Class<Long> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public LongTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdLongTensor<I> getReferenceTensor(Index<I> index) {
            return (IdLongTensor<I>) super.getReferenceTensor(index);
        }

        protected LongTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class FloatD6UnmodifiableTensorShell extends D6UnmodifiableTensorShell<Float> implements FloatD6Tensor {
        private final FloatD6Tensor tensor;
        
        private FloatD6UnmodifiableTensorShell(FloatD6Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public float getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index);
        }
        
        public void setCell(float value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public float getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(float value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(FloatTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public FloatTypeSafeArray getTensorValues(Class<Float> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public FloatTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdFloatTensor<I> getReferenceTensor(Index<I> index) {
            return (IdFloatTensor<I>) super.getReferenceTensor(index);
        }

        protected FloatTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class DoubleD6UnmodifiableTensorShell extends D6UnmodifiableTensorShell<Double> implements DoubleD6Tensor {
        private final DoubleD6Tensor tensor;
        
        private DoubleD6UnmodifiableTensorShell(DoubleD6Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public double getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index);
        }
        
        public void setCell(double value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public double getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(double value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(DoubleTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public DoubleTypeSafeArray getTensorValues(Class<Double> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public DoubleTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdDoubleTensor<I> getReferenceTensor(Index<I> index) {
            return (IdDoubleTensor<I>) super.getReferenceTensor(index);
        }

        protected DoubleTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class CharD6UnmodifiableTensorShell extends D6UnmodifiableTensorShell<Character> implements CharD6Tensor {
        private final CharD6Tensor tensor;
        
        private CharD6UnmodifiableTensorShell(CharD6Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public char getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index);
        }
        
        public void setCell(char value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public char getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(char value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(CharTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public CharTypeSafeArray getTensorValues(Class<Character> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public CharTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdCharTensor<I> getReferenceTensor(Index<I> index) {
            return (IdCharTensor<I>) super.getReferenceTensor(index);
        }

        protected CharTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class BooleanD6UnmodifiableTensorShell extends D6UnmodifiableTensorShell<Boolean> implements BooleanD6Tensor {
        private final BooleanD6Tensor tensor;
        
        private BooleanD6UnmodifiableTensorShell(BooleanD6Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public boolean getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index);
        }
        
        public void setCell(boolean value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public boolean getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(boolean value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(BooleanTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public BooleanTypeSafeArray getTensorValues(Class<Boolean> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public BooleanTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdBooleanTensor<I> getReferenceTensor(Index<I> index) {
            return (IdBooleanTensor<I>) super.getReferenceTensor(index);
        }

        protected BooleanTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class ByteD7UnmodifiableTensorShell extends D7UnmodifiableTensorShell<Byte> implements ByteD7Tensor {
        private final ByteD7Tensor tensor;
        
        private ByteD7UnmodifiableTensorShell(ByteD7Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public byte getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index);
        }
        
        public void setCell(byte value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public byte getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(byte value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ByteTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ByteTypeSafeArray getTensorValues(Class<Byte> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ByteTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdByteTensor<I> getReferenceTensor(Index<I> index) {
            return (IdByteTensor<I>) super.getReferenceTensor(index);
        }

        protected ByteTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class ShortD7UnmodifiableTensorShell extends D7UnmodifiableTensorShell<Short> implements ShortD7Tensor {
        private final ShortD7Tensor tensor;
        
        private ShortD7UnmodifiableTensorShell(ShortD7Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public short getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index);
        }
        
        public void setCell(short value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public short getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(short value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ShortTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ShortTypeSafeArray getTensorValues(Class<Short> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ShortTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdShortTensor<I> getReferenceTensor(Index<I> index) {
            return (IdShortTensor<I>) super.getReferenceTensor(index);
        }

        protected ShortTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class IntD7UnmodifiableTensorShell extends D7UnmodifiableTensorShell<Integer> implements IntD7Tensor {
        private final IntD7Tensor tensor;
        
        private IntD7UnmodifiableTensorShell(IntD7Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public int getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index);
        }
        
        public void setCell(int value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public int getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(int value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(IntTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public IntTypeSafeArray getTensorValues(Class<Integer> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public IntTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdIntTensor<I> getReferenceTensor(Index<I> index) {
            return (IdIntTensor<I>) super.getReferenceTensor(index);
        }

        protected IntTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class LongD7UnmodifiableTensorShell extends D7UnmodifiableTensorShell<Long> implements LongD7Tensor {
        private final LongD7Tensor tensor;
        
        private LongD7UnmodifiableTensorShell(LongD7Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public long getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index);
        }
        
        public void setCell(long value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public long getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(long value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(LongTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public LongTypeSafeArray getTensorValues(Class<Long> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public LongTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdLongTensor<I> getReferenceTensor(Index<I> index) {
            return (IdLongTensor<I>) super.getReferenceTensor(index);
        }

        protected LongTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class FloatD7UnmodifiableTensorShell extends D7UnmodifiableTensorShell<Float> implements FloatD7Tensor {
        private final FloatD7Tensor tensor;
        
        private FloatD7UnmodifiableTensorShell(FloatD7Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public float getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index);
        }
        
        public void setCell(float value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public float getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(float value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(FloatTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public FloatTypeSafeArray getTensorValues(Class<Float> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public FloatTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdFloatTensor<I> getReferenceTensor(Index<I> index) {
            return (IdFloatTensor<I>) super.getReferenceTensor(index);
        }

        protected FloatTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class DoubleD7UnmodifiableTensorShell extends D7UnmodifiableTensorShell<Double> implements DoubleD7Tensor {
        private final DoubleD7Tensor tensor;
        
        private DoubleD7UnmodifiableTensorShell(DoubleD7Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public double getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index);
        }
        
        public void setCell(double value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public double getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(double value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(DoubleTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public DoubleTypeSafeArray getTensorValues(Class<Double> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public DoubleTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdDoubleTensor<I> getReferenceTensor(Index<I> index) {
            return (IdDoubleTensor<I>) super.getReferenceTensor(index);
        }

        protected DoubleTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class CharD7UnmodifiableTensorShell extends D7UnmodifiableTensorShell<Character> implements CharD7Tensor {
        private final CharD7Tensor tensor;
        
        private CharD7UnmodifiableTensorShell(CharD7Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public char getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index);
        }
        
        public void setCell(char value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public char getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(char value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(CharTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public CharTypeSafeArray getTensorValues(Class<Character> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public CharTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdCharTensor<I> getReferenceTensor(Index<I> index) {
            return (IdCharTensor<I>) super.getReferenceTensor(index);
        }

        protected CharTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class BooleanD7UnmodifiableTensorShell extends D7UnmodifiableTensorShell<Boolean> implements BooleanD7Tensor {
        private final BooleanD7Tensor tensor;
        
        private BooleanD7UnmodifiableTensorShell(BooleanD7Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public boolean getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index);
        }
        
        public void setCell(boolean value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public boolean getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(boolean value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(BooleanTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public BooleanTypeSafeArray getTensorValues(Class<Boolean> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public BooleanTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdBooleanTensor<I> getReferenceTensor(Index<I> index) {
            return (IdBooleanTensor<I>) super.getReferenceTensor(index);
        }

        protected BooleanTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class ByteD8UnmodifiableTensorShell extends D8UnmodifiableTensorShell<Byte> implements ByteD8Tensor {
        private final ByteD8Tensor tensor;
        
        private ByteD8UnmodifiableTensorShell(ByteD8Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public byte getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index);
        }
        
        public void setCell(byte value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public byte getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(byte value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ByteTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ByteTypeSafeArray getTensorValues(Class<Byte> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ByteTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdByteTensor<I> getReferenceTensor(Index<I> index) {
            return (IdByteTensor<I>) super.getReferenceTensor(index);
        }

        protected ByteTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class ShortD8UnmodifiableTensorShell extends D8UnmodifiableTensorShell<Short> implements ShortD8Tensor {
        private final ShortD8Tensor tensor;
        
        private ShortD8UnmodifiableTensorShell(ShortD8Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public short getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index);
        }
        
        public void setCell(short value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public short getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(short value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ShortTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ShortTypeSafeArray getTensorValues(Class<Short> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ShortTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdShortTensor<I> getReferenceTensor(Index<I> index) {
            return (IdShortTensor<I>) super.getReferenceTensor(index);
        }

        protected ShortTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class IntD8UnmodifiableTensorShell extends D8UnmodifiableTensorShell<Integer> implements IntD8Tensor {
        private final IntD8Tensor tensor;
        
        private IntD8UnmodifiableTensorShell(IntD8Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public int getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index);
        }
        
        public void setCell(int value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public int getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(int value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(IntTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public IntTypeSafeArray getTensorValues(Class<Integer> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public IntTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdIntTensor<I> getReferenceTensor(Index<I> index) {
            return (IdIntTensor<I>) super.getReferenceTensor(index);
        }

        protected IntTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class LongD8UnmodifiableTensorShell extends D8UnmodifiableTensorShell<Long> implements LongD8Tensor {
        private final LongD8Tensor tensor;
        
        private LongD8UnmodifiableTensorShell(LongD8Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public long getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index);
        }
        
        public void setCell(long value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public long getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(long value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(LongTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public LongTypeSafeArray getTensorValues(Class<Long> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public LongTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdLongTensor<I> getReferenceTensor(Index<I> index) {
            return (IdLongTensor<I>) super.getReferenceTensor(index);
        }

        protected LongTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class FloatD8UnmodifiableTensorShell extends D8UnmodifiableTensorShell<Float> implements FloatD8Tensor {
        private final FloatD8Tensor tensor;
        
        private FloatD8UnmodifiableTensorShell(FloatD8Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public float getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index);
        }
        
        public void setCell(float value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public float getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(float value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(FloatTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public FloatTypeSafeArray getTensorValues(Class<Float> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public FloatTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdFloatTensor<I> getReferenceTensor(Index<I> index) {
            return (IdFloatTensor<I>) super.getReferenceTensor(index);
        }

        protected FloatTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class DoubleD8UnmodifiableTensorShell extends D8UnmodifiableTensorShell<Double> implements DoubleD8Tensor {
        private final DoubleD8Tensor tensor;
        
        private DoubleD8UnmodifiableTensorShell(DoubleD8Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public double getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index);
        }
        
        public void setCell(double value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public double getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(double value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(DoubleTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public DoubleTypeSafeArray getTensorValues(Class<Double> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public DoubleTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdDoubleTensor<I> getReferenceTensor(Index<I> index) {
            return (IdDoubleTensor<I>) super.getReferenceTensor(index);
        }

        protected DoubleTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class CharD8UnmodifiableTensorShell extends D8UnmodifiableTensorShell<Character> implements CharD8Tensor {
        private final CharD8Tensor tensor;
        
        private CharD8UnmodifiableTensorShell(CharD8Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public char getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index);
        }
        
        public void setCell(char value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public char getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(char value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(CharTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public CharTypeSafeArray getTensorValues(Class<Character> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public CharTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdCharTensor<I> getReferenceTensor(Index<I> index) {
            return (IdCharTensor<I>) super.getReferenceTensor(index);
        }

        protected CharTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class BooleanD8UnmodifiableTensorShell extends D8UnmodifiableTensorShell<Boolean> implements BooleanD8Tensor {
        private final BooleanD8Tensor tensor;
        
        private BooleanD8UnmodifiableTensorShell(BooleanD8Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public boolean getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index);
        }
        
        public void setCell(boolean value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public boolean getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(boolean value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(BooleanTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public BooleanTypeSafeArray getTensorValues(Class<Boolean> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public BooleanTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdBooleanTensor<I> getReferenceTensor(Index<I> index) {
            return (IdBooleanTensor<I>) super.getReferenceTensor(index);
        }

        protected BooleanTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class ByteD9UnmodifiableTensorShell extends D9UnmodifiableTensorShell<Byte> implements ByteD9Tensor {
        private final ByteD9Tensor tensor;
        
        private ByteD9UnmodifiableTensorShell(ByteD9Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public byte getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index,d8index);
        }
        
        public void setCell(byte value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public byte getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(byte value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ByteTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ByteTypeSafeArray getTensorValues(Class<Byte> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ByteTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdByteTensor<I> getReferenceTensor(Index<I> index) {
            return (IdByteTensor<I>) super.getReferenceTensor(index);
        }

        protected ByteTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class ShortD9UnmodifiableTensorShell extends D9UnmodifiableTensorShell<Short> implements ShortD9Tensor {
        private final ShortD9Tensor tensor;
        
        private ShortD9UnmodifiableTensorShell(ShortD9Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public short getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index,d8index);
        }
        
        public void setCell(short value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public short getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(short value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(ShortTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public ShortTypeSafeArray getTensorValues(Class<Short> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public ShortTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdShortTensor<I> getReferenceTensor(Index<I> index) {
            return (IdShortTensor<I>) super.getReferenceTensor(index);
        }

        protected ShortTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class IntD9UnmodifiableTensorShell extends D9UnmodifiableTensorShell<Integer> implements IntD9Tensor {
        private final IntD9Tensor tensor;
        
        private IntD9UnmodifiableTensorShell(IntD9Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public int getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index,d8index);
        }
        
        public void setCell(int value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public int getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(int value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(IntTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public IntTypeSafeArray getTensorValues(Class<Integer> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public IntTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdIntTensor<I> getReferenceTensor(Index<I> index) {
            return (IdIntTensor<I>) super.getReferenceTensor(index);
        }

        protected IntTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class LongD9UnmodifiableTensorShell extends D9UnmodifiableTensorShell<Long> implements LongD9Tensor {
        private final LongD9Tensor tensor;
        
        private LongD9UnmodifiableTensorShell(LongD9Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public long getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index,d8index);
        }
        
        public void setCell(long value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public long getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(long value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(LongTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public LongTypeSafeArray getTensorValues(Class<Long> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public LongTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdLongTensor<I> getReferenceTensor(Index<I> index) {
            return (IdLongTensor<I>) super.getReferenceTensor(index);
        }

        protected LongTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class FloatD9UnmodifiableTensorShell extends D9UnmodifiableTensorShell<Float> implements FloatD9Tensor {
        private final FloatD9Tensor tensor;
        
        private FloatD9UnmodifiableTensorShell(FloatD9Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public float getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index,d8index);
        }
        
        public void setCell(float value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public float getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(float value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(FloatTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public FloatTypeSafeArray getTensorValues(Class<Float> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public FloatTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdFloatTensor<I> getReferenceTensor(Index<I> index) {
            return (IdFloatTensor<I>) super.getReferenceTensor(index);
        }

        protected FloatTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class DoubleD9UnmodifiableTensorShell extends D9UnmodifiableTensorShell<Double> implements DoubleD9Tensor {
        private final DoubleD9Tensor tensor;
        
        private DoubleD9UnmodifiableTensorShell(DoubleD9Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public double getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index,d8index);
        }
        
        public void setCell(double value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public double getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(double value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(DoubleTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public DoubleTypeSafeArray getTensorValues(Class<Double> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public DoubleTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdDoubleTensor<I> getReferenceTensor(Index<I> index) {
            return (IdDoubleTensor<I>) super.getReferenceTensor(index);
        }

        protected DoubleTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class CharD9UnmodifiableTensorShell extends D9UnmodifiableTensorShell<Character> implements CharD9Tensor {
        private final CharD9Tensor tensor;
        
        private CharD9UnmodifiableTensorShell(CharD9Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public char getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index,d8index);
        }
        
        public void setCell(char value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public char getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(char value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(CharTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public CharTypeSafeArray getTensorValues(Class<Character> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public CharTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdCharTensor<I> getReferenceTensor(Index<I> index) {
            return (IdCharTensor<I>) super.getReferenceTensor(index);
        }

        protected CharTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class BooleanD9UnmodifiableTensorShell extends D9UnmodifiableTensorShell<Boolean> implements BooleanD9Tensor {
        private final BooleanD9Tensor tensor;
        
        private BooleanD9UnmodifiableTensorShell(BooleanD9Tensor tensor) {
            super(tensor);
            this.tensor = tensor;
        }

        public JavaType getType() {
            return tensor.getType();
        }
        
        public boolean getCell(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            return tensor.getCell(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index,d8index);
        }
        
        public void setCell(boolean value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public boolean getCell(int ... indices) {
            return tensor.getCell(indices);
        }

        @Override
        public void setCell(boolean value, int ... indices) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @Override
        public void setTensorValues(BooleanTypeSafeArray valuesArray) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }    

        @Override
        public BooleanTypeSafeArray getTensorValues(Class<Boolean> c) {
            return tensor.getTensorValues(c);
        }

        @Override
        public BooleanTypeSafeArray getTensorValues() {
            return tensor.getTensorValues();
        }

        @Override
        public <I> IdBooleanTensor<I> getReferenceTensor(Index<I> index) {
            return (IdBooleanTensor<I>) super.getReferenceTensor(index);
        }

        protected BooleanTensor getComposedTensor(Index<?> index) {
            return TensorImplUtil.getComposedTensor(this,index);
        }
    }
 
    private static class D0UnmodifiableTensorShell<T> extends UnmodifiableTensorShell<T> implements Scalar<T> {
        private final D0Tensor<T> tensor;
    
        private D0UnmodifiableTensorShell(D0Tensor<T> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public T getValue() {
            return tensor.getValue();
        }
        
        public void setValue(T value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
    }
 
    private static class D1UnmodifiableTensorShell<T> extends UnmodifiableTensorShell<T> implements Vector<T> {
        private final D1Tensor<T> tensor;
    
        private D1UnmodifiableTensorShell(D1Tensor<T> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public T getValue(int index) {
            return tensor.getValue(index);
        }
        
        public void setValue(T value,int index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
    }
 
    private static class D2UnmodifiableTensorShell<T> extends UnmodifiableTensorShell<T> implements Matrix<T> {
        private final D2Tensor<T> tensor;
    
        private D2UnmodifiableTensorShell(D2Tensor<T> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public T getValue(int d0index, int d1index) {
            return tensor.getValue(d0index,d1index);
        }
        
        public void setValue(T value,int d0index, int d1index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
    }
 
    private static class D3UnmodifiableTensorShell<T> extends UnmodifiableTensorShell<T> implements D3Tensor<T> {
        private final D3Tensor<T> tensor;
    
        private D3UnmodifiableTensorShell(D3Tensor<T> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public T getValue(int d0index, int d1index, int d2index) {
            return tensor.getValue(d0index,d1index,d2index);
        }
        
        public void setValue(T value,int d0index, int d1index, int d2index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
    }
 
    private static class D4UnmodifiableTensorShell<T> extends UnmodifiableTensorShell<T> implements D4Tensor<T> {
        private final D4Tensor<T> tensor;
    
        private D4UnmodifiableTensorShell(D4Tensor<T> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public T getValue(int d0index, int d1index, int d2index, int d3index) {
            return tensor.getValue(d0index,d1index,d2index,d3index);
        }
        
        public void setValue(T value,int d0index, int d1index, int d2index, int d3index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
    }
 
    private static class D5UnmodifiableTensorShell<T> extends UnmodifiableTensorShell<T> implements D5Tensor<T> {
        private final D5Tensor<T> tensor;
    
        private D5UnmodifiableTensorShell(D5Tensor<T> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public T getValue(int d0index, int d1index, int d2index, int d3index, int d4index) {
            return tensor.getValue(d0index,d1index,d2index,d3index,d4index);
        }
        
        public void setValue(T value,int d0index, int d1index, int d2index, int d3index, int d4index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
    }
 
    private static class D6UnmodifiableTensorShell<T> extends UnmodifiableTensorShell<T> implements D6Tensor<T> {
        private final D6Tensor<T> tensor;
    
        private D6UnmodifiableTensorShell(D6Tensor<T> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public T getValue(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            return tensor.getValue(d0index,d1index,d2index,d3index,d4index,d5index);
        }
        
        public void setValue(T value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
    }
 
    private static class D7UnmodifiableTensorShell<T> extends UnmodifiableTensorShell<T> implements D7Tensor<T> {
        private final D7Tensor<T> tensor;
    
        private D7UnmodifiableTensorShell(D7Tensor<T> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public T getValue(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            return tensor.getValue(d0index,d1index,d2index,d3index,d4index,d5index,d6index);
        }
        
        public void setValue(T value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
    }
 
    private static class D8UnmodifiableTensorShell<T> extends UnmodifiableTensorShell<T> implements D8Tensor<T> {
        private final D8Tensor<T> tensor;
    
        private D8UnmodifiableTensorShell(D8Tensor<T> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public T getValue(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            return tensor.getValue(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index);
        }
        
        public void setValue(T value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
    }
 
    private static class D9UnmodifiableTensorShell<T> extends UnmodifiableTensorShell<T> implements D9Tensor<T> {
        private final D9Tensor<T> tensor;
    
        private D9UnmodifiableTensorShell(D9Tensor<T> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public T getValue(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            return tensor.getValue(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index,d8index);
        }
        
        public void setValue(T value,int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
    }

    private static class IdUnmodifiableTensorShell<T,I> extends UnmodifiableTensorShell<T> implements IdTensor<T,I> {
        private final IdTensor<T,I> tensor;

        private IdUnmodifiableTensorShell(IdTensor<T,I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public T getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(T value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<T>> iterator() {
            return new Iterator<Tensor<T>>() {
                Iterator<Tensor<T>> it = IdUnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<T> next() {
                    return new IdTensorShell<T,I>(it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    private static class IdByteUnmodifiableTensorShell<I> extends ByteUnmodifiableTensorShell implements IdByteTensor<I> {
        private final IdByteTensor<I> tensor;

        private IdByteUnmodifiableTensorShell(IdByteTensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public byte getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Byte getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Byte>> iterator() {
            return new Iterator<Tensor<Byte>>() {
                Iterator<Tensor<Byte>> it = IdByteUnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Byte> next() {
                    return new IdByteTensorShell<I>((ByteTensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    private static class IdShortUnmodifiableTensorShell<I> extends ShortUnmodifiableTensorShell implements IdShortTensor<I> {
        private final IdShortTensor<I> tensor;

        private IdShortUnmodifiableTensorShell(IdShortTensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public short getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Short getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Short>> iterator() {
            return new Iterator<Tensor<Short>>() {
                Iterator<Tensor<Short>> it = IdShortUnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Short> next() {
                    return new IdShortTensorShell<I>((ShortTensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    private static class IdIntUnmodifiableTensorShell<I> extends IntUnmodifiableTensorShell implements IdIntTensor<I> {
        private final IdIntTensor<I> tensor;

        private IdIntUnmodifiableTensorShell(IdIntTensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public int getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(int value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Integer getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Integer value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Integer>> iterator() {
            return new Iterator<Tensor<Integer>>() {
                Iterator<Tensor<Integer>> it = IdIntUnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Integer> next() {
                    return new IdIntTensorShell<I>((IntTensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    private static class IdLongUnmodifiableTensorShell<I> extends LongUnmodifiableTensorShell implements IdLongTensor<I> {
        private final IdLongTensor<I> tensor;

        private IdLongUnmodifiableTensorShell(IdLongTensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public long getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Long getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Long>> iterator() {
            return new Iterator<Tensor<Long>>() {
                Iterator<Tensor<Long>> it = IdLongUnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Long> next() {
                    return new IdLongTensorShell<I>((LongTensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    private static class IdFloatUnmodifiableTensorShell<I> extends FloatUnmodifiableTensorShell implements IdFloatTensor<I> {
        private final IdFloatTensor<I> tensor;

        private IdFloatUnmodifiableTensorShell(IdFloatTensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public float getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Float getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Float>> iterator() {
            return new Iterator<Tensor<Float>>() {
                Iterator<Tensor<Float>> it = IdFloatUnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Float> next() {
                    return new IdFloatTensorShell<I>((FloatTensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    private static class IdDoubleUnmodifiableTensorShell<I> extends DoubleUnmodifiableTensorShell implements IdDoubleTensor<I> {
        private final IdDoubleTensor<I> tensor;

        private IdDoubleUnmodifiableTensorShell(IdDoubleTensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public double getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Double getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Double>> iterator() {
            return new Iterator<Tensor<Double>>() {
                Iterator<Tensor<Double>> it = IdDoubleUnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Double> next() {
                    return new IdDoubleTensorShell<I>((DoubleTensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    private static class IdCharUnmodifiableTensorShell<I> extends CharUnmodifiableTensorShell implements IdCharTensor<I> {
        private final IdCharTensor<I> tensor;

        private IdCharUnmodifiableTensorShell(IdCharTensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public char getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(char value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Character getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Character value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Character>> iterator() {
            return new Iterator<Tensor<Character>>() {
                Iterator<Tensor<Character>> it = IdCharUnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Character> next() {
                    return new IdCharTensorShell<I>((CharTensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    private static class IdBooleanUnmodifiableTensorShell<I> extends BooleanUnmodifiableTensorShell implements IdBooleanTensor<I> {
        private final IdBooleanTensor<I> tensor;

        private IdBooleanUnmodifiableTensorShell(IdBooleanTensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public boolean getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Boolean getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Boolean>> iterator() {
            return new Iterator<Tensor<Boolean>>() {
                Iterator<Tensor<Boolean>> it = IdBooleanUnmodifiableTensorShell.super.iterator();
    
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
 
    private static class IdByteD0UnmodifiableTensorShell<I> extends ByteD0UnmodifiableTensorShell implements IdByteScalar<I> {
        private final IdByteD0Tensor<I> tensor;
        
        private IdByteD0UnmodifiableTensorShell(IdByteD0Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public byte getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Byte getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public byte getCellById() {
            return tensor.getCellById();
        }
        
        public void setCellById(byte value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Byte getValueById() {
            return tensor.getValueById();
        }

        public void setValueById(Byte value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Byte>> iterator() {
            return new Iterator<Tensor<Byte>>() {
                Iterator<Tensor<Byte>> it = IdByteD0UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Byte> next() {
                    return new IdByteD0TensorShell<I>((ByteD0Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdByteD1UnmodifiableTensorShell<I> extends ByteD1UnmodifiableTensorShell implements IdByteVector<I> {
        private final IdByteD1Tensor<I> tensor;
        
        private IdByteD1UnmodifiableTensorShell(IdByteD1Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public byte getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Byte getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public byte getCellById(I id) {
            return tensor.getCellById(id);
        }
        
        public void setCellById(byte value,I id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Byte getValueById(I id) {
            return tensor.getValueById(id);
        }

        public void setValueById(Byte value, I id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Byte>> iterator() {
            return new Iterator<Tensor<Byte>>() {
                Iterator<Tensor<Byte>> it = IdByteD1UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Byte> next() {
                    return new IdByteD0TensorShell<I>((ByteD0Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdShortD0UnmodifiableTensorShell<I> extends ShortD0UnmodifiableTensorShell implements IdShortScalar<I> {
        private final IdShortD0Tensor<I> tensor;
        
        private IdShortD0UnmodifiableTensorShell(IdShortD0Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public short getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Short getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public short getCellById() {
            return tensor.getCellById();
        }
        
        public void setCellById(short value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Short getValueById() {
            return tensor.getValueById();
        }

        public void setValueById(Short value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Short>> iterator() {
            return new Iterator<Tensor<Short>>() {
                Iterator<Tensor<Short>> it = IdShortD0UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Short> next() {
                    return new IdShortD0TensorShell<I>((ShortD0Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdShortD1UnmodifiableTensorShell<I> extends ShortD1UnmodifiableTensorShell implements IdShortVector<I> {
        private final IdShortD1Tensor<I> tensor;
        
        private IdShortD1UnmodifiableTensorShell(IdShortD1Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public short getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Short getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public short getCellById(I id) {
            return tensor.getCellById(id);
        }
        
        public void setCellById(short value,I id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Short getValueById(I id) {
            return tensor.getValueById(id);
        }

        public void setValueById(Short value, I id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Short>> iterator() {
            return new Iterator<Tensor<Short>>() {
                Iterator<Tensor<Short>> it = IdShortD1UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Short> next() {
                    return new IdShortD0TensorShell<I>((ShortD0Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdIntD0UnmodifiableTensorShell<I> extends IntD0UnmodifiableTensorShell implements IdIntScalar<I> {
        private final IdIntD0Tensor<I> tensor;
        
        private IdIntD0UnmodifiableTensorShell(IdIntD0Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public int getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(int value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Integer getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Integer value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public int getCellById() {
            return tensor.getCellById();
        }
        
        public void setCellById(int value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Integer getValueById() {
            return tensor.getValueById();
        }

        public void setValueById(Integer value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Integer>> iterator() {
            return new Iterator<Tensor<Integer>>() {
                Iterator<Tensor<Integer>> it = IdIntD0UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Integer> next() {
                    return new IdIntD0TensorShell<I>((IntD0Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdIntD1UnmodifiableTensorShell<I> extends IntD1UnmodifiableTensorShell implements IdIntVector<I> {
        private final IdIntD1Tensor<I> tensor;
        
        private IdIntD1UnmodifiableTensorShell(IdIntD1Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public int getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(int value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Integer getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Integer value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public int getCellById(I id) {
            return tensor.getCellById(id);
        }
        
        public void setCellById(int value,I id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Integer getValueById(I id) {
            return tensor.getValueById(id);
        }

        public void setValueById(Integer value, I id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Integer>> iterator() {
            return new Iterator<Tensor<Integer>>() {
                Iterator<Tensor<Integer>> it = IdIntD1UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Integer> next() {
                    return new IdIntD0TensorShell<I>((IntD0Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdLongD0UnmodifiableTensorShell<I> extends LongD0UnmodifiableTensorShell implements IdLongScalar<I> {
        private final IdLongD0Tensor<I> tensor;
        
        private IdLongD0UnmodifiableTensorShell(IdLongD0Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public long getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Long getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public long getCellById() {
            return tensor.getCellById();
        }
        
        public void setCellById(long value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Long getValueById() {
            return tensor.getValueById();
        }

        public void setValueById(Long value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Long>> iterator() {
            return new Iterator<Tensor<Long>>() {
                Iterator<Tensor<Long>> it = IdLongD0UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Long> next() {
                    return new IdLongD0TensorShell<I>((LongD0Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdLongD1UnmodifiableTensorShell<I> extends LongD1UnmodifiableTensorShell implements IdLongVector<I> {
        private final IdLongD1Tensor<I> tensor;
        
        private IdLongD1UnmodifiableTensorShell(IdLongD1Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public long getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Long getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public long getCellById(I id) {
            return tensor.getCellById(id);
        }
        
        public void setCellById(long value,I id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Long getValueById(I id) {
            return tensor.getValueById(id);
        }

        public void setValueById(Long value, I id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Long>> iterator() {
            return new Iterator<Tensor<Long>>() {
                Iterator<Tensor<Long>> it = IdLongD1UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Long> next() {
                    return new IdLongD0TensorShell<I>((LongD0Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdFloatD0UnmodifiableTensorShell<I> extends FloatD0UnmodifiableTensorShell implements IdFloatScalar<I> {
        private final IdFloatD0Tensor<I> tensor;
        
        private IdFloatD0UnmodifiableTensorShell(IdFloatD0Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public float getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Float getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public float getCellById() {
            return tensor.getCellById();
        }
        
        public void setCellById(float value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Float getValueById() {
            return tensor.getValueById();
        }

        public void setValueById(Float value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Float>> iterator() {
            return new Iterator<Tensor<Float>>() {
                Iterator<Tensor<Float>> it = IdFloatD0UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Float> next() {
                    return new IdFloatD0TensorShell<I>((FloatD0Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdFloatD1UnmodifiableTensorShell<I> extends FloatD1UnmodifiableTensorShell implements IdFloatVector<I> {
        private final IdFloatD1Tensor<I> tensor;
        
        private IdFloatD1UnmodifiableTensorShell(IdFloatD1Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public float getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Float getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public float getCellById(I id) {
            return tensor.getCellById(id);
        }
        
        public void setCellById(float value,I id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Float getValueById(I id) {
            return tensor.getValueById(id);
        }

        public void setValueById(Float value, I id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Float>> iterator() {
            return new Iterator<Tensor<Float>>() {
                Iterator<Tensor<Float>> it = IdFloatD1UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Float> next() {
                    return new IdFloatD0TensorShell<I>((FloatD0Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdDoubleD0UnmodifiableTensorShell<I> extends DoubleD0UnmodifiableTensorShell implements IdDoubleScalar<I> {
        private final IdDoubleD0Tensor<I> tensor;
        
        private IdDoubleD0UnmodifiableTensorShell(IdDoubleD0Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public double getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Double getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public double getCellById() {
            return tensor.getCellById();
        }
        
        public void setCellById(double value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Double getValueById() {
            return tensor.getValueById();
        }

        public void setValueById(Double value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Double>> iterator() {
            return new Iterator<Tensor<Double>>() {
                Iterator<Tensor<Double>> it = IdDoubleD0UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Double> next() {
                    return new IdDoubleD0TensorShell<I>((DoubleD0Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdDoubleD1UnmodifiableTensorShell<I> extends DoubleD1UnmodifiableTensorShell implements IdDoubleVector<I> {
        private final IdDoubleD1Tensor<I> tensor;
        
        private IdDoubleD1UnmodifiableTensorShell(IdDoubleD1Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public double getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Double getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public double getCellById(I id) {
            return tensor.getCellById(id);
        }
        
        public void setCellById(double value,I id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Double getValueById(I id) {
            return tensor.getValueById(id);
        }

        public void setValueById(Double value, I id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Double>> iterator() {
            return new Iterator<Tensor<Double>>() {
                Iterator<Tensor<Double>> it = IdDoubleD1UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Double> next() {
                    return new IdDoubleD0TensorShell<I>((DoubleD0Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdCharD0UnmodifiableTensorShell<I> extends CharD0UnmodifiableTensorShell implements IdCharScalar<I> {
        private final IdCharD0Tensor<I> tensor;
        
        private IdCharD0UnmodifiableTensorShell(IdCharD0Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public char getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(char value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Character getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Character value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public char getCellById() {
            return tensor.getCellById();
        }
        
        public void setCellById(char value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Character getValueById() {
            return tensor.getValueById();
        }

        public void setValueById(Character value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Character>> iterator() {
            return new Iterator<Tensor<Character>>() {
                Iterator<Tensor<Character>> it = IdCharD0UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Character> next() {
                    return new IdCharD0TensorShell<I>((CharD0Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdCharD1UnmodifiableTensorShell<I> extends CharD1UnmodifiableTensorShell implements IdCharVector<I> {
        private final IdCharD1Tensor<I> tensor;
        
        private IdCharD1UnmodifiableTensorShell(IdCharD1Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public char getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(char value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Character getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Character value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public char getCellById(I id) {
            return tensor.getCellById(id);
        }
        
        public void setCellById(char value,I id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Character getValueById(I id) {
            return tensor.getValueById(id);
        }

        public void setValueById(Character value, I id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Character>> iterator() {
            return new Iterator<Tensor<Character>>() {
                Iterator<Tensor<Character>> it = IdCharD1UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Character> next() {
                    return new IdCharD0TensorShell<I>((CharD0Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdBooleanD0UnmodifiableTensorShell<I> extends BooleanD0UnmodifiableTensorShell implements IdBooleanScalar<I> {
        private final IdBooleanD0Tensor<I> tensor;
        
        private IdBooleanD0UnmodifiableTensorShell(IdBooleanD0Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public boolean getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Boolean getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public boolean getCellById() {
            return tensor.getCellById();
        }
        
        public void setCellById(boolean value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Boolean getValueById() {
            return tensor.getValueById();
        }

        public void setValueById(Boolean value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Boolean>> iterator() {
            return new Iterator<Tensor<Boolean>>() {
                Iterator<Tensor<Boolean>> it = IdBooleanD0UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Boolean> next() {
                    return new IdBooleanD0TensorShell<I>((BooleanD0Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdBooleanD1UnmodifiableTensorShell<I> extends BooleanD1UnmodifiableTensorShell implements IdBooleanVector<I> {
        private final IdBooleanD1Tensor<I> tensor;
        
        private IdBooleanD1UnmodifiableTensorShell(IdBooleanD1Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public boolean getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Boolean getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public boolean getCellById(I id) {
            return tensor.getCellById(id);
        }
        
        public void setCellById(boolean value,I id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Boolean getValueById(I id) {
            return tensor.getValueById(id);
        }

        public void setValueById(Boolean value, I id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Boolean>> iterator() {
            return new Iterator<Tensor<Boolean>>() {
                Iterator<Tensor<Boolean>> it = IdBooleanD1UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Boolean> next() {
                    return new IdBooleanD0TensorShell<I>((BooleanD0Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdByteD2UnmodifiableTensorShell<I> extends ByteD2UnmodifiableTensorShell implements IdByteMatrix<I> {
        private final IdByteD2Tensor<I> tensor;
        
        private IdByteD2UnmodifiableTensorShell(IdByteD2Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public byte getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Byte getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public byte getCellById(I d0id, I d1id) {
            return tensor.getCellById(d0id,d1id);
        }
        
        public void setCellById(byte value,I d0id, I d1id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Byte getValueById(I d0id, I d1id) {
            return tensor.getValueById(d0id,d1id);
        }

        public void setValueById(Byte value, I d0id, I d1id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Byte>> iterator() {
            return new Iterator<Tensor<Byte>>() {
                Iterator<Tensor<Byte>> it = IdByteD2UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Byte> next() {
                    return new IdByteD1TensorShell<I>((ByteD1Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdShortD2UnmodifiableTensorShell<I> extends ShortD2UnmodifiableTensorShell implements IdShortMatrix<I> {
        private final IdShortD2Tensor<I> tensor;
        
        private IdShortD2UnmodifiableTensorShell(IdShortD2Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public short getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Short getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public short getCellById(I d0id, I d1id) {
            return tensor.getCellById(d0id,d1id);
        }
        
        public void setCellById(short value,I d0id, I d1id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Short getValueById(I d0id, I d1id) {
            return tensor.getValueById(d0id,d1id);
        }

        public void setValueById(Short value, I d0id, I d1id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Short>> iterator() {
            return new Iterator<Tensor<Short>>() {
                Iterator<Tensor<Short>> it = IdShortD2UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Short> next() {
                    return new IdShortD1TensorShell<I>((ShortD1Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdIntD2UnmodifiableTensorShell<I> extends IntD2UnmodifiableTensorShell implements IdIntMatrix<I> {
        private final IdIntD2Tensor<I> tensor;
        
        private IdIntD2UnmodifiableTensorShell(IdIntD2Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public int getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(int value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Integer getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Integer value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public int getCellById(I d0id, I d1id) {
            return tensor.getCellById(d0id,d1id);
        }
        
        public void setCellById(int value,I d0id, I d1id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Integer getValueById(I d0id, I d1id) {
            return tensor.getValueById(d0id,d1id);
        }

        public void setValueById(Integer value, I d0id, I d1id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Integer>> iterator() {
            return new Iterator<Tensor<Integer>>() {
                Iterator<Tensor<Integer>> it = IdIntD2UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Integer> next() {
                    return new IdIntD1TensorShell<I>((IntD1Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdLongD2UnmodifiableTensorShell<I> extends LongD2UnmodifiableTensorShell implements IdLongMatrix<I> {
        private final IdLongD2Tensor<I> tensor;
        
        private IdLongD2UnmodifiableTensorShell(IdLongD2Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public long getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Long getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public long getCellById(I d0id, I d1id) {
            return tensor.getCellById(d0id,d1id);
        }
        
        public void setCellById(long value,I d0id, I d1id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Long getValueById(I d0id, I d1id) {
            return tensor.getValueById(d0id,d1id);
        }

        public void setValueById(Long value, I d0id, I d1id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Long>> iterator() {
            return new Iterator<Tensor<Long>>() {
                Iterator<Tensor<Long>> it = IdLongD2UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Long> next() {
                    return new IdLongD1TensorShell<I>((LongD1Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdFloatD2UnmodifiableTensorShell<I> extends FloatD2UnmodifiableTensorShell implements IdFloatMatrix<I> {
        private final IdFloatD2Tensor<I> tensor;
        
        private IdFloatD2UnmodifiableTensorShell(IdFloatD2Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public float getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Float getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public float getCellById(I d0id, I d1id) {
            return tensor.getCellById(d0id,d1id);
        }
        
        public void setCellById(float value,I d0id, I d1id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Float getValueById(I d0id, I d1id) {
            return tensor.getValueById(d0id,d1id);
        }

        public void setValueById(Float value, I d0id, I d1id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Float>> iterator() {
            return new Iterator<Tensor<Float>>() {
                Iterator<Tensor<Float>> it = IdFloatD2UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Float> next() {
                    return new IdFloatD1TensorShell<I>((FloatD1Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdDoubleD2UnmodifiableTensorShell<I> extends DoubleD2UnmodifiableTensorShell implements IdDoubleMatrix<I> {
        private final IdDoubleD2Tensor<I> tensor;
        
        private IdDoubleD2UnmodifiableTensorShell(IdDoubleD2Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public double getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Double getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public double getCellById(I d0id, I d1id) {
            return tensor.getCellById(d0id,d1id);
        }
        
        public void setCellById(double value,I d0id, I d1id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Double getValueById(I d0id, I d1id) {
            return tensor.getValueById(d0id,d1id);
        }

        public void setValueById(Double value, I d0id, I d1id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Double>> iterator() {
            return new Iterator<Tensor<Double>>() {
                Iterator<Tensor<Double>> it = IdDoubleD2UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Double> next() {
                    return new IdDoubleD1TensorShell<I>((DoubleD1Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdCharD2UnmodifiableTensorShell<I> extends CharD2UnmodifiableTensorShell implements IdCharMatrix<I> {
        private final IdCharD2Tensor<I> tensor;
        
        private IdCharD2UnmodifiableTensorShell(IdCharD2Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public char getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(char value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Character getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Character value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public char getCellById(I d0id, I d1id) {
            return tensor.getCellById(d0id,d1id);
        }
        
        public void setCellById(char value,I d0id, I d1id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Character getValueById(I d0id, I d1id) {
            return tensor.getValueById(d0id,d1id);
        }

        public void setValueById(Character value, I d0id, I d1id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Character>> iterator() {
            return new Iterator<Tensor<Character>>() {
                Iterator<Tensor<Character>> it = IdCharD2UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Character> next() {
                    return new IdCharD1TensorShell<I>((CharD1Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdBooleanD2UnmodifiableTensorShell<I> extends BooleanD2UnmodifiableTensorShell implements IdBooleanMatrix<I> {
        private final IdBooleanD2Tensor<I> tensor;
        
        private IdBooleanD2UnmodifiableTensorShell(IdBooleanD2Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public boolean getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Boolean getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public boolean getCellById(I d0id, I d1id) {
            return tensor.getCellById(d0id,d1id);
        }
        
        public void setCellById(boolean value,I d0id, I d1id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Boolean getValueById(I d0id, I d1id) {
            return tensor.getValueById(d0id,d1id);
        }

        public void setValueById(Boolean value, I d0id, I d1id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Boolean>> iterator() {
            return new Iterator<Tensor<Boolean>>() {
                Iterator<Tensor<Boolean>> it = IdBooleanD2UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Boolean> next() {
                    return new IdBooleanD1TensorShell<I>((BooleanD1Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdByteD3UnmodifiableTensorShell<I> extends ByteD3UnmodifiableTensorShell implements IdByteD3Tensor<I> {
        private final IdByteD3Tensor<I> tensor;
        
        private IdByteD3UnmodifiableTensorShell(IdByteD3Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public byte getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Byte getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public byte getCellById(I d0id, I d1id, I d2id) {
            return tensor.getCellById(d0id,d1id,d2id);
        }
        
        public void setCellById(byte value,I d0id, I d1id, I d2id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Byte getValueById(I d0id, I d1id, I d2id) {
            return tensor.getValueById(d0id,d1id,d2id);
        }

        public void setValueById(Byte value, I d0id, I d1id, I d2id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Byte>> iterator() {
            return new Iterator<Tensor<Byte>>() {
                Iterator<Tensor<Byte>> it = IdByteD3UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Byte> next() {
                    return new IdByteD2TensorShell<I>((ByteD2Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdShortD3UnmodifiableTensorShell<I> extends ShortD3UnmodifiableTensorShell implements IdShortD3Tensor<I> {
        private final IdShortD3Tensor<I> tensor;
        
        private IdShortD3UnmodifiableTensorShell(IdShortD3Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public short getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Short getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public short getCellById(I d0id, I d1id, I d2id) {
            return tensor.getCellById(d0id,d1id,d2id);
        }
        
        public void setCellById(short value,I d0id, I d1id, I d2id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Short getValueById(I d0id, I d1id, I d2id) {
            return tensor.getValueById(d0id,d1id,d2id);
        }

        public void setValueById(Short value, I d0id, I d1id, I d2id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Short>> iterator() {
            return new Iterator<Tensor<Short>>() {
                Iterator<Tensor<Short>> it = IdShortD3UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Short> next() {
                    return new IdShortD2TensorShell<I>((ShortD2Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdIntD3UnmodifiableTensorShell<I> extends IntD3UnmodifiableTensorShell implements IdIntD3Tensor<I> {
        private final IdIntD3Tensor<I> tensor;
        
        private IdIntD3UnmodifiableTensorShell(IdIntD3Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public int getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(int value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Integer getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Integer value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public int getCellById(I d0id, I d1id, I d2id) {
            return tensor.getCellById(d0id,d1id,d2id);
        }
        
        public void setCellById(int value,I d0id, I d1id, I d2id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Integer getValueById(I d0id, I d1id, I d2id) {
            return tensor.getValueById(d0id,d1id,d2id);
        }

        public void setValueById(Integer value, I d0id, I d1id, I d2id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Integer>> iterator() {
            return new Iterator<Tensor<Integer>>() {
                Iterator<Tensor<Integer>> it = IdIntD3UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Integer> next() {
                    return new IdIntD2TensorShell<I>((IntD2Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdLongD3UnmodifiableTensorShell<I> extends LongD3UnmodifiableTensorShell implements IdLongD3Tensor<I> {
        private final IdLongD3Tensor<I> tensor;
        
        private IdLongD3UnmodifiableTensorShell(IdLongD3Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public long getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Long getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public long getCellById(I d0id, I d1id, I d2id) {
            return tensor.getCellById(d0id,d1id,d2id);
        }
        
        public void setCellById(long value,I d0id, I d1id, I d2id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Long getValueById(I d0id, I d1id, I d2id) {
            return tensor.getValueById(d0id,d1id,d2id);
        }

        public void setValueById(Long value, I d0id, I d1id, I d2id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Long>> iterator() {
            return new Iterator<Tensor<Long>>() {
                Iterator<Tensor<Long>> it = IdLongD3UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Long> next() {
                    return new IdLongD2TensorShell<I>((LongD2Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdFloatD3UnmodifiableTensorShell<I> extends FloatD3UnmodifiableTensorShell implements IdFloatD3Tensor<I> {
        private final IdFloatD3Tensor<I> tensor;
        
        private IdFloatD3UnmodifiableTensorShell(IdFloatD3Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public float getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Float getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public float getCellById(I d0id, I d1id, I d2id) {
            return tensor.getCellById(d0id,d1id,d2id);
        }
        
        public void setCellById(float value,I d0id, I d1id, I d2id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Float getValueById(I d0id, I d1id, I d2id) {
            return tensor.getValueById(d0id,d1id,d2id);
        }

        public void setValueById(Float value, I d0id, I d1id, I d2id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Float>> iterator() {
            return new Iterator<Tensor<Float>>() {
                Iterator<Tensor<Float>> it = IdFloatD3UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Float> next() {
                    return new IdFloatD2TensorShell<I>((FloatD2Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdDoubleD3UnmodifiableTensorShell<I> extends DoubleD3UnmodifiableTensorShell implements IdDoubleD3Tensor<I> {
        private final IdDoubleD3Tensor<I> tensor;
        
        private IdDoubleD3UnmodifiableTensorShell(IdDoubleD3Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public double getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Double getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public double getCellById(I d0id, I d1id, I d2id) {
            return tensor.getCellById(d0id,d1id,d2id);
        }
        
        public void setCellById(double value,I d0id, I d1id, I d2id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Double getValueById(I d0id, I d1id, I d2id) {
            return tensor.getValueById(d0id,d1id,d2id);
        }

        public void setValueById(Double value, I d0id, I d1id, I d2id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Double>> iterator() {
            return new Iterator<Tensor<Double>>() {
                Iterator<Tensor<Double>> it = IdDoubleD3UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Double> next() {
                    return new IdDoubleD2TensorShell<I>((DoubleD2Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdCharD3UnmodifiableTensorShell<I> extends CharD3UnmodifiableTensorShell implements IdCharD3Tensor<I> {
        private final IdCharD3Tensor<I> tensor;
        
        private IdCharD3UnmodifiableTensorShell(IdCharD3Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public char getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(char value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Character getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Character value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public char getCellById(I d0id, I d1id, I d2id) {
            return tensor.getCellById(d0id,d1id,d2id);
        }
        
        public void setCellById(char value,I d0id, I d1id, I d2id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Character getValueById(I d0id, I d1id, I d2id) {
            return tensor.getValueById(d0id,d1id,d2id);
        }

        public void setValueById(Character value, I d0id, I d1id, I d2id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Character>> iterator() {
            return new Iterator<Tensor<Character>>() {
                Iterator<Tensor<Character>> it = IdCharD3UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Character> next() {
                    return new IdCharD2TensorShell<I>((CharD2Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdBooleanD3UnmodifiableTensorShell<I> extends BooleanD3UnmodifiableTensorShell implements IdBooleanD3Tensor<I> {
        private final IdBooleanD3Tensor<I> tensor;
        
        private IdBooleanD3UnmodifiableTensorShell(IdBooleanD3Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public boolean getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Boolean getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public boolean getCellById(I d0id, I d1id, I d2id) {
            return tensor.getCellById(d0id,d1id,d2id);
        }
        
        public void setCellById(boolean value,I d0id, I d1id, I d2id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Boolean getValueById(I d0id, I d1id, I d2id) {
            return tensor.getValueById(d0id,d1id,d2id);
        }

        public void setValueById(Boolean value, I d0id, I d1id, I d2id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Boolean>> iterator() {
            return new Iterator<Tensor<Boolean>>() {
                Iterator<Tensor<Boolean>> it = IdBooleanD3UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Boolean> next() {
                    return new IdBooleanD2TensorShell<I>((BooleanD2Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdByteD4UnmodifiableTensorShell<I> extends ByteD4UnmodifiableTensorShell implements IdByteD4Tensor<I> {
        private final IdByteD4Tensor<I> tensor;
        
        private IdByteD4UnmodifiableTensorShell(IdByteD4Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public byte getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Byte getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public byte getCellById(I d0id, I d1id, I d2id, I d3id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id);
        }
        
        public void setCellById(byte value,I d0id, I d1id, I d2id, I d3id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Byte getValueById(I d0id, I d1id, I d2id, I d3id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id);
        }

        public void setValueById(Byte value, I d0id, I d1id, I d2id, I d3id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Byte>> iterator() {
            return new Iterator<Tensor<Byte>>() {
                Iterator<Tensor<Byte>> it = IdByteD4UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Byte> next() {
                    return new IdByteD3TensorShell<I>((ByteD3Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdShortD4UnmodifiableTensorShell<I> extends ShortD4UnmodifiableTensorShell implements IdShortD4Tensor<I> {
        private final IdShortD4Tensor<I> tensor;
        
        private IdShortD4UnmodifiableTensorShell(IdShortD4Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public short getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Short getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public short getCellById(I d0id, I d1id, I d2id, I d3id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id);
        }
        
        public void setCellById(short value,I d0id, I d1id, I d2id, I d3id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Short getValueById(I d0id, I d1id, I d2id, I d3id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id);
        }

        public void setValueById(Short value, I d0id, I d1id, I d2id, I d3id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Short>> iterator() {
            return new Iterator<Tensor<Short>>() {
                Iterator<Tensor<Short>> it = IdShortD4UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Short> next() {
                    return new IdShortD3TensorShell<I>((ShortD3Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdIntD4UnmodifiableTensorShell<I> extends IntD4UnmodifiableTensorShell implements IdIntD4Tensor<I> {
        private final IdIntD4Tensor<I> tensor;
        
        private IdIntD4UnmodifiableTensorShell(IdIntD4Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public int getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(int value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Integer getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Integer value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public int getCellById(I d0id, I d1id, I d2id, I d3id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id);
        }
        
        public void setCellById(int value,I d0id, I d1id, I d2id, I d3id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Integer getValueById(I d0id, I d1id, I d2id, I d3id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id);
        }

        public void setValueById(Integer value, I d0id, I d1id, I d2id, I d3id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Integer>> iterator() {
            return new Iterator<Tensor<Integer>>() {
                Iterator<Tensor<Integer>> it = IdIntD4UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Integer> next() {
                    return new IdIntD3TensorShell<I>((IntD3Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdLongD4UnmodifiableTensorShell<I> extends LongD4UnmodifiableTensorShell implements IdLongD4Tensor<I> {
        private final IdLongD4Tensor<I> tensor;
        
        private IdLongD4UnmodifiableTensorShell(IdLongD4Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public long getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Long getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public long getCellById(I d0id, I d1id, I d2id, I d3id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id);
        }
        
        public void setCellById(long value,I d0id, I d1id, I d2id, I d3id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Long getValueById(I d0id, I d1id, I d2id, I d3id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id);
        }

        public void setValueById(Long value, I d0id, I d1id, I d2id, I d3id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Long>> iterator() {
            return new Iterator<Tensor<Long>>() {
                Iterator<Tensor<Long>> it = IdLongD4UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Long> next() {
                    return new IdLongD3TensorShell<I>((LongD3Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdFloatD4UnmodifiableTensorShell<I> extends FloatD4UnmodifiableTensorShell implements IdFloatD4Tensor<I> {
        private final IdFloatD4Tensor<I> tensor;
        
        private IdFloatD4UnmodifiableTensorShell(IdFloatD4Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public float getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Float getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public float getCellById(I d0id, I d1id, I d2id, I d3id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id);
        }
        
        public void setCellById(float value,I d0id, I d1id, I d2id, I d3id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Float getValueById(I d0id, I d1id, I d2id, I d3id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id);
        }

        public void setValueById(Float value, I d0id, I d1id, I d2id, I d3id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Float>> iterator() {
            return new Iterator<Tensor<Float>>() {
                Iterator<Tensor<Float>> it = IdFloatD4UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Float> next() {
                    return new IdFloatD3TensorShell<I>((FloatD3Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdDoubleD4UnmodifiableTensorShell<I> extends DoubleD4UnmodifiableTensorShell implements IdDoubleD4Tensor<I> {
        private final IdDoubleD4Tensor<I> tensor;
        
        private IdDoubleD4UnmodifiableTensorShell(IdDoubleD4Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public double getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Double getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public double getCellById(I d0id, I d1id, I d2id, I d3id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id);
        }
        
        public void setCellById(double value,I d0id, I d1id, I d2id, I d3id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Double getValueById(I d0id, I d1id, I d2id, I d3id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id);
        }

        public void setValueById(Double value, I d0id, I d1id, I d2id, I d3id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Double>> iterator() {
            return new Iterator<Tensor<Double>>() {
                Iterator<Tensor<Double>> it = IdDoubleD4UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Double> next() {
                    return new IdDoubleD3TensorShell<I>((DoubleD3Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdCharD4UnmodifiableTensorShell<I> extends CharD4UnmodifiableTensorShell implements IdCharD4Tensor<I> {
        private final IdCharD4Tensor<I> tensor;
        
        private IdCharD4UnmodifiableTensorShell(IdCharD4Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public char getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(char value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Character getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Character value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public char getCellById(I d0id, I d1id, I d2id, I d3id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id);
        }
        
        public void setCellById(char value,I d0id, I d1id, I d2id, I d3id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Character getValueById(I d0id, I d1id, I d2id, I d3id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id);
        }

        public void setValueById(Character value, I d0id, I d1id, I d2id, I d3id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Character>> iterator() {
            return new Iterator<Tensor<Character>>() {
                Iterator<Tensor<Character>> it = IdCharD4UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Character> next() {
                    return new IdCharD3TensorShell<I>((CharD3Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdBooleanD4UnmodifiableTensorShell<I> extends BooleanD4UnmodifiableTensorShell implements IdBooleanD4Tensor<I> {
        private final IdBooleanD4Tensor<I> tensor;
        
        private IdBooleanD4UnmodifiableTensorShell(IdBooleanD4Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public boolean getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Boolean getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public boolean getCellById(I d0id, I d1id, I d2id, I d3id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id);
        }
        
        public void setCellById(boolean value,I d0id, I d1id, I d2id, I d3id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Boolean getValueById(I d0id, I d1id, I d2id, I d3id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id);
        }

        public void setValueById(Boolean value, I d0id, I d1id, I d2id, I d3id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Boolean>> iterator() {
            return new Iterator<Tensor<Boolean>>() {
                Iterator<Tensor<Boolean>> it = IdBooleanD4UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Boolean> next() {
                    return new IdBooleanD3TensorShell<I>((BooleanD3Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdByteD5UnmodifiableTensorShell<I> extends ByteD5UnmodifiableTensorShell implements IdByteD5Tensor<I> {
        private final IdByteD5Tensor<I> tensor;
        
        private IdByteD5UnmodifiableTensorShell(IdByteD5Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public byte getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Byte getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public byte getCellById(I d0id, I d1id, I d2id, I d3id, I d4id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id);
        }
        
        public void setCellById(byte value,I d0id, I d1id, I d2id, I d3id, I d4id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Byte getValueById(I d0id, I d1id, I d2id, I d3id, I d4id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id);
        }

        public void setValueById(Byte value, I d0id, I d1id, I d2id, I d3id, I d4id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Byte>> iterator() {
            return new Iterator<Tensor<Byte>>() {
                Iterator<Tensor<Byte>> it = IdByteD5UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Byte> next() {
                    return new IdByteD4TensorShell<I>((ByteD4Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdShortD5UnmodifiableTensorShell<I> extends ShortD5UnmodifiableTensorShell implements IdShortD5Tensor<I> {
        private final IdShortD5Tensor<I> tensor;
        
        private IdShortD5UnmodifiableTensorShell(IdShortD5Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public short getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Short getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public short getCellById(I d0id, I d1id, I d2id, I d3id, I d4id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id);
        }
        
        public void setCellById(short value,I d0id, I d1id, I d2id, I d3id, I d4id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Short getValueById(I d0id, I d1id, I d2id, I d3id, I d4id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id);
        }

        public void setValueById(Short value, I d0id, I d1id, I d2id, I d3id, I d4id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Short>> iterator() {
            return new Iterator<Tensor<Short>>() {
                Iterator<Tensor<Short>> it = IdShortD5UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Short> next() {
                    return new IdShortD4TensorShell<I>((ShortD4Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdIntD5UnmodifiableTensorShell<I> extends IntD5UnmodifiableTensorShell implements IdIntD5Tensor<I> {
        private final IdIntD5Tensor<I> tensor;
        
        private IdIntD5UnmodifiableTensorShell(IdIntD5Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public int getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(int value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Integer getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Integer value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public int getCellById(I d0id, I d1id, I d2id, I d3id, I d4id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id);
        }
        
        public void setCellById(int value,I d0id, I d1id, I d2id, I d3id, I d4id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Integer getValueById(I d0id, I d1id, I d2id, I d3id, I d4id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id);
        }

        public void setValueById(Integer value, I d0id, I d1id, I d2id, I d3id, I d4id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Integer>> iterator() {
            return new Iterator<Tensor<Integer>>() {
                Iterator<Tensor<Integer>> it = IdIntD5UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Integer> next() {
                    return new IdIntD4TensorShell<I>((IntD4Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdLongD5UnmodifiableTensorShell<I> extends LongD5UnmodifiableTensorShell implements IdLongD5Tensor<I> {
        private final IdLongD5Tensor<I> tensor;
        
        private IdLongD5UnmodifiableTensorShell(IdLongD5Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public long getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Long getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public long getCellById(I d0id, I d1id, I d2id, I d3id, I d4id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id);
        }
        
        public void setCellById(long value,I d0id, I d1id, I d2id, I d3id, I d4id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Long getValueById(I d0id, I d1id, I d2id, I d3id, I d4id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id);
        }

        public void setValueById(Long value, I d0id, I d1id, I d2id, I d3id, I d4id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Long>> iterator() {
            return new Iterator<Tensor<Long>>() {
                Iterator<Tensor<Long>> it = IdLongD5UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Long> next() {
                    return new IdLongD4TensorShell<I>((LongD4Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdFloatD5UnmodifiableTensorShell<I> extends FloatD5UnmodifiableTensorShell implements IdFloatD5Tensor<I> {
        private final IdFloatD5Tensor<I> tensor;
        
        private IdFloatD5UnmodifiableTensorShell(IdFloatD5Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public float getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Float getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public float getCellById(I d0id, I d1id, I d2id, I d3id, I d4id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id);
        }
        
        public void setCellById(float value,I d0id, I d1id, I d2id, I d3id, I d4id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Float getValueById(I d0id, I d1id, I d2id, I d3id, I d4id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id);
        }

        public void setValueById(Float value, I d0id, I d1id, I d2id, I d3id, I d4id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Float>> iterator() {
            return new Iterator<Tensor<Float>>() {
                Iterator<Tensor<Float>> it = IdFloatD5UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Float> next() {
                    return new IdFloatD4TensorShell<I>((FloatD4Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdDoubleD5UnmodifiableTensorShell<I> extends DoubleD5UnmodifiableTensorShell implements IdDoubleD5Tensor<I> {
        private final IdDoubleD5Tensor<I> tensor;
        
        private IdDoubleD5UnmodifiableTensorShell(IdDoubleD5Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public double getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Double getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public double getCellById(I d0id, I d1id, I d2id, I d3id, I d4id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id);
        }
        
        public void setCellById(double value,I d0id, I d1id, I d2id, I d3id, I d4id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Double getValueById(I d0id, I d1id, I d2id, I d3id, I d4id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id);
        }

        public void setValueById(Double value, I d0id, I d1id, I d2id, I d3id, I d4id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Double>> iterator() {
            return new Iterator<Tensor<Double>>() {
                Iterator<Tensor<Double>> it = IdDoubleD5UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Double> next() {
                    return new IdDoubleD4TensorShell<I>((DoubleD4Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdCharD5UnmodifiableTensorShell<I> extends CharD5UnmodifiableTensorShell implements IdCharD5Tensor<I> {
        private final IdCharD5Tensor<I> tensor;
        
        private IdCharD5UnmodifiableTensorShell(IdCharD5Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public char getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(char value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Character getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Character value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public char getCellById(I d0id, I d1id, I d2id, I d3id, I d4id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id);
        }
        
        public void setCellById(char value,I d0id, I d1id, I d2id, I d3id, I d4id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Character getValueById(I d0id, I d1id, I d2id, I d3id, I d4id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id);
        }

        public void setValueById(Character value, I d0id, I d1id, I d2id, I d3id, I d4id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Character>> iterator() {
            return new Iterator<Tensor<Character>>() {
                Iterator<Tensor<Character>> it = IdCharD5UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Character> next() {
                    return new IdCharD4TensorShell<I>((CharD4Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdBooleanD5UnmodifiableTensorShell<I> extends BooleanD5UnmodifiableTensorShell implements IdBooleanD5Tensor<I> {
        private final IdBooleanD5Tensor<I> tensor;
        
        private IdBooleanD5UnmodifiableTensorShell(IdBooleanD5Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public boolean getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Boolean getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public boolean getCellById(I d0id, I d1id, I d2id, I d3id, I d4id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id);
        }
        
        public void setCellById(boolean value,I d0id, I d1id, I d2id, I d3id, I d4id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Boolean getValueById(I d0id, I d1id, I d2id, I d3id, I d4id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id);
        }

        public void setValueById(Boolean value, I d0id, I d1id, I d2id, I d3id, I d4id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Boolean>> iterator() {
            return new Iterator<Tensor<Boolean>>() {
                Iterator<Tensor<Boolean>> it = IdBooleanD5UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Boolean> next() {
                    return new IdBooleanD4TensorShell<I>((BooleanD4Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdByteD6UnmodifiableTensorShell<I> extends ByteD6UnmodifiableTensorShell implements IdByteD6Tensor<I> {
        private final IdByteD6Tensor<I> tensor;
        
        private IdByteD6UnmodifiableTensorShell(IdByteD6Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public byte getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Byte getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public byte getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id);
        }
        
        public void setCellById(byte value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Byte getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id);
        }

        public void setValueById(Byte value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Byte>> iterator() {
            return new Iterator<Tensor<Byte>>() {
                Iterator<Tensor<Byte>> it = IdByteD6UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Byte> next() {
                    return new IdByteD5TensorShell<I>((ByteD5Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdShortD6UnmodifiableTensorShell<I> extends ShortD6UnmodifiableTensorShell implements IdShortD6Tensor<I> {
        private final IdShortD6Tensor<I> tensor;
        
        private IdShortD6UnmodifiableTensorShell(IdShortD6Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public short getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Short getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public short getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id);
        }
        
        public void setCellById(short value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Short getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id);
        }

        public void setValueById(Short value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Short>> iterator() {
            return new Iterator<Tensor<Short>>() {
                Iterator<Tensor<Short>> it = IdShortD6UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Short> next() {
                    return new IdShortD5TensorShell<I>((ShortD5Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdIntD6UnmodifiableTensorShell<I> extends IntD6UnmodifiableTensorShell implements IdIntD6Tensor<I> {
        private final IdIntD6Tensor<I> tensor;
        
        private IdIntD6UnmodifiableTensorShell(IdIntD6Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public int getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(int value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Integer getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Integer value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public int getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id);
        }
        
        public void setCellById(int value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Integer getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id);
        }

        public void setValueById(Integer value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Integer>> iterator() {
            return new Iterator<Tensor<Integer>>() {
                Iterator<Tensor<Integer>> it = IdIntD6UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Integer> next() {
                    return new IdIntD5TensorShell<I>((IntD5Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdLongD6UnmodifiableTensorShell<I> extends LongD6UnmodifiableTensorShell implements IdLongD6Tensor<I> {
        private final IdLongD6Tensor<I> tensor;
        
        private IdLongD6UnmodifiableTensorShell(IdLongD6Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public long getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Long getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public long getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id);
        }
        
        public void setCellById(long value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Long getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id);
        }

        public void setValueById(Long value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Long>> iterator() {
            return new Iterator<Tensor<Long>>() {
                Iterator<Tensor<Long>> it = IdLongD6UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Long> next() {
                    return new IdLongD5TensorShell<I>((LongD5Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdFloatD6UnmodifiableTensorShell<I> extends FloatD6UnmodifiableTensorShell implements IdFloatD6Tensor<I> {
        private final IdFloatD6Tensor<I> tensor;
        
        private IdFloatD6UnmodifiableTensorShell(IdFloatD6Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public float getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Float getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public float getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id);
        }
        
        public void setCellById(float value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Float getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id);
        }

        public void setValueById(Float value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Float>> iterator() {
            return new Iterator<Tensor<Float>>() {
                Iterator<Tensor<Float>> it = IdFloatD6UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Float> next() {
                    return new IdFloatD5TensorShell<I>((FloatD5Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdDoubleD6UnmodifiableTensorShell<I> extends DoubleD6UnmodifiableTensorShell implements IdDoubleD6Tensor<I> {
        private final IdDoubleD6Tensor<I> tensor;
        
        private IdDoubleD6UnmodifiableTensorShell(IdDoubleD6Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public double getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Double getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public double getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id);
        }
        
        public void setCellById(double value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Double getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id);
        }

        public void setValueById(Double value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Double>> iterator() {
            return new Iterator<Tensor<Double>>() {
                Iterator<Tensor<Double>> it = IdDoubleD6UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Double> next() {
                    return new IdDoubleD5TensorShell<I>((DoubleD5Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdCharD6UnmodifiableTensorShell<I> extends CharD6UnmodifiableTensorShell implements IdCharD6Tensor<I> {
        private final IdCharD6Tensor<I> tensor;
        
        private IdCharD6UnmodifiableTensorShell(IdCharD6Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public char getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(char value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Character getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Character value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public char getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id);
        }
        
        public void setCellById(char value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Character getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id);
        }

        public void setValueById(Character value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Character>> iterator() {
            return new Iterator<Tensor<Character>>() {
                Iterator<Tensor<Character>> it = IdCharD6UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Character> next() {
                    return new IdCharD5TensorShell<I>((CharD5Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdBooleanD6UnmodifiableTensorShell<I> extends BooleanD6UnmodifiableTensorShell implements IdBooleanD6Tensor<I> {
        private final IdBooleanD6Tensor<I> tensor;
        
        private IdBooleanD6UnmodifiableTensorShell(IdBooleanD6Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public boolean getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Boolean getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public boolean getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id);
        }
        
        public void setCellById(boolean value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Boolean getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id);
        }

        public void setValueById(Boolean value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Boolean>> iterator() {
            return new Iterator<Tensor<Boolean>>() {
                Iterator<Tensor<Boolean>> it = IdBooleanD6UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Boolean> next() {
                    return new IdBooleanD5TensorShell<I>((BooleanD5Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdByteD7UnmodifiableTensorShell<I> extends ByteD7UnmodifiableTensorShell implements IdByteD7Tensor<I> {
        private final IdByteD7Tensor<I> tensor;
        
        private IdByteD7UnmodifiableTensorShell(IdByteD7Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public byte getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Byte getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public byte getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id);
        }
        
        public void setCellById(byte value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Byte getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id);
        }

        public void setValueById(Byte value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Byte>> iterator() {
            return new Iterator<Tensor<Byte>>() {
                Iterator<Tensor<Byte>> it = IdByteD7UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Byte> next() {
                    return new IdByteD6TensorShell<I>((ByteD6Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdShortD7UnmodifiableTensorShell<I> extends ShortD7UnmodifiableTensorShell implements IdShortD7Tensor<I> {
        private final IdShortD7Tensor<I> tensor;
        
        private IdShortD7UnmodifiableTensorShell(IdShortD7Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public short getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Short getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public short getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id);
        }
        
        public void setCellById(short value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Short getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id);
        }

        public void setValueById(Short value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Short>> iterator() {
            return new Iterator<Tensor<Short>>() {
                Iterator<Tensor<Short>> it = IdShortD7UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Short> next() {
                    return new IdShortD6TensorShell<I>((ShortD6Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdIntD7UnmodifiableTensorShell<I> extends IntD7UnmodifiableTensorShell implements IdIntD7Tensor<I> {
        private final IdIntD7Tensor<I> tensor;
        
        private IdIntD7UnmodifiableTensorShell(IdIntD7Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public int getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(int value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Integer getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Integer value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public int getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id);
        }
        
        public void setCellById(int value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Integer getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id);
        }

        public void setValueById(Integer value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Integer>> iterator() {
            return new Iterator<Tensor<Integer>>() {
                Iterator<Tensor<Integer>> it = IdIntD7UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Integer> next() {
                    return new IdIntD6TensorShell<I>((IntD6Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdLongD7UnmodifiableTensorShell<I> extends LongD7UnmodifiableTensorShell implements IdLongD7Tensor<I> {
        private final IdLongD7Tensor<I> tensor;
        
        private IdLongD7UnmodifiableTensorShell(IdLongD7Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public long getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Long getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public long getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id);
        }
        
        public void setCellById(long value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Long getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id);
        }

        public void setValueById(Long value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Long>> iterator() {
            return new Iterator<Tensor<Long>>() {
                Iterator<Tensor<Long>> it = IdLongD7UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Long> next() {
                    return new IdLongD6TensorShell<I>((LongD6Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdFloatD7UnmodifiableTensorShell<I> extends FloatD7UnmodifiableTensorShell implements IdFloatD7Tensor<I> {
        private final IdFloatD7Tensor<I> tensor;
        
        private IdFloatD7UnmodifiableTensorShell(IdFloatD7Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public float getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Float getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public float getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id);
        }
        
        public void setCellById(float value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Float getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id);
        }

        public void setValueById(Float value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Float>> iterator() {
            return new Iterator<Tensor<Float>>() {
                Iterator<Tensor<Float>> it = IdFloatD7UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Float> next() {
                    return new IdFloatD6TensorShell<I>((FloatD6Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdDoubleD7UnmodifiableTensorShell<I> extends DoubleD7UnmodifiableTensorShell implements IdDoubleD7Tensor<I> {
        private final IdDoubleD7Tensor<I> tensor;
        
        private IdDoubleD7UnmodifiableTensorShell(IdDoubleD7Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public double getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Double getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public double getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id);
        }
        
        public void setCellById(double value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Double getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id);
        }

        public void setValueById(Double value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Double>> iterator() {
            return new Iterator<Tensor<Double>>() {
                Iterator<Tensor<Double>> it = IdDoubleD7UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Double> next() {
                    return new IdDoubleD6TensorShell<I>((DoubleD6Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdCharD7UnmodifiableTensorShell<I> extends CharD7UnmodifiableTensorShell implements IdCharD7Tensor<I> {
        private final IdCharD7Tensor<I> tensor;
        
        private IdCharD7UnmodifiableTensorShell(IdCharD7Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public char getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(char value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Character getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Character value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public char getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id);
        }
        
        public void setCellById(char value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Character getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id);
        }

        public void setValueById(Character value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Character>> iterator() {
            return new Iterator<Tensor<Character>>() {
                Iterator<Tensor<Character>> it = IdCharD7UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Character> next() {
                    return new IdCharD6TensorShell<I>((CharD6Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdBooleanD7UnmodifiableTensorShell<I> extends BooleanD7UnmodifiableTensorShell implements IdBooleanD7Tensor<I> {
        private final IdBooleanD7Tensor<I> tensor;
        
        private IdBooleanD7UnmodifiableTensorShell(IdBooleanD7Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public boolean getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Boolean getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public boolean getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id);
        }
        
        public void setCellById(boolean value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Boolean getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id);
        }

        public void setValueById(Boolean value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Boolean>> iterator() {
            return new Iterator<Tensor<Boolean>>() {
                Iterator<Tensor<Boolean>> it = IdBooleanD7UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Boolean> next() {
                    return new IdBooleanD6TensorShell<I>((BooleanD6Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdByteD8UnmodifiableTensorShell<I> extends ByteD8UnmodifiableTensorShell implements IdByteD8Tensor<I> {
        private final IdByteD8Tensor<I> tensor;
        
        private IdByteD8UnmodifiableTensorShell(IdByteD8Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public byte getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Byte getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public byte getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id);
        }
        
        public void setCellById(byte value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Byte getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id);
        }

        public void setValueById(Byte value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Byte>> iterator() {
            return new Iterator<Tensor<Byte>>() {
                Iterator<Tensor<Byte>> it = IdByteD8UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Byte> next() {
                    return new IdByteD7TensorShell<I>((ByteD7Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdShortD8UnmodifiableTensorShell<I> extends ShortD8UnmodifiableTensorShell implements IdShortD8Tensor<I> {
        private final IdShortD8Tensor<I> tensor;
        
        private IdShortD8UnmodifiableTensorShell(IdShortD8Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public short getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Short getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public short getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id);
        }
        
        public void setCellById(short value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Short getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id);
        }

        public void setValueById(Short value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Short>> iterator() {
            return new Iterator<Tensor<Short>>() {
                Iterator<Tensor<Short>> it = IdShortD8UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Short> next() {
                    return new IdShortD7TensorShell<I>((ShortD7Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdIntD8UnmodifiableTensorShell<I> extends IntD8UnmodifiableTensorShell implements IdIntD8Tensor<I> {
        private final IdIntD8Tensor<I> tensor;
        
        private IdIntD8UnmodifiableTensorShell(IdIntD8Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public int getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(int value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Integer getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Integer value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public int getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id);
        }
        
        public void setCellById(int value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Integer getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id);
        }

        public void setValueById(Integer value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Integer>> iterator() {
            return new Iterator<Tensor<Integer>>() {
                Iterator<Tensor<Integer>> it = IdIntD8UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Integer> next() {
                    return new IdIntD7TensorShell<I>((IntD7Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdLongD8UnmodifiableTensorShell<I> extends LongD8UnmodifiableTensorShell implements IdLongD8Tensor<I> {
        private final IdLongD8Tensor<I> tensor;
        
        private IdLongD8UnmodifiableTensorShell(IdLongD8Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public long getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Long getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public long getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id);
        }
        
        public void setCellById(long value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Long getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id);
        }

        public void setValueById(Long value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Long>> iterator() {
            return new Iterator<Tensor<Long>>() {
                Iterator<Tensor<Long>> it = IdLongD8UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Long> next() {
                    return new IdLongD7TensorShell<I>((LongD7Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdFloatD8UnmodifiableTensorShell<I> extends FloatD8UnmodifiableTensorShell implements IdFloatD8Tensor<I> {
        private final IdFloatD8Tensor<I> tensor;
        
        private IdFloatD8UnmodifiableTensorShell(IdFloatD8Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public float getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Float getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public float getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id);
        }
        
        public void setCellById(float value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Float getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id);
        }

        public void setValueById(Float value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Float>> iterator() {
            return new Iterator<Tensor<Float>>() {
                Iterator<Tensor<Float>> it = IdFloatD8UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Float> next() {
                    return new IdFloatD7TensorShell<I>((FloatD7Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdDoubleD8UnmodifiableTensorShell<I> extends DoubleD8UnmodifiableTensorShell implements IdDoubleD8Tensor<I> {
        private final IdDoubleD8Tensor<I> tensor;
        
        private IdDoubleD8UnmodifiableTensorShell(IdDoubleD8Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public double getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Double getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public double getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id);
        }
        
        public void setCellById(double value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Double getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id);
        }

        public void setValueById(Double value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Double>> iterator() {
            return new Iterator<Tensor<Double>>() {
                Iterator<Tensor<Double>> it = IdDoubleD8UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Double> next() {
                    return new IdDoubleD7TensorShell<I>((DoubleD7Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdCharD8UnmodifiableTensorShell<I> extends CharD8UnmodifiableTensorShell implements IdCharD8Tensor<I> {
        private final IdCharD8Tensor<I> tensor;
        
        private IdCharD8UnmodifiableTensorShell(IdCharD8Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public char getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(char value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Character getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Character value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public char getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id);
        }
        
        public void setCellById(char value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Character getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id);
        }

        public void setValueById(Character value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Character>> iterator() {
            return new Iterator<Tensor<Character>>() {
                Iterator<Tensor<Character>> it = IdCharD8UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Character> next() {
                    return new IdCharD7TensorShell<I>((CharD7Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdBooleanD8UnmodifiableTensorShell<I> extends BooleanD8UnmodifiableTensorShell implements IdBooleanD8Tensor<I> {
        private final IdBooleanD8Tensor<I> tensor;
        
        private IdBooleanD8UnmodifiableTensorShell(IdBooleanD8Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public boolean getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Boolean getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public boolean getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id);
        }
        
        public void setCellById(boolean value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Boolean getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id);
        }

        public void setValueById(Boolean value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Boolean>> iterator() {
            return new Iterator<Tensor<Boolean>>() {
                Iterator<Tensor<Boolean>> it = IdBooleanD8UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Boolean> next() {
                    return new IdBooleanD7TensorShell<I>((BooleanD7Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdByteD9UnmodifiableTensorShell<I> extends ByteD9UnmodifiableTensorShell implements IdByteD9Tensor<I> {
        private final IdByteD9Tensor<I> tensor;
        
        private IdByteD9UnmodifiableTensorShell(IdByteD9Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public byte getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Byte getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Byte value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public byte getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id,d8id);
        }
        
        public void setCellById(byte value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Byte getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id,d8id);
        }

        public void setValueById(Byte value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Byte>> iterator() {
            return new Iterator<Tensor<Byte>>() {
                Iterator<Tensor<Byte>> it = IdByteD9UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Byte> next() {
                    return new IdByteD8TensorShell<I>((ByteD8Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdShortD9UnmodifiableTensorShell<I> extends ShortD9UnmodifiableTensorShell implements IdShortD9Tensor<I> {
        private final IdShortD9Tensor<I> tensor;
        
        private IdShortD9UnmodifiableTensorShell(IdShortD9Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public short getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Short getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Short value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public short getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id,d8id);
        }
        
        public void setCellById(short value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Short getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id,d8id);
        }

        public void setValueById(Short value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Short>> iterator() {
            return new Iterator<Tensor<Short>>() {
                Iterator<Tensor<Short>> it = IdShortD9UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Short> next() {
                    return new IdShortD8TensorShell<I>((ShortD8Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdIntD9UnmodifiableTensorShell<I> extends IntD9UnmodifiableTensorShell implements IdIntD9Tensor<I> {
        private final IdIntD9Tensor<I> tensor;
        
        private IdIntD9UnmodifiableTensorShell(IdIntD9Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public int getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(int value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Integer getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Integer value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public int getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id,d8id);
        }
        
        public void setCellById(int value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Integer getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id,d8id);
        }

        public void setValueById(Integer value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Integer>> iterator() {
            return new Iterator<Tensor<Integer>>() {
                Iterator<Tensor<Integer>> it = IdIntD9UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Integer> next() {
                    return new IdIntD8TensorShell<I>((IntD8Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdLongD9UnmodifiableTensorShell<I> extends LongD9UnmodifiableTensorShell implements IdLongD9Tensor<I> {
        private final IdLongD9Tensor<I> tensor;
        
        private IdLongD9UnmodifiableTensorShell(IdLongD9Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public long getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Long getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Long value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public long getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id,d8id);
        }
        
        public void setCellById(long value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Long getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id,d8id);
        }

        public void setValueById(Long value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Long>> iterator() {
            return new Iterator<Tensor<Long>>() {
                Iterator<Tensor<Long>> it = IdLongD9UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Long> next() {
                    return new IdLongD8TensorShell<I>((LongD8Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdFloatD9UnmodifiableTensorShell<I> extends FloatD9UnmodifiableTensorShell implements IdFloatD9Tensor<I> {
        private final IdFloatD9Tensor<I> tensor;
        
        private IdFloatD9UnmodifiableTensorShell(IdFloatD9Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public float getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Float getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Float value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public float getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id,d8id);
        }
        
        public void setCellById(float value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Float getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id,d8id);
        }

        public void setValueById(Float value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Float>> iterator() {
            return new Iterator<Tensor<Float>>() {
                Iterator<Tensor<Float>> it = IdFloatD9UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Float> next() {
                    return new IdFloatD8TensorShell<I>((FloatD8Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdDoubleD9UnmodifiableTensorShell<I> extends DoubleD9UnmodifiableTensorShell implements IdDoubleD9Tensor<I> {
        private final IdDoubleD9Tensor<I> tensor;
        
        private IdDoubleD9UnmodifiableTensorShell(IdDoubleD9Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public double getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Double getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Double value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public double getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id,d8id);
        }
        
        public void setCellById(double value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Double getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id,d8id);
        }

        public void setValueById(Double value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Double>> iterator() {
            return new Iterator<Tensor<Double>>() {
                Iterator<Tensor<Double>> it = IdDoubleD9UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Double> next() {
                    return new IdDoubleD8TensorShell<I>((DoubleD8Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdCharD9UnmodifiableTensorShell<I> extends CharD9UnmodifiableTensorShell implements IdCharD9Tensor<I> {
        private final IdCharD9Tensor<I> tensor;
        
        private IdCharD9UnmodifiableTensorShell(IdCharD9Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public char getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(char value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Character getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Character value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public char getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id,d8id);
        }
        
        public void setCellById(char value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Character getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id,d8id);
        }

        public void setValueById(Character value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Character>> iterator() {
            return new Iterator<Tensor<Character>>() {
                Iterator<Tensor<Character>> it = IdCharD9UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Character> next() {
                    return new IdCharD8TensorShell<I>((CharD8Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdBooleanD9UnmodifiableTensorShell<I> extends BooleanD9UnmodifiableTensorShell implements IdBooleanD9Tensor<I> {
        private final IdBooleanD9Tensor<I> tensor;
        
        private IdBooleanD9UnmodifiableTensorShell(IdBooleanD9Tensor<I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }

        @SuppressWarnings({"unchecked","varargs"})
        public boolean getCellById(I ... ids) {
            return tensor.getCellById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setCellById(boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public Boolean getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(Boolean value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }
        
        public boolean getCellById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            return tensor.getCellById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id,d8id);
        }
        
        public void setCellById(boolean value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Boolean getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id,d8id);
        }

        public void setValueById(Boolean value, I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<Boolean>> iterator() {
            return new Iterator<Tensor<Boolean>>() {
                Iterator<Tensor<Boolean>> it = IdBooleanD9UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<Boolean> next() {
                    return new IdBooleanD8TensorShell<I>((BooleanD8Tensor) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdD0UnmodifiableTensorShell<T,I> extends D0UnmodifiableTensorShell<T> implements IdScalar<T,I> {
        private final IdD0Tensor<T,I> tensor;
    
        private IdD0UnmodifiableTensorShell(IdD0Tensor<T,I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }
        
        public T getValueById() {
            return tensor.getValueById();
        }
        
        public void setValueById(T value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public T getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(T value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public T getValue() {
            return tensor.getValue();
        }

        public void setValue(T value) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<T>> iterator() {
            return new Iterator<Tensor<T>>() {
                Iterator<Tensor<T>> it = IdD0UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<T> next() {
                    return new IdD0TensorShell<T,I>((D0Tensor<T>) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdD1UnmodifiableTensorShell<T,I> extends D1UnmodifiableTensorShell<T> implements IdVector<T,I> {
        private final IdD1Tensor<T,I> tensor;
    
        private IdD1UnmodifiableTensorShell(IdD1Tensor<T,I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }
        
        public T getValueById(I id) {
            return tensor.getValueById(id);
        }
        
        public void setValueById(T value,I id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public T getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(T value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public T getValue(int index) {
            return tensor.getValue(index);
        }

        public void setValue(T value, int index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<T>> iterator() {
            return new Iterator<Tensor<T>>() {
                Iterator<Tensor<T>> it = IdD1UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<T> next() {
                    return new IdD0TensorShell<T,I>((D0Tensor<T>) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdD2UnmodifiableTensorShell<T,I> extends D2UnmodifiableTensorShell<T> implements IdMatrix<T,I> {
        private final IdD2Tensor<T,I> tensor;
    
        private IdD2UnmodifiableTensorShell(IdD2Tensor<T,I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }
        
        public T getValueById(I d0id, I d1id) {
            return tensor.getValueById(d0id,d1id);
        }
        
        public void setValueById(T value,I d0id, I d1id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public T getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(T value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public T getValue(int d0index, int d1index) {
            return tensor.getValue(d0index,d1index);
        }

        public void setValue(T value, int d0index, int d1index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<T>> iterator() {
            return new Iterator<Tensor<T>>() {
                Iterator<Tensor<T>> it = IdD2UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<T> next() {
                    return new IdD1TensorShell<T,I>((D1Tensor<T>) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdD3UnmodifiableTensorShell<T,I> extends D3UnmodifiableTensorShell<T> implements IdD3Tensor<T,I> {
        private final IdD3Tensor<T,I> tensor;
    
        private IdD3UnmodifiableTensorShell(IdD3Tensor<T,I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }
        
        public T getValueById(I d0id, I d1id, I d2id) {
            return tensor.getValueById(d0id,d1id,d2id);
        }
        
        public void setValueById(T value,I d0id, I d1id, I d2id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public T getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(T value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public T getValue(int d0index, int d1index, int d2index) {
            return tensor.getValue(d0index,d1index,d2index);
        }

        public void setValue(T value, int d0index, int d1index, int d2index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<T>> iterator() {
            return new Iterator<Tensor<T>>() {
                Iterator<Tensor<T>> it = IdD3UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<T> next() {
                    return new IdD2TensorShell<T,I>((D2Tensor<T>) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdD4UnmodifiableTensorShell<T,I> extends D4UnmodifiableTensorShell<T> implements IdD4Tensor<T,I> {
        private final IdD4Tensor<T,I> tensor;
    
        private IdD4UnmodifiableTensorShell(IdD4Tensor<T,I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }
        
        public T getValueById(I d0id, I d1id, I d2id, I d3id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id);
        }
        
        public void setValueById(T value,I d0id, I d1id, I d2id, I d3id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public T getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(T value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public T getValue(int d0index, int d1index, int d2index, int d3index) {
            return tensor.getValue(d0index,d1index,d2index,d3index);
        }

        public void setValue(T value, int d0index, int d1index, int d2index, int d3index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<T>> iterator() {
            return new Iterator<Tensor<T>>() {
                Iterator<Tensor<T>> it = IdD4UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<T> next() {
                    return new IdD3TensorShell<T,I>((D3Tensor<T>) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdD5UnmodifiableTensorShell<T,I> extends D5UnmodifiableTensorShell<T> implements IdD5Tensor<T,I> {
        private final IdD5Tensor<T,I> tensor;
    
        private IdD5UnmodifiableTensorShell(IdD5Tensor<T,I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }
        
        public T getValueById(I d0id, I d1id, I d2id, I d3id, I d4id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id);
        }
        
        public void setValueById(T value,I d0id, I d1id, I d2id, I d3id, I d4id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public T getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(T value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public T getValue(int d0index, int d1index, int d2index, int d3index, int d4index) {
            return tensor.getValue(d0index,d1index,d2index,d3index,d4index);
        }

        public void setValue(T value, int d0index, int d1index, int d2index, int d3index, int d4index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<T>> iterator() {
            return new Iterator<Tensor<T>>() {
                Iterator<Tensor<T>> it = IdD5UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<T> next() {
                    return new IdD4TensorShell<T,I>((D4Tensor<T>) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdD6UnmodifiableTensorShell<T,I> extends D6UnmodifiableTensorShell<T> implements IdD6Tensor<T,I> {
        private final IdD6Tensor<T,I> tensor;
    
        private IdD6UnmodifiableTensorShell(IdD6Tensor<T,I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }
        
        public T getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id);
        }
        
        public void setValueById(T value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public T getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(T value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public T getValue(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            return tensor.getValue(d0index,d1index,d2index,d3index,d4index,d5index);
        }

        public void setValue(T value, int d0index, int d1index, int d2index, int d3index, int d4index, int d5index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<T>> iterator() {
            return new Iterator<Tensor<T>>() {
                Iterator<Tensor<T>> it = IdD6UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<T> next() {
                    return new IdD5TensorShell<T,I>((D5Tensor<T>) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdD7UnmodifiableTensorShell<T,I> extends D7UnmodifiableTensorShell<T> implements IdD7Tensor<T,I> {
        private final IdD7Tensor<T,I> tensor;
    
        private IdD7UnmodifiableTensorShell(IdD7Tensor<T,I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }
        
        public T getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id);
        }
        
        public void setValueById(T value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public T getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(T value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public T getValue(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            return tensor.getValue(d0index,d1index,d2index,d3index,d4index,d5index,d6index);
        }

        public void setValue(T value, int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<T>> iterator() {
            return new Iterator<Tensor<T>>() {
                Iterator<Tensor<T>> it = IdD7UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<T> next() {
                    return new IdD6TensorShell<T,I>((D6Tensor<T>) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdD8UnmodifiableTensorShell<T,I> extends D8UnmodifiableTensorShell<T> implements IdD8Tensor<T,I> {
        private final IdD8Tensor<T,I> tensor;
    
        private IdD8UnmodifiableTensorShell(IdD8Tensor<T,I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }
        
        public T getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id);
        }
        
        public void setValueById(T value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public T getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(T value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public T getValue(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            return tensor.getValue(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index);
        }

        public void setValue(T value, int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<T>> iterator() {
            return new Iterator<Tensor<T>>() {
                Iterator<Tensor<T>> it = IdD8UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<T> next() {
                    return new IdD7TensorShell<T,I>((D7Tensor<T>) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
 
    private static class IdD9UnmodifiableTensorShell<T,I> extends D9UnmodifiableTensorShell<T> implements IdD9Tensor<T,I> {
        private final IdD9Tensor<T,I> tensor;
    
        private IdD9UnmodifiableTensorShell(IdD9Tensor<T,I> tensor) {
            super(tensor);
            this.tensor = tensor;
        }
        
        public Index<I> getIndex() {
            return tensor.getIndex();
        }
        
        public T getValueById(I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            return tensor.getValueById(d0id,d1id,d2id,d3id,d4id,d5id,d6id,d7id,d8id);
        }
        
        public void setValueById(T value,I d0id, I d1id, I d2id, I d3id, I d4id, I d5id, I d6id, I d7id, I d8id) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        @SuppressWarnings({"unchecked","varargs"})
        public T getValueById(I ... ids) {
            return tensor.getValueById(ids);
        }

        @SuppressWarnings({"unchecked","varargs"})
        public void setValueById(T value, I ... ids) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public T getValue(int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            return tensor.getValue(d0index,d1index,d2index,d3index,d4index,d5index,d6index,d7index,d8index);
        }

        public void setValue(T value, int d0index, int d1index, int d2index, int d3index, int d4index, int d5index, int d6index, int d7index, int d8index) {
            throw new UnsupportedOperationException("Tensor is unmodifiable.");
        }

        public Iterator<Tensor<T>> iterator() {
            return new Iterator<Tensor<T>>() {
                Iterator<Tensor<T>> it = IdD9UnmodifiableTensorShell.super.iterator();
    
                public boolean hasNext() {
                    return it.hasNext();
                }
    
                public Tensor<T> next() {
                    return new IdD8TensorShell<T,I>((D8Tensor<T>) it.next());
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
}