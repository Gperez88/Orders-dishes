package com.android.app.ordersdishes.activities;

import com.android.app.ordersdishes.R;
import com.android.app.ordersdishes.fragments.RegisterFragment;

public class RegisterActivity extends BaseAppActivity {
    private static final String REGISTER_FRAGMENT_TAG = "register_fragment_tag";

    public RegisterActivity() {
        super(R.layout.activity_register);
    }

    @Override
    protected void initComponents() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, RegisterFragment.create(), REGISTER_FRAGMENT_TAG)
                .commit();
    }
}
