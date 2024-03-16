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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kilimanjaroz.tips.R;
import com.kilimanjaroz.tips.adapter.NotificationAdapter;
import com.kilimanjaroz.tips.model.NewsNotiResponse;
import com.kilimanjaroz.tips.model.NewsNotifcationDatumn;
import com.kilimanjaroz.tips.network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_notification extends Fragment {


    View view;

    private RecyclerView dashBoardRecycler, recent;
    private NotificationAdapter dashboardAdapter;

    ImageView profile;
    LinearLayoutManager HorizontalLayout;

    ArrayList<NewsNotifcationDatumn> ReportMOdels2 = new ArrayList<>();
    TextView old;

    public Fragment_notification() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notification, container, false);
        initView();
        initFunction();
        CategoryList();
        clicklistener();
        return view;
    }
    private void CategoryList() {
        final lottiedialogfragment lottie = new lottiedialogfragment(getActivity());
        lottie.show();
        Call<NewsNotiResponse> call = RetrofitClient.getInstance().getApi().getNotificatio();
        call.enqueue(new Callback<NewsNotiResponse>() {

            @Override
            public void onResponse(Call<NewsNotiResponse> call, Response<NewsNotiResponse> response) {

                if (response.isSuccessful()) {
                    lottie.dismiss();

                    NewsNotiResponse list = response.body();
                    ReportMOdels2 = (ArrayList<NewsNotifcationDatumn>) list.getData();
                    showCatData();

                }
            }

            @Override
            public void onFailure(Call<NewsNotiResponse> call, Throwable t) {
                t.printStackTrace();
                lottie.dismiss();

            }
        });
    }

    private void clicklistener() {


    }



    private void showCatData() {
        dashboardAdapter = new NotificationAdapter(getActivity(), ReportMOdels2);
        LinearLayoutManager layoutManager = new GridLayoutManager(getContext(), 1, LinearLayoutManager.VERTICAL, false);
        dashBoardRecycler.setLayoutManager(layoutManager);
        dashBoardRecycler.setAdapter(dashboardAdapter);

    }


    private void initView() {
        dashBoardRecycler = view.findViewById(R.id.dashBoardRecycler);


    }

    private void initFunction() {


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
