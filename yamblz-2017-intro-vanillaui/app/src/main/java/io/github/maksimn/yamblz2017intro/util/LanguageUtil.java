package io.github.maksimn.yamblz2017intro.util;

import java.util.Arrays;

public class LanguageUtil {

    public static String determineSecondLanguage(String firstLanguage, String defaultLanguage,
                                                 String secondDefaultLanguage, String[] languages) {
        if (!defaultLanguage.equals(firstLanguage) && contains(languages, defaultLanguage)) {

            return defaultLanguage;

        } else if (!secondDefaultLanguage.equals(firstLanguage) &&
                contains(languages, secondDefaultLanguage)) {

            return secondDefaultLanguage;

        } else if (languages != null && languages.length > 0) {

            return languages[0];

        } else {
            return null;
        }
    }

    private static boolean contains(String[] arr, String str) {
        return Arrays.asList(arr).contains(str);
    }
}
