package io.github.maksimn.yamblz2017intro.data.repository;

import android.content.Context;
import android.content.res.Resources;
import io.github.maksimn.yamblz2017intro.R;
import io.github.maksimn.yamblz2017intro.data.pojo.Lang;
import io.github.maksimn.yamblz2017intro.util.JsonUtil;
import io.github.maksimn.yamblz2017intro.util.ResourceUtil;
import io.reactivex.Single;

public class LangRepository {

    private static Lang[] _langs;
    private static String[] _languageNames;

    private static String _defaultLanguage;
    private static String _secondDefaultLanguage;

    public LangRepository(Context context) {
        if (_langs == null) {
            /*
             * 1. Get list of all languages from a raw resource
             */
            final Resources res = context.getResources();
            final String languagesJson = ResourceUtil.readRawAsString(res, R.raw.languages);

            _langs = JsonUtil.toLanguageList(languagesJson);

            final int n = _langs.length;

            _languageNames = new String[n];

            for(int i = 0; i < n; i++) {
                _languageNames[i] = _langs[i].lang_name;
            }

            /*
             * 2. Get default languages from app resources
             */
            _defaultLanguage = res.getString(R.string.default_language);
            _secondDefaultLanguage = res.getString(R.string.second_default_language);
        }
    }

    public String[] getLanguageNames() { return _languageNames; }

    public String defaultLanguage() { return _defaultLanguage; }

    public String secondDefaultLanguage() { return _secondDefaultLanguage; }

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
