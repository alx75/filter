package com.forgerock.filter.types.operator;

import com.forgerock.filter.Filter;
import com.forgerock.filter.format.Formatter;
import com.forgerock.filter.format.OperatorFilterFormatter;

/**
 * Created by alexandre on 5/20/2017.
 */
public interface FilterOperator extends Filter {

    default Formatter getFormatter() {
        return new OperatorFilterFormatter();
    }
}
