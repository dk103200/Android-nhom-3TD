package com.shopee.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shopee.DetailProductActivity;
import com.shopee.Product;
import com.shopee.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.viewHoder> {

    ArrayList<Product> products;
    Context context;
    int layout;

    public ProductAdapter(ArrayList<Product> products, Context context, int layout) {
        this.products = products;
        this.context = context;
        this.layout = layout;
    }

    @NonNull
    @Override
    public viewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View itemView = inflater.inflate(layout,parent,false);
        return new viewHoder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHoder holder, int position) {
        NumberFormat fmt = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        holder.tvName.setText(products.get(position).getName());


        Picasso.get().load(products.get(position).getImg()).into(holder.img, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
//                Toast.makeText(getContext(),"Không lấy đc img từ link",Toast.LENGTH_LONG).show();
            }
        });
        holder.gia.setText(fmt.format(Integer.parseInt(products.get(position).getGia())));
        holder.gia_km.setText(fmt.format(Integer.parseInt(products.get(position).getGia_km())));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(context, DetailProductActivity.class);
                myIntent.putExtra("id",products.get(position).getId());

                context.startActivity(myIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class viewHoder extends RecyclerView.ViewHolder {
        TextView tvName,gia,gia_km;
        ImageView img;

        public viewHoder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            img = (ImageView) itemView.findViewById(R.id.img_product);
            gia = (TextView) itemView.findViewById(R.id.tv_gia);
            gia.setPaintFlags(gia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            gia_km = (TextView) itemView.findViewById(R.id.tv_km);

        }
    }
}
