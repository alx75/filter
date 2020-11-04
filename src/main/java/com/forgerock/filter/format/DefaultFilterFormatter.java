package com.forgerock.filter.format;

import com.forgerock.filter.Filter;
import com.forgerock.filter.util.Sanitizer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alexandre on 5/22/2017.
 */
public class DefaultFilterFormatter implements Formatter {

    public String format(Filter filter,String... params) {
        String format = filter.getContent().getPattern().replaceAll("\\{}","%s");
        return String.format("\"" + format + "\"", Arrays.stream(params).map(Sanitizer::sanitize).toArray());
    }
}
