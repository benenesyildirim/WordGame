package com.benenesyildirim.wordgame;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StartScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private Button playButton, leaderBoardButton, infoButton;
    private SharedPreferences sharedPreferences;
    private FirebaseDatabase firebaseDatabase;
    private TextView welcomeUser, usersHighScore;

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
        usersHighScore = findViewById(R.id.usersHighScore);

        playButton.setOnClickListener(this);
        leaderBoardButton.setOnClickListener(this);
        infoButton.setOnClickListener(this);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
                        //usersLastScore.setText(getString(R.string.users_last_score, String.valueOf(value.getLastScore())));
                        usersHighScore.setText(getString(R.string.users_high_score, String.valueOf(value.getHighScore())));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    @SuppressLint({"ResourceAsColor", "NewApi"})
    @Override
    public void onClick(View view) {
        if (view.getId() == playButton.getId()) {
            checkUserPropertiesForPlay();
        }
        if (view.getId() == leaderBoardButton.getId()) {
            Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Coming Soon!", Snackbar.LENGTH_SHORT);
            View view1= snack.getView();
            view1.setBackgroundColor(R.color.red);
            TextView tv = view1.findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(ContextCompat.getColor(StartScreenActivity.this, R.color.comingSoon));
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tv.setTextSize(20);
            snack.show();
        }
        if (view.getId() == infoButton.getId()) {
            InfoDialog infoDialog = new InfoDialog(StartScreenActivity.this);
            infoDialog.show();
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
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
