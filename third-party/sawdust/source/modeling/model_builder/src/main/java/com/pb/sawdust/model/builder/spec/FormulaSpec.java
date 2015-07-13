package com.pb.sawdust.model.builder.spec;

import java.util.Map;

/**
 * The {@code FormulaSpec} ...
 *
 * @author crf <br/>
 *         Started 4/10/11 2:39 PM
 */
public interface FormulaSpec {
    String getFormula();
    Map<String,Object> getConstraints();
}