package com.forgerock.filter.exception;

import com.forgerock.filter.Filter;

/**
 * Created by alexandre on 5/21/2017.
 */
public class TooManyDefaultConstructor extends RuntimeException {

    public TooManyDefaultConstructor(Class<?> filterClass,int numberOfDefaultConstructor) {
        super("The class " + filterClass + " has " + numberOfDefaultConstructor + " default constructors. \n"  +
                "The @Default annotation must be used to specify the constructor to use during the generate step. You cannot have more than one @Default constructor "
        );
    }
}
