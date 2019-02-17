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

    private LanguagesAdapter createLanguagesAdapter() {
        return new LanguagesAdapter(getContext(), R.layout.item_language,
                languagesRepository.getLanguageNames(), getLayoutInflater());
    }

    private void initializeSpinner(int spinnerId, String language) {
        final Spinner spinner = getActivity().findViewById(spinnerId);
        final LanguagesAdapter adapter = createLanguagesAdapter();
        final int langPos = adapter.getPosition(language);

        spinner.setAdapter(adapter);
        spinner.setSelection(langPos);
    }
}
