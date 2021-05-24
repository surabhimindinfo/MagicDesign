package com.deepit.magicdesign.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deepit.magicdesign.R;
import com.deepit.magicdesign.model.MainCategory;
import com.deepit.magicdesign.model.Result;
import com.deepit.magicdesign.view.fragment.FileFormatFragment;

import java.util.ArrayList;
import java.util.List;

public class PrefAdapter extends RecyclerView.Adapter<PrefAdapter.ViewHolder> {

    private final Context context;
    private final FileFormatFragment fileFragment;
    private final String EXTRA_SPACE = "  ";
    private List<MainCategory> mainCategory = new ArrayList<>();
    private List<Result> results = new ArrayList<>();
    private int page = 0;

    public PrefAdapter(Context context, FileFormatFragment fragment) {
        this.context = context;
        this.fileFragment = fragment;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.pref_value, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        int isChecked = 0;


        if (mainCategory.size() > 0) {
            final MainCategory record = mainCategory.get(position);
            holder.textTV.setText(record.getName() + EXTRA_SPACE);
            isChecked = record.getChecked();
            if (isChecked == 1) {
                if (page == 1) {
                    if (mainCategory.size() > 0)
                        fileFragment.arrMainCat.add((mainCategory.get(position)).getMainCategoryId());
                } else if (page == 2)
                    fileFragment.arrCat.add((mainCategory.get(position)).getCategoryId());
                else if (page == 3)
                    fileFragment.arrSubCat.add((mainCategory.get(position)).getSubcategoryId());
            }

        } else {
            final Result record = results.get(position);
            holder.textTV.setText(record.getName() + EXTRA_SPACE);
            isChecked = record.getChecked();
            if (isChecked == 1)
                fileFragment.arrMachine.add((results.get(position)).getMachineFormatId());
        }
        if (isChecked == 1) {
            holder.textTV.setChecked(true);
            holder.textTV.setCheckMarkDrawable(R.drawable.ic_check);
        } else {
            holder.textTV.setChecked(false);
            holder.textTV.setCheckMarkDrawable(R.drawable.ic_uncheck);
        }


        holder.textTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = holder.textTV.isChecked();
                if (isChecked) {
                    if (page == 1) {
                        fileFragment.arrMainCat.remove((mainCategory.get(position)).getMainCategoryId());
                    } else if (page == 2)
                        fileFragment.arrCat.remove((mainCategory.get(position)).getCategoryId());
                    else if (page == 3)
                        fileFragment.arrSubCat.remove((mainCategory.get(position)).getSubcategoryId());
                    else
                        fileFragment.arrMachine.remove((results.get(position)).getMachineFormatId());


                    holder.textTV.setCheckMarkDrawable(R.drawable.ic_uncheck);
                    holder.textTV.setChecked(false);
                } else {
                    if (page == 1) {
                        if (mainCategory.size() > 0)
                            fileFragment.arrMainCat.add((mainCategory.get(position)).getMainCategoryId());
                    } else if (page == 2)
                        fileFragment.arrCat.add((mainCategory.get(position)).getCategoryId());
                    else if (page == 3)
                        fileFragment.arrSubCat.add((mainCategory.get(position)).getSubcategoryId());
                    else {
                        if (results.size() > 0)
                            fileFragment.arrMachine.add((results.get(position)).getMachineFormatId());
                    }


                    holder.textTV.setCheckMarkDrawable(R.drawable.ic_check);
                    holder.textTV.setChecked(true);
//                    Toast.makeText(context, "Checked", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        if (mainCategory != null && mainCategory.size() > 0)
            return mainCategory.size();
        else
            return results.size();
    }

    public void setResults(List<Result> results) {
        page = 0;
        this.results = results;
        notifyDataSetChanged();
    }

    public void setResults(List<MainCategory> mainCategory, int page) {
        this.mainCategory = mainCategory;
        this.page = page;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public CheckedTextView textTV;
        public RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);

            textTV = itemView.findViewById(R.id.textTV);
            recyclerView = itemView.findViewById(R.id.recyclerView);

        }
    }
}
