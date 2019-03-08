package io.github.maksimn.yamblz2017intro.data.interfaces;

import io.reactivex.Single;

public interface ILanguageRepository {

    String[] getLanguageNames();

    Single<String[]> getSupportedLanguageNames(String language);
}
