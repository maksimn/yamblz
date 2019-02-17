package io.github.maksimn.yamblz2017intro.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import io.github.maksimn.yamblz2017intro.R;
import io.github.maksimn.yamblz2017intro.data.repository.LanguagesRepository;
import io.github.maksimn.yamblz2017intro.ui.adapter.LanguagesAdapter;

public class TranslatorFragment extends Fragment {

    private TranslatorViewModel viewModel;
    private LanguagesRepository languagesRepository;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_translator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        languagesRepository = new LanguagesRepository(getActivity());

        viewModel = new TranslatorViewModel();
        viewModel.setFromLanguage(languagesRepository.defaultLanguage());
        viewModel.setToLanguage(languagesRepository.secondDefaultLanguage());

        initializeSpinner(R.id.from_language_spinner, viewModel.getFromLanguage());
        initializeSpinner(R.id.to_language_spinner, viewModel.getToLanguage());
    }

    private void initializeSpinner(final int spinnerId, String language) {
        final Spinner spinner = getActivity().findViewById(spinnerId);
        final String[] languages = spinnerId == R.id.from_language_spinner ?
                languagesRepository.getLanguageNames() :
                languagesRepository.getSupportedLanguageNames();
        final LanguagesAdapter adapter = new LanguagesAdapter(getContext(), R.layout.item_language,
                languages, getLayoutInflater());

        spinner.setAdapter(adapter);

        final int langPos = adapter.getPosition(language);

        spinner.setSelection(langPos);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final TextView spinnerTextView = (TextView) view;
                final String languageName = spinnerTextView.getText().toString();

                if (spinnerId == R.id.from_language_spinner) {
                    viewModel.setFromLanguage(languageName);
                } else if (spinnerId == R.id.to_language_spinner) {
                    viewModel.setToLanguage(languageName);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }
}
