package com.forgerock.filter.types;

import com.forgerock.filter.Filter;
import com.forgerock.filter.exception.GeneratorException;
import com.forgerock.filter.generator.FilterGenerator;
import com.forgerock.filter.types.constant.TrueFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by alexandre on 5/21/2017.
 */
public class TrueFilterTest {

    @Test
    public void testTRUE() {
        Assert.assertTrue(new TrueFilter().matches(new HashMap<>()));
    }

    @Test
    public void testGeneratorFromString() throws GeneratorException {
        // GIVEN
        String trueFilterAsString = "\"truefilter\"";

        // WHEN
        Filter trueFilter = FilterGenerator.fromString(trueFilterAsString);

        //THEN
        Assert.assertEquals(TrueFilter.class, trueFilter.getClass());
    }
}