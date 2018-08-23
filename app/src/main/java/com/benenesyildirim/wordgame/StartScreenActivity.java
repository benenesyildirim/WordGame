package com.benenesyildirim.wordgame;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartScreenActivity extends AppCompatActivity implements View.OnClickListener {

    Button playButton,leaderBoardButton,infoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen_activity);

        initView();
    }

    private void initView() {
        playButton = findViewById(R.id.playButton);
        leaderBoardButton = findViewById(R.id.leaderboardButton);
        infoButton = findViewById(R.id.infoButton);

        playButton.setOnClickListener(this);
        leaderBoardButton.setOnClickListener(this);
        infoButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == playButton.getId()){
            Intent startToGameIntent = new Intent(this,GameScreen.class);
            startActivity(startToGameIntent);
        }
        if (view.getId() == leaderBoardButton.getId()){
            Toast.makeText(this,"Coming Soon!",Toast.LENGTH_LONG).show();
        }
        if (view.getId() == infoButton.getId()){
            Toast.makeText(this,"Googled Me Like benenesyildirim",Toast.LENGTH_LONG).show();
        }
    }
}
