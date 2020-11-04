package com.forgerock.filter.builder;

import com.forgerock.filter.Filter;
import com.forgerock.filter.types.operator.ANDFilter;
import com.forgerock.filter.types.operator.NotFilter;
import com.forgerock.filter.types.operator.ORFilter;
import com.forgerock.filter.types.operator.XORFilter;

/**
 * Created by alexandre on 5/22/2017.
 */
public class OperatorFactory {

    enum Operator {
        OR,AND,NOT,XOR
    }

    public static Filter getInstanceOf(Operator operator, Filter filter1, Filter filter2) {
        Filter filter = null;
        switch (operator) {
            case AND:
                filter = new ANDFilter(filter1,filter2);
                break;
            case OR :
                filter = new ORFilter(filter1,filter2);
                break;
            case XOR :
                filter = new XORFilter(filter1,filter2);
                break;
            case NOT :
                filter = new NotFilter(filter1);
                break;
        }

        return filter;
    }

}