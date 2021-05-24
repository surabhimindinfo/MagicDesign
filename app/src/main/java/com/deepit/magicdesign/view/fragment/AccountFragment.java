package com.deepit.magicdesign.view.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.deepit.magicdesign.R;
import com.deepit.magicdesign.model.UserRecord;
import com.deepit.magicdesign.network.ApiController;
import com.deepit.magicdesign.network.ApiInterface;
import com.deepit.magicdesign.network.response.VerifyResponse;
import com.deepit.magicdesign.view.activity.BaseActivity;
import com.deepit.magicdesign.view.activity.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.deepit.magicdesign.Constant.TITLE;

public class AccountFragment extends Fragment {
    private EditText etname;
    private EditText etEmail;
    private EditText etState;
    private EditText etCity;
    private EditText etArea;
    private LinearLayout mainLayout;
    private UserRecord userRecord = null;
    private Context context;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        System.out.println("---- context at fragment --- " + context);

    }
    private void updateProfile(String name, String email,
                               String city, String state, String area) {
            final ProgressDialog progressDialog = new ProgressDialog(context);
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

                        UserRecord.setUserRecord(registerResponse.getRecord());
                        startActivity(new Intent(context, MainActivity.class));
                     }

                    Toast.makeText(context, registerResponse.getMessage(), Toast.LENGTH_LONG).show();

                    progressDialog.cancel();

                }

                @Override
                public void onFailure(Call<VerifyResponse> call, Throwable t) {
                    progressDialog.cancel();
                }
            });

    }

    public void upDateProfile( ) {
        String name = etname.getText().toString();
        String email = etEmail.getText().toString();
        String city = etCity.getText().toString();
        String state = etState.getText().toString();
        String area = etArea.getText().toString();

        if (name.length() == 0 || email.length() == 0 || city.length() == 0 || state.length() == 0 || area.length() == 0) {
            {
                if (context instanceof BaseActivity)
                    ((BaseActivity)context).showSnackBar(mainLayout, "All fields are mandatory");
            }
        } else
            updateProfile(name, email, city, state, area);

    }
    @SuppressLint("SetTextI18n")
    private void init(View v) {

        mainLayout = v.findViewById(R.id.mainLayout);
        etname = v.findViewById(R.id.etName);
        EditText etMobile = v.findViewById(R.id.etMobile);
        etEmail = v.findViewById(R.id.etEmail);
        EditText etCountry = v.findViewById(R.id.etCountry);
        etCity = v.findViewById(R.id.etCity);
        etState = v.findViewById(R.id.etState);
        etArea = v.findViewById(R.id.etArea);
      Button submtBtn = v.findViewById(R.id.submtBtn);

      submtBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              upDateProfile();
          }
      });
        if (context instanceof MainActivity) {
            Bundle bundle = getArguments();
            if (bundle != null) {
                userRecord = (UserRecord) bundle.get(TITLE);
                 ((MainActivity) context).tvHead.setText(R.string.my_account);
                 if (userRecord != null) {
                    etname.setText(userRecord.getName());
                    etMobile.setText(userRecord.getPhoneCode()+"-"+userRecord.getMobile());
                    etCountry.setText(userRecord.getCountryName());
                    etEmail.setText(userRecord.getEmail());
                    etState.setText(userRecord.getState());
                    etCity.setText(userRecord.getCity());
                    etArea.setText(userRecord.getArea());

                 }
            }
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_account, container, false);

        init(view);
        return view;
    }



}