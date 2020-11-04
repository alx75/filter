package com.forgerock.filter;

/**
 * Created by alexandre on 5/18/2017.
 */

import com.forgerock.filter.format.DefaultFilterFormatter;
import com.forgerock.filter.format.Formatter;

import java.util.Map;

/**
 * A filter that can be match against a resource.
 */
public interface Filter  {

    /**
     * Given the resource return true if an entry is the resource match the criteria of the filter
     * @param resource : An object represented as a map
     * @return : true if the resource match the filter
     */
    boolean matches(final Map<String,String> resource);

    /**
     * The internal representation of the filter.
     * @return : information about the data filter.
     */
    FilterContent getContent();

    /**
     * @return : A formatter to print the filter as a string
     */
    default Formatter getFormatter() {
        return new DefaultFilterFormatter();
    }

}
