package com.forgerock.filter.types;

import com.forgerock.filter.Filter;
import com.forgerock.filter.types.comparison.RegexFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexandre on 5/22/2017.
 */
public class RegexFilterTest{

    @Test
    public void testIsEqualTRUE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "value");

        //WHEN
        Filter filter= new RegexFilter("key","v.*e");

        // THEN
        Assert.assertTrue(filter.matches(resource));
    }

    @Test
    public void testIsRegexBadKeyFALSE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "value");

        //WHEN
        Filter filter= new RegexFilter("wrong_key","v.*e");

        // THEN
        Assert.assertFalse(filter.matches(resource));
    }

    @Test
    public void testIsRegexNullKeyFALSE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "value");

        //WHEN
        Filter filter= new RegexFilter(null,"v.*e");

        // THEN
        Assert.assertFalse(filter.matches(resource));
    }

    @Test
    public void testIsRegexNullValueFALSE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "value");

        //WHEN
        Filter filter= new RegexFilter("key",null);

        // THEN
        Assert.assertFalse(filter.matches(resource));    }

    
    
    @Test
    public void testIsEqualFALSE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "value");

        //WHEN
        Filter filter= new RegexFilter("key","va.?e");

        // THEN
        Assert.assertFalse(filter.matches(resource));
    }
}