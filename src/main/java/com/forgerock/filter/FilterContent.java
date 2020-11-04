package com.forgerock.filter;

import com.forgerock.filter.generator.FilterMatch;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by alexandre on 5/22/2017.
 */
public class FilterContent{

    private Class<?> filterClass;
    private Set<Filter> filters = new HashSet<>();
    private HashMap<String,String> parameters = new HashMap<>();

    public FilterContent(Class<?> filterClass) {
        this.filterClass = filterClass;
    }

    public FilterContent(Class<?> filterClass,Filter ...filtersToAdd) {
        this(filterClass);
        for(Filter filter : filtersToAdd) {
            filters.add(filter);
        }
    }

    public FilterContent(Class<?> filterClass,Parameter ...parametersToAdd) {
        this(filterClass);
        for(Parameter parameter : parametersToAdd) {
            parameters.put(parameter.getKey(),parameter.getValue());
        }
    }

    public FilterContent(Class<?> filterClass,Collection<Parameter> parametersToAdd) {
        this(filterClass);
        for(Parameter parameter : parametersToAdd) {
            parameters.put(parameter.getKey(),parameter.getValue());
        }
    }

    public Class<?> getFilterClass() {
        return filterClass;
    }

    public Collection<Filter> getFilters() {
        return filters;
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public boolean hasParameters() {
        return parameters.size() > 0;
    }

    public boolean hasFilters() {
        return filters.size() > 0;
    }

    public String getPattern() {
        FilterMatch filterMatch = filterClass.getDeclaredAnnotation(FilterMatch.class);
        if(filterMatch == null) {
            throw new RuntimeException("the class " + filterClass.getName() + " must have the annotation " + FilterMatch.class.getName());
        }
        return filterMatch.pattern();
    }
}