package com.kilimanjaroz.tips.model.livescore;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {
    @SerializedName("matches")
    private List<Match> matches;

    // Add getter

    public List<Match> getMatches() {
        return matches;
    }
}