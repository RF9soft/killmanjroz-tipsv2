package com.kilimanjaroz.tips.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kilimanjaroz.tips.fragment.FragmentFreeTips;
import com.kilimanjaroz.tips.fragment.FragmentVIPTipsCategory;

public class TabLayoutCatAdapter extends FragmentPagerAdapter {

    Context mContext;
    int mTotalTabs;

    public TabLayoutCatAdapter(Context context, FragmentManager fragmentManager, int totalTabs) {
        super(fragmentManager);
        mContext = context;
        mTotalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentFreeTips();
            case 1:
                return new FragmentVIPTipsCategory();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTotalTabs;
    }
}
