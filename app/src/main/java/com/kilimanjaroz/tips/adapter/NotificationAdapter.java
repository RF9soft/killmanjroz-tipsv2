package com.kilimanjaroz.tips.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kilimanjaroz.tips.model.NewsNotifcationDatumn;
import com.kilimanjaroz.tips.network.OnUserClickListener;
import com.kilimanjaroz.tips.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.DashboardViewHolder> {

    private Context context;
    private ArrayList<NewsNotifcationDatumn> dashboardItemList;
    private OnUserClickListener onCategoryItemClick;

    public NotificationAdapter(Context context, ArrayList<NewsNotifcationDatumn> dashboardItemList) {
        this.context = context;
        this.dashboardItemList = dashboardItemList;

    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {


        View view = LayoutInflater.from(context).inflate(R.layout.rec_notification, parent, false);
        return new DashboardViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        NewsNotifcationDatumn dashboardItemModel = dashboardItemList.get(position);

        holder.dashItemTitle.setText(dashboardItemModel.getNotificationTitle());
        holder.desc.setText(dashboardItemModel.getNotificationDescription());
        Glide.with(context).load(dashboardItemList.get(position).getNotificationImage()).apply(new RequestOptions().
                error(R.drawable.fc).fitCenter()).into(holder.dashItemImage);



    }

    @Override
    public int getItemCount() {
        return dashboardItemList.size();
    }

    public class DashboardViewHolder extends RecyclerView.ViewHolder {

        private ImageView dashItemImage;
        private TextView dashItemTitle, desc;

        public DashboardViewHolder(@NonNull View itemView) {
            super(itemView);

            dashItemImage = itemView.findViewById(R.id.img);
            dashItemTitle = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
        }
    }


}
