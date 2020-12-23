package com.shopee.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shopee.MainActivity;
import com.shopee.OrderActivity;
import com.shopee.chat.ChatActivity;
import com.shopee.R;
import com.shopee.home.FlashSale;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {
    ArrayList<Cart> listCarts;
    ImageView back, chat;
    TextView tv_order , tv_total;
    long Total = 0;
    private DatabaseReference mData;
    NumberFormat fmt = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mData = FirebaseDatabase.getInstance().getReference().child("CartProduct");
        Back();
        initCart();
        openChat();
        openOrder();


    }


    public void initCart() {
        tv_total = findViewById(R.id.tien);
        RecyclerView rcv_Cart = findViewById(R.id.rcv_cart);
        rcv_Cart.setHasFixedSize(true);
        LinearLayoutManager gird = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rcv_Cart.setLayoutManager(gird);

        listCarts = new ArrayList<>();
//
//        listCarts.add(new Cart(R.drawable.cart1, "DẦU NHỜN TỔNG HỢP", "NHỚT POWER UP 4T 20W50 DÀNH CHO XE SỐ 1L", "đ 119.000", 1));
//
//
//        listCarts.add(new Cart(R.drawable.cart2, "Kho sỉ cửa khẩu", "[Mã GROMS25 hoàn 8% đơn 199K] (Combo gói 30 cái) chân vịt cay Dacheng cực kì ngon", "đ 660.000", 4));


        CartAdapter cartAdapter = new CartAdapter(listCarts, CartActivity.this, R.layout.item_cart);
        mData = FirebaseDatabase.getInstance().getReference().child("CartProduct");
        mData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @com.google.firebase.database.annotations.Nullable String previousChildName) {
                Cart values =  snapshot.getValue (Cart.class);

//                tv_tmp.append(values+ "\n");
                Total +=(long) values.getSolg() * values.getGia();
                tv_total.setText(fmt.format(Total));
                listCarts.add(values);
                cartAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @com.google.firebase.database.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @com.google.firebase.database.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        rcv_Cart.setAdapter(cartAdapter);

        tv_total.setText(fmt.format(Total));

    }

    public void Back() {
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void openChat() {
        chat = findViewById(R.id.chat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(CartActivity.this, ChatActivity.class);
                startActivity(myIntent);
            }
        });
    }
    public void openOrder() {
        tv_order = findViewById(R.id.tv_buynow);
        tv_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(CartActivity.this, OrderActivity.class);
                startActivity(myIntent);
            }
        });
    }


}
