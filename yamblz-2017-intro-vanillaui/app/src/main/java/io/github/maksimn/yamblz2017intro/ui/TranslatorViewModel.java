package io.github.maksimn.yamblz2017intro.ui;

import io.github.maksimn.yamblz2017intro.data.repository.LangRepository;
import io.reactivex.Single;

public class TranslatorViewModel {

    private String fromLanguage;
    private String toLanguage;
    private String textForTranslation;
    private LangRepository langRepository;

    public TranslatorViewModel() {
        langRepository = new LangRepository(null);
        setFromLanguage(langRepository.defaultLanguage());
    }

    public void setFromLanguage(String value) {
        fromLanguage = value;
    }

    public String getFromLanguage() {
        return fromLanguage;
    }

    public void setToLanguage(String value) {
        toLanguage = value;
    }

    public String getToLanguage() {
        return toLanguage;
    }

    public void setTextForTranslation(String value) {
        textForTranslation = value;
    }

    public String getTextForTranslation() {
        return textForTranslation;
    }

    public String[] getLanguageNames() {
        return langRepository.getLanguageNames();
    }

    public Single<String[]> getSupportedLanguageNames() {
        return langRepository.getSupportedLanguageNames(fromLanguage);
    }
}
