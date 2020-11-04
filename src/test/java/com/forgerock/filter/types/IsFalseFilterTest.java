package com.forgerock.filter.types;

import com.forgerock.filter.Filter;
import com.forgerock.filter.exception.GeneratorException;
import com.forgerock.filter.generator.FilterGenerator;
import com.forgerock.filter.types.constant.IsFalseFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexandre on 5/21/2017.
 */
public class IsFalseFilterTest {

    @Test
    public void testIsFalseTRUE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("admin" , "false");

        //WHEN
        Filter filter = new IsFalseFilter("admin");

        // THEN
        Assert.assertTrue(filter.matches(resource));
    }

    @Test
    public void testIsFalseFALSE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("admin" , "true");

        //WHEN
        Filter filter = new IsFalseFilter("admin");

        // THEN
        Assert.assertFalse(filter.matches(resource));
    }

    @Test
    public void testGeneratorFromString() throws GeneratorException {
        // GIVEN
        String isFalseFilterAsString = "\"'key' is false\"";

        // WHEN
        Filter isFalseFilter = FilterGenerator.fromString(isFalseFilterAsString);

        //THEN
        Assert.assertEquals(IsFalseFilter.class, isFalseFilter.getClass());
    }

}