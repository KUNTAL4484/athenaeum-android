package tech.aftershock.athenaeum.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.aftershock.athenaeum.R;
import tech.aftershock.athenaeum.adapters.VideoBoardAdapter;
import tech.aftershock.athenaeum.libs.NetworkClient;
import tech.aftershock.athenaeum.libs.NetworkOperations;
import tech.aftershock.athenaeum.models.VideoBoard;

/**
 * A simple {@link Fragment} subclass.
 */
public class Videos extends Fragment {

    private View mView;
    private RecyclerView mBoard;

    private NetworkOperations mOperations;
    private VideoBoardAdapter mAdapter;

    private Call<List<VideoBoard>> mGetVideoBoardCall = null;

    public Videos() { }

    public static Videos newInstance() {
        Bundle args = new Bundle();

        Videos fragment = new Videos();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_videos, container, false);

        mBoard = mView.findViewById(R.id.video_board_list);

        mOperations = NetworkClient.getOperations(getActivity());

        mAdapter = new VideoBoardAdapter(getActivity(), new ArrayList<VideoBoard>());
        mBoard.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        mBoard.setAdapter(mAdapter);

        loadData();

        return mView;
    }

    private void loadData() {
        mGetVideoBoardCall = mOperations.getVideoBoard();
        mGetVideoBoardCall.enqueue(new Callback<List<VideoBoard>>() {
            @Override
            public void onResponse(Call<List<VideoBoard>> call, Response<List<VideoBoard>> response) {
                if(response.isSuccessful()) {
                    List<VideoBoard> boards = response.body();
                    if(boards != null)
                        mAdapter.addBoards(boards);
                }
            }

            @Override
            public void onFailure(Call<List<VideoBoard>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
