package io.github.maksimn.yamblz2017intro.util;

import java.util.Arrays;

public class LangUtil {

    public static String determineToLang(String fromLang, String defaultLang,
                                         String secondDefaultLang, String[] langs) {
        if (!defaultLang.equals(fromLang) && contains(langs, defaultLang)) {

            return defaultLang;

        } else if (!secondDefaultLang.equals(fromLang) && contains(langs, secondDefaultLang)) {

            return secondDefaultLang;

        } else if (langs != null && langs.length > 0) {

            return langs[0];

        } else {
            return null;
        }
    }

    private static boolean contains(String[] arr, String str) {
        return Arrays.asList(arr).contains(str);
    }
}
