package com.deepit.magicdesign.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.deepit.magicdesign.R;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void openMainPage(View view) {
        startActivity(new Intent(LoginActivity.this, OtpActivity.class));
        finish();
    }
}