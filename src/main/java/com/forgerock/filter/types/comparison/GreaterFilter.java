package com.forgerock.filter.types.comparison;

import com.forgerock.filter.Filter;
import com.forgerock.filter.FilterContent;
import com.forgerock.filter.Parameter;
import com.forgerock.filter.generator.Default;
import com.forgerock.filter.generator.FilterMatch;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Map;

/**
 * Created by alexandre on 5/20/2017.
 */
@FilterMatch(pattern = "'{}' > '{}'")
public class GreaterFilter implements Filter {

    private String key;
    private String value;

    @Default
    public GreaterFilter(String key,String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean matches(Map<String, String> resource) {

        if(resource == null || key == null || value == null) return false;

        String attributeValue = resource.get(key);
        if(attributeValue == null) return false;
        boolean match;
        if(NumberUtils.isCreatable(value) && StringUtils.isNumeric(attributeValue)) {
            match = NumberUtils.createNumber(value).doubleValue() < NumberUtils.createNumber(attributeValue).doubleValue();
        }else {
            match =  value.compareToIgnoreCase(attributeValue) < 0;
        }
        return match;

    }

    @Override
    public FilterContent getContent() {
        return new FilterContent(GreaterFilter.class,new Parameter("KEY",key),new Parameter("VALUE",value));
    }

    @Override
    public String toString() {
        return getFormatter().format(this,key,value);
    }
}