package com.deepit.magicdesign.view.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.deepit.magicdesign.R;
import com.deepit.magicdesign.adapter.CountryCodeAdapter;
import com.deepit.magicdesign.model.CountryRecord;
import com.deepit.magicdesign.model.OnItemClick;
import com.deepit.magicdesign.model.UserRecord;
import com.deepit.magicdesign.network.ApiController;
import com.deepit.magicdesign.network.ApiInterface;
import com.deepit.magicdesign.network.response.CountryCodeResponse;
import com.deepit.magicdesign.network.response.VerifyResponse;
import com.deepit.magicdesign.viewmodel.CountryCodeViewModel;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.deepit.magicdesign.Constant.GUEST;
import static com.deepit.magicdesign.Constant.LOGIN;
import static com.deepit.magicdesign.Constant.LOGIN_TYPE;
import static com.deepit.magicdesign.Constant.USER;
import static com.deepit.magicdesign.network.MySharedPref.saveData;
import static com.deepit.magicdesign.network.MySharedPref.saveUser;

public class LoginActivity extends BaseActivity implements OnItemClick {


    public TextView countryCodeTV;
    public EditText et_phone;
    public RecyclerView codeList;
    public LinearLayout listLayout;
    public Button login;
    private String device_id;
    private CountryCodeViewModel viewModel;
    private CountryCodeAdapter codeAdapter;
    private String country_id = "";
    private boolean isListOpen = false;
    private List<CountryRecord> results=new ArrayList<>();

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        codeAdapter = new CountryCodeAdapter(LoginActivity.this, this);
        device_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        viewModel = new ViewModelProvider(this).get(CountryCodeViewModel.class);
        viewModel.init();
        viewModel.getCountryCodeResponseLiveData().observe(this, new Observer<CountryCodeResponse>() {
            @Override
            public void onChanged(CountryCodeResponse response) {
                if (response != null) {
                    System.out.println("----- country code response ---- " + response.getCountryRecord().size());
                    results=response.getCountryRecord();
                    codeAdapter.setResults(response.getCountryRecord());
                } else
                    Toast.makeText(LoginActivity.this, "Server not responding, Try again", Toast.LENGTH_LONG).show();

            }
        });

        init();
    }

    void filter(String text){
        List<CountryRecord> temp = new ArrayList();
        for(CountryRecord d:results){

            if(d.getName().toLowerCase().contains(text)){
                temp.add(d);
            }
        }
        //update recyclerview
        codeAdapter.setResults(temp);


    }
    private void init() {

        login = findViewById(R.id.login);
        codeList = findViewById(R.id.codeList);
        listLayout = findViewById(R.id.listLayout);
        et_phone = findViewById(R.id.et_phone);
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
                    login.setVisibility(View.VISIBLE);
                     listLayout.setVisibility(View.GONE);
                } else {

                    isListOpen = true;
                    login.setVisibility(View.GONE);
                     listLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        EditText searchET = findViewById(R.id.searchET);
        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                System.out.println("-- entered string --- " + s.toString());
                // filter your list from your input
                filter(s.toString());
                //you can use runnable postDelayed like 500 ms to delay search text
            }
        });
    }

    public void openMainPage(View view) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class)
                .putExtra(LOGIN_TYPE, GUEST));
        finish();
    }

    public void loginUser(View view) {
        et_phone = findViewById(R.id.et_phone);
        TextView select_country_code = findViewById(R.id.countryCodeTV);
        String mobile = et_phone.getText().toString();

        String countryCode = select_country_code.getText().toString();

        if (countryCode.equalsIgnoreCase(getString(R.string.select_country_code)) || country_id.length() == 0) {
            Toast.makeText(LoginActivity.this, "All fields are mandatory", Toast.LENGTH_LONG).show();
        } else
            loginUseronServer(mobile, country_id, device_id);
    }

    private void loginUseronServer(final String mobile, final String country_id,
                                   final String device_id) {

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);

        Call<VerifyResponse> call = apiInterface.login(mobile, country_id, "android", device_id);

        call.enqueue(new Callback<VerifyResponse>() {
            @Override
            public void onResponse(Call<VerifyResponse> call, Response<VerifyResponse> response) {

                VerifyResponse registerResponse = response.body();
                 assert registerResponse != null;
                if (registerResponse.getStatus() == 1) {
                    UserRecord.setUserRecord( registerResponse.getRecord());
                    saveData(getApplicationContext(), LOGIN_TYPE,USER);
                    saveUser(getApplicationContext(), USER,registerResponse.getRecord());

                    startActivity(new Intent(LoginActivity.this, MainActivity.class)
                            .putExtra(LOGIN_TYPE, USER));
                    finish();

                } else if (registerResponse.getStatus() == 3) {
                    startActivity(new Intent(LoginActivity.this, OtpActivity.class)
                            .putExtra("mobile", mobile)
                            .putExtra("country", country_id)
                            .putExtra("type", LOGIN)
                            .putExtra("device_id", device_id)
                            .putExtra("userID", registerResponse.getUser_id())
                    );
                }
                Toast.makeText(LoginActivity.this, registerResponse.getMessage(), Toast.LENGTH_LONG).show();

                progressDialog.cancel();
                System.out.println(" ------- login status ----- " + registerResponse.getStatus());
                System.out.println(" ------- login message ----- " + registerResponse.getMessage());

            }

            @Override
            public void onFailure(Call<VerifyResponse> call, Throwable t) {
                progressDialog.cancel();
                t.printStackTrace();
            }
        });


    }

    @Override
    public void onListItemClick(String data) {
        country_id = data;
    }

    @Override
    public void onBackPressed() {
        if (isListOpen) {
            isListOpen=false;
            login.setVisibility(View.VISIBLE);
            listLayout.setVisibility(View.GONE);
        }
        else {

            super.onBackPressed();
        }
    }
}