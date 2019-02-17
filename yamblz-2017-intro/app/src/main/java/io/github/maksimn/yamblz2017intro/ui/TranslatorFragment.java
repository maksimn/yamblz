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

public class TranslatorFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_translator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final LanguagesRepository languagesRepository = new LanguagesRepository(getActivity());
        final Spinner spinner = getActivity().findViewById(R.id.from_language_spinner);

        LanguagesAdapter adapter = new LanguagesAdapter(getContext(), R.layout.item_language,
                languagesRepository.getLanguageNames(), getLayoutInflater());

        spinner.setAdapter(adapter);
    }
}
