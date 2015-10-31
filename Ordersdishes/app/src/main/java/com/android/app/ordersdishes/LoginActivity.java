package com.android.app.ordersdishes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView emailACTextView;
    private EditText passwordEditView;
    private Button signInButton;
    private Button registerButton;
    private View progressView;
    private View loginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}

