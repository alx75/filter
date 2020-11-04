package com.forgerock.filter.generator;

import com.forgerock.filter.Filter;
import com.forgerock.filter.builder.FilterBuilder;
import com.forgerock.filter.exception.GeneratorException;
import com.forgerock.filter.exception.NoFilterMatchException;
import com.forgerock.filter.types.comparison.GreaterFilter;
import com.forgerock.filter.types.comparison.LowerFilter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by alexandre on 5/21/2017.
 */
public class FilterGeneratorTest {

    @Test
    public void testFromString() throws GeneratorException {
        Filter f = new FilterBuilder()
                .equals("toto","Test")
                .AND().lower("toto","t == toto")
                .OR().greater("key", "value")
                .AND().like("toto","t??t?")
                .AND(new FilterBuilder()
                        .equals("toto","Tests")
                        .AND().equals("test","test")
                        .build())
                .OR().isPresent("toto")
                .OR().between("toto","aze","ret")
                .OR().isTrue("privileged")
                .OR().isFalse("admin")
                .build();

        Assert.assertEquals(f.toString(),FilterGenerator.fromString(f.toString()).toString() );
    }

    @Test
    public void testFromFilterDoesNotExist() throws GeneratorException {
        try {
            FilterGenerator.fromString("(( \"'firstname' == 'alex'\" AND \"'lastname' == 'robuchon'\" ) OR \" 'test' - 'toto'\" ) ");
        }catch (NoFilterMatchException e) {
            Assert.assertTrue(true);
            return;
        }
        Assert.fail();
    }

    @Test
    public void testFromOperatorDoesNotExist() throws GeneratorException {
        try {
            FilterGenerator.fromString("(( \"'firstname' == 'alex'\" DUMMY \"'lastname' == 'robuchon'\" ) ) ");
        }catch (NoFilterMatchException e) {
            Assert.assertTrue(true);
            return;
        }
        Assert.fail();
    }

}