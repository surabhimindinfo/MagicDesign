package com.deepit.magicdesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deepit.magicdesign.R;
import com.deepit.magicdesign.model.CountryRecord;
import com.deepit.magicdesign.model.OnItemClick;
import com.deepit.magicdesign.view.activity.LoginActivity;
import com.deepit.magicdesign.view.activity.SignUpActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountryCodeAdapter extends RecyclerView.Adapter<CountryCodeAdapter.ViewHolder> {

    private List<CountryRecord> results = new ArrayList<>();
    private OnItemClick onItemClick;
    private Context context;

    public CountryCodeAdapter(Context context, OnItemClick onItemClick) {
        this.context = context;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.code_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final CountryRecord countryRecord = results.get(position);

        holder.codeTV.setText(("+") + countryRecord.getPhonecode());
        holder.codenameTV.setText(countryRecord.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof SignUpActivity) {

                    onItemClick.onListItemClick(countryRecord.getId());
                    System.out.println("---- instance of signup activity ------ ");
                    ((SignUpActivity) context).listLayout.setVisibility(View.GONE);
                    ((SignUpActivity) context).signUpbutton.setVisibility(View.VISIBLE);
                     ((SignUpActivity) context).countryCodeTV.setText(countryRecord.getPhonecode());

                } else if (context instanceof LoginActivity) {
                    onItemClick.onListItemClick(countryRecord.getId());
                    System.out.println("---- instance of signup activity ------ ");
                     ((LoginActivity) context).listLayout.setVisibility(View.GONE);
                     ((LoginActivity) context).login.setVisibility(View.VISIBLE);
                    ((LoginActivity) context).countryCodeTV.setText(countryRecord.getPhonecode());
                }
            }
        });
    }


    public void setResults(List<CountryRecord> results) {
        System.out.println("----- result size ------- " + results.size());
        this.results = results;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView codeTV;
        public TextView codenameTV;

        public ViewHolder(View itemView) {
            super(itemView);

            codeTV = itemView.findViewById(R.id.codeTV);
            codenameTV = itemView.findViewById(R.id.codenameTV);


        }
    }
}
