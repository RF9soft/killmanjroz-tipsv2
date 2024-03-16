package com.kilimanjaroz.tips.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kilimanjaroz.tips.model.history.Datum;
import com.kilimanjaroz.tips.model.history.FreeTipsHistoryResponse;
import com.kilimanjaroz.tips.network.RetrofitClient;
import com.kilimanjaroz.tips.adapter.TodayTipsAdapter;
import com.kilimanjaroz.tips.databinding.FragmentTodayBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodayFragment extends Fragment {
    private FragmentTodayBinding binding;
    private TodayTipsAdapter dashboardAdapter;
    ArrayList<Datum> ReportMOdels2 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTodayBinding.inflate(getLayoutInflater());



        Call<FreeTipsHistoryResponse> call = RetrofitClient.getInstance().getApi().getFreeTipsHistoryResponse();
        call.enqueue(new Callback<FreeTipsHistoryResponse>() {

            @Override
            public void onResponse(Call<FreeTipsHistoryResponse> call, Response<FreeTipsHistoryResponse> response) {

                if (response.isSuccessful()) {

                    FreeTipsHistoryResponse list = response.body();
                    ReportMOdels2 = list.getData();
                    showCatData();

                }
            }

            @Override
            public void onFailure(Call<FreeTipsHistoryResponse> call, Throwable t) {
                t.printStackTrace();

            }
        });
        return binding.getRoot();
    }
    private void showCatData() {

        dashboardAdapter = new TodayTipsAdapter(getActivity(), ReportMOdels2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewToday.setLayoutManager(layoutManager);
        binding.recyclerViewToday.setAdapter(dashboardAdapter);

    }
}