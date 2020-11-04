package com.forgerock.filter.types.constant;

import com.forgerock.filter.Filter;
import com.forgerock.filter.FilterContent;
import com.forgerock.filter.Parameter;
import com.forgerock.filter.format.DefaultFilterFormatter;
import com.forgerock.filter.generator.FilterMatch;

import java.util.Map;

/**
 * Created by alexandre on 5/20/2017.
 */
@FilterMatch(pattern = "'{}' is true")
public class IsTrueFilter implements Filter {
    protected String key;
    public IsTrueFilter(String key) {
        this.key = key;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        if(resource == null) return false;

        String attributeName = resource.get(key);
        if(attributeName == null) return false;

        return "true".equalsIgnoreCase(attributeName);
    }

    @Override
    public FilterContent getContent() {
        return new FilterContent(IsTrueFilter.class,new Parameter("KEY",key));
    }

    @Override
    public String toString() {
        return getFormatter().format(this, key);
    }
}