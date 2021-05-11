package com.deepit.magicdesign.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deepit.magicdesign.R;
import com.deepit.magicdesign.adapter.DesignAdapter;
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
    ArrayList<Banner> sliderDataArrayList = new ArrayList<>();
    private Context context;
    private MainDesignViewModel viewModel;
    private DesignAdapter adapter;
    private SliderAdapter sliderAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        System.out.println("---- context at fragment --- " + context);
        adapter = new DesignAdapter(context, R.string.home);
        sliderAdapter = new SliderAdapter(context);

        viewModel = new ViewModelProvider(this).get(MainDesignViewModel.class);
        viewModel.init();
        viewModel.getMainDesignListResponseLiveData().observe(this, new Observer<MainDesignListResponse>() {
            @Override
            public void onChanged(MainDesignListResponse mainDesignListResponse) {

                if (mainDesignListResponse != null) {
//                    progressBar.setVisibility(View.GONE);
                    adapter.setResults(mainDesignListResponse.getRecord());
                }
            }
        });

        viewModel.getBannerResponseLiveData().observe(this, new Observer<BannerResponse>() {
            @Override
            public void onChanged(BannerResponse mainDesignListResponse) {

                if (mainDesignListResponse != null) {
//                    progressBar.setVisibility(View.GONE);
                    sliderAdapter.setResults(mainDesignListResponse.getRecord());
                }
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        viewModel.getMainDesign();
        viewModel.getBanner();

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

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(sliderAdapter);

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        sliderView.startAutoCycle();
    }

}