package com.android.app.ordersdishes.fragments;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.android.app.ordersdishes.R;
import com.android.app.ordersdishes.service.ApiOrderDishesService;
import com.android.app.ordersdishes.service.impl.ApiOrderDishesImpl;
import com.gp89developers.android.commonutils.activity.dialog.ProgressDialog;
import com.gp89developers.android.commonutils.utils.CustomAsyncTask;

/**
 * Created by gabriel on 11/1/2015.
 */
public class RegisterFragment extends BaseAppFormFragment {
    private UserRegisterTask userRegisterTask;
    private SharedPreferences preferences;

    //ui elements
    private AutoCompleteTextView usernameTextView;
    private EditText passwordEditView;
    private EditText confirmationPasswordEditView;
    private Button registerButton;

    public RegisterFragment() {
        super(R.layout.fragment_register);
    }

    public static RegisterFragment create() {
        return new RegisterFragment();
    }

    @Override
    protected void initComponents(View view) {
        preferences = PreferenceManager.getDefaultSharedPreferences(getSupportActivity());

        usernameTextView = (AutoCompleteTextView) view.findViewById(R.id.username);
        passwordEditView = (EditText) view.findViewById(R.id.password);
        confirmationPasswordEditView = (EditText) view.findViewById(R.id.confirmation_password);

        inputFields.add(usernameTextView);
        inputFields.add(passwordEditView);
        inputFields.add(confirmationPasswordEditView);

        registerButton = (Button) view.findViewById(R.id.register_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    @Override
    protected boolean validFields() {
        if (!passwordEditView.getText().toString().equals(confirmationPasswordEditView.getText().toString())) {
            confirmationPasswordEditView.setError(getString(R.string.error_not_match_confirmation_password));
            return false;
        }

        return super.validFields();
    }


    // CUSTOMER METHODS
    private void register() {
        if (validFields()) {
            String username = usernameTextView.getText().toString();
            String password = passwordEditView.getText().toString();
            String confirmationPassword = confirmationPasswordEditView.getText().toString();

            userRegisterTask = new UserRegisterTask();
            userRegisterTask.execute(username, password, confirmationPassword);
        }
    }

    private class UserRegisterTask extends CustomAsyncTask<String, Void, Boolean> {
        private static final int COUNT_PARAMS = 3;
        private static final int USERNAME_INDEX_PARAM = 0;
        private static final int PASSWORD_INDEX_PARAM = 1;
        private static final int CONFIRMATION_PASSWORD_INDEX_PARAM = 2;

        private AlertDialog progressDialog;
        private ApiOrderDishesService apiOrderDishesService;

        private String username;
        private String password;
        private String confirmationPassword;

        public UserRegisterTask() {
            super(getSupportActivity());
            apiOrderDishesService = new ApiOrderDishesImpl();

            progressDialog = new ProgressDialog(getSupportActivity(), R.style.TransparentDialog);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            boolean isValidParams = (params != null && params.length == COUNT_PARAMS)
                    && (params[USERNAME_INDEX_PARAM] != null && params[PASSWORD_INDEX_PARAM] != null
                    && params[CONFIRMATION_PASSWORD_INDEX_PARAM] != null);

            if (!isValidParams)
                return false;

            username = params[USERNAME_INDEX_PARAM].trim();
            password = params[PASSWORD_INDEX_PARAM].trim();
            confirmationPassword = params[CONFIRMATION_PASSWORD_INDEX_PARAM].trim();

            //simulates a process
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return apiOrderDishesService.register(username, password, confirmationPassword);
        }

        @Override
        protected void onPostExecute(Boolean loginSuccessful) {
            if (loginSuccessful) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(getString(R.string.register_user), true);
                editor.apply();

                getSupportActivity().onBackPressed();
            }

            progressDialog.dismiss();
        }
    }
}
