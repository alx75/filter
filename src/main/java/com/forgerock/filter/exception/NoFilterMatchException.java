package com.forgerock.filter.exception;

/**
 * Created by alexandre on 5/21/2017.
 */
public class NoFilterMatchException extends RuntimeException {

    public NoFilterMatchException(String filterWhichDoesntMatch) {
        super("No filter matches " + filterWhichDoesntMatch);
    }
}
