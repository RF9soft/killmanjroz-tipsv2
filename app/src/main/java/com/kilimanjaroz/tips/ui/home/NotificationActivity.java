package com.kilimanjaroz.tips.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.kilimanjaroz.tips.R;
import com.kilimanjaroz.tips.adapter.NotificationAdapter;
import com.kilimanjaroz.tips.model.NewsNotiResponse;
import com.kilimanjaroz.tips.model.NewsNotifcationDatumn;
import com.kilimanjaroz.tips.network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {
    private RecyclerView dashBoardRecycler, recent;
    private NotificationAdapter dashboardAdapter;

    ImageView profile;
    LinearLayoutManager HorizontalLayout;

    ArrayList<NewsNotifcationDatumn> ReportMOdels2 = new ArrayList<>();
    TextView old;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initView();
        initFunction();
        CategoryList();
        clicklistener();


}
    private void CategoryList() {

        Call<NewsNotiResponse> call = RetrofitClient.getInstance().getApi().getNotificatio();
        call.enqueue(new Callback<NewsNotiResponse>() {

            @Override
            public void onResponse(Call<NewsNotiResponse> call, Response<NewsNotiResponse> response) {

                if (response.isSuccessful()) {


                    NewsNotiResponse list = response.body();
                    ReportMOdels2 = (ArrayList<NewsNotifcationDatumn>) list.getData();
                    showCatData();

                }
            }

            @Override
            public void onFailure(Call<NewsNotiResponse> call, Throwable t) {
                t.printStackTrace();


            }
        });
    }

    private void clicklistener() {


    }



    private void showCatData() {
        dashboardAdapter = new NotificationAdapter(getApplicationContext(), ReportMOdels2);
        LinearLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1, LinearLayoutManager.VERTICAL, false);
        dashBoardRecycler.setLayoutManager(layoutManager);
        dashBoardRecycler.setAdapter(dashboardAdapter);

    }


    private void initView() {
        dashBoardRecycler = findViewById(R.id.dashBoardRecycler);
        Toolbar toolbar = findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

        }
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

