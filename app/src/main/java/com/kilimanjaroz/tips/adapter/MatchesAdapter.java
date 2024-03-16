package com.kilimanjaroz.tips.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kilimanjaroz.tips.R;
import com.kilimanjaroz.tips.model.livescore.Match;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {
    private List<Match> matches;
    private Context context;

    public MatchesAdapter(Context context, List<Match> matches) {
        this.context = context;
        this.matches = matches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_match, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Match match = matches.get(position);

        holder.tvAwayTeam.setText("Away:\t"+match.getAwayTeam());
        holder.tvHomeTeam.setText("Home:\t"+match.getHomeTeam());
        try {
            holder.awayscore.setText("AwayScore:\t"+String.valueOf(match.getAwayScore()));
            holder.homescore.setText("HomeScore:\t"+String.valueOf(match.getHomeScore()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.status.setText(match.getStatus());
       holder.league.setText(match.getLeague());
        holder.inithomeodds.setText("home:\t"+String.valueOf(match.getInitialHomeOdd()));
        holder.initdrawodds.setText("draw:\t"+String.valueOf(match.getInitialDrawOdd()));
        holder.initawayodds.setText("away:\t"+String.valueOf(match.getInitialAwayOdd()));



    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAwayTeam,tvHomeTeam,homescore,awayscore,
                status,league,inithomeodds,initawayodds,initdrawodds,livedrawodds,liveawayodds,livehomeodds;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAwayTeam = itemView.findViewById(R.id.awayTeam);
            tvHomeTeam = itemView.findViewById(R.id.homeTeam);
            homescore = itemView.findViewById(R.id.homeTeamScore);
            awayscore = itemView.findViewById(R.id.awayTeamScore);
           status = itemView.findViewById(R.id.status);
           league = itemView.findViewById(R.id.league_name);
            inithomeodds = itemView.findViewById(R.id.initHomeOdd);
            initdrawodds = itemView.findViewById(R.id.initDrawOdd);
            initawayodds = itemView.findViewById(R.id.initawayOdd);
//            livedrawodds = itemView.findViewById(R.id.liveDrawOdd);
//            liveawayodds = itemView.findViewById(R.id.liveawayOdd);
//            livehomeodds = itemView.findViewById(R.id.liveHomeOdd);

        }
    }
}
