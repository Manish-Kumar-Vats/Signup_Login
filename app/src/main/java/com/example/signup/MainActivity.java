package com.example.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hbb20.CountryCodePicker;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    CountryCodePicker countryCodeCCP;
    CheckBox showPasswordCB, checkBoxTermsAndConditions;
    EditText firstNameET, lastNameET, phoneET, emailET, passwordET, confirmPasswordET;
    TextView errorFirstNameTV, errorLastNameTV, errorPhoneTV, errorEmailTV, errorPasswordTV, errorConfirmPasswordTV;
    RadioButton radioButtonMale, radioButtonFemale, radioButtonOther;
    Button signupButton, loginPageButton;
    String firstName, lastName, phone, email, password, confirmPassword, gender, countryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//edit text view
        firstNameET = findViewById(R.id.firstNameET);
        lastNameET = findViewById(R.id.lastNameET);
        phoneET = findViewById(R.id.phoneET);
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        confirmPasswordET = findViewById(R.id.confirmPasswordET);
//other view like radio btn , checkbox etc
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        radioButtonOther = findViewById(R.id.radioButtonOther);
        //  showPasswordCB=findViewById(R.id.checkbox);
        checkBoxTermsAndConditions = findViewById(R.id.checkBoxTermsAndCondition);
        countryCodeCCP = findViewById(R.id.countryCodeCCP);
        signupButton = findViewById(R.id.signupButton);
        loginPageButton = findViewById(R.id.loginpage);
//error text view
        errorFirstNameTV = findViewById(R.id.errorFirstNameTV);
        errorLastNameTV = findViewById(R.id.errorLastNameTV);
        errorPhoneTV = findViewById(R.id.errorPhoneTV);
        errorEmailTV = findViewById(R.id.errorEmailTV);
        errorPasswordTV = findViewById(R.id.errorPasswordTV);
        errorConfirmPasswordTV = findViewById(R.id.errorConfirmPasswordTV);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValidFirstName() && isValidLastName() && checkingGender() && isValidPhone() && isValidEmail() && isValidPassword() && isValidConfirmPassword() && isCheckBoxChecked()) {

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("FirstNameData", firstName);
                    intent.putExtra("LastNameData", lastName);
                    intent.putExtra("CountryCode", countryCode);
                    intent.putExtra("PhoneData", phone);
                    intent.putExtra("EmailData", email);
                    intent.putExtra("Gender", gender);

                    startActivity(intent);
                }
            }
        });

        loginPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }


    boolean checkingGender() {
        if (radioButtonMale.isChecked()) {
            gender = "Male";
            return true;
        } else if (radioButtonFemale.isChecked()) {
            gender = "Female";
            return true;
        } else {
            gender = "Other";
            return true;
        }


    }

    boolean isValidFirstName() {

        firstName = firstNameET.getText().toString();
        if (firstName.length() == 0) {
            errorFirstNameTV.setVisibility(View.VISIBLE);
            errorFirstNameTV.setText("Name Can't be empty");
            return false;
        } else if (firstName.length() < 2) {
            errorFirstNameTV.setVisibility(View.VISIBLE);
            errorFirstNameTV.setText("Must contain 2 or more charaters");
            return false;
        } else {
            errorFirstNameTV.setVisibility(View.INVISIBLE);
            return true;  //Pattern.compile("[a-zA-Z]{2,}").matcher(firstName).matches();
        }
    }

    boolean isValidLastName() {
        lastName = lastNameET.getText().toString();
        if (lastName.length() == 0) {
            errorLastNameTV.setVisibility(View.VISIBLE);
            errorLastNameTV.setText("Name Can't be empty");
            return false;
        } else if (lastName.length() < 2) {
            errorLastNameTV.setVisibility(View.VISIBLE);
            errorLastNameTV.setText("Must contain 2 or more charaters");
            return false;
        } else {
            errorLastNameTV.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    boolean isValidPhone() {
        if (countryCodeCCP.getSelectedCountryCode().equals(null)) {
            countryCode = countryCodeCCP.getDefaultCountryCodeWithPlus();
        } else {
            countryCode = countryCodeCCP.getSelectedCountryCode();
        }
        phone = phoneET.getText().toString();
        if (phone.isEmpty()) {
            errorPhoneTV.setVisibility(View.VISIBLE);
            errorPhoneTV.setText("Is empty");
            return false;
        } else if (phone.length() > 7 && phone.length() < 13) {
            errorPhoneTV.setVisibility(View.INVISIBLE);
            return true;
        } else {
            errorPhoneTV.setVisibility(View.VISIBLE);
            errorPhoneTV.setText("Number must in btw 8-12");
            return false;

        }
    }

    boolean isValidEmail() {
        email = emailET.getText().toString();
        if (email.isEmpty()) {
            errorEmailTV.setVisibility(View.VISIBLE);
            errorEmailTV.setText("Email is required!!");
            return false;
        } else if (Pattern.compile("^[a-zA-Z0-9]+[.!#$%&'*+/=?^_`{|}~-]*[a-zA-Z0-9]*@[a-zA-Z0-9]+.[a-zA-Z0-9.]+[a-zA-Z0-9]+").matcher(email).matches()) {
            errorEmailTV.setVisibility(View.INVISIBLE);
            return true;
        } else {
            errorEmailTV.setVisibility(View.VISIBLE);
            errorEmailTV.setText("Email is not valid!!");
            return false;
        }
    }

    boolean isValidPassword() {
        password = passwordET.getText().toString();
        String strRegEx = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$";
        if (password.isEmpty()) {
            errorPasswordTV.setVisibility(View.VISIBLE);
            errorPasswordTV.setText("Enter Password!!");
            return false;
        } else if (password.length() < 8) {
            errorPasswordTV.setVisibility(View.VISIBLE);
            errorPasswordTV.setText("Password must be greater than 8 characters!!");
            return false;
        } else if (Pattern.compile(strRegEx).matcher(password).matches()) {
            errorPasswordTV.setVisibility(View.INVISIBLE);
            return true;
        } else {
            errorPasswordTV.setVisibility(View.VISIBLE);
            errorPasswordTV.setText("must contains a Capital Letter, a digit and from @#$%^&+=");
            return false;
        }
    }

    boolean isValidConfirmPassword() {
        confirmPassword = confirmPasswordET.getText().toString();
        if (confirmPassword.length() == 0) {
            errorConfirmPasswordTV.setVisibility(View.VISIBLE);
            errorConfirmPasswordTV.setText("Enter Confirm Password!!");
            return false;
        } else if (confirmPassword.equals(password)) {
            errorConfirmPasswordTV.setVisibility(View.INVISIBLE);
            return true;
        } else {
            errorConfirmPasswordTV.setVisibility(View.VISIBLE);
            errorConfirmPasswordTV.setText("not matched!!");
            return false;
        }
    }

    boolean isCheckBoxChecked() {
        if (checkBoxTermsAndConditions.isChecked()) {
            checkBoxTermsAndConditions.setTextColor(0xFF000000);
            return true;
        } else {
            checkBoxTermsAndConditions.setTextColor(0xFFFF0000);
            checkBoxTermsAndConditions.setLinkTextColor(0xFFFF0000);
            return false;
        }
    }

//    void showPassword() {
//        showPasswordCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean value) {
//                if (value)
//                {
//                    // Show Password
//                    passwordET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                    confirmPasswordET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                }
//                else
//                {
//                    // Hide Password
//                    passwordET.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    confirmPasswordET.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                }
//            }
//        });
//    }

}
