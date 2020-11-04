package com.forgerock.filter.exception;

import com.forgerock.filter.Filter;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by alexandre on 5/21/2017.
 */
public class SeveralFilterMatchException extends RuntimeException{

    public SeveralFilterMatchException(String currentFilterAsString, Collection<Class<? extends Filter>> matchFilters) {
        super("Too many filters matches : "
                + currentFilterAsString + " [ "
                + matchFilters.stream().map(filter -> filter.getName()).collect(Collectors.joining(","))
                + " ]"
        );
    }
}
