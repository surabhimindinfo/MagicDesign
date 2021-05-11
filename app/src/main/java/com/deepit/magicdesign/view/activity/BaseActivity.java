package com.deepit.magicdesign.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.deepit.magicdesign.network.ApiController;
import com.deepit.magicdesign.network.ApiInterface;
import com.deepit.magicdesign.network.response.RegisterResponse;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.deepit.magicdesign.Constant.REGISTER;

public class BaseActivity extends AppCompatActivity {


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showSnackBar(final View mainLayout, final String msg) {

        Snackbar snackbar = Snackbar
                .make(mainLayout, msg, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    public void registerUser(final String name, final String mobile, final String country_id, final String device_id,
                             final Context context) {


        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        ApiInterface apiInterface = ApiController.createService(ApiInterface.class);

        Call<RegisterResponse> call = apiInterface.register(mobile, country_id, "android", device_id);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                RegisterResponse registerResponse = response.body();

                assert registerResponse != null;
                if (registerResponse.getStatus() == 1) {

                    System.out.println("----- otp is ---- " + registerResponse.getRecord().getOtp());

                    if (!(context instanceof OtpActivity)) {
                        startActivity(new Intent(context, OtpActivity.class)
                                .putExtra("mobile", mobile)
                                .putExtra("country", country_id)
                                .putExtra("name", name)
                                .putExtra("type", REGISTER)
                                .putExtra("device_id", device_id)
                        );
                    }
                }
                Toast.makeText(context, registerResponse.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.cancel();
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                progressDialog.cancel();
            }
        });


    }


}