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
import io.github.maksimn.yamblz2017intro.data.repository.LangRepository;
import io.github.maksimn.yamblz2017intro.util.SpinnerUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TranslatorFragment extends Fragment {

    private LangRepository langRepository;

    private Disposable disposable;

    private Spinner fromLangSpinner;
    private Spinner toLangSpinner;

    private String fromLang;
    private String toLang;

    private final static String FROM_LANGUAGE = "FROM_LANGUAGE";
    private final static String TO_LANGUAGE = "TO_LANGUAGE";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_translator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            fromLang = savedInstanceState.getString(FROM_LANGUAGE);
            toLang = savedInstanceState.getString(TO_LANGUAGE);
        }

        fromLangSpinner = getActivity().findViewById(R.id.from_language_spinner);
        toLangSpinner = getActivity().findViewById(R.id.to_language_spinner);

        langRepository = new LangRepository(null);

        final String currentFromLang = fromLang != null ?
                fromLang : langRepository.defaultLanguage();

        SpinnerUtil.setDataAndBehavior(fromLangSpinner, langRepository.getLanguageNames(),
                currentFromLang, this::fetchSupportedLanguages);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        final String selectedFromLang = fromLangSpinner.getSelectedItem().toString();
        final String selectedToLang = toLangSpinner.getSelectedItem().toString();

        outState.putString(FROM_LANGUAGE, selectedFromLang);
        outState.putString(TO_LANGUAGE, selectedToLang);
    }

    @Override
    public void onStop() {
        super.onStop();

        if (disposable != null) {
            disposable.dispose();
        }
    }

    private void fetchSupportedLanguages(String selectedLang) {
        disposable = langRepository.getSupportedLanguageNames(selectedLang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::fetchSupportedLanguagesSuccess, this::fetchSupportedLanguagesError);
    }

    private void fetchSupportedLanguagesSuccess(String[] supportedLangs) {
        final String currentToLang = toLang != null ? toLang : supportedLangs[0];

        SpinnerUtil.setDataAndBehavior(toLangSpinner, supportedLangs, currentToLang, null);
    }

    private void fetchSupportedLanguagesError(Throwable e) {
        final String[] error = {"[Нет языка]"};
        SpinnerUtil.setDataAndBehavior(toLangSpinner, error, error[0], null);
    }
}
