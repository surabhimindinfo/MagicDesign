package com.deepit.magicdesign.view.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deepit.magicdesign.R;
import com.deepit.magicdesign.adapter.CheckBoxAdapter;
import com.deepit.magicdesign.adapter.FilterAdapter;
import com.deepit.magicdesign.adapter.ItemAdapter;
import com.deepit.magicdesign.model.Record;
import com.deepit.magicdesign.model.Result;
import com.deepit.magicdesign.network.response.FilterResponse;
import com.deepit.magicdesign.network.response.ItemResponse;
import com.deepit.magicdesign.view.activity.MainActivity;
import com.deepit.magicdesign.viewmodel.SubCatViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.deepit.magicdesign.Constant.TAG;
import static com.deepit.magicdesign.Constant.TITLE;

public class CategoryItemFragment extends Fragment {
    public ArrayList<String> strItem = new ArrayList<>();
    private Context context;
    private SubCatViewModel viewModel;
    private ItemAdapter adapter;
    private FilterAdapter filterAdapter;
    private CheckBoxAdapter filterListadapter;
    private RecyclerView filterItemList;
    private ProgressBar progressBar;
    private Record record;
    private int tag;
    private boolean isFilterOpen = false;
    private String filterIds;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        adapter = new ItemAdapter(context);
        filterAdapter = new FilterAdapter(context, R.string.filter, CategoryItemFragment.this);
        filterListadapter = new CheckBoxAdapter(context, CategoryItemFragment.this);
        viewModel = new ViewModelProvider(this).get(SubCatViewModel.class);
        viewModel.init();


        viewModel.getFilterResponseLiveData().observe(this, new Observer<FilterResponse>() {
            @Override
            public void onChanged(FilterResponse itemResponse) {
                if (itemResponse != null) {
                    System.out.println("------ filter----");
                    if (itemResponse.getStatus() == 1)
                        filterAdapter.setResults(itemResponse.getRecord());
                    else
                        Toast.makeText(context, itemResponse.getMessage(), Toast.LENGTH_LONG).show();

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

        if (context instanceof MainActivity) {
            Bundle bundle = getArguments();
            if (bundle != null) {
                record = (Record) bundle.get(TITLE);
                tag = bundle.getInt(TAG);
                ((MainActivity) context).tvHead.setText(record.getSubName());
            }
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        RecyclerView filterList = view.findViewById(R.id.filterList);
        final RelativeLayout filterLayout = view.findViewById(R.id.filterLayout);
        TextView filterTV = view.findViewById(R.id.filterTV);
        TextView clearBtn = view.findViewById(R.id.clearBtn);
        TextView sortTV = view.findViewById(R.id.sortTV);
        Button apply_btn = view.findViewById(R.id.apply_btn);
        recyclerView = view.findViewById(R.id.recyclerView);
        filterItemList = view.findViewById(R.id.filterItemList);
        progressBar = view.findViewById(R.id.progressBar);
        openListVM();
        viewModel.showFilter();
        viewModel.getLoadingResponse();

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("--- saved items -- " + strItem.size());
                filterLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                viewModel.showFilter();
                filterIds ="";
                viewModel.getItem(record.getMainCategoryId(), record.getCategory_id(),
                        record.getSubcategoryId(), 20, 1, "", filterIds);
            }
        });
        sortTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("--- saved items -- " + strItem.size());
                filterLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                viewModel.showFilter();
                filterIds ="";
                viewModel.getItem(record.getMainCategoryId(), record.getCategory_id(),
                        record.getSubcategoryId(), 20, 1, "", filterIds);
            }
        });
        apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.showFilter();
                System.out.println("--- saved items -- " + strItem.size());
                filterLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                filterIds = strItem.toString().replace("[", "").replace("]", "")
                        .replace(", ", ",");
                viewModel.getItem(record.getMainCategoryId(), record.getCategory_id(),
                        record.getSubcategoryId(), 20, 1, "", filterIds);
            }
        });

        filterTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFilterOpen) {
                    isFilterOpen = false;
                    filterLayout.setVisibility(View.GONE);
                } else {
                    isFilterOpen = true;
                    filterLayout.setVisibility(View.VISIBLE);

                }
            }
        });

        viewModel.getItem(record.getMainCategoryId(), record.getCategory_id(),
                record.getSubcategoryId(), 20, 1, "", filterIds);


        filterList.setHasFixedSize(true);
        filterList.setLayoutManager(new LinearLayoutManager(context));
        filterList.setAdapter(filterAdapter);

        filterItemList.setHasFixedSize(true);
        filterItemList.setLayoutManager(new LinearLayoutManager(context));
        filterItemList.setAdapter(filterListadapter);

        RecyclerView.LayoutManager manager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void openAdapterItemList(List<Result> items) {
        filterItemList.setAdapter(filterListadapter);
        filterListadapter.setResults(items);
        filterListadapter.notifyDataSetChanged();
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void openListVM() {

        viewModel.getItemResponseLiveData().observe(this, new Observer<ItemResponse>() {
            @Override
            public void onChanged(ItemResponse itemResponse) {
                if (itemResponse != null) {
                            System.out.println("------ size items-----" + itemResponse.getStatus());
                    if (itemResponse.getStatus() == 1) {
                        recyclerView.setVisibility(View.VISIBLE);
                        adapter.setResults(itemResponse.getRecord());
                    }
                    else {
                        Toast.makeText(context, itemResponse.getMessage(), Toast.LENGTH_LONG).show();
                        recyclerView.setVisibility(View.GONE);
                    }
                }
            }
        });

    }
}
