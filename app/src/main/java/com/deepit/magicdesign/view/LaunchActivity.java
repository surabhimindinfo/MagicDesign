package com.deepit.magicdesign.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;

import com.deepit.magicdesign.R;

public class LaunchActivity extends BaseActivity {

    RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        mainLayout = findViewById(R.id.mainLayout);
        proceedToTheNextActivity();
    }

    private void proceedToTheNextActivity() {

        Handler handler = new Handler();
        long SPLASH_DELAY = 4000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                if (isNetworkAvailable(LaunchActivity.this)) {

                    startActivity(new Intent(LaunchActivity.this, SignUpActivity.class));
                    finish();


                } else
                    showSnackBar(mainLayout);

            }
        }, SPLASH_DELAY);
    }

}