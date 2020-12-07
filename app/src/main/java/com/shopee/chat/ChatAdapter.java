package com.shopee.chat;

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

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.viewHoder> {

    ArrayList<Chat> chats;
    Context context;
    int layout;

    public ChatAdapter(ArrayList<Chat> chats, Context context, int layout) {
        this.chats = chats;
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
        holder.tvName.setText(chats.get(position).getName());
        holder.tvDetail.setText(chats.get(position).getDetail());
        holder.tvTime.setText(chats.get(position).getTime());
        holder.img.setImageResource(chats.get(position).getImg());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick)
                    Log.d(TAG, "savedInstanceState is null"+chats.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }
    public void addItem(int position, Chat data) {
        chats.add(position, data);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        chats.remove(position);
        notifyItemRemoved(position);
    }


    public class viewHoder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }


    }


}
