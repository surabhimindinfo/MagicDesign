package com.deepit.magicdesign.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.deepit.magicdesign.R;

public class EditAccountDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account_detail);
    }

    public void openMainPage(View view) {
        startActivity(new Intent(EditAccountDetailActivity.this, MainActivity.class));
        finish();
    }
}