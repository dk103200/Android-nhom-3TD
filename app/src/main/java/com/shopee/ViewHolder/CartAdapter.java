package com.shopee.ViewHolder;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.shopee.Model.Order;
import com.shopee.R;
import com.shopee.cart.Cart;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewHoder> {

   private ArrayList<Order> carts = new ArrayList<>();
   private Context context;
    int layout;

    public CartAdapter(ArrayList<Order> carts, Context context, int layout) {
        this.carts = carts;
        this.context = context;
        this.layout = layout;
    }

    @NonNull
    @Override
    public viewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(layout, parent, false);
        return new viewHoder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHoder holder, int position) {
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(""+ carts.get(position).getQuantity(), Color.RED);
        holder.img.setImageDrawable(drawable);

        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int price = (Integer.parseInt(carts.get(position).getPrice())) * (Integer.parseInt(carts.get(position).getQuantity()));
        holder.tvGia.setText(fmt.format(price));

//        holder.tvName.setText(carts.get(position).getName());

        holder.tvProduct.setText(carts.get(position).getProductName());

//        holder.img.setImageResource(carts.get(position).getImg());
//        holder.solg.setText(String.valueOf(carts.get(position).getSolg()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                Toast.makeText(context, carts.get(position).getName(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

//    public void addItem(int position, Cart data) {
//        carts.add(position, data);
//        notifyItemInserted(position);
//    }
//
//    public void removeItem(int position) {
//        carts.remove(position);
//        notifyItemRemoved(position);
//    }


    public class viewHoder extends RecyclerView.ViewHolder{
        public TextView tvName, tvProduct, tvGia;
        public ImageView img;
        EditText solg;


        public viewHoder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvProduct = itemView.findViewById(R.id.tv_product);
            tvGia = itemView.findViewById(R.id.tv_gia);
            img = itemView.findViewById(R.id.img_product);
            solg = itemView.findViewById(R.id.edt_solg);

        }


    }
}
