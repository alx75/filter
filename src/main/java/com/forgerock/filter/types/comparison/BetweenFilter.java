package com.forgerock.filter.types.comparison;

import com.forgerock.filter.Filter;
import com.forgerock.filter.FilterContent;
import com.forgerock.filter.Parameter;
import com.forgerock.filter.generator.FilterMatch;
import com.forgerock.filter.types.operator.ANDFilter;

import java.util.Map;

/**
 * Created by alexandre on 5/20/2017.
 */
@FilterMatch(pattern = "'{}' < '{}' < '{}'")
public class BetweenFilter implements Filter {

    private String key;
    private String valueInf;
    private String valueSup;

    public BetweenFilter(String valueInf,String key,String valueSup) {
        this.key = key;
        this.valueInf = valueInf;
        this.valueSup = valueSup;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        if(resource == null) return false;

        return new ANDFilter(new LowerFilter(key, valueSup),new GreaterFilter(key, valueInf)).matches(resource);
    }

    @Override
    public FilterContent getContent() {
        return new FilterContent(BetweenFilter.class,new Parameter("KEY",key)
                ,new Parameter("VALUE_INF",valueInf)
                ,new Parameter("VALUE_SUP",valueSup)
        );
    }

    @Override
    public String toString() {
        return getFormatter().format(this,valueInf,key,valueSup);
    }

}
