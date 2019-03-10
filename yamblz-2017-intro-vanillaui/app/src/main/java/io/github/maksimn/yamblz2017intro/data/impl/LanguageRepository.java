package io.github.maksimn.yamblz2017intro.data.impl;

import android.content.Context;
import java.util.TreeSet;
import io.github.maksimn.yamblz2017intro.R;
import io.github.maksimn.yamblz2017intro.data.interfaces.ILanguageRepository;
import io.github.maksimn.yamblz2017intro.data.pojo.TranslationDirections;
import io.github.maksimn.yamblz2017intro.util.JsonUtil;
import io.github.maksimn.yamblz2017intro.util.ResourceUtil;

public class LanguageRepository implements ILanguageRepository {

    private static TranslationDirections translationDirections;
    private static String[] languages;

    public LanguageRepository(Context context) {
        if (translationDirections == null) {
            final String json = ResourceUtil.readRawAsString(context.getResources(),
                    R.raw.translator_langs);

            translationDirections = JsonUtil.toTranslationsDirections(json);

            final TreeSet<String> set = new TreeSet<>();

            for (String dir : translationDirections.dirs) {
                final int ind = dir.indexOf('-');
                final String langCode = dir.substring(0, ind);
                final String language = translationDirections.langs.get(langCode);

                set.add(language);
            }

            languages = set.toArray(new String[0]);
        }
    }

    @Override
    public String[] getLanguageNames() {
        return languages;
    }

    @Override
    public String[] getSupportedLanguageNames(String language) {
         return new String[]{};
    }
}
