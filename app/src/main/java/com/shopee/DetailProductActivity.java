package com.shopee;


import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shopee.cart.CartActivity;
import com.shopee.chat.ChatActivity;
import com.shopee.home.BannerAdapter;
import com.shopee.home.FlashSale;
import com.shopee.home.TopSale;
import com.squareup.picasso.Picasso;

import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import me.relex.circleindicator.CircleIndicator;

public class DetailProductActivity extends AppCompatActivity {

    private TextView tv_name,tv_gia,tv_km,tv_rating;
    private RatingBar ratingBar;
    ImageView back,cart,chat,add_cart;
    private DatabaseReference mData;


    ViewPager2 viewPager2;
    private CircleIndicator circleIndicator;
    PhotoAdapter adapter;
    ArrayList<String> data;

    String url="https://cf.shopee.vn/file/6d3c20c6e9fb283a6af08e9b8a3ed0e0_tn",

            url6="https://i.ytimg.com/vi/lUrmyU1cnxU/maxresdefault.jpg";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);


        data=new ArrayList<>();

        setData();

        data.add( url);
        viewPager2=findViewById(R.id.viewpager);
        adapter=new PhotoAdapter(data);
        viewPager2.setAdapter(adapter);
        init ();

        setbtn_Cart();
        setbtn_Back();
        setbtn_Chat();




    }

    public void init (){
        add_cart = findViewById(R.id.add_card);
        tv_name = findViewById(R.id.name_product);
        tv_gia = findViewById(R.id.gia_product);
        tv_km = findViewById(R.id.gia_km);
        tv_rating = findViewById(R.id.rating_product);
        ratingBar = findViewById(R.id.ratingBar);
    }

//    @Override
//    protected void onResume() {
//        setData();
//        super.onResume();
//    }

    public void setData(){
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        mData = FirebaseDatabase.getInstance().getReference().child("Product");
        mData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Product values =  snapshot.getValue (Product.class);
                if (values.getId() == id){
                    data.add(url);
                    tv_name.setText(values.getName());
                    NumberFormat fmt = NumberFormat.getCurrencyInstance(new Locale("vi","VN"));
                    tv_km.setText(fmt.format(Integer.parseInt(values.getGia_km())));
                    tv_gia.setPaintFlags(tv_km.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG );
                    tv_gia.setText(fmt.format(Integer.parseInt(values.getGia())));
                    tv_rating.setText(String.valueOf(values.getSao()) );
                    ratingBar.setRating(values.getSao());

                }


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
