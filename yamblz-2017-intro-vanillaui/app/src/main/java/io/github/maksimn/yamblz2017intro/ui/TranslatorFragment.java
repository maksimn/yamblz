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
import io.github.maksimn.yamblz2017intro.util.LangUtil;
import io.github.maksimn.yamblz2017intro.util.SpinnerUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TranslatorFragment extends Fragment {

    private LangRepository langRepository = new LangRepository(null);

    private Disposable disposable;

    private Spinner fromLangSpinner;
    private Spinner toLangSpinner;

    private String fromLang;
    private String toLang;

    private final static String FROM_LANGUAGE = "FROM_LANGUAGE";
    private final static String TO_LANGUAGE = "TO_LANGUAGE";

    private final static String[] error = {"[Нет языка]"};

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

        final String currentFromLang = fromLang != null ?
                fromLang : langRepository.defaultLanguage();

        SpinnerUtil.setDataAndBehavior(fromLangSpinner, langRepository.getLanguageNames(),
                currentFromLang, this::fetchSupportedLanguages);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(FROM_LANGUAGE, fromLangSpinner.getSelectedItem().toString());
        outState.putString(TO_LANGUAGE, toLangSpinner.getSelectedItem().toString());
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
        String currentToLang = toLang != null ? toLang :
                LangUtil.determineToLang(fromLang, langRepository.defaultLanguage(),
                    langRepository.secondDefaultLanguage(), supportedLangs);

        if (currentToLang == null) {
            currentToLang = error[0];
        }

        SpinnerUtil.setDataAndBehavior(toLangSpinner, supportedLangs, currentToLang, null);
    }

    private void fetchSupportedLanguagesError(Throwable e) {
        SpinnerUtil.setDataAndBehavior(toLangSpinner, error, error[0], null);
    }
}
