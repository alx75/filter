package com.forgerock.filter.util;

/**
 * Created by alexandre on 5/22/2017.
 */
public final class Sanitizer {

    private static final String DOUBLE_BACK_SLASH_STR = "__DOUBLE_BACK_SLASH__";
    private Sanitizer() {
    }

    public static String sanitize(String stringToSanitize) {
        return stringToSanitize.replaceAll("['\"\\\\]","\\\\$0");
    }

    public static String unSanitize(String stringToSanitize) {
        return stringToSanitize
                .replaceAll("\\\\\\\\",DOUBLE_BACK_SLASH_STR)
                .replaceAll("\\\\","")
                .replaceAll("__DOUBLE_BACK_SLASH__","\\\\");
    }

}
