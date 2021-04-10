package com.deepit.magicdesign.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deepit.magicdesign.R;
import com.deepit.magicdesign.adapter.DesignAdapter;
import com.deepit.magicdesign.view.activity.MainActivity;

public class FragmentDesignList extends Fragment {

    View view;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_designer_detail, container, false);
        context = getActivity();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

         DesignAdapter adapter = new DesignAdapter(context,R.string.detail);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        if (context instanceof MainActivity)
        {
            System.out.println("---- main activity instance----");
            ((MainActivity)context).tvHead.setText("Design List");
        }
        return view;
    }
}
