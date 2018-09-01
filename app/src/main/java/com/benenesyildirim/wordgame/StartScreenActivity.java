package com.benenesyildirim.wordgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StartScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private Button playButton, leaderBoardButton, infoButton;
    private SharedPreferences sharedPreferences;
    private FirebaseDatabase firebaseDatabase;
    private TextView welcomeUser, usersLastScore, usersHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen_activity);
        sharedPreferences = getSharedPreferences("savedUserID", MODE_PRIVATE);
        initView();
        fillViewIfUserCreatedElseNotCreated();
    }

    private void initView() {
        playButton = findViewById(R.id.playButton);
        leaderBoardButton = findViewById(R.id.leaderboardButton);
        infoButton = findViewById(R.id.infoButton);
        firebaseDatabase = FirebaseDatabase.getInstance();
        welcomeUser = findViewById(R.id.welcomeUsername);
        usersLastScore = findViewById(R.id.usersLastScore);
        usersHighScore = findViewById(R.id.usersHighScore);

        playButton.setOnClickListener(this);
        leaderBoardButton.setOnClickListener(this);
        infoButton.setOnClickListener(this);
    }

    private void fillViewIfUserCreatedElseNotCreated() {
        if (!sharedPreferences.getString("userID", "").isEmpty()) {
            DatabaseReference user = firebaseDatabase.getReference("users").child(sharedPreferences.getString("userID", ""));
            user.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        UserProperties value = dataSnapshot.getValue(UserProperties.class);
                        welcomeUser.setText(getString(R.string.welcome_to_user, String.valueOf(value.getUsername())));
                        usersLastScore.setText(getString(R.string.users_last_score, String.valueOf(value.getLastScore())));
                        usersHighScore.setText(getString(R.string.users_high_score, String.valueOf(value.getHighScore())));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == playButton.getId()) {
            checkUserPropertiesForPlay();
        }
        if (view.getId() == leaderBoardButton.getId()) {
            Intent goToLeaderBoardScreen = new Intent(this, LeaderBoardActivity.class);
            startActivity(goToLeaderBoardScreen);
        }
        if (view.getId() == infoButton.getId()) {
            InfoDialog infoDialog = new InfoDialog(StartScreenActivity.this);
            infoDialog.show();
        }
    }

    private void checkUserPropertiesForPlay() {
        if (!sharedPreferences.getString("userID", "").isEmpty()) {
            Intent startToGameIntent = new Intent(this, GameScreen.class);
            startActivity(startToGameIntent);
        } else {
            UserAddDialog userAddDialog = new UserAddDialog(StartScreenActivity.this);
            userAddDialog.show();
        }
    }
}
