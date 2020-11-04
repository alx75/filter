package com.forgerock.filter.types.comparison;

import com.forgerock.filter.Filter;
import com.forgerock.filter.FilterContent;
import com.forgerock.filter.Parameter;
import com.forgerock.filter.format.DefaultFilterFormatter;
import com.forgerock.filter.generator.FilterMatch;

import java.util.Map;

/**
 * Created by alexandre on 5/20/2017.
 */
@FilterMatch(pattern = "'{}' exists")
public class IsPresentFilter implements Filter  {

    private String key;

    public IsPresentFilter(String key) {
        this.key = key;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        return resource.containsKey(key);
    }

    @Override
    public FilterContent getContent() {
        return new FilterContent(IsPresentFilter.class,new Parameter("KEY",key));
    }

    @Override
    public String toString() {
        return getFormatter().format(this,key);
    }
}

