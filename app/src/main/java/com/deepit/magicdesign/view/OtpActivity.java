package com.deepit.magicdesign.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.deepit.magicdesign.R;

public class OtpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
    }

    public void openMainPage(View view) {
        startActivity(new Intent(OtpActivity.this, EditAccountDetailActivity.class));
        finish();
    }
}