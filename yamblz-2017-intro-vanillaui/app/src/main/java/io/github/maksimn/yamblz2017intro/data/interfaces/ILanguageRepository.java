package io.github.maksimn.yamblz2017intro.data.interfaces;

import io.reactivex.Completable;

public interface ILanguageRepository {

    String[] getLanguageNames();

    String[] getSupportedLanguageNames(String language);

    Completable loadLangData();
}
