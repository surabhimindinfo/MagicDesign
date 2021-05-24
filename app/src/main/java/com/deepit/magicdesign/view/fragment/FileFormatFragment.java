package com.deepit.magicdesign.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deepit.magicdesign.R;
import com.deepit.magicdesign.adapter.PrefAdapter;
import com.deepit.magicdesign.model.UserRecord;
import com.deepit.magicdesign.network.response.FormatPrefResponse;
import com.deepit.magicdesign.view.activity.MainActivity;
import com.deepit.magicdesign.viewmodel.SubCatViewModel;

import java.util.ArrayList;

import static com.deepit.magicdesign.Constant.TAG;
import static com.deepit.magicdesign.Constant.TITLE;

public class FileFormatFragment extends Fragment {
    public ArrayList<String> strItem = new ArrayList<>();
    public ArrayList<String> arrMachine = new ArrayList<>();
    public ArrayList<String> arrMainCat = new ArrayList<>();
    public ArrayList<String> arrCat = new ArrayList<>();
    public ArrayList<String> arrSubCat = new ArrayList<>();
    private Context context;
    private SubCatViewModel viewModel;
    private PrefAdapter filterListadapter;
    private PrefAdapter catAdapter;
    private UserRecord record;
    private int tag;
    private ProgressBar progressBar;
    private int pageCount = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        filterListadapter = new PrefAdapter(context, FileFormatFragment.this);
        catAdapter = new PrefAdapter(context, FileFormatFragment.this);
        viewModel = new ViewModelProvider(this).get(SubCatViewModel.class);
        viewModel.init();
        if (context instanceof MainActivity) {
            Bundle bundle = getArguments();
            if (bundle != null) {
                record = (UserRecord) bundle.get(TITLE);
                tag = bundle.getInt(TAG);
                ((MainActivity) context).tvHead.setText(R.string.my_file_formats);
                ((MainActivity) context).btn_menu.setImageResource(R.drawable.ic_arrow);
            }
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_file_format, container, false);

        final RecyclerView formatListView = view.findViewById(R.id.formatListView);
        final TextView layoutHeadTV = view.findViewById(R.id.layoutHeadTV);
        RecyclerView mainCatListView = view.findViewById(R.id.mainCatListView);
        progressBar = view.findViewById(R.id.progressBar);
        final Button prevBtn = view.findViewById(R.id.prevBtn);
        final Button nextBtn = view.findViewById(R.id.nextBtn);
        final TextView stepTV = view.findViewById(R.id.stepTV);

        progressBar.setVisibility(View.VISIBLE);
        getFormatVM1();
        viewModel.getFormatPref(record.getUserId());


