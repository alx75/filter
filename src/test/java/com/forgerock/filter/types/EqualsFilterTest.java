package com.forgerock.filter.types;

import com.forgerock.filter.Filter;
import com.forgerock.filter.exception.GeneratorException;
import com.forgerock.filter.generator.FilterGenerator;
import com.forgerock.filter.types.comparison.EqualsFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexandre on 5/21/2017.
 */
public class EqualsFilterTest {

    @Test
    public void testIsEqualTRUE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "good_value");

        //WHEN
        Filter filter = new EqualsFilter("key", "good_value");

        // THEN
        Assert.assertTrue(filter.matches(resource));
    }

    @Test
    public void test_matches_When_ValueDoesntMatch_Should_ReturnFalse() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "good_value");

        //WHEN
        Filter filter = new EqualsFilter("key", "wrong_value");

        // THEN
        Assert.assertFalse(filter.matches(resource));
    }

    @Test
    public void test_matches_when_KeyNotPresent_Should_ReturnFalse() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "good_value");

        //WHEN
        Filter filter = new EqualsFilter("key doesn't exist", "any_value");

        // THEN
        Assert.assertFalse(filter.matches(resource));
    }

    @Test
    public void testIsEqualNullKeyFALSE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "good_value");

        //WHEN
        Filter filter = new EqualsFilter(null, "any_value");

        // THEN
        Assert.assertFalse(filter.matches(resource));
    }

    @Test
    public void testIsEqualNullValueFALSE() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "good_value");

        //WHEN
        Filter filter = new EqualsFilter("key", null);

        // THEN
        Assert.assertFalse(filter.matches(resource));
    }

    @Test
    public void testGeneratorFromString() throws GeneratorException {
        // GIVEN
        String equalFilterAsString = "\"'key' == 'value'\"";

        // WHEN
        Filter equalFilter = FilterGenerator.fromString(equalFilterAsString);

        //THEN
        Assert.assertEquals(EqualsFilter.class, equalFilter.getClass());
    }

}