package com.deepit.magicdesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deepit.magicdesign.OnItemClickListener;
import com.deepit.magicdesign.R;
import com.deepit.magicdesign.model.Filter;
import com.deepit.magicdesign.model.Result;
import com.deepit.magicdesign.view.activity.MainActivity;
import com.deepit.magicdesign.view.fragment.CategoryItemFragment;

import java.util.ArrayList;
import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {

    private final Context context;
    private final int tag;
    private List<Filter> results = new ArrayList<>();
    private List<Result> items = new ArrayList<>();
    private final CategoryItemFragment fragment;

    public FilterAdapter(Context context, int tag, CategoryItemFragment fragment) {
        this.tag = tag;
        this.context = context;
        this.fragment = fragment;
    }


    public void setResults(List<Filter> results) {
        this.results = results;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.filter_text, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Filter record = results.get(position);

        holder.textTV.setText(record.getName());

        items.clear();
        holder.textTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof MainActivity) {

                    items = record.getResult();
                    ((CategoryItemFragment) fragment).openAdapterItemList(items);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textTV;


        public ViewHolder(View itemView) {
            super(itemView);

            textTV = itemView.findViewById(R.id.textTV);

        }
    }
}
