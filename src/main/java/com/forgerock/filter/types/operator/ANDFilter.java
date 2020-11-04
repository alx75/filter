package com.forgerock.filter.types.operator;

import com.forgerock.filter.Filter;
import com.forgerock.filter.FilterContent;
import com.forgerock.filter.generator.Default;
import com.forgerock.filter.generator.FilterMatch;

import java.util.Map;

/**
 * Created by alexandre on 5/20/2017.
 */
@FilterMatch(pattern = "AND")
public class ANDFilter implements FilterOperator {

    private static final String NAME = "AND";
    private final Filter filter1;
    private final Filter filter2;

    @Default
    public ANDFilter(Filter filter1, Filter filter2) {
        if(filter1 == null || filter2 == null) {
            throw new IllegalArgumentException("The parameters cannot but null");
        }

        this.filter1 = filter1;
        this.filter2 = filter2;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        return filter1.matches(resource) && filter2.matches(resource);
    }

    @Override
    public FilterContent getContent() {
        return new FilterContent(ANDFilter.class, filter1, filter2);
    }

    @Override
    public String toString() {
        return  getFormatter().format(this,filter1.toString(), NAME, filter2.toString());
    }

}
