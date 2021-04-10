package com.deepit.magicdesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.deepit.magicdesign.R;
import com.deepit.magicdesign.model.Record;
import com.deepit.magicdesign.view.activity.MainActivity;
import com.deepit.magicdesign.view.fragment.FragmentDesignCategory;
import com.deepit.magicdesign.view.fragment.FragmentDesignList;

import java.util.ArrayList;
import java.util.List;

public class DesignAdapter extends RecyclerView.Adapter<DesignAdapter.ViewHolder> {

    private List<Record> results = new ArrayList<>();

   private Context context;
   private int tag;

    public DesignAdapter(Context context, int tag) {
        this.tag=tag;
        this.context = context;
    }

    public void setResults(List<Record> results) {
        this.results = results;
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



        Record record = results.get(position);

        System.out.println("---- image main ----- " +record.getMainImage() );
        Glide.with(holder.itemView).load(record.getMainImage()).into(holder.myimage);
        holder.myimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (context instanceof MainActivity)
                {
                    switch (tag)
                    {
                        case R.string.home:
                            FragmentDesignList fragmentDesignList = new FragmentDesignList();
                            ((MainActivity)context).openFragment(fragmentDesignList);
                            break;
                        case R.string.detail:
                            FragmentDesignCategory fragmentDesignCat = new FragmentDesignCategory();
                            ((MainActivity)context).openFragment(fragmentDesignCat);
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

       public ImageView myimage ;
        public ViewHolder(View itemView) {
            super(itemView);

            myimage = itemView.findViewById(R.id.myimage);


        }
    }
}
