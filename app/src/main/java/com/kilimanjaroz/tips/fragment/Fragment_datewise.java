package com.kilimanjaroz.tips.fragment;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.kilimanjaroz.tips.R;
import com.kilimanjaroz.tips.adapter.TipsAdapter;
import com.kilimanjaroz.tips.model.datewise.DatewiseResponse;
import com.kilimanjaroz.tips.network.RetrofitClient;
import com.kilimanjaroz.tips.response.Tip;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_datewise extends Fragment {

    View view;
    private RecyclerView recyclerView;
    CalendarView calendarView;
    private TipsAdapter predictionsAdapter;
    private ArrayList<Tip> predictionsList;
    DatePickerDialog picker;
    TextView tv_start_date, tv_end_date;
    TextView hederTitle;
    String startDate, endDate, startDateTimeStamp, EndDateTimeStamp;
    String userId, type;
    String dayID;
    String monthID;
    String yearID;
    String currentDate,currentMonth,currentYear;
    EditText inputdate;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_datewise, container, false);
        loadcurrentdatedate();
        view.findViewById(R.id.btnSearch).setVisibility(View.VISIBLE);
        inputdate = view.findViewById(R.id.tv_start_date);
        
        view.findViewById(R.id.btnSearch).setOnClickListener(view -> validateData());
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadcurrentdatedate() {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Note: Months are zero-based
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Display current date
        String currentDate = year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
        getData(currentDate);
    }


    private void validateData() {
        {
            String date = inputdate.getText().toString().trim();

            if (date.isEmpty()) {
                inputdate.setError("Enter Date");
                inputdate.requestFocus();
                return;
            }
            else {
                getData(date);
            }

        }
    }


    private void getData(String date){

        Call<DatewiseResponse> call = RetrofitClient.getInstance().getApi().DateResponse(date);
        call.enqueue(new Callback<DatewiseResponse>() {

            @Override
            public void onResponse(Call<DatewiseResponse> call, Response<DatewiseResponse> response) {

                if (response.isSuccessful()) {

                    DatewiseResponse list = response.body();
                    predictionsList = list.getData();
                    showCatData();

                }
            }

            @Override
            public void onFailure(Call<DatewiseResponse> call, Throwable t) {

                t.printStackTrace();

            }
        });



    }

    private void showCatData() {
        recyclerView = view.findViewById(R.id.predictions_recycler_view);
        predictionsAdapter = new TipsAdapter(getActivity(), predictionsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(predictionsAdapter);

    }
}