package io.github.maksimn.yamblz2017intro.ui.databindings;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import io.github.maksimn.yamblz2017intro.R;
import io.github.maksimn.yamblz2017intro.util.Action;

public class SpinnerBindings {

    @BindingAdapter("android:entries")
    public static void setEntries(Spinner spinner, String[] langs) {
        spinner.setAdapter(new ArrayAdapter<>(spinner.getContext(), R.layout.item_language, langs));
    }

    @BindingAdapter("android:text")
    public static void setText(Spinner spinner, String langName) {
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinner.getAdapter();
        spinner.setSelection(adapter.getPosition(langName));
    }

    @BindingAdapter("onItemSelected")
    public static void setItemSelectedListener(Spinner spinner, Action<Object> listener) {
        if (listener == null) {
            spinner.setOnItemSelectedListener(null);
        } else {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Object tag = spinner.getTag();
                    if (tag == null || (tag != null && !tag.equals(position))) {
                        listener.run(parent.getItemAtPosition(position));
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });
        }
    }
}
