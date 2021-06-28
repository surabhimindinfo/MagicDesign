package com.deepit.magicdesign.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deepit.magicdesign.R;
import com.deepit.magicdesign.adapter.MainCategoryAdapter;
import com.deepit.magicdesign.model.Category;
import com.deepit.magicdesign.network.response.SubCategoryResponse;
import com.deepit.magicdesign.view.activity.MainActivity;
import com.deepit.magicdesign.viewmodel.SubCatViewModel;

import static com.deepit.magicdesign.Constant.TAG;
import static com.deepit.magicdesign.Constant.TITLE;

public class SubCategoryFragment extends Fragment {

    private Context context;
    private SubCatViewModel viewModel;
    private MainCategoryAdapter adapter;
    private Category record;
    private ProgressBar progressBar;
    private int tag;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        adapter = new MainCategoryAdapter(context, R.string.detail);
        viewModel = new ViewModelProvider(this).get(SubCatViewModel.class);
        viewModel.init();
        viewModel.getSubCategoryResponseLiveData().observe(this, new Observer<SubCategoryResponse>() {
            @Override
            public void onChanged(SubCategoryResponse subCategoryResponse) {

                if (subCategoryResponse != null) {
                    System.out.println("------ size subcat-----");
                    if (subCategoryResponse.getSubCategory() != null)
                        adapter.setResults(subCategoryResponse.getSubCategory());
                    else
                        Toast.makeText(context, subCategoryResponse.getMessage(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(context, "Server not responding!", Toast.LENGTH_LONG).show();
                }
            }
        });

        viewModel.getLoadingResponse().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                System.out.println("--- loading value -- " + aBoolean);
                if (aBoolean) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        if (context instanceof MainActivity) {
            Bundle bundle = getArguments();
            if (bundle != null) {
                record = (Category) bundle.get(TITLE);
                tag = bundle.getInt(TAG);
                ((MainActivity) context).tvHead.setText(record.getCatName());
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_designer_detail, container, false);
        context = getContext();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);

        viewModel.getSubCategory(record.getMain_category_id(), record.getCategoryId());
        viewModel.getLoadingResponse();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        return view;
    }
}
