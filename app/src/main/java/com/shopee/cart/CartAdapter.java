package com.shopee.cart;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shopee.ItemClickListener;
import com.shopee.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewHoder> {

    ArrayList<Cart> carts;
    Context context;
    int layout;

    public CartAdapter(ArrayList<Cart> carts, Context context, int layout) {
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
        holder.tvName.setText(carts.get(position).getName());
        holder.tvProduct.setText(carts.get(position).getProduct());
        holder.tvGia.setText(carts.get(position).getGia());
        holder.img.setImageResource(carts.get(position).getImg());
        holder.solg.setText(String.valueOf(carts.get(position).getSolg()));

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                if(!isLongClick)
                   Log.d(TAG, "savedInstanceState is null"+carts.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }
    public void addItem(int position, Cart data) {
        carts.add(position, data);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        carts.remove(position);
        notifyItemRemoved(position);
    }


    public class viewHoder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        TextView tvName, tvProduct, tvGia;
        ImageView img;
        EditText solg;
        private ItemClickListener itemClickListener;

        public viewHoder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvProduct = itemView.findViewById(R.id.tv_product);
            tvGia = itemView.findViewById(R.id.tv_gia);
            img = itemView.findViewById(R.id.img_product);
            solg =  itemView.findViewById(R.id.edt_solg);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),true);
            return true;
        }
    }


}
