package com.deepit.magicdesign.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.deepit.magicdesign.R;
import com.deepit.magicdesign.model.Item;
import com.deepit.magicdesign.view.activity.MainActivity;

import static com.deepit.magicdesign.Constant.TITLE;

public class ItemDetailFragment extends Fragment {

    private Item item = null;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        Window window = getActivity().getWindow();
        WindowManager wm = getActivity().getWindowManager();
        wm.removeViewImmediate(window.getDecorView());
        wm.addView(window.getDecorView(), window.getAttributes());

    }
    @SuppressLint("SetTextI18n")
    private void init(View v) {

        TextView codeTV = v.findViewById(R.id.codeTV);
        TextView stitchTV = v.findViewById(R.id.stitchTV);
        TextView widthTV = v.findViewById(R.id.widthTV);
        TextView heightTV = v.findViewById(R.id.heightTV);
        TextView needleTV = v.findViewById(R.id.needleTV);
        TextView catTV = v.findViewById(R.id.catTV);
        ImageView itemImageView = v.findViewById(R.id.itemImageView);
        Button downloadBtn = v.findViewById(R.id.btn_download);

        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).downLoadImage(item.getFiles(),item.getItemId());
            }
        });
        if (context instanceof MainActivity) {
            Bundle bundle = getArguments();
            if (bundle != null) {
                item = (Item) bundle.get(TITLE);
                ((MainActivity) context).tvHead.setText("");
                if (item != null) {
                    codeTV.setText(getText(R.string.code, item.getItemCode()));
                    stitchTV.setText(getText(R.string.stitch, item.getStitch()));
                    widthTV.setText(getText(R.string.width, item.getWidth()));
                    widthTV.append(Html.fromHtml("<font color=#33C1FF>" + item.getUnit() + "</font>"));

                    heightTV.setText(getText(R.string.height, item.getHeight()));
                    heightTV.append(Html.fromHtml("<font color=#33C1FF>" + item.getUnit() + "</font>"));
                    needleTV.setText(getText(R.string.needle, item.getNeedle()));
                    catTV.setText(item.getName());

                    for (int i = 0; i < item.getFiles().size(); i++) {
                        if (item.getFiles().get(i).getType().equalsIgnoreCase("0")) {
                            System.out.println("--- image on cat at design list  --- " + item.getFiles().get(i).getFile());
                            Glide.with(context).load(item.getFiles().get(i).getFile()).into(itemImageView);
                        }
                    }
                }
            }
        }

    }
    private Spanned getText(int resString, String value) {
        return Html.fromHtml(String.format(getResources().getString(resString), value));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View  view = inflater.inflate(R.layout.fragment_item_detail, container, false);
        init(view);
        return view;
    }
}