package io.github.maksimn.yamblz2017intro.data.repository;

import android.content.Context;
import android.content.res.Resources;
import io.github.maksimn.yamblz2017intro.R;
import io.github.maksimn.yamblz2017intro.data.pojo.Language;
import io.github.maksimn.yamblz2017intro.util.JsonUtil;
import io.github.maksimn.yamblz2017intro.util.ResourceUtil;
import io.reactivex.Single;

public class LanguageRepository {

    private static Language[] _languages;
    private static String[] _languageNames;

    private static String _defaultLanguage;
    private static String _secondDefaultLanguage;

    public LanguageRepository(Context context) {
        if (_languages == null) {
            /*
             * 1. Get list of all languages from a raw resource
             */
            final Resources res = context.getResources();
            final String languagesJson = ResourceUtil.readRawAsString(res, R.raw.languages);

            _languages = JsonUtil.toLanguageList(languagesJson);

            final int n = _languages.length;

            _languageNames = new String[n];

            for(int i = 0; i < n; i++) {
                _languageNames[i] = _languages[i].lang_name;
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

    public Single<String[]> getSupportedLanguageNames(String language) {
        if ("Английский".equals(language)) {
            return Single.just(new String[] {"Эстонский", "Яванский", "Японский"});
        } else if ("Русский".equals(language)) {
            return Single.just(new String[] {"Азербайджанский", "Английский", "Баскский","Башкирский", "Вьетнамский"});
        } else {
            return Single.just(new String[] {"Asd", "Fgh"});
        }
    }
}
