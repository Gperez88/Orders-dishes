package com.android.app.ordersdishes.activities;

import com.android.app.ordersdishes.R;
import com.android.app.ordersdishes.fragments.LoginFragment;

public class LoginActivity extends BaseAppActivity {
    private static final String LOGIN_FRAGMENT_TAG = "login_fragment_tag";

    public LoginActivity() {
        super(R.layout.activity_login);
    }

    @Override
    protected void initComponents() {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, LoginFragment.create(), LOGIN_FRAGMENT_TAG)
                .commit();
    }
}

