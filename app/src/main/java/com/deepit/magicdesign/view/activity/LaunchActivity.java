package com.deepit.magicdesign.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;

import com.deepit.magicdesign.R;

import static com.deepit.magicdesign.Constant.LOGIN_TYPE;
import static com.deepit.magicdesign.Constant.USER;
import static com.deepit.magicdesign.network.MySharedPref.NullData;
import static com.deepit.magicdesign.network.MySharedPref.getData;

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
        long SPLASH_DELAY = 2000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isNetworkAvailable(LaunchActivity.this)) {

                    String userType = getData(LaunchActivity.this,LOGIN_TYPE,null);
                    System.out.println("---- login type ----- " + userType);
                    if (userType==null)
                    startActivity(new Intent(LaunchActivity.this, SignUpActivity.class));
                    else {
                        startActivity(new Intent(LaunchActivity.this, MainActivity.class)
                                .putExtra(LOGIN_TYPE, USER));
                     }
                    finish();
                } else
                    showSnackBar(mainLayout,"Connection Lost");

            }
        }, SPLASH_DELAY);
    }

}