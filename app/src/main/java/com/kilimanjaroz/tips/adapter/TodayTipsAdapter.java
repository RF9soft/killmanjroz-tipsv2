package com.kilimanjaroz.tips.adapter;

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

import com.kilimanjaroz.tips.R;
import com.kilimanjaroz.tips.model.history.Datum;

import java.util.ArrayList;
import java.util.Objects;


public class TodayTipsAdapter extends RecyclerView.Adapter<TodayTipsAdapter.DashboardViewHolder> {

    private Context context;
    private ArrayList<Datum> dashboardItemList;


    public TodayTipsAdapter(Context context, ArrayList<Datum> dashboardItemList) {
        this.context = context;
        this.dashboardItemList = dashboardItemList;

    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_layout2, parent, false);
        return new DashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, final int position) {
        Datum dashboardItemModel = dashboardItemList.get(position);

//        if (position % 3 == 0 ){
//
//
//            AdLoader.Builder builder = new AdLoader.Builder(
//                    context, "ca-app-pub-7507052797945562/9046034476");
//
//            builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
//                @Override
//                public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
//                    holder.templateView.setNativeAd(unifiedNativeAd);
//                }
//            });
//
//            final AdLoader adLoader = builder.build();
//            adLoader.loadAd(new AdRequest.Builder().build());
//
//            holder.templateView.setVisibility(View.VISIBLE);
//
//
//
//        }

            holder.date.setText(dashboardItemModel.getDate());
        holder.team1.setText(dashboardItemModel.getTeamOne());
        holder.team2.setText(dashboardItemModel.getTeamTwo());
        holder.odds.setText("Odds:" + dashboardItemModel.getOddsValue());
        holder.tips.setText(dashboardItemModel.getTipsName());
        holder.leage.setText(dashboardItemModel.getLeagueName());
        // holder.status.setText(dashboardItemModel.getStatus());
        holder.time.setText(dashboardItemModel.getTime());
        if (Objects.equals(dashboardItemList.get(position).getStatus(), "Win")) {
           // Picasso.get().load(R.drawable.check).into(holder.status);
            holder.status.setBackgroundResource(R.drawable.check);
        }
         if (Objects.equals(dashboardItemList.get(position).getStatus(), "Loss")) {
            holder.status.setBackgroundResource(R.drawable.cross);
        }
         if (Objects.equals(dashboardItemList.get(position).getStatus(), "Pending")) {
            holder.status.setBackgroundResource(R.drawable.ic_baseline_pending_actions_24);
        }

        Glide.with(context).load(dashboardItemList.get(position).getTeamOneImage()).apply(new RequestOptions().
                error(R.drawable.fc).fitCenter()).into(holder.team1logo);
        Glide.with(context).load(dashboardItemList.get(position).getTeamTwoImage()).apply(new RequestOptions().
                error(R.drawable.fc).fitCenter()).into(holder.team2logo);

    }

    @Override
    public int getItemCount() {
        if (dashboardItemList != null) {
            return dashboardItemList.size();
        } else {
            return 0;
        }

    }

    public class DashboardViewHolder extends RecyclerView.ViewHolder {


        private TextView date, team1, team2, odds, tips, time, leage;
        private ImageView team1logo, team2logo, status;

        public DashboardViewHolder(@NonNull View itemView) {
            super(itemView);

            leage = itemView.findViewById(R.id.league_name);
            date = itemView.findViewById(R.id.date);
            team1 = itemView.findViewById(R.id.team_one);
            team2 = itemView.findViewById(R.id.team_two);
            odds = itemView.findViewById(R.id.odds);
            time = itemView.findViewById(R.id.time);
            status = itemView.findViewById(R.id.status);
            tips = itemView.findViewById(R.id.pro_tips);
            team1logo = itemView.findViewById(R.id.team_one_img);
          //  templateView = itemView.findViewById(R.id.my_template);
            team2logo = itemView.findViewById(R.id.team_two_img);
        }
    }
}
