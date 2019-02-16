package io.github.maksimn.yamblz2017intro.data.repository;

import android.content.Context;
import io.github.maksimn.yamblz2017intro.R;
import io.github.maksimn.yamblz2017intro.data.pojo.Language;
import io.github.maksimn.yamblz2017intro.utils.JsonUtils;
import io.github.maksimn.yamblz2017intro.utils.ResourceUtils;

public class LanguagesRepository {

    private static Language[] smLanguages;
    private static String[] languageNames;

    public LanguagesRepository(Context context) {
        if (smLanguages == null) {
            final String languagesJson =
                    ResourceUtils.readRawAsString(context.getResources(), R.raw.languages);

            smLanguages = JsonUtils.toLanguageList(languagesJson);

            final int n = smLanguages.length;

            languageNames = new String[n];

            for(int i = 0; i < n; i++) {
                languageNames[i] = smLanguages[i].lang_name;
            }
        }
    }

    public Language[] getLanguages() {
        return smLanguages;
    }

    public String[] getLanguageNames() {
        return languageNames;
    }
}
