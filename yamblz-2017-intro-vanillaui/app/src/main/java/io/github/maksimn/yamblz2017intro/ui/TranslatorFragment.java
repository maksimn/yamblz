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
import io.github.maksimn.yamblz2017intro.data.interfaces.ILanguageRepository;
import io.github.maksimn.yamblz2017intro.util.Factories;
import io.github.maksimn.yamblz2017intro.util.SpinnerUtil;

public class TranslatorFragment extends Fragment {

    private ILanguageRepository languageRepository = Factories.getLanguageRepository();

    private Spinner firstLanguageSpinner;
    private Spinner secondLanguageSpinner;

    private String firstLanguage;
    private String secondLanguage;

    private final static String FIRST_LANGUAGE = "FIRST_LANGUAGE";
    private final static String SECOND_LANGUAGE = "SECOND_LANGUAGE";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_translator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firstLanguageSpinner = getActivity().findViewById(R.id.first_language_spinner);
        secondLanguageSpinner = getActivity().findViewById(R.id.second_language_spinner);

        if (savedInstanceState != null) {
            firstLanguage = savedInstanceState.getString(FIRST_LANGUAGE);
            secondLanguage = savedInstanceState.getString(SECOND_LANGUAGE);
        }

        if (firstLanguage == null) {
            firstLanguage = getResources().getString(R.string.default_language);
        }

        SpinnerUtil.setDataAndBehavior(firstLanguageSpinner, languageRepository.getLanguageNames(),
                firstLanguage, this::onFirstSpinnerItemSelected);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(FIRST_LANGUAGE, firstLanguageSpinner.getSelectedItem().toString());
        outState.putString(SECOND_LANGUAGE, secondLanguageSpinner.getSelectedItem().toString());
    }

    private void onFirstSpinnerItemSelected(String selectedLanguage) {
    }
}
