package com.forgerock.filter.format;

import com.forgerock.filter.Filter;

/**
 * Created by alexandre on 5/22/2017.
 */
public interface Formatter {
    String format(Filter filter,String ...params);
}
