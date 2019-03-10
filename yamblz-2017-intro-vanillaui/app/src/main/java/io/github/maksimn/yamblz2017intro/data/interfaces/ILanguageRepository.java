package io.github.maksimn.yamblz2017intro.data.interfaces;

public interface ILanguageRepository {

    String[] getLanguageNames();

    String[] getSupportedLanguageNames(String language);
}
