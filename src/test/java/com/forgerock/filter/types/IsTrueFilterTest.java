package com.forgerock.filter.types;

import com.forgerock.filter.Filter;
import com.forgerock.filter.exception.GeneratorException;
import com.forgerock.filter.generator.FilterGenerator;
import com.forgerock.filter.types.constant.IsTrueFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexandre on 5/21/2017.
 */
public class IsTrueFilterTest {

    @Test
    public void testIsTrueTRUE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("admin" , "true");

        //WHEN
        Filter filter = new IsTrueFilter("admin");

        // THEN
        Assert.assertTrue(filter.matches(resource));
    }

    @Test
    public void testIsTrueFalse() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("admin" , "false");

        //WHEN
        Filter filter = new IsTrueFilter("admin");

        // THEN
        Assert.assertFalse(filter.matches(resource));        }

    @Test
    public void testGeneratorFromString() throws GeneratorException {
        // GIVEN
        String isTrueFilterAsString = "\"'key' is true\"";

        // WHEN
        Filter isTrueFilter = FilterGenerator.fromString(isTrueFilterAsString);

        //THEN
        Assert.assertEquals(IsTrueFilter.class, isTrueFilter.getClass());
    }
}