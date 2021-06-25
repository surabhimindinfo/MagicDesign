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
import com.deepit.magicdesign.model.Category;
import com.deepit.magicdesign.view.activity.MainActivity;
import com.deepit.magicdesign.view.fragment.SubCategoryFragment;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final Context context;
    private final int tag;
    private List<Category> results = new ArrayList<>();

    public CategoryAdapter(Context context, int tag, List<Category> category) {
        this.tag = tag;
        this.context = context;
        this.results = category;
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

        final Category record = results.get(position);
        System.out.println("--- image on cat at design list  --- " + record.getCatImage());

        Glide.with(holder.itemView).load(record.getCatImage()).into(holder.myimage);
        holder.headTV.setText(record.getCatName());


        holder.myimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("--- clicked on cat at detail --- ");
                System.out.println(context);

                if (context instanceof MainActivity) {
                    System.out.println("--- context of MainActivity --- ");

                    if (tag == R.string.detail) {
                        SubCategoryFragment fragmentDesignCat = new SubCategoryFragment();
                        ((MainActivity) context).openFragment(fragmentDesignCat, results.get(position), tag);
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
