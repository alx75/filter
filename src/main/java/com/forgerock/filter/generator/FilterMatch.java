package com.forgerock.filter.generator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by alexandre on 5/20/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FilterMatch {

    /**
     * the character {} must be used for a group ( variable )
     * @return : The pattern to be used by the generator to match a specific string
     */
    String pattern();
}
