package com.forgerock.filter.types;

import com.forgerock.filter.Filter;
import com.forgerock.filter.exception.GeneratorException;
import com.forgerock.filter.generator.FilterGenerator;
import com.forgerock.filter.types.comparison.IsPresentFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexandre on 5/21/2017.
 */
public class IsPresentFilterTest {

    @Test
    public void testIsPresentTRUE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key present" , "5");

        //WHEN
        Filter filter = new IsPresentFilter("key present");

        // THEN
        Assert.assertTrue(filter.matches(resource));
    }

    @Test
    public void testIsPresentFALSE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "5");

        //WHEN
        Filter filter = new IsPresentFilter("key not present");

        // THEN
        Assert.assertFalse(filter.matches(resource));
    }

    @Test
    public void testGeneratorFromString() throws GeneratorException {
        // GIVEN
        String isPresentFilterAsString = "\"'key' exists\"";

        // WHEN
        Filter isPresentFilter = FilterGenerator.fromString(isPresentFilterAsString);

        //THEN
        Assert.assertEquals(IsPresentFilter.class, isPresentFilter.getClass());
    }

}