package tech.aftershock.athenaeum.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import tech.aftershock.athenaeum.R;
import tech.aftershock.athenaeum.adapters.HomePagerAdapter;


public class Home extends Fragment {

    private View mView;
    private TabLayout mTabs;
    private ViewPager mPager;

    public Home() { }

    public static Home newInstance() {
        return new Home();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);

        mTabs = mView.findViewById(R.id.home_tabs);
        mPager = mView.findViewById(R.id.home_pager);

        mPager.setAdapter(new HomePagerAdapter(getChildFragmentManager()));

        mTabs.post(new Runnable() {
            @Override
            public void run() {
                mTabs.setupWithViewPager(mPager);
            }
        });

        return mView;
    }

}
