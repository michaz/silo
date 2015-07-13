package com.pb.sawdust.geography;

import com.pb.sawdust.geography.*;
import com.pb.sawdust.geography.tensor.GeographicDoubleMatrix;
import com.pb.sawdust.tensor.ArrayTensor;
import com.pb.sawdust.tensor.TensorUtil;
import com.pb.sawdust.util.test.TestBase;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

/**
 * The {@code GeograhicNeighborhoodTest} ...
 *
 * @author crf
 *         Started 10/28/11 5:49 AM
 */
public class GeographicNeighborhoodTest extends TestBase {

    public static void main(String ... args) {
        TestBase.main();
    }


    protected Geography<String,NamedGeographyElement> basisGeography;
    protected Geography<String,NamedGeographyElement> overlappingGeography;
    protected Geography<String,NamedGeographyElement> intermediateGeography;
    protected Geography<Integer,IdGeographyElement> functionalGeography;
    protected GeographicMapping<NamedGeographyElement,NamedGeographyElement> intermediateMap;
    protected GeographicMapping<NamedGeographyElement,IdGeographyElement> functionalMap;
    protected GeographicMapping<NamedGeographyElement,NamedGeographyElement> overlappingMap;

    @Before
    public void beforeTest() {
        buildMappings();
    }

    /*
    Here are the geographies I'm defining (W,X,Y,Z are location markers):

    basis
    +-------+-------+-------+
    |W      |       |      X|
    |   A   |   B   |   C   |
    |       |       |       |
    +-------+-------+-------+
    |       |       |       |
    |   D   |   E   |   F   |
    |       |       |       |
    +-------+-------+-------+
    |       |       |       |
    |   G   |   H   |   I   |
    |Z      |       |      Y|
    +-------+-------+-------+

    intermediate
    +-------+  +-------+
    |W      |  |       |    +-------+
    |   i1  |  |   i2  |    |      Z|
    |       |  |       |    |       |
    +-------+  +-------+    |       |
    +-------+   +-------+   +  i5   +
    |       |   |       |   |       |
    |  i3   |   |  i4   |   |       |
    |       |   |       |   |       |
    +-------+   +-------+   +-------+
    +-------+   +-------+-------+
    |       |   |               |
    |  i6   |   |       i7      |
    |X      |   |              Y|
    +-------+   +-------+-------+

    functional
    +-------+-------+
    |W              |   +-------+
    |       1       |   |      Z|
    |               |   |       |
    +-------+-------+   |       |
    +-------+   +-------+       +
    |       |   |               |
    |   2   |   |         3     |
    |       |   |               |
    +-------+   +-------+-------+
    +-------+-------+-------+
    |                       |
    |           4           |
    |X                     Y|
    +-------+-------+-------+

    overlapping
    +-------+-------+-------+
    |W                     X|
    |         alpha         |     +-------+
    |                       |     |      X|
    +-------+-------+-------+     |       |
                                  |       |
    +-------+             +-------+       +
    |       |             |               |
    |       |             |       beta    |
    |       |             |               |
    + gamma +             +-------+       +
    |       |                     |       |
    |       |  +-------+-------+  |       |
    |Z      |  |               |  |      Y|
    +-------+  |    delta      |  +-------+
               |              Y|
               +-------+-------+
     */

