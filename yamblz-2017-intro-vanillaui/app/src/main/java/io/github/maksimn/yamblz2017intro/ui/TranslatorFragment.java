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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TranslatorFragment extends Fragment {

    private LangRepository langRepository;

    private Disposable disposable;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_translator, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        langRepository = new LangRepository(null);

        initializeSpinner(R.id.from_language_spinner,
                langRepository.getLanguageNames(),
                langRepository.defaultLanguage(),
                this::fetchSupportedLanguages);
    }

    @Override
    public void onStop() {
        super.onStop();

        if (disposable != null) {
            disposable.dispose();
        }
    }

    private void initializeSpinner(int spinnerId, String[] langs, String selectedLang,
                                   Action<String> onItemSelectedCallback) {
        final Spinner spinner = getActivity().findViewById(spinnerId);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                R.layout.item_language, langs);
        final int langPos = adapter.getPosition(selectedLang);

        spinner.setAdapter(adapter);
        spinner.setSelection(langPos);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final TextView spinnerTextView = (TextView) view;

                if (spinnerTextView != null) {
                    final String languageName = spinnerTextView.getText().toString();

                    if (onItemSelectedCallback != null) {
                        onItemSelectedCallback.run(languageName);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void fetchSupportedLanguages(String selectedLang) {
        disposable = langRepository.getSupportedLanguageNames(selectedLang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::fetchSupportedLanguagesSuccess, this::fetchSupportedLanguagesError);
    }

    private void fetchSupportedLanguagesSuccess(String[] supportedLangs) {
        initializeSpinner(R.id.to_language_spinner, supportedLangs, supportedLangs[0], null);
    }

    private void fetchSupportedLanguagesError(Throwable e) {
        final String[] error = {"[Нет языка]"};
        initializeSpinner(R.id.to_language_spinner, error, error[0], val -> {});
    }
}