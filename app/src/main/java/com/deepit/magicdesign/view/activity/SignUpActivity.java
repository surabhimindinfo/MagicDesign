package com.deepit.magicdesign.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deepit.magicdesign.R;
import com.deepit.magicdesign.adapter.CountryCodeAdapter;
import com.deepit.magicdesign.model.OnItemClick;
import com.deepit.magicdesign.network.response.CountryCodeResponse;
import com.deepit.magicdesign.viewmodel.CountryCodeViewModel;

import static com.deepit.magicdesign.Constant.GUEST;
import static com.deepit.magicdesign.Constant.LOGIN_TYPE;

public class SignUpActivity extends AppCompatActivity implements OnItemClick {

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
                }
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
                    signUpbutton.setVisibility(View.VISIBLE);
                    codeList.setVisibility(View.GONE);
                } else {
                    signUpbutton.setVisibility(View.GONE);

                    codeList.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void openLoginPage(View view) {

        EditText et_phone = findViewById(R.id.et_phone);
        EditText et_name = findViewById(R.id.editTextTextPersonName);
        String mobile = et_phone.getText().toString();
        String name = et_name.getText().toString();


        registerUser(mobile, country_id, device_id);

    }

    private void registerUser(String mobile, String country_id, String device_id) {

        System.out.println(" ------- mobile ----- " + mobile);
        System.out.println(" ------- country_id ----- " + country_id);
        System.out.println(" ------- device_id ----- " + device_id);

//        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);
//
//        Call<RegisterResponse> call = apiInterface.register(mobile, country_id, "android", device_id);
//
//        call.enqueue(new Callback<RegisterResponse>() {
//            @Override
//            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
//
//                RegisterResponse registerResponse = response.body();
//
//                assert registerResponse != null;
//                if (registerResponse.getStatus() == 1) {
//
//                    Toast.makeText(SignUpActivity.this, registerResponse.getMessage(), Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(SignUpActivity.this, OtpActivity.class));
//                    finish();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<RegisterResponse> call, Throwable t) {
//
//            }
//        });


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
}