package io.github.maksimn.yamblz2017intro.data.repository;

import android.content.Context;
import io.github.maksimn.yamblz2017intro.R;
import io.github.maksimn.yamblz2017intro.data.pojo.Language;
import io.github.maksimn.yamblz2017intro.util.JsonUtil;
import io.github.maksimn.yamblz2017intro.util.ResourceUtil;

public class LanguagesRepository {

    private static Language[] smLanguages;
    private static String[] smLanguageNames;

    public LanguagesRepository(Context context) {
        if (smLanguages == null) {
            final String languagesJson =
                    ResourceUtil.readRawAsString(context.getResources(), R.raw.languages);

            smLanguages = JsonUtil.toLanguageList(languagesJson);

            final int n = smLanguages.length;

            smLanguageNames = new String[n];

            for(int i = 0; i < n; i++) {
                smLanguageNames[i] = smLanguages[i].lang_name;
            }
        }
    }

    public Language[] getLanguages() {
        return smLanguages;
    }

    public String[] getLanguageNames() { return smLanguageNames; }
}
