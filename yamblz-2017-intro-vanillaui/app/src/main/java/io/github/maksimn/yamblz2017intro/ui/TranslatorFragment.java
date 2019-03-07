package io.github.maksimn.yamblz2017intro.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import io.github.maksimn.yamblz2017intro.R;
import io.github.maksimn.yamblz2017intro.data.repository.LanguageRepository;
import io.github.maksimn.yamblz2017intro.util.LanguageUtil;
import io.github.maksimn.yamblz2017intro.util.SpinnerUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TranslatorFragment extends Fragment {

    private LanguageRepository languageRepository = new LanguageRepository(null);

    private Disposable disposable;

    private Spinner firstLanguageSpinner;
    private Spinner secondLanguageSpinner;

    private String firstLanguage;
    private String secondLanguage;

    private final static String FIRST_LANGUAGE = "FIRST_LANGUAGE";
    private final static String SECOND_LANGUAGE = "SECOND_LANGUAGE";

    private final static String[] noLanguageError = {"[Нет языка]"};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_translator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            firstLanguage = savedInstanceState.getString(FIRST_LANGUAGE);
            secondLanguage = savedInstanceState.getString(SECOND_LANGUAGE);
        }

        firstLanguageSpinner = getActivity().findViewById(R.id.first_language_spinner);
        secondLanguageSpinner = getActivity().findViewById(R.id.second_language_spinner);

        if (firstLanguage == null) {
            firstLanguage = languageRepository.defaultLanguage();
        }

        SpinnerUtil.setDataAndBehavior(firstLanguageSpinner, languageRepository.getLanguageNames(),
                firstLanguage, this::fetchSupportedLanguages);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(FIRST_LANGUAGE, firstLanguageSpinner.getSelectedItem().toString());
        outState.putString(SECOND_LANGUAGE, secondLanguageSpinner.getSelectedItem().toString());
    }

    @Override
    public void onStop() {
        super.onStop();

        if (disposable != null) {
            disposable.dispose();
        }
    }

    private void fetchSupportedLanguages(String selectedLanguage) {
        disposable = languageRepository.getSupportedLanguageNames(selectedLanguage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::fetchSupportedLanguagesSuccess, this::fetchSupportedLanguagesError);
    }

    private void fetchSupportedLanguagesSuccess(String[] supportedLanguages) {
        firstLanguage = firstLanguageSpinner.getSelectedItem().toString();
        if (secondLanguage == null) {
            secondLanguage = LanguageUtil.determineSecondLanguage(firstLanguage,
                    languageRepository.defaultLanguage(),
                    languageRepository.secondDefaultLanguage(), supportedLanguages);

            if (secondLanguage == null) {
                secondLanguage = noLanguageError[0];
            }
        }

        SpinnerUtil.setDataAndBehavior(secondLanguageSpinner, supportedLanguages, secondLanguage,
                null);
    }

    private void fetchSupportedLanguagesError(Throwable e) {
        SpinnerUtil.setDataAndBehavior(secondLanguageSpinner, noLanguageError, noLanguageError[0],
                null);
    }
}
