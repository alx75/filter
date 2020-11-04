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
@FilterMatch(pattern = "'{}' is false")
public class IsFalseFilter implements Filter {

    protected String key;

    public IsFalseFilter(String key) {
        this.key = key;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        if(resource == null) return false;

        String attributeName = resource.get(key);
        if(attributeName == null) return false;

        return "false".equalsIgnoreCase(attributeName);
    }

    @Override
    public FilterContent getContent() {
        return new FilterContent(IsFalseFilter.class, new Parameter("KEY",key));
    }

    @Override
    public String toString() {
        return getFormatter().format(this, key);
    }

}