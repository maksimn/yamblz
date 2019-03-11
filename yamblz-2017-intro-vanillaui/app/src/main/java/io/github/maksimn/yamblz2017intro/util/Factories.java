package io.github.maksimn.yamblz2017intro.util;

import io.github.maksimn.yamblz2017intro.data.impl.LanguageRepository;
import io.github.maksimn.yamblz2017intro.data.interfaces.ILanguageRepository;

public class Factories {

    public static ILanguageRepository getLanguageRepository() {
        return new LanguageRepository();
    }
}
