package com.deepit.magicdesign.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.deepit.magicdesign.R;
import com.deepit.magicdesign.model.UserRecord;
import com.deepit.magicdesign.view.fragment.FragmentHome;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public TextView tvHead;
    private DrawerLayout dLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        FragmentHome fragmentHome = new FragmentHome();
        openFragment(fragmentHome);

        UserRecord userRecord = UserRecord.getInstance();
        System.out.println("-------- user name ---- " + userRecord.getName());
        System.out.println("-------- country name ---- " + userRecord.getCountryName());

    }


    public void openFragment(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit(); // save the changes
    }

    private void initView() {
        ImageView btn_menu = findViewById(R.id.btn_menu);
        dLayout = findViewById(R.id.dLayout);
        tvHead = findViewById(R.id.tvHead);
        btn_menu.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_menu: {

                dLayout.openDrawer(GravityCompat.START);


            }

        }


    }
}