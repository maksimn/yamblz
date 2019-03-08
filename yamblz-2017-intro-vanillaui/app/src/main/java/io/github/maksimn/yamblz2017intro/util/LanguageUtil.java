package io.github.maksimn.yamblz2017intro.util;

import android.content.res.Resources;
import java.util.Arrays;
import io.github.maksimn.yamblz2017intro.App;
import io.github.maksimn.yamblz2017intro.R;

public class LanguageUtil {

    public static String determineSecondLanguage(String firstLanguage, String[] languages) {
        final Resources res = App.getApplication().getResources();
        final String defaultLanguage = res.getString(R.string.default_language);
        final String secondDefaultLanguage = res.getString(R.string.second_default_language);

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
