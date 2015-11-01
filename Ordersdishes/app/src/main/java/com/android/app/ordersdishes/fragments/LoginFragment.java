package com.android.app.ordersdishes.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.android.app.ordersdishes.R;
import com.android.app.ordersdishes.activities.DishActivity;
import com.android.app.ordersdishes.activities.RegisterActivity;
import com.android.app.ordersdishes.service.ApiOrderDishesService;
import com.android.app.ordersdishes.service.impl.ApiOrderDishesImpl;
import com.gp89developers.android.commonutils.activity.dialog.ProgressDialog;
import com.gp89developers.android.commonutils.utils.CustomAsyncTask;

/**
 * Created by gabriel on 11/1/2015.
 */
public class LoginFragment extends BaseAppFormFragment {
    private UserLoginTask userLoginTask;
    private SharedPreferences preferences;

    //ui elements
    private AutoCompleteTextView usernameTextView;
    private EditText passwordEditView;
    private Button signInButton;
    private Button registerButton;

    public LoginFragment() {
        super(R.layout.fragment_login);
    }

    public static LoginFragment create() {
        return new LoginFragment();
    }

    @Override
    protected void initComponents(View view) {
        preferences = PreferenceManager.getDefaultSharedPreferences(getSupportActivity());

        usernameTextView = (AutoCompleteTextView) view.findViewById(R.id.username);
        passwordEditView = (EditText) view.findViewById(R.id.password);

        inputFields.add(usernameTextView);
        inputFields.add(passwordEditView);

        signInButton = (Button) view.findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        registerButton = (Button) view.findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getSupportActivity(), RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        boolean isRegister = preferences.getBoolean(getString(R.string.register_user), false);
        if (isRegister)
            registerButton.setVisibility(View.GONE);

        confirmationLogged();
    }

    // CUSTOMER METHODS
    private void confirmationLogged() {
        boolean isLogged = preferences.getString(getString(R.string.prompt_username), null) != null;

        if (isLogged) {
            goToDishActivity();
        }
    }

    private void goToDishActivity() {
        Intent dishIntent = new Intent(getSupportActivity(), DishActivity.class);
        startActivity(dishIntent);

        getSupportActivity().finish();
    }

    private void login() {
        if (validFields()) {
            String username = usernameTextView.getText().toString();
            String password = passwordEditView.getText().toString();

            userLoginTask = new UserLoginTask();
            userLoginTask.execute(username, password);
        }
    }

    private class UserLoginTask extends CustomAsyncTask<String, Void, Boolean> {
        private static final int COUNT_PARAMS = 2;
        private static final int USERNAME_INDEX_PARAM = 0;
        private static final int PASSWORD_INDEX_PARAM = 1;

        private AlertDialog progressDialog;
        private ApiOrderDishesService apiOrderDishesService;

        private String username;
        private String password;

        public UserLoginTask() {
            super(getSupportActivity());
            apiOrderDishesService = new ApiOrderDishesImpl();

            progressDialog = new ProgressDialog(getSupportActivity(), R.style.TransparentDialog);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            boolean isValidParams = (params != null && params.length == COUNT_PARAMS)
                    && (params[USERNAME_INDEX_PARAM] != null && params[PASSWORD_INDEX_PARAM] != null);

            if (!isValidParams)
                return false;

            username = params[USERNAME_INDEX_PARAM].trim();
            password = params[PASSWORD_INDEX_PARAM].trim();

            //simulates a process
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return apiOrderDishesService.login(username, password);
        }

        @Override
        protected void onPostExecute(Boolean loginSuccessful) {
            if (loginSuccessful) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(getString(R.string.prompt_username), username);
                editor.putString(getString(R.string.prompt_password), password);
                editor.apply();

                goToDishActivity();
            }
            progressDialog.dismiss();
        }
    }
}
