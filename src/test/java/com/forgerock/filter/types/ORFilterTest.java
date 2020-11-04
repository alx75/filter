package com.forgerock.filter.types;

import com.forgerock.filter.Filter;
import com.forgerock.filter.exception.GeneratorException;
import com.forgerock.filter.generator.FilterGenerator;
import com.forgerock.filter.types.operator.ORFilter;
import com.forgerock.filter.types.comparison.EqualsFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexandre on 5/21/2017.
 */
public class ORFilterTest {

    @Test
    public void testORWithBothTrueTRUE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key1" , "value1");
        resource.put("key2" , "value2");

        //WHEN
        Filter filter= new ORFilter(new EqualsFilter("key1","value1"),new EqualsFilter("key2","value2"));

        // THEN
        Assert.assertTrue(filter.matches(resource));
    }

    @Test
    public void testORWithOneValueFalseTRUE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key1" , "value1");
        resource.put("key2" , "value2");

        //WHEN
        Filter filter= new ORFilter(new EqualsFilter("key1","value1"),new EqualsFilter("key2","WRONG_VALUE"));

        // THEN
        Assert.assertTrue(filter.matches(resource));
    }

    @Test
    public void testORWithOneKeyFalseTRUE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key1" , "value1");
        resource.put("key2" , "value2");

        //WHEN
        Filter filter= new ORFilter(new EqualsFilter("key1","value1"),new EqualsFilter("WRONG_KEY","value2"));

        // THEN
        Assert.assertTrue(filter.matches(resource));
    }

    @Test
    public void testORWithBothKeysFalseFALSE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key1" , "value1");
        resource.put("key2" , "value2");

        //WHEN
        Filter filter= new ORFilter(new EqualsFilter("key1","WRONG_VALUE"),new EqualsFilter("WRONG_KEY","value2"));

        // THEN
        Assert.assertFalse(filter.matches(resource));
    }

    @Test
    public void testGeneratorFromString() throws GeneratorException {
        // GIVEN
        String orFilterAsString = "(\"truefilter\" OR \"truefilter\")";

        // WHEN
        Filter orFilter = FilterGenerator.fromString(orFilterAsString);

        //THEN
        Assert.assertEquals(ORFilter.class, orFilter.getClass());
    }
}