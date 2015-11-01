package com.android.app.ordersdishes.fragments;

import android.text.TextUtils;
import android.widget.EditText;

import com.android.app.ordersdishes.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabriel on 11/1/2015.
 */
public abstract class BaseAppFormFragment extends BaseAppFragment {
    protected List<EditText> inputFields;

    public BaseAppFormFragment(int layoutId) {
        super(layoutId);
        inputFields = new ArrayList<>();
    }

    protected boolean validFields() {
        if (inputFields == null || inputFields.size() == 0)
            return true;

        for (EditText editText : inputFields) {
            if (TextUtils.isEmpty(editText.getText().toString().trim())) {
                editText.setError(getString(R.string.error_field_required));
                return false;
            }
        }

        return true;
    }
}
