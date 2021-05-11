package com.deepit.magicdesign.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deepit.magicdesign.R;
import com.deepit.magicdesign.adapter.CategoryAdapter;
import com.deepit.magicdesign.model.Record;
import com.deepit.magicdesign.view.activity.MainActivity;

import static com.deepit.magicdesign.Constant.TITLE;

public class FragmentDesignList extends Fragment {

    private Record record;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_designer_detail, container, false);
        Context context = getContext();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        TextView noResultTV = view.findViewById(R.id.noResultTV);

        if (context instanceof MainActivity) {
            Bundle bundle = getArguments();
            if (bundle != null) {
                record = (Record) bundle.get(TITLE);
                System.out.println(record.getMainName());
                ((MainActivity) context).tvHead.setText(record.getMainName());
            }
        }
        if (record.getCategory().size()>0) {
            recyclerView.setVisibility(View.VISIBLE);
            noResultTV.setVisibility(View.GONE);
        }
        else {
            noResultTV.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

        CategoryAdapter adapter = new CategoryAdapter(context, R.string.detail, record.getCategory());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        return view;
    }
}
