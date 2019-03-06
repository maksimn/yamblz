package io.github.maksimn.yamblz2017intro.data.repository;

import android.content.Context;
import io.github.maksimn.yamblz2017intro.R;
import io.github.maksimn.yamblz2017intro.data.pojo.Lang;
import io.github.maksimn.yamblz2017intro.util.JsonUtil;
import io.github.maksimn.yamblz2017intro.util.ResourceUtil;
import io.reactivex.Single;

public class LangRepository {

    private static Lang[] smLangs;
    private static String[] smLanguageNames;

    public LangRepository(Context context) {
        if (smLangs == null) {
            final String languagesJson =
                    ResourceUtil.readRawAsString(context.getResources(), R.raw.languages);

            smLangs = JsonUtil.toLanguageList(languagesJson);

            final int n = smLangs.length;

            smLanguageNames = new String[n];

            for(int i = 0; i < n; i++) {
                smLanguageNames[i] = smLangs[i].lang_name;
            }
        }
    }

    public Lang[] getLanguages() {
        return smLangs;
    }

    public String[] getLanguageNames() { return smLanguageNames; }

    public String defaultLanguage() { return "Английский"; }

    public String secondDefaultLanguage() { return "Русский"; }

    public Single<String[]> getSupportedLanguageNames(String langName) {
        if ("Английский".equals(langName)) {
            return Single.just(new String[] {"Эстонский", "Яванский", "Японский"});
        } else if ("Русский".equals(langName)) {
            return Single.just(new String[] {"Азербайджанский", "Английский", "Баскский","Башкирский", "Вьетнамский"});
        } else {
            return Single.just(new String[] {"Asd", "Fgh"});
        }
    }
}
