package com.forgerock.filter.exception;

/**
 * Created by alexandre on 5/21/2017.
 */
public class FilterBuilderFormatException extends RuntimeException {

    public FilterBuilderFormatException() {
        super("The filter you are trying to build doesn't have a correct format. Perhaps you have forgotten an operator between two filters or add two operators in the row");
    }
}
