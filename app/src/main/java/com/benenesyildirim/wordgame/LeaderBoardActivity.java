package com.benenesyildirim.wordgame;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderBoardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> highScoreList = new ArrayList<>();
    private List<String> leaderBoardNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
//
//        recyclerView = findViewById(R.id.leaderboardRV);
//        getHighScores();

//        LeaderboardListAdapter leaderBoardListAdapter = new LeaderboardListAdapter(LeaderBoardActivity.this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
//        recyclerView.setAdapter(leaderBoardListAdapter);
    }
//    public void getHighScores() {
//        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference usersRef = rootRef.child("users");
//        ValueEventListener eventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    String highScore = String.valueOf(ds.child("highScore").getValue());
//                    String names = (String) ds.child("userName").getValue();
//                    highScoreList.add(highScore);
//                    leaderBoardNames.add(names);
//                    Collections.sort(Collections.singletonList(String.valueOf(ds.child("highScore").getValue())), Collections.<String>reverseOrder());
//                }
//
//
////                LeaderboardListAdapter leaderBoardListAdapter = new LeaderboardListAdapter(LeaderBoardActivity.this, leaderBoardList);
////                //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
////                recyclerView.setAdapter(leaderBoardListAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        };
//        usersRef.addListenerForSingleValueEvent(eventListener);
//    }
}
