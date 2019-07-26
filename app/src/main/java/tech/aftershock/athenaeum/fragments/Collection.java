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
public class Collection extends Fragment {


    public Collection() {
        // Required empty public constructor
    }

    public static Collection newInstance() {

        Bundle args = new Bundle();

        Collection fragment = new Collection();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collection, container, false);
    }

}
