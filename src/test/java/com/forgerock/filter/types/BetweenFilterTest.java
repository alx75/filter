package com.forgerock.filter.types;

import com.forgerock.filter.Filter;
import com.forgerock.filter.exception.GeneratorException;
import com.forgerock.filter.generator.FilterGenerator;
import com.forgerock.filter.types.comparison.BetweenFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexandre on 5/21/2017.
 */
public class BetweenFilterTest {

    @Test
    public void testIsBetweenIsTrue() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "5");

        //WHEN
        Filter filter = new BetweenFilter("2","key","7");

        // THEN
        Assert.assertTrue(filter.matches(resource));
    }

    @Test
    public void testIsBetweenIsFalseWhenValueInfIsSup() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "5");

        //WHEN
        Filter filter = new BetweenFilter("6","key","7");

        //THEN
        Assert.assertFalse(filter.matches(resource));
    }

    @Test
    public void testIsBetweenIsFalseWhenValueSupIsInf() {
        // GIVEN
        Map<String,String> resource = new HashMap<>();
        resource.put("key" , "5");

        //WHEN
        Filter filter = new BetweenFilter("2","key","4");

        //THEN
        Assert.assertFalse(filter.matches(resource));
    }

    @Test
    public void testGeneratorFromString() throws GeneratorException {
        // GIVEN
        String betweenFilterAsString = "\"'3' < '5' < '7'\"";

        // WHEN
        Filter betweenFilter = FilterGenerator.fromString(betweenFilterAsString);

        //THEN
        Assert.assertEquals(BetweenFilter.class, betweenFilter.getClass());
    }
}