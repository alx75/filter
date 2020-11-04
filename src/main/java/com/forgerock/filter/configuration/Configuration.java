package com.forgerock.filter.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * The configuration is loaded from two files. One is maintained by this project and the other one by the 3tiers applications. <br/>
 * Created by alexandre on 5/20/2017.
 */
public enum Configuration {

    FILTER_PACKAGES("filter.packages",false);

    public static final Logger LOGGER = LoggerFactory.getLogger( Configuration.class );
    private static final String CONFIGURATION_FILE_NAME = "filter.properties";
    private static final String CONFIGURATION_FILE_DEFAULT_NAME = "filter_default.properties";
    private static java.util.Properties customProp = new java.util.Properties(); // properties defined by 3tiers applications
    private static java.util.Properties defaultProp = new java.util.Properties();

    private String propertyName;
    private boolean isCustomPropOverrideDefault; // if false the properties will be joined

    static {
        loadProperties(CONFIGURATION_FILE_NAME, customProp);
        loadProperties(CONFIGURATION_FILE_DEFAULT_NAME, defaultProp);
    }

    /**
     *
     * @param propertyName : The name of the property
     * @param isCustomPropOverrideDefault : If true the property ( if present ) in the filter.properties will override the one in the filter_default.properties.
     *                                    Otherwise they will be joined.
     */
    Configuration(String propertyName,boolean isCustomPropOverrideDefault) {
        this.propertyName = propertyName;
        this.isCustomPropOverrideDefault = isCustomPropOverrideDefault;
    }

    /**
     *
     * @return : the value for the given enum key
     */
    public String getValue() {
        String customPropValue = (String) customProp.get(this.propertyName);
        String defaultPropValue = (String) defaultProp.get(this.propertyName);

        String valueToReturn;
        if(this.isCustomPropOverrideDefault) {
            valueToReturn = customPropValue == null ? defaultPropValue : customPropValue;
        }else {
            valueToReturn = customPropValue == null ? defaultPropValue : customPropValue + ',' + defaultPropValue;
        }
        return valueToReturn;
    }

    /**
     * Load the file from the fileName and put all the property in the properties object
     * @param fileName : the name of the file to read
     * @param properties : The property object in which to add properties
     */
    private static void loadProperties(String fileName , java.util.Properties properties) {
        InputStream input = null;

        try {
            input = Configuration.class.getClassLoader().getResourceAsStream(fileName);
            if(input==null){
                LOGGER.warn("The configuration file {} has not been found." ,  fileName);
            }
            properties.load(input);
            LOGGER.warn("The configuration file {} has been converted into properties successfully" ,  fileName);
        } catch (IOException ex) {
            LOGGER.error("Error while reading the configuration file.",ex);
        } finally{
            if(input != null){
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.error("Error while closing the configuration file.",e);
                }
            }
        }
    }

}

