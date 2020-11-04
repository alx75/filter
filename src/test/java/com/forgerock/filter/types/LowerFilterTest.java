package com.forgerock.filter.types;

import com.forgerock.filter.Filter;
import com.forgerock.filter.exception.GeneratorException;
import com.forgerock.filter.generator.FilterGenerator;
import com.forgerock.filter.types.comparison.LowerFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexandre on 5/21/2017.
 */
public class LowerFilterTest {

    @Test
    public void testIsLowerTRUE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "5");

        //WHEN
        Filter filter = new LowerFilter("key", "8");

        // THEN
        Assert.assertTrue(filter.matches(resource));
    }

    @Test
    public void testIsLowerBadKeyFALSE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "5");

        //WHEN
        Filter filter = new LowerFilter("wrong key", "8");

        // THEN
        Assert.assertFalse(filter.matches(resource));
    }

    @Test
    public void testIsLowerNullKeyFALSE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "5");

        //WHEN
        Filter filter = new LowerFilter(null, "8");

        // THEN
        Assert.assertFalse(filter.matches(resource));
    }

    @Test
    public void testIsLowerNullValueFALSE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "5");

        //WHEN
        Filter filter = new LowerFilter("key", null);

        // THEN
        Assert.assertFalse(filter.matches(resource));
    }
    
    @Test
    public void testIsLowerFALSE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "5");

        //WHEN
        Filter filter = new LowerFilter("wrong key", "3");

        // THEN
        Assert.assertFalse(filter.matches(resource));
    }

    @Test
    public void testGeneratorFromString() throws GeneratorException {
        // GIVEN
        String lowerFilterAsString = "\"'key' < 'value'\"";

        // WHEN
        Filter lowerFilter = FilterGenerator.fromString(lowerFilterAsString);

        //THEN
        Assert.assertEquals(LowerFilter.class, lowerFilter.getClass());
    }

}