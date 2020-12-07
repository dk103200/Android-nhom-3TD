package com.shopee;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.shopee.home.BannerAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Integer> listPhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        viewPager = findViewById(R.id.viewpager);
        listPhoto = getListPhoto();
        BannerAdapter bnAdapter = new BannerAdapter(this, listPhoto);
        viewPager.setAdapter(bnAdapter);

    }

    private List<Integer> getListPhoto() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.shop1);
        list.add(R.drawable.shop2);
        list.add(R.drawable.shop3);
        return list;
    }
}
