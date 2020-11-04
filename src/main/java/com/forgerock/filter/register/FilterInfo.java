package com.forgerock.filter.register;

import java.util.Arrays;

/**
 * Created by alexandre on 5/21/2017.
 */
public class FilterInfo {

    private FilterMetadata filterMetadata;
    private String[] parameters;

    public FilterInfo(FilterMetadata filterMetadata, String[] parameters) {
        this.filterMetadata = filterMetadata;
        this.parameters = parameters;
    }

    public FilterMetadata getFilterMetadata() {
        return filterMetadata;
    }

    public String[] getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "FilterInfo{" +
                "filterMetadata=" + filterMetadata +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }
}
