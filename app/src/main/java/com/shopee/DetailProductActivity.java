package com.shopee;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.shopee.cart.CartActivity;
import com.shopee.chat.ChatActivity;
import com.shopee.home.BannerAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailProductActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Integer> listPhoto;
    ImageView back,cart,chat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        setbtn_Cart();
        setbtn_Back();
        setbtn_Chat();
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
    public void setbtn_Chat(){
        chat = findViewById(R.id.chat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DetailProductActivity.this, ChatActivity.class);
                startActivity(myIntent);
            }
        });

    }

    public void setbtn_Cart(){
        cart = findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DetailProductActivity.this, CartActivity.class);
                startActivity(myIntent);
            }
        });

    }
    public void setbtn_Back(){
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
