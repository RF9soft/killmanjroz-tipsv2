package com.kilimanjaroz.tips.model.livescore;

import com.google.gson.annotations.SerializedName;

public class Match {
    @SerializedName("Away Score")
    private int awayScore;

    @SerializedName("Away Team")
    private String awayTeam;

    @SerializedName("Home Score")
    private int homeScore;

    @SerializedName("Home Team")
    private String homeTeam;

    @SerializedName("Initial Away Odd")
    private double initialAwayOdd;

    @SerializedName("Initial Draw Odd")
    private double initialDrawOdd;

    @SerializedName("Initial Home Odd")
    private double initialHomeOdd;

    @SerializedName("League")
    private String league;

    @SerializedName("League ID")
    private int leagueID;

    @SerializedName("Live Away Odd")
    private double liveAwayOdd;

    @SerializedName("Live Draw Odd")
    private double liveDrawOdd;

    @SerializedName("Live Home Odd")
    private double liveHomeOdd;

    @SerializedName("Match ID")
    private String matchID;

    @SerializedName("Status")
    private String status;

    public Match(int awayScore, String awayTeam, int homeScore, String homeTeam, double initialAwayOdd, double initialDrawOdd, double initialHomeOdd, String league, int leagueID, double liveAwayOdd, double liveDrawOdd, double liveHomeOdd, String matchID, String status) {
        this.awayScore = awayScore;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.homeTeam = homeTeam;
        this.initialAwayOdd = initialAwayOdd;
        this.initialDrawOdd = initialDrawOdd;
        this.initialHomeOdd = initialHomeOdd;
        this.league = league;
        this.leagueID = leagueID;
        this.liveAwayOdd = liveAwayOdd;
        this.liveDrawOdd = liveDrawOdd;
        this.liveHomeOdd = liveHomeOdd;
        this.matchID = matchID;
        this.status = status;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public double getInitialAwayOdd() {
        return initialAwayOdd;
    }

    public void setInitialAwayOdd(double initialAwayOdd) {
        this.initialAwayOdd = initialAwayOdd;
    }

    public double getInitialDrawOdd() {
        return initialDrawOdd;
    }

    public void setInitialDrawOdd(double initialDrawOdd) {
        this.initialDrawOdd = initialDrawOdd;
    }

    public double getInitialHomeOdd() {
        return initialHomeOdd;
    }

    public void setInitialHomeOdd(double initialHomeOdd) {
        this.initialHomeOdd = initialHomeOdd;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    public double getLiveAwayOdd() {
        return liveAwayOdd;
    }

    public void setLiveAwayOdd(double liveAwayOdd) {
        this.liveAwayOdd = liveAwayOdd;
    }

    public double getLiveDrawOdd() {
        return liveDrawOdd;
    }

    public void setLiveDrawOdd(double liveDrawOdd) {
        this.liveDrawOdd = liveDrawOdd;
    }

    public double getLiveHomeOdd() {
        return liveHomeOdd;
    }

    public void setLiveHomeOdd(double liveHomeOdd) {
        this.liveHomeOdd = liveHomeOdd;
    }

    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
        this.matchID = matchID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
