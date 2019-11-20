package com.example.signup;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    String firstName, lastName, phone, gender, email, countryCode;
    TextView FirstNameTV, LastNameTV, PhoneTV, GenderTV, emailTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirstNameTV = findViewById(R.id.FirstName);
        LastNameTV = findViewById(R.id.LastName);
        PhoneTV = findViewById(R.id.Phone);
        GenderTV = findViewById(R.id.Gender);
        emailTV = findViewById(R.id.email);


        firstName = getIntent().getStringExtra("FirstNameData");
        lastName = getIntent().getStringExtra("LastNameData");
        phone = getIntent().getStringExtra("PhoneData");
        gender = getIntent().getStringExtra("Gender");
        email = getIntent().getStringExtra("EmailData");
        countryCode = getIntent().getStringExtra("countryCode");


        FirstNameTV.setText(firstName);
        LastNameTV.setText(lastName);
        GenderTV.setText(gender);
        PhoneTV.setText(countryCode + " " + phone);
        emailTV.setText(email);
    }
}
