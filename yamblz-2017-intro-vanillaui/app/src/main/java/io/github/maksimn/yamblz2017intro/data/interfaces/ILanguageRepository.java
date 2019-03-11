package io.github.maksimn.yamblz2017intro.data.interfaces;

import io.github.maksimn.yamblz2017intro.data.pojo.LangsDirsRawData;
import io.reactivex.Single;

public interface ILanguageRepository {

    String[] getLanguageNames();

    String[] getSupportedLanguageNames(String language);

    Single<LangsDirsRawData> loadLangData();

    void initFromRawData(LangsDirsRawData langsDirsRawData);
}
