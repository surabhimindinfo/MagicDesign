package com.deepit.magicdesign.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.deepit.magicdesign.R;
import com.deepit.magicdesign.model.UserRecord;
import com.deepit.magicdesign.network.ApiController;
import com.deepit.magicdesign.network.ApiInterface;
import com.deepit.magicdesign.network.response.VerifyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.deepit.magicdesign.Constant.GUEST;
import static com.deepit.magicdesign.Constant.LOGIN_TYPE;
import static com.deepit.magicdesign.Constant.USER;

public class EditAccountDetailActivity extends BaseActivity {

    private EditText etname, etMobile, etEmail, etCountry, etState, etCity, etArea;
    private LinearLayout mainLayout;
    private UserRecord userRecord = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account_detail);

        init();
    }

    private void init() {

        mainLayout = findViewById(R.id.mainLayout);
        etname = findViewById(R.id.etName);
        etMobile = findViewById(R.id.etMobile);
        etEmail = findViewById(R.id.etEmail);
        etCountry = findViewById(R.id.etCountry);
        etCity = findViewById(R.id.etCity);
        etState = findViewById(R.id.etState);
        etArea = findViewById(R.id.etArea);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            userRecord = (UserRecord) bundle.get(USER);
            if (userRecord != null) {
                etname.setText(userRecord.getName());
                etMobile.setText(userRecord.getMobile());
                etCountry.setText(userRecord.getCountry_name());
            }
        }
    }

    public void openMainPage(View view) {
        startActivity(new Intent(EditAccountDetailActivity.this, MainActivity.class)
                .putExtra(LOGIN_TYPE, GUEST));
        finish();

    }

    private void updateProfile(String name, String email,
                               String city, String state, String area) {


        {


            final ProgressDialog progressDialog = new ProgressDialog(EditAccountDetailActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Please Wait");
            progressDialog.show();

            ApiInterface apiInterface = ApiController.createService(ApiInterface.class);

            Call<VerifyResponse> call = apiInterface.updateProfile(userRecord.getUserId(), name, email,
                    userRecord.getCountryId(), state, city, area);

            call.enqueue(new Callback<VerifyResponse>() {
                @Override
                public void onResponse(Call<VerifyResponse> call, Response<VerifyResponse> response) {

                    VerifyResponse registerResponse = response.body();

                    assert registerResponse != null;
                    if (registerResponse.getStatus() == 1) {

                        startActivity(new Intent(EditAccountDetailActivity.this, MainActivity.class));
                        finish();
                    }

                    Toast.makeText(EditAccountDetailActivity.this, registerResponse.getMessage(), Toast.LENGTH_LONG).show();

                    progressDialog.cancel();

                }

                @Override
                public void onFailure(Call<VerifyResponse> call, Throwable t) {
                    progressDialog.cancel();
                }
            });


        }
    }

    public void upDateProfile(View view) {
        String name = etname.getText().toString();
        String email = etEmail.getText().toString();
        String city = etCity.getText().toString();
        String state = etState.getText().toString();
        String area = etArea.getText().toString();

        if (name.length() == 0 || email.length() == 0 || city.length() == 0 || state.length() == 0 || area.length() == 0) {
            showSnackBar(mainLayout, "All fields are mandatory");
        } else
            updateProfile(name, email, city, state, area);

    }
}