package tech.aftershock.athenaeum.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tech.aftershock.athenaeum.PreviousQuestionBoard;
import tech.aftershock.athenaeum.R;
import tech.aftershock.athenaeum.adapters.StreamAdapter;
import tech.aftershock.athenaeum.libs.RecyclerItemClickListener;
import tech.aftershock.athenaeum.libs.RecyclerItemTouchListener;
import tech.aftershock.athenaeum.models.Stream;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrevQuestions extends Fragment {

    private View mView;
    private RecyclerView mStreamList;

    private List<Stream> mStreams;
    private StreamAdapter mAdapter;

    public PrevQuestions() { }

    public static PrevQuestions newInstance() {

        Bundle args = new Bundle();

        PrevQuestions fragment = new PrevQuestions();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_prev_questions, container, false);

        mStreamList = mView.findViewById(R.id.stream_list);

        createList();

        mAdapter = new StreamAdapter(mStreams);
        mStreamList.setLayoutManager(new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false));
        mStreamList.addOnItemTouchListener(new RecyclerItemTouchListener(getActivity(), mStreamList, new RecyclerItemClickListener() {
            @Override
            public void onClick(View item, int position) {
                Intent intent = new Intent(getActivity(), PreviousQuestionBoard.class);
                intent.putExtra("stream", position);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View item, int position) { }
        }));
        mStreamList.setAdapter(mAdapter);

        return mView;
    }

    private void createList() {
        mStreams = new ArrayList<>();
        mStreams.add(new Stream(0, "B.Tech", R.drawable.btech_banner));
        mStreams.add(new Stream(1, "BHM / MHA", R.drawable.bhm_banner));
        mStreams.add(new Stream(2, "BMS / MMS", R.drawable.bms_banner));
        mStreams.add(new Stream(3, "MBA / BBA", R.drawable.bba_banner));
        mStreams.add(new Stream(4, "BCA / MCA", R.drawable.bca_banner));
    }
}
