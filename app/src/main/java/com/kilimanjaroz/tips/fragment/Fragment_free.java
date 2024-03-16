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
import androidx.viewpager.widget.ViewPager;

import com.kilimanjaroz.tips.R;
import com.kilimanjaroz.tips.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Fragment_free extends Fragment {
    View view;
    public static final int int_value_10 = 10;
    TabLayout tabLayout;
    ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ArrayList<Fragment> fragmentList;
    private ArrayList<String> fragmentTitleList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        //loadSlider();

        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewPager);
        tab();


        return view;
    }

    private void tab() {
        getFragmentTitleList();
        getFragmentList();

        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragmentList, fragmentTitleList);
        viewPager.setOffscreenPageLimit(int_value_10);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setSaveEnabled(false);
    }
    private void getFragmentTitleList() {
        fragmentTitleList = new ArrayList<>();
        fragmentTitleList.add("VIP");
        fragmentTitleList.add("FREE");

    }

    private void getFragmentList() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new PreviousFragment());
        fragmentList.add(new TodayFragment());
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
    public void onResume() {
        super.onResume();

    }
}
