package com.pb.sawdust.tabledata.transform.column;

import com.pb.sawdust.tabledata.DataTable;

import java.util.*;

/**
 * The {@code CompositeColumnWiseDataTableTransformation} class provides a simple structure for combining multiple column-wise
 * data table transformations into a single row-wise transformation. It may provide slight efficiency gains in that the transformations
 * will be performed in a single step, rather than through multiple transformation passes on an input data table.
 * <p>
 * Instances of this class implement {@code Iterable<C>}, and will iterate over the composited data transformations in the
 * order that they are applied.
 *
 * @param <C>
 *        The type of the column-wise transformations that are composited by this class.
 *
 *
 * @author crf
 *         Started 1/20/12 6:34 AM
 */
public class CompositeColumnWiseDataTableTransformation<C extends ColumnWiseDataTableTransformation> extends ColumnWiseDataTableTransformation implements Iterable<C> {
    private final List<C> transformations;

    /**
     * Constructor specifying the transformations to composite.
     *
     * @param transformations
     *        The transformations this transformation will be comprised of, in the order they will be applied.
     */
    public CompositeColumnWiseDataTableTransformation(Collection<? extends C> transformations) {
        this.transformations = new LinkedList<>(transformations);
    }

    @Override
    public Set<String> getColumnsToTransform() {
        Set<String> columnsToTransform = new HashSet<>();
        for (C transformation : transformations)
            columnsToTransform.addAll(transformation.getColumnsToTransform());
        return columnsToTransform;
    }

    @Override
    public DataTable transformColumns(Set<String> column, DataTable table) {
        for (C transformation : transformations)
            table = transformation.transformColumns(transformation.getColumnsToTransform(),table);
        return table;
    }

    @Override
    public Iterator<C> iterator() {
        return transformations.iterator();
    }
}