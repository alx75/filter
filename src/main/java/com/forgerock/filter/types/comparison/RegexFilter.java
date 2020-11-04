package com.forgerock.filter.types.comparison;

import com.forgerock.filter.Filter;
import com.forgerock.filter.FilterContent;
import com.forgerock.filter.Parameter;
import com.forgerock.filter.format.DefaultFilterFormatter;
import com.forgerock.filter.generator.FilterMatch;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by alexandre on 5/22/2017.
 */
@FilterMatch(pattern = "'{}' like '{}'")
public class RegexFilter implements Filter {

    private String key;
    private String regex;

    public RegexFilter(String key,String regex) {
        this.key = key;
        this.regex = regex;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        if(resource == null || regex == null || key ==null) return false;

        String attributeName = resource.get(key);
        if(attributeName == null) return false;

        return Pattern
                .compile(this.regex,Pattern.CASE_INSENSITIVE)
                .matcher(attributeName)
                .matches();
    }

    @Override
    public FilterContent getContent() {
        return new FilterContent(RegexFilter.class, new Parameter("KEY",key), new Parameter("VALUE", regex));
    }

    @Override
    public String toString() {
        return getFormatter().format(this,key,regex);
    }
}
