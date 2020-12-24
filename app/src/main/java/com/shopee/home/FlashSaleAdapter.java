package com.shopee.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shopee.DetailProductActivity;
import com.shopee.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class FlashSaleAdapter extends RecyclerView.Adapter<FlashSaleAdapter.viewHoder> {

    ArrayList<FlashSale> flashSales;
    Context context;
    int layout;

    public FlashSaleAdapter(ArrayList<FlashSale> flashSales, Context context, int layout) {
        this.flashSales = flashSales;
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
        holder.tvName.setText(fmt.format(Integer.parseInt(flashSales.get(position).getGia())));
        Picasso.get().load(flashSales.get(position).getImg()).into(holder.img, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
//                Toast.makeText(getContext(),"Không lấy đc img từ link",Toast.LENGTH_LONG).show();
            }
        });
        holder.sell.setText("ĐÃ BÁN "+flashSales.get(position).getSell());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(context, DetailProductActivity.class);
                myIntent.putExtra("id",flashSales.get(position).getId());

                context.startActivity(myIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return flashSales.size();
    }

    public class viewHoder extends RecyclerView.ViewHolder {
        TextView tvName,sell;
        ImageView img;

        public viewHoder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_gia);
            img = (ImageView) itemView.findViewById(R.id.img_flashsale);
            sell = (TextView) itemView.findViewById(R.id.sell);
        }
    }
}
