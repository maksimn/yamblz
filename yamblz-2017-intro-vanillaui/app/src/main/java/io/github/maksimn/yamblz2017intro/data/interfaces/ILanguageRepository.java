package io.github.maksimn.yamblz2017intro.data.interfaces;

import io.reactivex.Single;

public interface ILanguageRepository {

    String[] getLanguageNames();

    String defaultLanguage();

    String secondDefaultLanguage();

    Single<String[]> getSupportedLanguageNames(String language);
}
