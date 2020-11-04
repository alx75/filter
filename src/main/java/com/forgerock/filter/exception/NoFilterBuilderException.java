package com.forgerock.filter.exception;

/**
 * Created by alexandre on 5/20/2017.
 */
public class NoFilterBuilderException extends RuntimeException {

    public NoFilterBuilderException() {
        super("You didn't add a filter or the filter you are trying to build is not correct.");
    }
}