        ((MainActivity) context).btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).btn_menu.setImageResource(R.drawable.ic_menu);
                if (getActivity() != null) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack();
                }
            }
        });


        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if (pageCount == 3) {
                    getCatPrefVM();
                    layoutHeadTV.setText(R.string.cat_support_format);
                    stepTV.setText(R.string.step_count2);
                    //page2
                    String mainCatiDs = arrMainCat.toString().replace("[", "").replace("]", "")
                            .replace(", ", ",");
                    System.out.println("-- selected main cat --- " + mainCatiDs);
                    viewModel.getCatPref(record.getUserId(), mainCatiDs);
                    nextBtn.setVisibility(View.VISIBLE);
                    prevBtn.setVisibility(View.VISIBLE);
                    formatListView.setVisibility(View.GONE);
                    pageCount--;
                } else if (pageCount == 2) {
                    //page1
                    stepTV.setText(R.string.step_count);
                    viewModel.getFormatPref(record.getUserId());
                    layoutHeadTV.setText(R.string.machine_support_format);
                    formatListView.setVisibility(View.VISIBLE);
                    nextBtn.setVisibility(View.VISIBLE);
                    prevBtn.setVisibility(View.GONE);

                    pageCount--;
                }
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String catIDs = null;
                String mainCatiDs = null;
                String subCatiDs = null;


                if (pageCount == 1) {
                    System.out.println("--main -- " + arrMainCat.size());
                    System.out.println("--machine -- " + arrMachine.size());
                    if (arrMainCat.size() > 0 && arrMachine.size() > 0) {
                        stepTV.setText(R.string.step_count2);
                        layoutHeadTV.setText(R.string.cat_support_format);
                        //page2
                        getCatPrefVM();
                        mainCatiDs = arrMainCat.toString().replace("[", "").replace("]", "")
                                .replace(", ", ",");
                        System.out.println("-- selected main cat --- " + mainCatiDs);
                        viewModel.getCatPref(record.getUserId(), mainCatiDs);

                        formatListView.setVisibility(View.GONE);
                        nextBtn.setVisibility(View.VISIBLE);
                        pageCount++;

                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(context, "Please select atleast 1 category and machine format ", Toast.LENGTH_LONG).show();
                    }
                } else if (pageCount == 2) {
                    if (arrCat.size() > 0) {
                        stepTV.setText(R.string.step_count3);
                        layoutHeadTV.setText(R.string.subcat_support_format);
                        //page3
                        getSubCatPrefVM();
                        catIDs = arrCat.toString().replace("[", "").replace("]", "")
                                .replace(", ", ",");
                        System.out.println("-- selected sub cat --- " + catIDs);
                        viewModel.getSubCatPref(record.getUserId(), catIDs);
                        nextBtn.setVisibility(View.VISIBLE);
                        formatListView.setVisibility(View.GONE);
                        pageCount++;

                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(context, "Please select atleast 1 saree category ", Toast.LENGTH_LONG).show();
                    }
                } else if (pageCount == 3) {
                    //upload to share pref

                    if (arrSubCat.size() > 0) {
                        catIDs = arrCat.toString().replace("[", "").replace("]", "")
                                .replace(", ", ",");

                        subCatiDs = arrSubCat.toString().replace("[", "").replace("]", "")
                                .replace(", ", ",");

                        String machineiDs = arrMachine.toString().replace("[", "").replace("]", "")
                                .replace(", ", ",");
                        mainCatiDs = arrMainCat.toString().replace("[", "").replace("]", "")
                                .replace(", ", ",");
                        uploadPrefVM();
                        viewModel.savePrefToServer(record.getUserId(), machineiDs, mainCatiDs, catIDs, subCatiDs);

                    } else {
                        Toast.makeText(context, "Please select atleast 1 option", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
                prevBtn.setVisibility(View.VISIBLE);
            }
        });

        formatListView.setLayoutManager(new GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, true));
        formatListView.setAdapter(filterListadapter);

        mainCatListView.setLayoutManager(new LinearLayoutManager(context));
        mainCatListView.setAdapter(catAdapter);
        return view;
    }


    private void getCatPrefVM() {
        viewModel.getCatPrefLiveData().observe(this, new Observer<FormatPrefResponse>() {
            @Override
            public void onChanged(FormatPrefResponse formatPrefResponse) {
                progressBar.setVisibility(View.GONE);
                if (formatPrefResponse != null) {
                    System.out.println("------ size items-----" + formatPrefResponse.getStatus());
                    if (formatPrefResponse.getStatus() == 1) {
                        catAdapter.setResults(formatPrefResponse.getRecord().getCategory(), 2);
                    } else
                        Toast.makeText(context, formatPrefResponse.getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    private void getSubCatPrefVM() {
        viewModel.getSubCatPrefLiveData().observe(this, new Observer<FormatPrefResponse>() {
            @Override
            public void onChanged(FormatPrefResponse formatPrefResponse) {
                progressBar.setVisibility(View.GONE);
                if (formatPrefResponse != null) {
                    if (formatPrefResponse.getStatus() == 1) {
                        System.out.println("------ size items-----" + formatPrefResponse.getRecord().getSubcategory().size());

                        catAdapter.setResults(formatPrefResponse.getRecord().getSubcategory(), 3);
                    } else
                        Toast.makeText(context, formatPrefResponse.getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    private void uploadPrefVM() {
        viewModel.uploadPrefLiveData().observe(this, new Observer<FormatPrefResponse>() {
            @Override
            public void onChanged(FormatPrefResponse formatPrefResponse) {
                progressBar.setVisibility(View.GONE);
                if (formatPrefResponse != null) {
                    if (formatPrefResponse.getStatus() == 1) {
                        ((MainActivity) context).btn_menu.setImageResource(R.drawable.ic_menu);
                        ((MainActivity) context).onBackPressed();
                    }

                    Toast.makeText(context, formatPrefResponse.getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        });

    }


    @SuppressLint("FragmentLiveDataObserve")
    private void getFormatVM1() {
        viewModel.getFormatPrefLiveData().observe(this, new Observer<FormatPrefResponse>() {
            @Override
            public void onChanged(FormatPrefResponse formatPrefResponse) {
                progressBar.setVisibility(View.GONE);
                if (formatPrefResponse != null) {
                    if (formatPrefResponse.getStatus() == 1) {
                        System.out.println("------ size items-----" + formatPrefResponse.getRecord().getMainCategory().size());

                        filterListadapter.setResults(formatPrefResponse.getRecord().getFormat());
                        catAdapter.setResults(formatPrefResponse.getRecord().getMainCategory(), 1);
                    } else
                        Toast.makeText(context, formatPrefResponse.getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        });

    }
}