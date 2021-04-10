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
import com.deepit.magicdesign.model.SliderData;
import com.deepit.magicdesign.network.response.MainDesignListResponse;
import com.deepit.magicdesign.viewmodel.MainDesignViewModel;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    View view;
    String url1 = "https://www.geeksforgeeks.org/wp-content/uploads/gfg_200X200-1.png";
    String url2 = "https://qphs.fs.quoracdn.net/main-qimg-8e203d34a6a56345f86f1a92570557ba.webp";
    String url3 = "https://bizzbucket.co/wp-content/uploads/2020/08/Life-in-The-Metro-Blog-Title-22.png";

    private Context context;
    private MainDesignViewModel viewModel;
    private DesignAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new DesignAdapter(context, R.string.home);

        viewModel = new ViewModelProvider(this).get(MainDesignViewModel.class);
        viewModel.init();
        viewModel.getMainDesignListResponseLiveData().observe(this, new Observer<MainDesignListResponse>() {
            @Override
            public void onChanged(MainDesignListResponse mainDesignListResponse) {

                if (mainDesignListResponse != null)
                    adapter.setResults(mainDesignListResponse.getRecord());
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity();
        viewModel.getMainDesign();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        setSliderView();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        return view;
    }


    private void setSliderView() {
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView = view.findViewById(R.id.slider);

        // adding the urls inside array list
        sliderDataArrayList.add(new SliderData(url1));
        sliderDataArrayList.add(new SliderData(url2));
        sliderDataArrayList.add(new SliderData(url3));

        // passing this array list inside our adapter class.
        SliderAdapter adapter = new SliderAdapter(context, sliderDataArrayList);

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(adapter);

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        sliderView.startAutoCycle();
    }
}