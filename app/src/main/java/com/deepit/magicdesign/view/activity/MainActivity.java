package com.deepit.magicdesign.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;
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

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
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

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.deepit.magicdesign.Constant.LOGIN_TYPE;
import static com.deepit.magicdesign.Constant.TITLE;
import static com.deepit.magicdesign.Constant.USER;
import static com.deepit.magicdesign.network.MySharedPref.NullData;
import static com.deepit.magicdesign.network.MySharedPref.getData;
import static com.deepit.magicdesign.network.MySharedPref.getUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "Download";
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
        AndroidNetworking.initialize(getApplicationContext());

        String login_type = getData(MainActivity.this, LOGIN_TYPE, null);

        if (login_type != null && login_type.equalsIgnoreCase(USER)) {
            userRecord = getUser(MainActivity.this, USER, null);
            Gson gson = new Gson();
            String json = gson.toJson(userRecord);
            if (userRecord != null) {
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
            runDownloadVM(file);
            CheckDownLoadAvailabitlity(item_id);
        }
    }

    private void runDownloadVM(final File file) {
        viewModel.downloadResponseLiveData().observe(this, new Observer<FormatPrefResponse>() {
            @Override
            public void onChanged(FormatPrefResponse itemResponse) {
                if (itemResponse != null) {
                    if (itemResponse.getStatus() == 1) {
                        downLoadFile(file);

                    } else
                        Toast.makeText(MainActivity.this, itemResponse.getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    private void downLoadFile(File file) {


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

            String fileName = file.getFileId() + ".jpeg";

            String dirPath = Environment.getExternalStorageDirectory()
                    + "/Android/data/"
                    + getApplicationContext().getPackageName()
                    + "/Files";
//            downloadImageFromUrl(file.getFile(), dirPath, fileName);
            new DownloadTask().execute(file.getFile());
            System.out.println("--- downLoad FIle --- " + fileName);
        }
    }

    private void CheckDownLoadAvailabitlity(String item_id) {
        viewModel.checkDownload(userRecord.getUserId(), item_id);
    }

    private void downloadImageFromUrl(final String file, String dirPath, String fileName) {
        AndroidNetworking.download(file, dirPath, fileName)
                .build()
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        Log.w("Download : ", file);
                        Toast.makeText(MainActivity.this, "Image downloaded successfully", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(MainActivity.this, "Download Failed", Toast.LENGTH_LONG).show();

                        anError.getCause().printStackTrace();
                    }
                });

    }

    class DownloadTask extends AsyncTask<String, Void, Void> {
        java.io.File apkStorage = null;
        java.io.File outputFile = null;


        @Override
        protected Void doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);//Create Download URl
                HttpURLConnection c = (HttpURLConnection) url.openConnection();//Open Url Connection
                c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
                c.connect();//connect the URL Connection

                //If Connection response is not OK then show Logs
                if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.e(TAG, "Server returned HTTP " + c.getResponseCode()
                            + " " + c.getResponseMessage());

                }
                String downloadFileName = "IMG_" + System.currentTimeMillis() + ".jpeg";
                apkStorage = new java.io.File(Environment.getExternalStorageDirectory() + "/Magic-Design/images");

                //If File is not present create directory
                if (!apkStorage.exists()) {
                    apkStorage.mkdir();
                    Log.e(TAG, "Directory Created." +apkStorage.getAbsolutePath());
                }

                outputFile = new java.io.File(apkStorage, downloadFileName);//Create Output file in Main File
                System.out.println("-- output file : " + outputFile);
                //Create New File if not present
                if (!outputFile.getParentFile().exists()) {
                    outputFile.getParentFile().mkdir();
                    Log.e(TAG, "File Created");
                } if (!outputFile.exists()) {
                    outputFile.createNewFile();
                    Log.e(TAG, "File Created");
                }

                FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location

                InputStream is = c.getInputStream();//Get InputStream for connection

                byte[] buffer = new byte[1024];//Set buffer type
                int len1 = 0;//init length
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);//Write new file
                }

                //Close all connection after doing task
                fos.close();
                is.close();

            } catch (Exception e) {

                //Read exception if something went wrong
                e.printStackTrace();
                outputFile = null;
                Log.e(TAG, "Download Error Exception " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                if (outputFile == null) {
                    Log.e(TAG, "Download Failed with Exception - ");
                    Toast.makeText(MainActivity.this, "Oops!! Download Failed.", Toast.LENGTH_LONG).show();

                } else {

                    Log.e(TAG, "Download Complete");
                    Toast.makeText(MainActivity.this, "Download Complete", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            super.onPostExecute(result);
        }

    }
}