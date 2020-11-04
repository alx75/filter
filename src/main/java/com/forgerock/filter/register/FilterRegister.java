package com.forgerock.filter.register;

import com.forgerock.filter.Filter;
import com.forgerock.filter.configuration.Configuration;
import com.forgerock.filter.exception.NoDefaultConstructor;
import com.forgerock.filter.exception.NoFilterMatchException;
import com.forgerock.filter.exception.SeveralFilterMatchException;
import com.forgerock.filter.exception.TooManyDefaultConstructor;
import com.forgerock.filter.generator.Default;
import com.forgerock.filter.generator.FilterMatch;
import com.forgerock.filter.util.Sanitizer;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class is responsible of fetching the filters classes in the project. <br/>
 * This class uses reflexion from packages defined in the property file to find classes that extend Filter </br>
 *
 * //TODO
 * Note : It could be interesting to create an interface and propose other kind of FilterRegistration. For instance
 * a simple map which require manual insertion. The user could choose which type of filter to use in the
 * <br/>
 * Created by alexandre on 5/18/2017.
 */
public class FilterRegister {

    private static final Logger LOGGER = LoggerFactory.getLogger( FilterRegister.class );
    private static final String REGEX_VARIABLE_FORMAT = "___VARIABLE___";
    private static final Set<FilterMetadata> registerfilters = new HashSet<>(12);

    static {
        addAllFilters();
    }

    /**
     * Get all the classes which extends Filter and add the to the list.
     */
    private static void addAllFilters() {
        final String[] filterPackages = Configuration.FILTER_PACKAGES.getValue().split(",");

        Arrays.stream(filterPackages).forEach(currentPackageName -> addAllFiltersFromPackageName(currentPackageName));

        LOGGER.info("All subtypes of Filter has been registered. ( {} classes)", registerfilters.size());
        LOGGER.debug("The classnames are : {} ", registerfilters.stream().map(filter -> filter.getFilter().getName()).collect(Collectors.joining(",")));
    }

    private static void addAllFiltersFromPackageName(String currentPackageName) {
        Reflections reflections = new Reflections(currentPackageName);
        Set<Class<? extends Filter>> subTypes = reflections.getSubTypesOf(Filter.class);
        Set<FilterMetadata> filtersFromCurrentPackage = subTypes.stream()
                .filter(filterClass -> !filterClass.isInterface())
                .filter(filterClass -> !Modifier.isAbstract(filterClass.getModifiers()))
                .map(filterClass -> {
                    final Pattern filterPattern = findPatternFromAnnotation(filterClass);
                    final Constructor<?> defaultConstructor = findDefaultConstructor(filterClass);
                    return new FilterMetadata( filterPattern , filterClass, defaultConstructor);

                }).collect(Collectors.toSet());

        registerfilters.addAll(filtersFromCurrentPackage);
    }

    private static Pattern findPatternFromAnnotation(Class<? extends Filter> filterClass) {
        Annotation filterMatcher = filterClass.getDeclaredAnnotation(FilterMatch.class);

        if(filterMatcher == null) {
            throw new RuntimeException("the class " + filterClass.getName() + " must have the annotation " + FilterMatch.class.getName());
        }

        String pattern = ( (FilterMatch) filterMatcher ).pattern();
        return Pattern.compile( sanitizeAndFormatPattern(pattern) );
    }

    private static String sanitizeAndFormatPattern(String pattern) {
        return "^" + pattern.replaceAll("\\{}",REGEX_VARIABLE_FORMAT)
                .replaceAll(REGEX_VARIABLE_FORMAT,"([^)']*)")
                .concat("$");
    }

    private static Constructor<?> findDefaultConstructor(Class<? extends Filter> filterClass) {
        Constructor<?>[] constructors = filterClass.getConstructors();
        if(constructors.length ==1) return constructors[0];

        final List<Constructor<?>> defaultConstructors = Stream.of(constructors)
                .filter(currentConstructor -> currentConstructor.getDeclaredAnnotation(Default.class) != null )
                .collect(Collectors.toList());

        if(defaultConstructors.size() == 0) {
            throw new NoDefaultConstructor(filterClass);
        }else if(defaultConstructors.size() > 1) {
            throw new TooManyDefaultConstructor(filterClass,defaultConstructors.size());
        }

        return defaultConstructors.get(0);
    }


    public static FilterInfo getFilterFromString(String currentFilterAsString) {

        final List<FilterInfo> matchingFiltersInfo = findMatchingFiltersInfo(currentFilterAsString);

        if(matchingFiltersInfo.size() == 0) {
            throw new NoFilterMatchException(currentFilterAsString);
        }

        if(matchingFiltersInfo.size() > 1) {

            throw new SeveralFilterMatchException(currentFilterAsString, matchingFiltersInfo.stream()
                    .map(f -> f.getFilterMetadata().getFilter())
                    .collect(Collectors.toList()));
        }

        LOGGER.trace("The string {} has been resolve to {}", currentFilterAsString, matchingFiltersInfo.get(0));
        return matchingFiltersInfo.get(0);

    }

    private static List<FilterInfo> findMatchingFiltersInfo(String currentFilterAsString) {
        List<FilterInfo> matchFilter = new ArrayList<>();

        for(FilterMetadata currentFilterMetadata : registerfilters) {

            Matcher matcher = currentFilterMetadata.getPattern().matcher(currentFilterAsString);

            if(matcher.matches()) {
                int numberOfGroups = matcher.groupCount();
                String[] parameters = new String[numberOfGroups];

                for(int i=0; i< numberOfGroups ; i++) {
                    parameters[i] = Sanitizer.unSanitize(matcher.group(i+1));
                }


                matchFilter.add(new FilterInfo(currentFilterMetadata,parameters));
            }
        }
        return matchFilter;
    }
}
