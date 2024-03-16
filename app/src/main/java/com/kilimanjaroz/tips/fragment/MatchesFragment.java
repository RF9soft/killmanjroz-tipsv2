package com.kilimanjaroz.tips.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kilimanjaroz.tips.R;
import com.kilimanjaroz.tips.adapter.MatchesAdapter;
import com.kilimanjaroz.tips.model.livescore.ApiResponse;
import com.kilimanjaroz.tips.model.livescore.Match;
import com.kilimanjaroz.tips.network.APIService;
import com.kilimanjaroz.tips.network.APiClient;
import com.kilimanjaroz.tips.network.Constantine;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchesFragment extends Fragment {

    private RecyclerView recyclerView;
    private MatchesAdapter adapter;
    ProgressBar progressBar;

    public MatchesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matches, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Get the API client instance
        APIService apiService = APiClient.getInstance().getApi();
        progressBar.setVisibility(View.VISIBLE);

        Call<ApiResponse> call = apiService.getLiveMatches(Constantine.API_KEY, Constantine.API_HOST);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    List<Match> matches = response.body().getMatches();
                    adapter = new MatchesAdapter(requireContext(), matches);
                    recyclerView.setAdapter(adapter);
                } else {
                    // Handle unsuccessful response
                    // Show an error message or retry
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);

            }
        });
    }
}