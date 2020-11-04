package com.forgerock.filter.exception;

import com.forgerock.filter.Filter;

/**
 * Created by alexandre on 5/21/2017.
 */
public class NoDefaultConstructor extends RuntimeException{
    public NoDefaultConstructor(Class<?> filterClass) {
        super("The class " + filterClass + " doesn't have a default constructor. \n"  +
                "You must use the @Default interface to mark the constructor to be used during the generator phase. \n" +
                "You may have this error because you have declared several constructors and you need to specify which one will be used during the generator phase"
        );
    }
}
