package com.kilimanjaroz.tips.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kilimanjaroz.tips.model.history.Datum;
import com.kilimanjaroz.tips.model.history.VipHistoryResponse;
import com.kilimanjaroz.tips.network.RetrofitClient;
import com.kilimanjaroz.tips.adapter.TodayTipsAdapter;
import com.kilimanjaroz.tips.databinding.FragmentPreviousBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PreviousFragment extends Fragment {
    private FragmentPreviousBinding binding;
    ArrayList<Datum> ReportMOdels2 = new ArrayList<>();
    public TodayTipsAdapter dashboardAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPreviousBinding.inflate(getLayoutInflater());
        response();

        return binding.getRoot();
    }




    private void showOldData() {
        dashboardAdapter = new TodayTipsAdapter(getActivity(), ReportMOdels2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rvold.setLayoutManager(layoutManager);
        binding.rvold.setAdapter(dashboardAdapter);
    }

    private void response() {

        Call<VipHistoryResponse> call = RetrofitClient.getInstance().getApi().getVIPTipsHistoryResponse();
        call.enqueue(new Callback<VipHistoryResponse>() {

            @Override
            public void onResponse(Call<VipHistoryResponse> call, Response<VipHistoryResponse> response) {

                if (response.isSuccessful()) {


                    VipHistoryResponse list = response.body();
                    ReportMOdels2 = list.getData();
                    showOldData();

                }
            }

            @Override
            public void onFailure(Call<VipHistoryResponse> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        response();
    }

    @Override
    public void onPause() {
        super.onPause();
        response();
    }
}