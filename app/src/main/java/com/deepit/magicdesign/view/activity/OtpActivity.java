package com.deepit.magicdesign.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.deepit.magicdesign.R;
import com.deepit.magicdesign.network.ApiController;
import com.deepit.magicdesign.network.ApiInterface;
import com.deepit.magicdesign.network.response.VerifyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.deepit.magicdesign.Constant.USER;

public class OtpActivity extends BaseActivity {

    private EditText et_otp;
    private String mobile, name, countryId, deviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        et_otp = findViewById(R.id.et_otp);
        TextView resendTv = findViewById(R.id.resendTV);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            mobile = bundle.getString("mobile", "");
            name = bundle.getString("name", "");
            countryId = bundle.getString("country", "");
            deviceId = bundle.getString("device_id", "");


        }

        resendTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser(mobile, name, countryId, deviceId, OtpActivity.this);

            }
        });
    }

    public void openMainPage(View view) {


        String strOtp = et_otp.getText().toString();
        if (strOtp.length() == 0) {
            Toast.makeText(OtpActivity.this, "Enter OTP", Toast.LENGTH_LONG).show();

        } else {
            verifyOtp(mobile, name, countryId, deviceId, strOtp);

        }
    }

    private void verifyOtp(String mobile, String name, String countryId, String deviceId, String strOtp) {

        System.out.println(" ------- mobile ----- " + mobile);
        System.out.println(" ------- country_id ----- " + countryId);
        System.out.println(" ------- device_id ----- " + deviceId);
        final ProgressDialog progressDialog = new ProgressDialog(OtpActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);

        Call<VerifyResponse> call = apiInterface.verifyOTP(name, strOtp, mobile, countryId, "android", deviceId);

        call.enqueue(new Callback<VerifyResponse>() {
            @Override
            public void onResponse(Call<VerifyResponse> call, Response<VerifyResponse> response) {

                VerifyResponse registerResponse = response.body();
                progressDialog.cancel();

                assert registerResponse != null;
                if (registerResponse.getStatus() == 1) {


                    startActivity(new Intent(OtpActivity.this, EditAccountDetailActivity.class)
                            .putExtra(USER, registerResponse.getRecord()));
                    finish();

                }

                Toast.makeText(OtpActivity.this, registerResponse.getMessage(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<VerifyResponse> call, Throwable t) {
                progressDialog.cancel();
            }
        });


    }

    public void openMainPae(View view) {

    }
}