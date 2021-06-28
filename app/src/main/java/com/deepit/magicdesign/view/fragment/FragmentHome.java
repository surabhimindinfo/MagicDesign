package com.deepit.magicdesign.view.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deepit.magicdesign.R;
import com.deepit.magicdesign.adapter.MainCategoryAdapter;
import com.deepit.magicdesign.adapter.SliderAdapter;
import com.deepit.magicdesign.model.Banner;
import com.deepit.magicdesign.network.response.BannerResponse;
import com.deepit.magicdesign.network.response.MainDesignListResponse;
import com.deepit.magicdesign.view.activity.MainActivity;
import com.deepit.magicdesign.viewmodel.MainDesignViewModel;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    View view;
    private Context context;
    private MainDesignViewModel viewModel;
    private MainCategoryAdapter adapter;
    private SliderAdapter sliderAdapter;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        System.out.println("---- context at fragment --- " + context);
        adapter = new MainCategoryAdapter(context, R.string.home);
        sliderAdapter = new SliderAdapter(context);

        viewModel = new ViewModelProvider(this).get(MainDesignViewModel.class);
        viewModel.init();
        viewModel.getMainDesignListResponseLiveData().observe(this, new Observer<MainDesignListResponse>() {
            @Override
            public void onChanged(MainDesignListResponse mainDesignListResponse) {

                if (mainDesignListResponse != null) {
                    adapter.setResults(mainDesignListResponse.getRecord());
                }
            }
        });

        viewModel.getBannerResponseLiveData().observe(this, new Observer<BannerResponse>() {
            @Override
            public void onChanged(BannerResponse mainDesignListResponse) {

                if (mainDesignListResponse != null) {
//
                    sliderAdapter.setResults(mainDesignListResponse.getRecord());
                }
            }
        });

        viewModel.getLoadingResponse().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                System.out.println("--- loading value -- " + aBoolean);
                if (aBoolean) {
                    progressBar.setVisibility(View.VISIBLE);                }
                else {
                    progressBar.setVisibility(View.GONE);                }
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        viewModel.getMainDesign();
        viewModel.getBanner();
        viewModel.getLoadingResponse();

        setSliderView();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        if (context instanceof MainActivity) {
            System.out.println("---- main activity instance----");
            ((MainActivity) context).tvHead.setText(R.string.home);
        }
        return view;
    }

    private void setSliderView() {

        // initializing the slider view.
        SliderView sliderView = view.findViewById(R.id.slider);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);

        sliderView.startAutoCycle();
    }

}