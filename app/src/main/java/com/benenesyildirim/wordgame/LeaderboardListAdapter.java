package com.benenesyildirim.wordgame;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class LeaderboardListAdapter /*extends RecyclerView.Adapter<RecyclerView.ViewHolder> */{

    /*private List<String> leaderBoardList;
    private Context context;

    LeaderboardListAdapter(Context context, List<String> leaderBoardList) {
        this.context = context;
        this.leaderBoardList = leaderBoardList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.leaderboard_row_design, viewGroup, false);
        return new LeaderboardListAdapter.ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        final LeaderboardListAdapter.ListHolder listHolder = (ListHolder) viewHolder;
        final String leaders = leaderBoardList.get(i);

        listHolder.userName.setText("Enes Yıldırım");
        listHolder.userScore.setText("1205 Point");
    }

    @Override
    public int getItemCount() {
        return leaderBoardList.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder {

        TextView userName, userScore;

        ListHolder(@NonNull View v) {
            super(v);
            userName = v.findViewById(R.id.usernameLeaderboard);
            userScore = v.findViewById(R.id.userScoreLeaderboard);
        }
    }*/
}
