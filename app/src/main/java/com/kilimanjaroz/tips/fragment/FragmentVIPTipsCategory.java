package com.kilimanjaroz.tips.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kilimanjaroz.tips.R;
import com.kilimanjaroz.tips.adapter.VIPCategoryAdapter;
import com.kilimanjaroz.tips.databinding.FragmentViptipscategoryBinding;
import com.kilimanjaroz.tips.network.RetrofitClient;
import com.kilimanjaroz.tips.response.vip.VIPCategoryList;
import com.kilimanjaroz.tips.response.vip.VIPCategoryResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentVIPTipsCategory extends Fragment {
    private FragmentViptipscategoryBinding binding;
    private VIPCategoryAdapter dashboardAdapter;
    ArrayList<VIPCategoryList> ReportMOdels2 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentViptipscategoryBinding.inflate(getLayoutInflater());

        final lottiedialogfragment lottie = new lottiedialogfragment(getActivity());
        lottie.show();


        Call<VIPCategoryResponse> call = RetrofitClient.getInstance().getApi().getGameVipResponse();
        call.enqueue(new Callback<VIPCategoryResponse>() {

            @Override
            public void onResponse(Call<VIPCategoryResponse> call, Response<VIPCategoryResponse> response) {

                if (response.isSuccessful()) {
                    lottie.dismiss();
                    VIPCategoryResponse list = response.body();
                    ReportMOdels2 = list.getData();
                    showCatData();

                }
            }

            @Override
            public void onFailure(Call<VIPCategoryResponse> call, Throwable t) {
                lottie.dismiss();
                t.printStackTrace();

            }
        });
        return binding.getRoot();
    }
    private void showCatData() {

        dashboardAdapter = new VIPCategoryAdapter(getActivity(), ReportMOdels2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rvold.setLayoutManager(layoutManager);
        binding.rvold.setAdapter(dashboardAdapter);

    }
    public class lottiedialogfragment extends Dialog {
        public lottiedialogfragment(Context context) {
            super(context);

            WindowManager.LayoutParams wlmp = getWindow().getAttributes();

            wlmp.gravity = Gravity.CENTER_HORIZONTAL;
            getWindow().setAttributes(wlmp);
            getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            setTitle(null);
            setCancelable(false);
            setOnCancelListener(null);
            View view = LayoutInflater.from(context).inflate(
                    R.layout.dialog_lottie, null);
            setContentView(view);
        }
    }
}