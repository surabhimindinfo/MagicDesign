package com.deepit.magicdesign.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.deepit.magicdesign.Constant;
import com.deepit.magicdesign.R;
import com.deepit.magicdesign.model.File;
import com.deepit.magicdesign.model.UserRecord;
import com.deepit.magicdesign.network.response.FormatPrefResponse;
import com.deepit.magicdesign.view.fragment.AccountFragment;
import com.deepit.magicdesign.view.fragment.FileFormatFragment;
import com.deepit.magicdesign.view.fragment.FragmentHome;
import com.deepit.magicdesign.viewmodel.SubCatViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.deepit.magicdesign.Constant.LOGIN_TYPE;
import static com.deepit.magicdesign.Constant.TITLE;
import static com.deepit.magicdesign.Constant.USER;
import static com.deepit.magicdesign.network.MySharedPref.NullData;
import static com.deepit.magicdesign.network.MySharedPref.getData;
import static com.deepit.magicdesign.network.MySharedPref.getUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    public ImageView btn_menu;
    public TextView tvHead;
    private DrawerLayout dLayout;
    private TextView nameTV, numberTV;
    private NavigationView navigationView;
    private UserRecord userRecord;
    private SubCatViewModel viewModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(SubCatViewModel.class);
        viewModel.init();
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
        btn_menu = findViewById(R.id.btn_menu);
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
            case R.id.file_format:
                if (dLayout.isDrawerVisible(GravityCompat.START))
                    dLayout.closeDrawer(GravityCompat.START);
                FileFormatFragment fragmentFormat = new FileFormatFragment();
                openFragment(fragmentFormat, userRecord, R.string.my_file_formats);

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

    public void downLoadImage(List<File> files, String item_id) {


        for (int i = 0; i < files.size(); i++) {

            File file = files.get(i);

            String downLoadURl = file.getFile();
            runDownloadVM(downLoadURl);
            CheckDownLoadAvailabitlity(item_id);
        }
    }

    private void runDownloadVM(final String file) {
        viewModel.downloadResponseLiveData().observe(this, new Observer<FormatPrefResponse>() {
            @Override
            public void onChanged(FormatPrefResponse itemResponse) {
                if (itemResponse != null) {
                    System.out.println("------ filter----");
                    if (itemResponse.getStatus() == 1) {
                        downLoadFile(file);
                    } else
                        Toast.makeText(MainActivity.this, itemResponse.getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    private void downLoadFile(String file) {

        System.out.println("--- downLoad FIle --- " + file);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);
            Toast.makeText(MainActivity.this, "Need Permission to access storage for Downloading Image", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Downloading Image...", Toast.LENGTH_LONG).show();
            //Asynctask to create a thread to downlaod image in the background
            new DownloadsImage().execute(file);
        }
    }

    private void CheckDownLoadAvailabitlity(String item_id) {
        viewModel.checkDownload(userRecord.getUserId(), item_id);
    }

    @SuppressLint("StaticFieldLeak")
    private class DownloadsImage extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            URL url = null;
            try {
                url = new URL(strings[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Bitmap bm = null;
            try {
                bm = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Create Path to save Image
            java.io.File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/Magic Design"); //Creates app specific folder

            if (!path.exists()) {
                path.mkdirs();
            }

            java.io.File imageFile = new java.io.File(path, System.currentTimeMillis() + ".png"); // Imagename.png
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(imageFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                bm.compress(Bitmap.CompressFormat.PNG, 100, out); // Compress Image
                out.flush();
                out.close();
                // Tell the media scanner about the new file so that it is
                // immediately available to the user.
                MediaScannerConnection.scanFile(MainActivity.this, new String[]{imageFile.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        // Log.i("ExternalStorage", "Scanned " + path + ":");
                        //    Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(MainActivity.this, "Image downloaded successfully", Toast.LENGTH_LONG).show();

        }
    }
}