    protected void buildMappings() {
        List<NamedGeographyElement> basisElements = new LinkedList<>();
        for (String s : Arrays.asList("A","B","C","D","E","F","G","H","I"))
            basisElements.add(new NamedGeographyElement(s,1));
        List<NamedGeographyElement> overlappingElements = new LinkedList<>();
        for (String s : Arrays.asList("alpha","beta","gamma","delta"))
            overlappingElements.add(new NamedGeographyElement(s,1));
        List<NamedGeographyElement> intermediateElements = new LinkedList<>();
        for (String s : Arrays.asList("i1","i2","i3","i4","i5","i6","i7"))
            intermediateElements.add(new NamedGeographyElement(s,1));
        List<IdGeographyElement> functionalElements = new LinkedList<>();
        for (Integer i : Arrays.asList(1,2,3,4))
            functionalElements.add(new IdGeographyElement(i,1));

        basisGeography = new Geography<>(basisElements);
        overlappingGeography = new Geography<>(overlappingElements);
        intermediateGeography = new Geography<>(intermediateElements);
        functionalGeography = new Geography<>(functionalElements);

        Map<NamedGeographyElement,NamedGeographyElement> intermediateMapping = new HashMap<>();
        intermediateMapping.put(basisElements.get(0),intermediateElements.get(0));
        intermediateMapping.put(basisElements.get(1),intermediateElements.get(1));
        intermediateMapping.put(basisElements.get(2),intermediateElements.get(4));
        intermediateMapping.put(basisElements.get(3),intermediateElements.get(2));
        intermediateMapping.put(basisElements.get(4),intermediateElements.get(3));
        intermediateMapping.put(basisElements.get(5),intermediateElements.get(4));
        intermediateMapping.put(basisElements.get(6),intermediateElements.get(5));
        intermediateMapping.put(basisElements.get(7),intermediateElements.get(6));
        intermediateMapping.put(basisElements.get(8),intermediateElements.get(6));
        intermediateMap = new FunctionalGeographicMapping<>(basisGeography,intermediateGeography,intermediateMapping);

        Map<NamedGeographyElement,IdGeographyElement> functionalMapping = new HashMap<>();
        functionalMapping.put(intermediateElements.get(0),functionalElements.get(0));
        functionalMapping.put(intermediateElements.get(1),functionalElements.get(0));
        functionalMapping.put(intermediateElements.get(2),functionalElements.get(1));
        functionalMapping.put(intermediateElements.get(3),functionalElements.get(2));
        functionalMapping.put(intermediateElements.get(4),functionalElements.get(2));
        functionalMapping.put(intermediateElements.get(5),functionalElements.get(3));
        functionalMapping.put(intermediateElements.get(6),functionalElements.get(3));
        functionalMap = new FunctionalGeographicMapping<>(intermediateGeography,functionalGeography,functionalMapping);

        double[][] overlappingOverlay = new double[][] {
                {1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                {0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0},
                {0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0},
                {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0}
        };

        GeographicDoubleMatrix<NamedGeographyElement,NamedGeographyElement> overlay = new GeographicDoubleMatrix<>(
                overlappingGeography,basisGeography,
                TensorUtil.arrayToMatrix(ArrayTensor.getFactory(),overlappingOverlay));
        overlappingMap = new FullGeographicMapping<>(overlay);
    }

//    @Test
//    public void testGetBasisMapping() {
//        System.out.println(GeographicNeighborhood.getMappingNeighborhoods(basisGeography,mappings));
////        System.out.println(GeographicNeighborhood.getBasisMapping(basisGeography,mappings,intermediateGeography));
//    }
    
    @Test
    public void testIntermediateNeighborhoods() {
        Collection<GeographicNeighborhood<NamedGeographyElement>> neighborhoods = new HashSet<>();
        neighborhoods.add(new GeographicNeighborhood<>(Arrays.asList(new NamedGeographyElement("A",1.0))));   
        neighborhoods.add(new GeographicNeighborhood<>(Arrays.asList(new NamedGeographyElement("B",1.0))));
        neighborhoods.add(new GeographicNeighborhood<>(Arrays.asList(new NamedGeographyElement("C",1.0),
                                                                     new NamedGeographyElement("F",1.0))));
        neighborhoods.add(new GeographicNeighborhood<>(Arrays.asList(new NamedGeographyElement("D",1.0))));
        neighborhoods.add(new GeographicNeighborhood<>(Arrays.asList(new NamedGeographyElement("E",1.0))));
        neighborhoods.add(new GeographicNeighborhood<>(Arrays.asList(new NamedGeographyElement("G",1.0))));
        neighborhoods.add(new GeographicNeighborhood<>(Arrays.asList(new NamedGeographyElement("H",1.0),
                                                                     new NamedGeographyElement("I",1.0))));
        assertEquals(neighborhoods,GeographicNeighborhoods.getMappingNeighborhoods(basisGeography,Arrays.<GeographicMapping<?,?>>asList(intermediateMap)));
    }     
    
    @Test
    public void testFunctionalNeighborhoods() {
        Collection<GeographicNeighborhood<NamedGeographyElement>> neighborhoods = new HashSet<>();
        neighborhoods.add(new GeographicNeighborhood<>(Arrays.asList(new NamedGeographyElement("A",1.0),
                                                                     new NamedGeographyElement("B",1.0))));
        neighborhoods.add(new GeographicNeighborhood<>(Arrays.asList(new NamedGeographyElement("C",1.0),
                                                                     new NamedGeographyElement("E",1.0),
                                                                     new NamedGeographyElement("F",1.0))));
        neighborhoods.add(new GeographicNeighborhood<>(Arrays.asList(new NamedGeographyElement("D",1.0))));
        neighborhoods.add(new GeographicNeighborhood<>(Arrays.asList(new NamedGeographyElement("G",1.0),
                                                                     new NamedGeographyElement("H",1.0),
                                                                     new NamedGeographyElement("I",1.0))));
        assertEquals(neighborhoods,GeographicNeighborhoods.getMappingNeighborhoods(basisGeography,Arrays.<GeographicMapping<?,?>>asList(intermediateMap,functionalMap)));
    }    
    
    @Test
    public void testOverlappingNeighborhoods() {
        Collection<GeographicNeighborhood<NamedGeographyElement>> neighborhoods = new HashSet<>();
        neighborhoods.add(new GeographicNeighborhood<>(Arrays.asList(new NamedGeographyElement("A",1.0),
                                                                     new NamedGeographyElement("B",1.0),
                                                                     new NamedGeographyElement("C",1.0),
                                                                     new NamedGeographyElement("E",1.0),
                                                                     new NamedGeographyElement("F",1.0),
                                                                     new NamedGeographyElement("H",1.0),
                                                                     new NamedGeographyElement("I",1.0))));
        neighborhoods.add(new GeographicNeighborhood<>(Arrays.asList(new NamedGeographyElement("D",1.0),
                                                                     new NamedGeographyElement("G",1.0))));
        assertEquals(neighborhoods,GeographicNeighborhoods.getMappingNeighborhoods(basisGeography,Arrays.<GeographicMapping<?,?>>asList(overlappingMap)));
    } 
    
    @Test
    public void testAllNeighborhoods() {
        Collection<GeographicNeighborhood<NamedGeographyElement>> neighborhoods = new HashSet<>();
        neighborhoods.add(new GeographicNeighborhood<>(Arrays.asList(new NamedGeographyElement("A",1.0),
                                                                     new NamedGeographyElement("B",1.0),new NamedGeographyElement("C",1.0),
                                                                     new NamedGeographyElement("D",1.0),new NamedGeographyElement("E",1.0),
                                                                     new NamedGeographyElement("F",1.0),new NamedGeographyElement("G",1.0),
                                                                     new NamedGeographyElement("H",1.0),new NamedGeographyElement("I",1.0))));
        assertEquals(neighborhoods,GeographicNeighborhoods.getMappingNeighborhoods(basisGeography,Arrays.<GeographicMapping<?,?>>asList(functionalMap,overlappingMap,intermediateMap)));
    }

    @Test
    public void testFunctionalInIntermediateNeighborhoods() {
        Collection<GeographicNeighborhood<NamedGeographyElement>> neighborhoods = new HashSet<>();
        neighborhoods.add(new GeographicNeighborhood<>(Arrays.asList(new NamedGeographyElement("i1",1.0),
                                                                     new NamedGeographyElement("i2",1.0))));
        neighborhoods.add(new GeographicNeighborhood<>(Arrays.asList(new NamedGeographyElement("i4",1.0),
                                                                     new NamedGeographyElement("i5",1.0))));
        neighborhoods.add(new GeographicNeighborhood<>(Arrays.asList(new NamedGeographyElement("i3",1.0))));
        neighborhoods.add(new GeographicNeighborhood<>(Arrays.asList(new NamedGeographyElement("i6",1.0),
                                                                     new NamedGeographyElement("i7",1.0))));
        assertEquals(neighborhoods,GeographicNeighborhoods.getMappingNeighborhoods(intermediateGeography,Arrays.<GeographicMapping<?,?>>asList(functionalMap)));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNeighborhoodsMissingBasis() {
        GeographicNeighborhoods.getMappingNeighborhoods(basisGeography,Arrays.<GeographicMapping<?,?>>asList(functionalMap));
    }
}