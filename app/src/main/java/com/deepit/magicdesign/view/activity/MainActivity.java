package com.deepit.magicdesign.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.deepit.magicdesign.Constant;
import com.deepit.magicdesign.R;
import com.deepit.magicdesign.model.UserRecord;
import com.deepit.magicdesign.view.fragment.AccountFragment;
import com.deepit.magicdesign.view.fragment.CategoryItemFragment;
import com.deepit.magicdesign.view.fragment.FragmentHome;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import static com.deepit.magicdesign.Constant.ACCOUNT_DETAIL;
import static com.deepit.magicdesign.Constant.LOGIN_TYPE;
import static com.deepit.magicdesign.Constant.TITLE;
import static com.deepit.magicdesign.Constant.USER;
import static com.deepit.magicdesign.network.MySharedPref.NullData;
import static com.deepit.magicdesign.network.MySharedPref.getData;
import static com.deepit.magicdesign.network.MySharedPref.getUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {


    public TextView tvHead;
    private DrawerLayout dLayout;
    private TextView nameTV, numberTV;
    private NavigationView navigationView;
    private UserRecord userRecord;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        FragmentHome fragmentHome = new FragmentHome();
        openFragment(fragmentHome, null, 0);

        String login_type = getData(MainActivity.this, LOGIN_TYPE, null);

        if (login_type != null && login_type.equalsIgnoreCase(USER)) {
            userRecord = getUser(MainActivity.this, USER, null);
            Gson gson = new Gson();
            String json = gson.toJson(userRecord);
            System.out.println(" ---- user ---- " + json);
            if (userRecord != null) {
                System.out.println("--- user available---");

                nameTV.setText(userRecord.getName());
                numberTV.setText("(" + userRecord.getPhoneCode() + ")" + userRecord.getMobile());
                navigationView.getMenu().clear(); //clear old inflated items.
                navigationView.inflateMenu(R.menu.navigation_menu_loggedin);
            }
        }
    }

    public void sendWhatsapp(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Install MAgic Design");
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        } else
            Toast.makeText(MainActivity.this, "Whatsapp app not installed in your phone", Toast.LENGTH_SHORT).show();


    }

    public void openFragment(Fragment fragment, Object data, int tag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        if (data != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(TITLE, (Parcelable) data);
            bundle.putInt(Constant.TAG, tag);
            fragment.setArguments(bundle);
            fragmentTransaction.addToBackStack(fragment.toString());

        }

        fragmentTransaction.replace(R.id.content_frame, fragment);

        fragmentTransaction.commit(); // save the changes
    }

    private void initView() {
        ImageView btn_menu = findViewById(R.id.btn_menu);
        ImageView btn_home = findViewById(R.id.btn_home);
        dLayout = findViewById(R.id.dLayout);
        tvHead = findViewById(R.id.tvHead);
        navigationView = findViewById(R.id.nav_view);
        btn_menu.setOnClickListener(this);
        btn_home.setOnClickListener(this);

        nameTV = navigationView.getHeaderView(0).findViewById(R.id.nameTV);
        numberTV = navigationView.getHeaderView(0).findViewById(R.id.numberTV);

        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_menu) {
            if (dLayout.isDrawerVisible(GravityCompat.START))
                dLayout.closeDrawer(GravityCompat.START);
            else
                dLayout.openDrawer(GravityCompat.START);
        } else if (v.getId() == R.id.btn_home)
            openHome();


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout:
                NullData(getApplicationContext(), LOGIN_TYPE);
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finishAffinity();
                break;

            case R.id.login:

                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finishAffinity();
                break;

            case R.id.home:
                openHome();
                break;
            case R.id.profile:
                if (dLayout.isDrawerVisible(GravityCompat.START))
                    dLayout.closeDrawer(GravityCompat.START);
                AccountFragment fragmentDesignCat = new AccountFragment();
               openFragment(fragmentDesignCat, userRecord, R.string.my_account);

                break;
        }
        return true;
    }

    private void openHome() {
        System.out.println("---- current fragment is --" + getCurrentFragment());

        if (getCurrentFragment().equalsIgnoreCase(FragmentHome.class.getSimpleName())) {
            if (dLayout.isDrawerVisible(GravityCompat.START))
                dLayout.closeDrawer(GravityCompat.START);
        } else {
            dLayout.closeDrawer(GravityCompat.START);
            FragmentHome fragmentHome = new FragmentHome();
            openFragment(fragmentHome, null, R.string.home);
        }
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    public String getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.content_frame).getClass().getSimpleName();
    }

}