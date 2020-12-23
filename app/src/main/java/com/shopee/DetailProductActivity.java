package com.shopee;


import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.database.ValueEventListener;
import com.shopee.cart.Cart;
import com.shopee.cart.CartActivity;
import com.shopee.chat.ChatActivity;
import com.shopee.home.BannerAdapter;
import com.shopee.home.FlashSale;
import com.shopee.home.TopSale;
import com.shopee.me.User;
import com.shopee.me.dangky_activity;
import com.squareup.picasso.Picasso;

import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import me.relex.circleindicator.CircleIndicator;

public class DetailProductActivity extends AppCompatActivity {

    private TextView tv_name, tv_gia, tv_km, tv_rating;
    private RatingBar ratingBar;
    ImageView back, cart, chat, add_cart;
    private DatabaseReference mData, mCart;

    int id, gia;
    ViewPager2 viewPager2;
    private CircleIndicator circleIndicator;
    PhotoAdapter adapter;
    ArrayList<String> data;
    int check;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        data = new ArrayList<>();

        setData();

        viewPager2 = findViewById(R.id.viewpager);
        adapter = new PhotoAdapter(data);
        viewPager2.setAdapter(adapter);
        init();

        setadd_Cart();
        setbtn_Cart();
        setbtn_Back();
        setbtn_Chat();
    }

    private void setadd_Cart() {

        add_cart = findViewById(R.id.add_card);
        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = 0;
                mData = FirebaseDatabase.getInstance().getReference().child("CartProduct");

                mData.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                        Toast.makeText(DetailProductActivity.this, Integer.parseInt(snapshot.child("id").getValue().toString()) == id ? "true" :"false" , Toast.LENGTH_SHORT).show();

                        if (Integer.parseInt(snapshot.child("id").getValue().toString()) == id) {
                            Cart values = snapshot.getValue(Cart.class);
                            values.setSolg(values.getSolg() + 1);
                            mData.child(String.valueOf(id)).setValue(values);
                           check = 1;
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
                if (check == 0){

                    Cart product = new Cart(id,data.get(0), "PDT Computer", tv_name.getText().toString(),gia, 0);
                    mData.child(String.valueOf(id)).setValue(product);
                }
                Toast.makeText(DetailProductActivity.this, "Đã thêm vào giỏ hàng" , Toast.LENGTH_SHORT).show();

        }
    });
}

    public void init() {

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

    public void setData() {

        mData = FirebaseDatabase.getInstance().getReference().child("Product");
        mData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Product values = snapshot.getValue(Product.class);
                if (values.getId() == id) {

                    data.add("" + values.getImg());
                    tv_name.setText(values.getName());
                    NumberFormat fmt = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                    gia = Integer.parseInt(values.getGia_km());
                    tv_km.setText(fmt.format(gia));
                    tv_gia.setPaintFlags(tv_km.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    tv_gia.setText(fmt.format(Integer.parseInt(values.getGia())));
                    tv_rating.setText(String.valueOf(values.getSao()));
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

    public void setbtn_Chat() {
        chat = findViewById(R.id.chat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DetailProductActivity.this, ChatActivity.class);
                startActivity(myIntent);
            }
        });

    }

    public void setbtn_Cart() {
        cart = findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DetailProductActivity.this, CartActivity.class);
                startActivity(myIntent);
            }
        });

    }

    public void setbtn_Back() {
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
