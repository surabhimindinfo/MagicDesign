package com.deepit.magicdesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.deepit.magicdesign.OnItemClickListener;
import com.deepit.magicdesign.R;
import com.deepit.magicdesign.model.Item;
import com.deepit.magicdesign.view.activity.MainActivity;
import com.deepit.magicdesign.view.fragment.CategoryFragment;
import com.deepit.magicdesign.view.fragment.ItemDetailFragment;

import java.util.ArrayList;
import java.util.List;

import static com.deepit.magicdesign.Constant.JPG_FORMAT;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private final Context context;
    private List<Item> results = new ArrayList<>();



    public ItemAdapter(Context context) {
        this.context = context;
    }

    public void setResults(List<Item> results) {
        this.results = results;
        System.out.println("---- item result added  --- " + results.size());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final Item record = results.get(position);

        for (int i = 0; i < record.getFiles().size(); i++) {
            if (record.getFiles().get(i).getType().equalsIgnoreCase("0")) {
                System.out.println("--- image on cat at design list  --- " + record.getFiles().get(i).getFile200());
                Glide.with(holder.itemView).load(record.getFiles().get(i).getFile200()).into(holder.myimage);
            }
        }

        holder.btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).downLoadImage(record.getFiles(),record.getItemId());

            }
        });

        holder.myimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("--- clicked on item --- ");
                ItemDetailFragment fragmentItem = new ItemDetailFragment();
                ((MainActivity) context).openFragment(fragmentItem,record, R.string.item_detail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView myimage;
        public ImageButton btn_download;
        public ViewHolder(View itemView) {
            super(itemView);
            btn_download = itemView.findViewById(R.id.btn_download);
            myimage = itemView.findViewById(R.id.myimage);
        }
    }
}
