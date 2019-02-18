package io.github.maksimn.yamblz2017intro.ui;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class TranslatorViewModel extends BaseObservable {

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

    @Bindable
    public String getTextForTranslation() {
        return textForTranslation;
    }
}
