package com.shopee;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

public class ViewPagerImage extends PagerAdapter {
    private Context context;

    public ViewPagerImage(Context context, String[] img_url) {
        this.context = context;
        this.img_url = img_url;
    }

    private String[] img_url;
    @Override
    public int getCount() {
        return img_url.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView img = new ImageView(context);
        Picasso.get()
                .load(img_url[position])
                .fit().into(img);
        container.addView(i);
    }
}
