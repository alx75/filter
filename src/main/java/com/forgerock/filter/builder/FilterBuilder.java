package com.forgerock.filter.builder;

import com.forgerock.filter.Filter;
import com.forgerock.filter.exception.FilterBuilderFormatException;
import com.forgerock.filter.exception.NoFilterBuilderException;
import com.forgerock.filter.types.comparison.*;
import com.forgerock.filter.types.constant.IsFalseFilter;
import com.forgerock.filter.types.constant.IsTrueFilter;

import java.util.LinkedList;

/**
 * You can use this class to create a complex filter <br/>
 * Created by alexandre on 5/20/2017.
 */
public class FilterBuilder {

    private LinkedList<Filter> filters = new LinkedList<>();
    private LinkedList<OperatorFactory.Operator> operators = new LinkedList<>();
    private int notOperatorCount = 0; // keep count of the number of NOT operator in order to be able to say if the filter building is correct

    public FilterBuilder equals(String key,String value) {
        filters.add(new EqualsFilter(key,value));
        return this;
    }

    public FilterBuilder between(String key,String value1, String value2) {
        filters.add(new BetweenFilter(key,value1,value2));
        return this;
    }

    public FilterBuilder greater(String key,String value) {
        filters.add(new GreaterFilter(key,value));
        return this;
    }

    public FilterBuilder lower(String key,String value) {
        filters.add(new LowerFilter(key,value));
        return this;
    }

    public FilterBuilder isFalse(String key) {
        filters.add(new IsFalseFilter(key));
        return this;
    }

    public FilterBuilder isPresent(String key) {
        filters.add(new IsPresentFilter(key));
        return this;
    }

    public FilterBuilder isTrue(String key) {
        filters.add(new IsTrueFilter(key));
        return this;
    }

    public FilterBuilder like(String key,String value) {
        filters.add(new RegexFilter(key,value));
        return this;
    }

    public FilterBuilder AND() {
        operators.add(OperatorFactory.Operator.AND);
        return this;
    }

    public FilterBuilder NOT() {
        notOperatorCount++;
        operators.add(OperatorFactory.Operator.NOT);
        return this;
    }

    public FilterBuilder OR() {
        operators.add(OperatorFactory.Operator.OR);
        return this;
    }

    public FilterBuilder OR(Filter filter) {
        operators.add(OperatorFactory.Operator.OR);
        filters.add(filter);
        return this;
    }

    public FilterBuilder XOR() {
        operators.add(OperatorFactory.Operator.XOR);
        return this;
    }

    public FilterBuilder XOR(Filter filter) {
        operators.add(OperatorFactory.Operator.XOR);
        filters.add(filter);
        return this;
    }


    public FilterBuilder AND(Filter filter) {
        operators.add(OperatorFactory.Operator.AND);
        filters.add(filter);
        return this;
    }

    /**
     * Call this method to build one filter
     * @return : The filter built
     */
    public Filter build() {

        if(filters.size() == 0) {
            throw new NoFilterBuilderException();
        }

        if(filters.size() != operators.size() + 1 - notOperatorCount) {
            throw new FilterBuilderFormatException();
        }

        Filter filter = filters.pop();
        while (filters.size() > 0 && operators.size() > 0) {
            final OperatorFactory.Operator currentOperator = operators.pop();
            Filter currentFilter = filters.pop();
            filter = OperatorFactory.getInstanceOf(currentOperator, filter, handleNotOperator(currentFilter));
        }

        return filter;
    }

    private Filter handleNotOperator(Filter currentFilter) {
        if(operators.size() != 0) {
            final OperatorFactory.Operator nextOperator = operators.peek();
            if(nextOperator == OperatorFactory.Operator.NOT) {
                operators.pop();
                currentFilter = OperatorFactory.getInstanceOf(OperatorFactory.Operator.NOT,currentFilter,null);
            }
        }
        return currentFilter;
    }

}
