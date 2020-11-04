package com.forgerock.filter;

/**
 * Created by alexandre on 5/22/2017.
 */
public class Parameter {
    private String key;
    private String value;

    public Parameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
