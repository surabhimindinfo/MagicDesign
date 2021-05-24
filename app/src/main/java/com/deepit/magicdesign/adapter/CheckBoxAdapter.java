package com.deepit.magicdesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deepit.magicdesign.R;
import com.deepit.magicdesign.model.Result;
import com.deepit.magicdesign.view.fragment.CategoryItemFragment;
import com.deepit.magicdesign.view.fragment.FileFormatFragment;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxAdapter extends RecyclerView.Adapter<CheckBoxAdapter.ViewHolder> {

    private final Context context;
    private List<Result> results = new ArrayList<>();
    private CategoryItemFragment fragment;
    private FileFormatFragment fileFragment;

    public CheckBoxAdapter(Context context, CategoryItemFragment fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    public CheckBoxAdapter(Context context, FileFormatFragment fragment) {
        this.context = context;
        this.fileFragment = fragment;
    }

    public void setResults(List<Result> results) {
        this.results = results;
        System.out.println("---- result added  --- " + results.size());
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.filter_value, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Result record = results.get(position);

        holder.textTV.setText(record.getValue());

        if (fragment != null) {
            if (fragment.strItem.contains(record.getId())) {
                holder.textTV.setCheckMarkDrawable(R.drawable.ic_check);
                holder.textTV.setChecked(true);
            } else {

                holder.textTV.setCheckMarkDrawable(R.drawable.ic_uncheck);
                holder.textTV.setChecked(false);
            }
        } else {
            if (record.getChecked() == 1) {
                holder.textTV.setCheckMarkDrawable(R.drawable.ic_check);
                holder.textTV.setChecked(true);
            } else {
                holder.textTV.setCheckMarkDrawable(R.drawable.ic_uncheck);
                holder.textTV.setChecked(false);
            }

        }
        holder.textTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean value = holder.textTV.isChecked();
                if (value) {
                    // set check mark drawable and set checked property to false
                    if (fragment != null)
                        fragment.strItem.remove(record.getId());
                    holder.textTV.setCheckMarkDrawable(R.drawable.ic_uncheck);
                    holder.textTV.setChecked(false);
                    Toast.makeText(context, "un-Checked", Toast.LENGTH_LONG).show();
                } else {
                    // set check mark drawable and set checked property to true
                    if (fragment != null)
                        fragment.strItem.add(record.getId());

                    holder.textTV.setCheckMarkDrawable(R.drawable.ic_check);
                    holder.textTV.setChecked(true);
                    Toast.makeText(context, "Checked", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return results.size();
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
