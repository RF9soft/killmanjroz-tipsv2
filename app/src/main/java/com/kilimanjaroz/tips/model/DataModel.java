package com.kilimanjaroz.tips.model;

public class DataModel {

    String league_name, team_1,team_2,vs1,vs2, tips_name,odds, date;

    public DataModel(String league_name, String team_1, String team_2, String vs1, String vs2, String tips_name, String odds, String date) {
        this.league_name = league_name;
        this.team_1 = team_1;
        this.team_2 = team_2;
        this.vs1 = vs1;
        this.vs2 = vs2;
        this.tips_name = tips_name;
        this.odds = odds;
        this.date = date;
    }

    public DataModel() {



    }

    public String getLeague_name() {
        return league_name;
    }

    public String getTeam_1() {
        return team_1;
    }

    public String getTeam_2() {
        return team_2;
    }

    public String getVs1() {
        return vs1;
    }

    public String getVs2() {
        return vs2;
    }

    public String getTips_name() {
        return tips_name;
    }

    public String getOdds() {
        return odds;
    }

    public String getDate() {
        return date;
    }
}

