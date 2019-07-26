package tech.aftershock.athenaeum.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tech.aftershock.athenaeum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Videos extends Fragment {


    public Videos() { }

    public static Videos newInstance() {

        Bundle args = new Bundle();

        Videos fragment = new Videos();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_videos, container, false);
    }

}
