package com.forgerock.filter.types;

import com.forgerock.filter.Filter;
import com.forgerock.filter.exception.GeneratorException;
import com.forgerock.filter.generator.FilterGenerator;
import com.forgerock.filter.types.operator.ANDFilter;
import com.forgerock.filter.types.comparison.EqualsFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexandre on 5/21/2017.
 */
public class ANDFilterTest {

    @Test
    public void testANDTRUE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key1" , "value");
        resource.put("key2" , "value");

        // WHEN
        Filter filter= new ANDFilter(new EqualsFilter("key1", "value"),new EqualsFilter("key2", "value"));

        // THEN
        Assert.assertTrue(filter.matches(resource));
    }

    @Test
    public void testANDFALSE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key1" , "value");
        resource.put("key2" , "value");

        // WHEN
        Filter filter= new ANDFilter(new EqualsFilter("key1","value"), new EqualsFilter("key2", "dummyvalue"));

        // THEN
        Assert.assertFalse(filter.matches(resource));
    }

    @Test
    public void testGeneratorFromString() throws GeneratorException {
        // GIVEN
        String andFilterAsString = "(\"truefilter\" AND \"truefilter\")";

        // WHEN
        Filter andFilter = FilterGenerator.fromString(andFilterAsString);

        //THEN
        Assert.assertEquals(ANDFilter.class, andFilter.getClass());
    }
}