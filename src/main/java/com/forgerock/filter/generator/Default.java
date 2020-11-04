package com.forgerock.filter.generator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Should be used to mark a filter constructor as the default. It's important
 * that only one constructor is marked as default. <br /><br />
 *
 * This is used by the filterGenerator to know which constructor used by reflexion.<br />
 * Created by alexandre on 5/21/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
public @interface Default {
}
