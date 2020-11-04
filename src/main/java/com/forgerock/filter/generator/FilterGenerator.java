package com.forgerock.filter.generator;

import com.forgerock.filter.Filter;
import com.forgerock.filter.exception.GeneratorException;
import com.forgerock.filter.exception.NoFilterMatchException;
import com.forgerock.filter.register.FilterInfo;
import com.forgerock.filter.register.FilterRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

/**
 * Created by alexandre on 5/20/2017.
 */
public class FilterGenerator {

    public static final Logger LOGGER = LoggerFactory.getLogger( FilterGenerator.class );

    public static Filter fromString(String filterAsString) throws GeneratorException{
        LOGGER.debug("Start generating filters from string : " + filterAsString);

        int numberOfParenthesis = 0;
        final Stack<FilterInfo> operators = new Stack<>();
        final Stack<Filter> filters = new Stack<>();

        char[] chars = filterAsString.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            switch (chars[i]) {
                case '(':
                    numberOfParenthesis++;
                    break;
                case '"':
                    StringBuilder currentFilterAsString = new StringBuilder();
                    while (chars[++i] != '"' || chars[i - 1] == '\\') {
                        currentFilterAsString.append(chars[i]);
                    }

                    FilterInfo filterInfo = FilterRegister.getFilterFromString(currentFilterAsString.toString());
                    filters.push(getFilterInstance(filterInfo));
                    break;
                case ')' :
                    numberOfParenthesis--;
                    FilterInfo operator = operators.pop();
                    Filter filter = getFilterOperatorInstance(filters, operator);
                    filters.push(filter);
                    break;
                default:
                    StringBuilder currentOperatorAsString = new StringBuilder();
                    if(Character.isLetter(chars[i])) {
                        while (i < chars.length && Character.isLetter(chars[i])) {
                            currentOperatorAsString.append(chars[i++]);
                        }
                        operators.push(FilterRegister.getFilterFromString(currentOperatorAsString.toString()));
                        i--;
                    }
                    break;
            }

        }

        if (filters.size() == 0 ) throw new NoFilterMatchException(filterAsString);
        else return filters.pop();
    }

    private static Filter getFilterInstance(FilterInfo filterClass) throws GeneratorException{
        Filter filter = null;
        try {
            filter = (Filter) filterClass.getFilterMetadata().getDefaultConstructor().newInstance((Object[]) filterClass.getParameters());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new GeneratorException("Cannot instantiate the filter " + filterClass.getFilterMetadata().getFilter().getName() , e);
        }
        return filter;
    }

    private static Filter getFilterOperatorInstance(Stack<Filter> filters,FilterInfo operatorFilterInfo) throws GeneratorException {
        Filter filter = null;
        try {
            final Constructor constructor = operatorFilterInfo.getFilterMetadata().getDefaultConstructor();
            final Filter[] filtersRequired = new Filter[constructor.getParameterCount()];
            for(int j=constructor.getParameterCount()-1 ; j >= 0 ; j-- ) {
                if(filters.empty()) {
                    throw new GeneratorException(constructor.getParameterCount() + " parameters where needed to call the constructor but there is not enough parameters");
                }
                filtersRequired[j] = filters.pop();
            }

            filter = (Filter) constructor.newInstance((Object[]) filtersRequired);
        }  catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new GeneratorException("Cannot instantiate the filter " + operatorFilterInfo.getFilterMetadata().getFilter().getName() ,e);
        }
        return filter;
    }

}