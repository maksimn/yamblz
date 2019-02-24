package io.github.maksimn.yamblz2017intro.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import io.github.maksimn.yamblz2017intro.R;
import io.github.maksimn.yamblz2017intro.data.repository.LangRepository;
import io.github.maksimn.yamblz2017intro.util.Action;

public class TranslatorFragment extends Fragment {

    private TranslatorViewModel viewModel;
    private LangRepository langRepository;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_translator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        langRepository = new LangRepository(getActivity());

        viewModel = new TranslatorViewModel();
        viewModel.setFromLanguage(langRepository.defaultLanguage());
        viewModel.setToLanguage(langRepository.secondDefaultLanguage());

        initializeSpinner(R.id.from_language_spinner,
                viewModel.getFromLanguage(),
                langRepository.getLanguageNames(),
                langName -> viewModel.setFromLanguage(langName)
        );
        initializeSpinner(
                R.id.to_language_spinner,
                viewModel.getToLanguage(),
                langRepository.getSupportedLanguageNames(),
                langName -> viewModel.setToLanguage(langName)
        );
    }

    private void initializeSpinner(final int spinnerId, String language, String[] languages,
                                   Action<String> onItemSelectedCallback) {
        final Spinner spinner = getActivity().findViewById(spinnerId);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                R.layout.item_language, languages);
        final int langPos = adapter.getPosition(language);

        spinner.setAdapter(adapter);
        spinner.setSelection(langPos);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final TextView spinnerTextView = (TextView) view;

                if (spinnerTextView != null) {
                    final String languageName = spinnerTextView.getText().toString();

                    onItemSelectedCallback.run(languageName);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}
