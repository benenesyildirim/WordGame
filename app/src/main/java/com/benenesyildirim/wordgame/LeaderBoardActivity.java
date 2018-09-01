package com.benenesyildirim.wordgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class LeaderBoardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        recyclerView = findViewById(R.id.leaderboardRV);

        LeaderboardListAdapter leaderBoardListAdapter = new LeaderboardListAdapter(LeaderBoardActivity.this);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(leaderBoardListAdapter);
    }
}
