package com.forgerock.filter.types.constant;

import com.forgerock.filter.Filter;
import com.forgerock.filter.FilterContent;
import com.forgerock.filter.generator.FilterMatch;

import java.util.Map;

/**
 * Created by alexandre on 5/21/2017.
 */
@FilterMatch(pattern = "truefilter")
public class TrueFilter implements Filter {

    public TrueFilter() {
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        return true;
    }

    @Override
    public FilterContent getContent() {
        return new FilterContent(TrueFilter.class);
    }

    @Override
    public String toString() {
        return "\"truefilter\"" ;
    }
}
