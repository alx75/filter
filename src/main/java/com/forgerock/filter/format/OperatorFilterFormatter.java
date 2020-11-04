package com.forgerock.filter.format;

import com.forgerock.filter.Filter;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by alexandre on 5/22/2017.
 */
public class OperatorFilterFormatter implements Formatter{

    public String format(Filter filter, String... params) {
        return String.format("( %s )", String.join(" ", params));
    }
}
