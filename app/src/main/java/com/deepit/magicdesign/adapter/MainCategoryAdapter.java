package com.deepit.magicdesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.deepit.magicdesign.R;
import com.deepit.magicdesign.model.Record;
import com.deepit.magicdesign.view.activity.MainActivity;
import com.deepit.magicdesign.view.fragment.CategoryItemFragment;
import com.deepit.magicdesign.view.fragment.CategoryFragment;

import java.util.ArrayList;
import java.util.List;

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.ViewHolder> {

    private final Context context;
    private final int tag;
    private List<Record> results = new ArrayList<>();

    public MainCategoryAdapter(Context context, int tag) {
        this.tag = tag;
        this.context = context;
    }

    public void setResults(List<Record> results) {
        this.results = results;
        System.out.println("---- result added  --- " + results.size());
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.design_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        final Record record = results.get(position);
        if (tag == R.string.home) {
            holder.headTV.setText(record.getMainName());
            Glide.with(holder.itemView).load(record.getMainImage()).into(holder.myimage);
        }
        else {
            holder.headTV.setText(record.getSubName());
            Glide.with(holder.itemView).load(record.getSubImage()).into(holder.myimage);
        }


        holder.myimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof MainActivity) {

                    switch (tag) {
                        case R.string.home:
                            System.out.println("--- clicked on cat at home --- ");
                            CategoryFragment fragmentDesignList = new CategoryFragment();
                            ((MainActivity) context).openFragment(fragmentDesignList, results.get(position), tag);
                            break;
                        case R.string.detail:
                            CategoryItemFragment fragmentDesignCat = new CategoryItemFragment();
                            ((MainActivity) context).openFragment(fragmentDesignCat, results.get(position), tag);
                            break;
                    }

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView myimage;
        public TextView headTV;

        public ViewHolder(View itemView) {
            super(itemView);

            myimage = itemView.findViewById(R.id.myimage);
            headTV = itemView.findViewById(R.id.headTV);


        }
    }
}
