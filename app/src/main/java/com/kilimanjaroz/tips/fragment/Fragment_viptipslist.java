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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kilimanjaroz.tips.R;
import com.kilimanjaroz.tips.adapter.viptips.VipTipsAdapter;
import com.kilimanjaroz.tips.network.RetrofitClient;
import com.kilimanjaroz.tips.response.vip.viptips.VIPTipsList;
import com.kilimanjaroz.tips.response.vip.viptips.VIPTipsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_viptipslist extends Fragment {
    View view;
    ArrayList<VIPTipsList> ReportMOdels2 = new ArrayList<>();
    public VipTipsAdapter dashboardAdapter;
    RecyclerView rvfreetips;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_viptips, container, false);

        rvfreetips = view.findViewById(R.id.predictions_recycler_view);
        response();


        return view;
    }

    private void showOldData() {
        dashboardAdapter = new VipTipsAdapter(getActivity(), ReportMOdels2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvfreetips.setLayoutManager(layoutManager);
        rvfreetips.setAdapter(dashboardAdapter);
    }

    private void response() {

        final lottiedialogfragment lottie = new lottiedialogfragment(getActivity());
        lottie.show();
        Call<VIPTipsResponse> call = RetrofitClient.getInstance().getApi().getVipTipsResponse();
        call.enqueue(new Callback<VIPTipsResponse>() {

            @Override
            public void onResponse(Call<VIPTipsResponse> call, Response<VIPTipsResponse> response) {

                if (response.isSuccessful()) {
                    lottie.dismiss();

                    VIPTipsResponse list = response.body();
                    ReportMOdels2 = (ArrayList<VIPTipsList>) list.getData();
                    showOldData();

                }
            }

            @Override
            public void onFailure(Call<VIPTipsResponse> call, Throwable t) {
                lottie.dismiss();
                t.printStackTrace();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        response();
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
    @Override
    public void onPause() {
        super.onPause();
        response();
    }

}
