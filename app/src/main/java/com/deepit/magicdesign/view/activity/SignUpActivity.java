package com.deepit.magicdesign.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.deepit.magicdesign.R;
import com.deepit.magicdesign.adapter.CountryCodeAdapter;
import com.deepit.magicdesign.model.OnItemClick;
import com.deepit.magicdesign.network.response.CountryCodeResponse;
import com.deepit.magicdesign.viewmodel.CountryCodeViewModel;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.deepit.magicdesign.Constant.GUEST;
import static com.deepit.magicdesign.Constant.LOGIN_TYPE;

public class SignUpActivity extends BaseActivity implements OnItemClick {

    public RecyclerView codeList;
    public Button signUpbutton;
    public TextView countryCodeTV;
    private String device_id;
    private CountryCodeViewModel viewModel;
    private CountryCodeAdapter codeAdapter;
    private String country_id = "";
    private boolean isListOpen = false;

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        codeAdapter = new CountryCodeAdapter(SignUpActivity.this, this);
        device_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        viewModel = new ViewModelProvider(this).get(CountryCodeViewModel.class);
        viewModel.init();
        viewModel.getCountryCodeResponseLiveData().observe(this, new Observer<CountryCodeResponse>() {
            @Override
            public void onChanged(CountryCodeResponse response) {
                if (response != null) {
                    System.out.println("----- country code response ---- " + response.getCountryRecord().size());
                    codeAdapter.setResults(response.getCountryRecord());
                } else
                    Toast.makeText(SignUpActivity.this, "Server not responding, Try again", Toast.LENGTH_LONG).show();

            }
        });
        init();
    }

    private void init() {
        signUpbutton = findViewById(R.id.signUpbutton);
        codeList = findViewById(R.id.codeList);
        countryCodeTV = findViewById(R.id.countryCodeTV);
        codeList.setHasFixedSize(true);
        codeList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        codeList.setAdapter(codeAdapter);

        viewModel.getCountryCode();
        countryCodeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isListOpen) {
                    isListOpen = false;
                    signUpbutton.setVisibility(View.VISIBLE);
                    codeList.setVisibility(View.GONE);
                } else {
                    signUpbutton.setVisibility(View.GONE);
                    isListOpen = true;
                    codeList.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void openLoginPage(View view) {
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class)
                .putExtra(LOGIN_TYPE, GUEST));

    }

    @Override
    public void onBackPressed() {

        if (isListOpen)
            codeList.setVisibility(View.GONE);
        else
            super.onBackPressed();
    }

    public void openMainPage(View view) {
        startActivity(new Intent(SignUpActivity.this, MainActivity.class)
                .putExtra(LOGIN_TYPE, GUEST));
        finish();
    }

    @Override
    public void onListItemClick(String data) {

        country_id = data;
    }

    public void clickSignUp(View view) {

        EditText et_phone = findViewById(R.id.et_phone);
        EditText et_name = findViewById(R.id.editTextTextPersonName);
        TextView select_country_code = findViewById(R.id.countryCodeTV);
        String mobile = et_phone.getText().toString();
        String name = et_name.getText().toString();
        String countryCode = select_country_code.getText().toString();

        if (name.length() == 0 || et_name.length() == 0 || countryCode.equalsIgnoreCase(getString(R.string.select_country_code)) || country_id.length() == 0) {
            Toast.makeText(SignUpActivity.this, "All fields are mandatory", Toast.LENGTH_LONG).show();
        } else
            registerUser(name, mobile, country_id, device_id, SignUpActivity.this);
    }
}