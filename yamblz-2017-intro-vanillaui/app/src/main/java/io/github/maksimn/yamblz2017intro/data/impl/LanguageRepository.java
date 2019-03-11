package io.github.maksimn.yamblz2017intro.data.impl;

import java.util.ArrayList;
import java.util.TreeMap;
import io.github.maksimn.yamblz2017intro.data.interfaces.ILanguageRepository;
import io.github.maksimn.yamblz2017intro.data.pojo.LangsDirsRawData;
import io.reactivex.Single;

public class LanguageRepository implements ILanguageRepository {

    private static String[] languages;
    private static TreeMap<String, ArrayList<String>> supportedLanguages;

    @Override
    public void initFromRawData(LangsDirsRawData langsDirsRawData) {
        supportedLanguages = new TreeMap<>();

        for (String dir : langsDirsRawData.dirs) {
            final int ind = dir.indexOf('-');
            final String firstLangCode = dir.substring(0, ind);
            final String secondLangCode = dir.substring(ind + 1);
            final String firstLanguage = langsDirsRawData.langs.get(firstLangCode);
            final String secondLanguage = langsDirsRawData.langs.get(secondLangCode);

            if (!supportedLanguages.containsKey(firstLanguage)) {
                supportedLanguages.put(firstLanguage, new ArrayList<>());
            }

            final ArrayList<String> list = supportedLanguages.get(firstLanguage);

            list.add(secondLanguage);
        }

        languages = supportedLanguages.keySet().toArray(new String[0]);
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

    @Override
    public Single<LangsDirsRawData> loadLangData() {
        HttpLangDataLoader httpLangDataLoader = new HttpLangDataLoader();

        return httpLangDataLoader.getLangsDirsRawData();
    }
}
