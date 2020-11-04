package com.forgerock.filter.types.comparison;

import com.forgerock.filter.Filter;
import com.forgerock.filter.FilterContent;
import com.forgerock.filter.Parameter;
import com.forgerock.filter.generator.FilterMatch;

import java.util.Map;

/**
 * Created by alexandre on 5/18/2017.
 */
@FilterMatch(pattern = "'{}' == '{}'")
public class EqualsFilter implements Filter {
    private String key;
    private String value;


    public EqualsFilter(String key,String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        if(resource == null || key == null || value == null) return false;

        String attributeName = resource.get(key);
        if(attributeName == null) return false;

        return value.equalsIgnoreCase(attributeName);
    }

    @Override
    public FilterContent getContent() {
        return new FilterContent(EqualsFilter.class, new Parameter("KEY",key), new Parameter("VALUE",value));
    }

    @Override
    public String toString() {
        return getFormatter().format(this,key,value);
    }
}
