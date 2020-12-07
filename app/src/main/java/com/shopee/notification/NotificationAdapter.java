package com.shopee.notification;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shopee.ItemClickListener;
import com.shopee.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewHoder> {

    ArrayList<Notification> notifications;
    Context context;
    int layout;

    public NotificationAdapter(ArrayList<Notification> notifications, Context context, int layout) {
        this.notifications = notifications;
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
        holder.tvName.setText(notifications.get(position).getName());
        holder.tvDetail.setText(notifications.get(position).getDetail());
        holder.tvTime.setText(notifications.get(position).getTime());
        holder.img.setImageResource(notifications.get(position).getImg());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
//                if(isLongClick)
//                    Toast.makeText(context, "Long Click: "+notifications.get(position), Toast.LENGTH_SHORT).show();
//                else {
//                    Log.d(TAG, "savedInstanceState is null"+notifications.get(position));
//                   Toast.makeText(context, " Click " + notifications.get(position), Toast.LENGTH_SHORT).show();
//                }
                if(!isLongClick)
                   Log.d(TAG, "savedInstanceState is null"+notifications.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }
    public void addItem(int position, Notification data) {
        notifications.add(position, data);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        notifications.remove(position);
        notifyItemRemoved(position);
    }


    public class viewHoder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        TextView tvName, tvDetail, tvTime;
        ImageView img;
        private ItemClickListener itemClickListener;

        public viewHoder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDetail = itemView.findViewById(R.id.tv_detail);
            tvTime = itemView.findViewById(R.id.tv_time);
            img = itemView.findViewById(R.id.img_notifi);

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
