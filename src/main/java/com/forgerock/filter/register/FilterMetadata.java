package com.forgerock.filter.register;

import com.forgerock.filter.Filter;

import java.lang.reflect.Constructor;
import java.util.regex.Pattern;

/**
 * Created by alexandre on 5/20/2017.
 */
public class FilterMetadata {
    private Pattern pattern;
    private Constructor<?> defaultConstructor;
    private Class<? extends Filter> filter;

    public FilterMetadata(Pattern pattern, Class<? extends Filter>  filter,Constructor<?> defaultCOnstructor) {
        this.pattern = pattern;
        this.filter = filter;
        this.defaultConstructor = defaultCOnstructor;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Class<? extends Filter>  getFilter() {
        return filter;
    }

    public Constructor<?> getDefaultConstructor() {
        return defaultConstructor;
    }

    @Override
    public String toString() {
        return "FilterMetadata{" +
                "pattern=" + pattern +
                ", filter=" + filter +
                '}';
    }
}