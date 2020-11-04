package com.forgerock.filter;

import com.forgerock.filter.builder.FilterBuilder;
import com.forgerock.filter.exception.FilterBuilderFormatException;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexandre on 5/18/2017.
 */
public class FilterAPITest {

    public static Map<String,String> resource = new HashMap<>();

    static {
        resource.put("test" , "test");
        resource.put("toto","test");
        resource.put("firstname" , "Joe");
        resource.put("surname", "Bloggs");
        resource.put("role", "administrator");
        resource.put("age", "45");
        resource.put("privileged", "true");
        resource.put("admin", "false");
        resource.put("average", "112");
    }

    @Test
    public void testComplexFilterTrue() {
        Filter f = new FilterBuilder()
                .equals("toto","Test")
                .AND().lower("toto","test")
                .OR(new FilterBuilder()
                        .equals("toto","Tests")
                        .AND().equals("test","test")
                        .build())
                .OR().isPresent("toto")
                .OR().between("toto","alex","zoro")
                .OR().isTrue("privileged")
                .OR().isFalse("admin")
                .AND().NOT().equals("toto","Tests")
                .build();

        Assert.assertTrue(f.matches(resource));
    }

    @Test
    public void testComplexFilterNotIsFalseFalse() {
        Filter f = new FilterBuilder()
                .equals("toto","Test")
                .AND().lower("toto","test")
                .OR(new FilterBuilder()
                        .equals("toto","Tests")
                        .AND().equals("test","test")
                        .build())
                .OR().isPresent("toto")
                .OR().isTrue("privileged")
                .OR().isFalse("admin")
                .AND().NOT().equals("toto","Test")
                .build();

        Assert.assertFalse(f.matches(resource));
    }

    @Test
    public void testComplexFilterInnerFilterIsFalseFalse() {
        Filter f = new FilterBuilder()
                .equals("toto","Test")
                .AND().lower("toto","test")
                .AND(new FilterBuilder()
                        .equals("toto","Tests")
                        .AND().equals("test","test")
                        .build())
                .OR().isPresent("toto")
                .OR().isTrue("privileged")
                .OR().isFalse("admin")
                .AND().NOT().equals("toto","Test")
                .build();

        Assert.assertFalse(f.matches(resource));
    }

    @Test
    public void testBadTwoOperatorInTHeRowFormat() {
        try{
            Filter f = new FilterBuilder()
                    .equals("toto","Test")
                    .AND().OR().lower("toto","test")
                    .build();
        }catch (FilterBuilderFormatException e) {
            Assert.assertTrue(true);
            return;
        }
        Assert.fail();
    }

    @Test
    public void testBadTwoOperatorInTHeRowWithNotFormat() {
        try{
            Filter f = new FilterBuilder()
                    .NOT().equals("toto","Test")
                    .AND().OR().lower("toto","test")
                    .build();
        }catch (FilterBuilderFormatException e) {
            Assert.assertTrue(true);
            return;
        }
        Assert.fail();
    }

    @Test
    public void testBadMissingOperatorFormat() {
        try{
            Filter f = new FilterBuilder()
                    .NOT()
                    .equals("toto","Test")
                    .lower("toto","test")
                    .build();
        }catch (FilterBuilderFormatException e) {
            Assert.assertTrue(true);
            return;
        }
        Assert.fail();
    }

}