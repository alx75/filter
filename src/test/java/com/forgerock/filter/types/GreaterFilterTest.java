package com.forgerock.filter.types;

import com.forgerock.filter.Filter;
import com.forgerock.filter.exception.GeneratorException;
import com.forgerock.filter.generator.FilterGenerator;
import com.forgerock.filter.types.comparison.GreaterFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexandre on 5/21/2017.
 */
public class GreaterFilterTest {

    @Test
    public void testIsGreaterTRUE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "5");

        //WHEN
        Filter filter = new GreaterFilter("key", "3");

        // THEN
        Assert.assertTrue(filter.matches(resource));
    }

    @Test
    public void testIsGreaterBadKeyFALSE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "5");

        //WHEN
        Filter filter = new GreaterFilter("wrong_key", "3");

        // THEN
        Assert.assertFalse(filter.matches(resource));
    }

    @Test
    public void testIsGreaterNullKeyFALSE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "5");

        //WHEN
        Filter filter = new GreaterFilter(null, "3");

        // THEN
        Assert.assertFalse(filter.matches(resource));
    }

    @Test
    public void testIsGreaterNullValueFALSE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "5");

        //WHEN
        Filter filter = new GreaterFilter("key", null);

        // THEN
        Assert.assertFalse(filter.matches(resource));    }

    @Test
    public void testIsGreaterFALSE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "5");

        //WHEN
        Filter filter = new GreaterFilter("key", "9");

        // THEN
        Assert.assertFalse(filter.matches(resource));    }

    @Test
    public void testGeneratorFromString() throws GeneratorException {
        // GIVEN
        String biggerFilterAsString = "\"'key' > 'value'\"";

        // WHEN
        Filter greaterFilter = FilterGenerator.fromString(biggerFilterAsString);

        //THEN
        Assert.assertEquals(GreaterFilter.class, greaterFilter.getClass());
    }
}