package com.kilimanjaroz.tips.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kilimanjaroz.tips.R;
import com.kilimanjaroz.tips.network.OnUserClickListener;
import com.kilimanjaroz.tips.response.details.ViewDetailsActivity;
import com.kilimanjaroz.tips.response.vip.VIPCategoryList;

import java.util.ArrayList;

public class VIPCategoryAdapter extends RecyclerView.Adapter<VIPCategoryAdapter.DashboardViewHolder> {

    private Context context;
    private ArrayList<VIPCategoryList> dashboardItemList;
    private OnUserClickListener onCategoryItemClick;

    public VIPCategoryAdapter(Context context, ArrayList<VIPCategoryList> dashboardItemList) {
        this.context = context;
        this.dashboardItemList = dashboardItemList;

    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {


        View view = LayoutInflater.from(context).inflate(R.layout.layout_category, parent, false);
        return new DashboardViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        VIPCategoryList dashboardItemModel = dashboardItemList.get(position);

        holder.dashItemTitle.setText(dashboardItemModel.getCategoryName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = String.valueOf(dashboardItemModel.getId());
                Intent mIntent = new Intent(context, ViewDetailsActivity.class);
                mIntent.putExtra("id", id);

                context.startActivity(mIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dashboardItemList.size();
    }

    public class DashboardViewHolder extends RecyclerView.ViewHolder {

        private ImageView dashItemImage;
        private TextView dashItemTitle, sub;
        private RelativeLayout cardView;

        public DashboardViewHolder(@NonNull View itemView) {
            super(itemView);

            //  dashItemImage = itemView.findViewById(R.id.img_row_HomeMenu);
            dashItemTitle = itemView.findViewById(R.id.tv_row_HomeMenu_Title);
          //  sub = itemView.findViewById(R.id.tv_category_type);
            cardView = itemView.findViewById(R.id.dashboard_card);
        }
    }

    public void SetItemClick(OnUserClickListener onCategoryItemClick) {
        this.onCategoryItemClick = onCategoryItemClick;
    }

}
