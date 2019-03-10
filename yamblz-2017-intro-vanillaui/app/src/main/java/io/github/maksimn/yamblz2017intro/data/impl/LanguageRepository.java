package io.github.maksimn.yamblz2017intro.data.impl;

import android.content.Context;
import java.util.ArrayList;
import java.util.TreeMap;
import io.github.maksimn.yamblz2017intro.R;
import io.github.maksimn.yamblz2017intro.data.interfaces.ILanguageRepository;
import io.github.maksimn.yamblz2017intro.data.pojo.TranslationDirections;
import io.github.maksimn.yamblz2017intro.util.JsonUtil;
import io.github.maksimn.yamblz2017intro.util.ResourceUtil;

public class LanguageRepository implements ILanguageRepository {

    private static TranslationDirections translationDirections;
    private static TreeMap<String, ArrayList<String>> supportedLanguages;
    private static String[] languages;

    public LanguageRepository(Context context) {
        if (translationDirections == null) {
            final String json = ResourceUtil.readRawAsString(context.getResources(),
                    R.raw.translator_langs);

            translationDirections = JsonUtil.toTranslationsDirections(json);

            supportedLanguages = new TreeMap<>();

            for (String dir : translationDirections.dirs) {
                final int ind = dir.indexOf('-');
                final String firstLangCode = dir.substring(0, ind);
                final String secondLangCode = dir.substring(ind + 1);
                final String firstLanguage = translationDirections.langs.get(firstLangCode);
                final String secondLanguage = translationDirections.langs.get(secondLangCode);

                if (!supportedLanguages.containsKey(firstLanguage)) {
                    supportedLanguages.put(firstLanguage, new ArrayList<>());
                }

                final ArrayList<String> list = supportedLanguages.get(firstLanguage);

                list.add(secondLanguage);
            }

            languages = supportedLanguages.keySet().toArray(new String[0]);
        }
    }

    @Override
    public String[] getLanguageNames() {
        return languages;
    }

    @Override
    public String[] getSupportedLanguageNames(String language) {
        final ArrayList<String> list = supportedLanguages.get(language);

        return list.toArray(new String[0]);
    }
}
