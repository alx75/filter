package com.forgerock.filter.types.operator;

import com.forgerock.filter.Filter;
import com.forgerock.filter.FilterContent;
import com.forgerock.filter.generator.FilterMatch;

import java.util.Map;

/**
 * Created by alexandre on 5/20/2017.
 */
@FilterMatch(pattern = "NOT")
public class NotFilter implements FilterOperator {

    private static final String NAME = "NOT";

    private Filter filter;

    public NotFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        return !filter.matches(resource);
    }

    @Override
    public FilterContent getContent() {
        return new FilterContent(NotFilter.class,filter);
    }

    @Override
    public String toString() {
        return  getFormatter().format(this,NAME,filter.toString());
    }

}
