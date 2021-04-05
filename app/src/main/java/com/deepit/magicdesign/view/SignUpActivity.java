package com.deepit.magicdesign.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.deepit.magicdesign.R;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void openLoginPage(View view) {
        startActivity(new Intent(SignUpActivity.this, OtpActivity.class));
        finish();
    }
}