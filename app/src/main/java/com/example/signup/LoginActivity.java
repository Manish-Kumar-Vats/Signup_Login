package com.example.signup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {

    private EditText editTextEmail, editTextPassword;
    TextView errorEmail, errorPassword, errorCapchaTV;
    boolean emailBoolean = false, capchaBoolean = false;
    Button loginButton;
    CheckBox capchaCheckBox;
    GoogleApiClient googleApiClient;
    String siteKey = "6LfspcMUAAAAAOjrC5a4Z7Vpw8Ne4pqbbF_ijQTN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.emailET);
        editTextPassword = findViewById(R.id.passwordET);
        errorEmail = findViewById(R.id.errorEmailTV);
        errorPassword = findViewById(R.id.errorPasswordTV);
        errorCapchaTV = findViewById(R.id.errorCapchaTV);

        loginButton = findViewById(R.id.loginButton);
        capchaCheckBox = findViewById(R.id.capchaCheckBox);
        editTextEmail.addTextChangedListener(emailTW);
        //    editTextPassword.addTextChangedListener(passwordTW);


        googleApiClient = new GoogleApiClient.Builder(this).addApi(SafetyNet.API).addConnectionCallbacks(LoginActivity.this).build();
        googleApiClient.connect();
        capchaCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (capchaCheckBox.isChecked()) {
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient, siteKey).setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                        @Override
                        public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                            Status status = recaptchaTokenResult.getStatus();
                            if ((status != null) && (status.isSuccess())) {
                                Toast.makeText(LoginActivity.this, "Succefully VERIFFIED", Toast.LENGTH_SHORT).show();

                                capchaCheckBox.setTextColor(Color.BLUE);
                                capchaBoolean = true;
                            }
                        }
                    });
                } else {
                    capchaCheckBox.setTextColor(Color.BLACK);
                }
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailBoolean && isCapchaBoolean()) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });


    }

    private boolean isCapchaBoolean() {
        if (capchaBoolean) {
            errorCapchaTV.setVisibility(View.INVISIBLE);
            return capchaBoolean;
        } else {
            errorCapchaTV.setText("Capcha!!");
            errorCapchaTV.setVisibility(View.VISIBLE);
            return capchaBoolean;
        }
    }

    private TextWatcher emailTW = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


            String email = editTextEmail.getText().toString();
            if (email.isEmpty()) {
                errorEmail.setVisibility(View.VISIBLE);
                errorEmail.setText("Email is required!!");


            } else if (Pattern.compile("^[a-zA-Z0-9]+[.!#$%&'*+/=?^_`{|}~-]*[a-zA-Z0-9]*@[a-zA-Z0-9]+.[a-zA-Z0-9.]+[a-zA-Z0-9]+").matcher(email).matches()) {
                errorEmail.setVisibility(View.INVISIBLE);
                emailBoolean = true;

            } else {
                errorEmail.setVisibility(View.VISIBLE);
                errorEmail.setText("Email is not valid!!");


            }

        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
//    private TextWatcher passwordTW=new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            String  password=editTextPassword.getText().toString().trim();
//            if(password.isEmpty()){
//                errorPassword.setVisibility(View.VISIBLE);
//                errorPassword.setText("Email is required!!");
//                passwordBoolean=false;
//
//            } else if(Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$").matcher(password).matches()){
//                errorPassword.setVisibility(View.INVISIBLE);
//                passwordBoolean=true;
//
//            } else {
//                errorPassword.setVisibility(View.VISIBLE);
//                errorPassword.setText("Password is not valid!!");
//                passwordBoolean=false;
//
//            }
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//
//        }
//    };
}
