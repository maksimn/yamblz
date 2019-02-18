package io.github.maksimn.yamblz2017intro.ui;

public class TranslatorViewModel {

    private String fromLanguage;
    private String toLanguage;

    private String textForTranslation;

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
}
