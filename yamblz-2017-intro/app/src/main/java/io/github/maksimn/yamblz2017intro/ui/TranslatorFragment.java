package io.github.maksimn.yamblz2017intro.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.github.maksimn.yamblz2017intro.R;
import io.github.maksimn.yamblz2017intro.databinding.FragmentTranslatorBinding;

public class TranslatorFragment extends Fragment {

    private TranslatorViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentTranslatorBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_translator, container, false);
        View view = binding.getRoot();
        viewModel = new TranslatorViewModel();
        binding.setViewModel(viewModel);
        return view;
    }
}
