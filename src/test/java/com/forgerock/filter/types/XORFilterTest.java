package com.forgerock.filter.types;

import com.forgerock.filter.Filter;
import com.forgerock.filter.exception.GeneratorException;
import com.forgerock.filter.generator.FilterGenerator;
import com.forgerock.filter.types.comparison.EqualsFilter;
import com.forgerock.filter.types.operator.XORFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexandre on 5/21/2017.
 */
public class XORFilterTest {


    @Test
    public void testXORWithBothTrueFALSE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key1" , "value1");
        resource.put("key2" , "value2");

        //WHEN
        Filter filter= new XORFilter(new EqualsFilter("key1","value1"),new EqualsFilter("key2","value2"));

        // THEN
        Assert.assertFalse(filter.matches(resource));
    }

    @Test
    public void testXORWithOneValueFalseTRUE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key1" , "value1");
        resource.put("key2" , "value2");

        //WHEN
        Filter filter= new XORFilter(new EqualsFilter("key1","value1"),new EqualsFilter("key2","WRONG_VALUE"));

        // THEN
        Assert.assertTrue(filter.matches(resource));
    }

    @Test
    public void testXORWithOneKeyFalseTRUE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key1" , "value1");
        resource.put("key2" , "value2");

        //WHEN
        Filter filter= new XORFilter(new EqualsFilter("WRONG_KEY","value1"),new EqualsFilter("key2","value2"));

        // THEN
        Assert.assertTrue(filter.matches(resource));
    }

    @Test
    public void testGeneratorFromString() throws GeneratorException {
        // GIVEN
        String xorFilterAsString = "(\"truefilter\" XOR \"truefilter\")";

        // WHEN
        Filter xorFilter = FilterGenerator.fromString(xorFilterAsString);

        //THEN
        Assert.assertEquals(XORFilter.class, xorFilter.getClass());
    }

}