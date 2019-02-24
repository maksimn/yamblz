package io.github.maksimn.yamblz2017intro.ui;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import io.github.maksimn.yamblz2017intro.BR;
import io.github.maksimn.yamblz2017intro.data.repository.LangRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TranslatorViewModel extends BaseObservable {

    private String fromLanguage;
    private String toLanguage;
    private String textForTranslation;
    private LangRepository langRepository;

    private String[] supportedLanguageNames = new String[]{};

    private Disposable disposable;

    public TranslatorViewModel() {
        langRepository = new LangRepository(null);
        setFromLanguage(langRepository.defaultLanguage());
    }

    public void setFromLanguage(String value) {
        fromLanguage = value;

        fetchSupportedLangNames();
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

    public String[] getLanguageNames() {
        return langRepository.getLanguageNames();
    }

    public void setSupportedLanguageNames(String[] names) {
        supportedLanguageNames = names;

        notifyPropertyChanged(BR.supportedLanguageNames);
    }

    @Bindable
    public String[] getSupportedLanguageNames() {
        return supportedLanguageNames;
    }

    private void fetchSupportedLangNames() {
        disposable = langRepository.getSupportedLanguageNames(fromLanguage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onSupportedLangsLoadingSuccess,
                        this::onSupportedLangsLoadingError
                );
    }

    private void onSupportedLangsLoadingSuccess(String[] langs) {
        setSupportedLanguageNames(langs);
    }

    private void onSupportedLangsLoadingError(Throwable t) {
        setSupportedLanguageNames(new String[] {"[язык не задан]"});
    }

    public void dispose() {
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
