package com.benenesyildirim.wordgame;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameScreen extends AppCompatActivity implements ButtonsAdapter.ButtonsClickListener {

    private RecyclerView viewsList, buttonsList;
    private List<String> separatedWord = new ArrayList<>();
    private List<String> trueWord = new ArrayList<>();
    private List<String> shuffledSeparatedWord;
    private int level = 1, score = 0;
    private List<ViewObject> viewObjectList;
    private TextView timeText;
    private List<String> usersWord = new ArrayList<>();
    private TextView levelText, scoreText;
    private InputStream stream;
    private CountDownTimer countDownTimer;
    private String word;
    private long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        initViews();
        initBannerAds();
        takeWord();
        createRandomLettersandButtons();
        fillButtons();
        startTimer();
    }

    private void initViews() {
        levelText = findViewById(R.id.levelTxt);
        timeText = findViewById(R.id.timeTxt);
        scoreText = findViewById(R.id.scoreTxt);
        viewsList = findViewById(R.id.viewsRL);
        buttonsList = findViewById(R.id.buttonsRL);
        levelText.setText(getString(R.string.level, level));
        scoreText.setText(getString(R.string.score, score));
        viewObjectList = new ArrayList<>();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void initBannerAds() {
        MobileAds.initialize(this, getString(R.string.banner_ads_id));
        AdView adViewTop = findViewById(R.id.adViewTop);
        AdView adViewBottom = findViewById(R.id.adViewBottom);
        AdRequest adRequest = new AdRequest.Builder().build();
        adViewTop.loadAd(adRequest);
        adViewBottom.loadAd(adRequest);
    }

    private void takeWord() {
        String data;
        List<String> dataList = new ArrayList<>();
        Random random = new Random();
        int randomNumber = random.nextInt(100);

        getWordSize();
        selectRawForLevel();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        try {
            while (((data = reader.readLine()) != null)) {
                dataList.add(data);
            }
            for (int i = 0; i < dataList.size(); i++) {
                if (i == randomNumber) {
                    String word = dataList.get(i);
                    separateWords(word);
                    stream.close();
                }
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "FATAL ERROR !", Toast.LENGTH_SHORT).show();
        }
    }

    private void selectRawForLevel() {
        switch (level) {
            case 1:
                stream = this.getResources().openRawResource(R.raw.four_letter_words);
                break;
            case 2:
                stream = this.getResources().openRawResource(R.raw.four_letter_words);
                break;
            case 3:
                stream = this.getResources().openRawResource(R.raw.five_letter_words);
                break;
            case 4:
                stream = this.getResources().openRawResource(R.raw.five_letter_words);
                break;
            case 5:
                stream = this.getResources().openRawResource(R.raw.six_letter_words);
                break;
            case 6:
                stream = this.getResources().openRawResource(R.raw.six_letter_words);
                break;
            case 7:
                stream = this.getResources().openRawResource(R.raw.seven_letter_words);
                break;
            case 8:
                stream = this.getResources().openRawResource(R.raw.eight_letter_words);
                break;
            case 9:
                stream = this.getResources().openRawResource(R.raw.nine_letter_words);
                break;
            case 10:
                stream = this.getResources().openRawResource(R.raw.ten_letter_words);
                break;
        }
    }

    private void separateWords(String word) {
        this.word = word;
        word = String.valueOf(word.toCharArray());
        separatedWord = Arrays.asList(word.split(""));

        for (int i = 0; i < separatedWord.size(); i++) {
            if (!separatedWord.get(i).isEmpty()) {
                trueWord.add(separatedWord.get(i));
            }
        }
    }

    private int getWordSize() {
        switch (level) {
            case 1:
                return 4;
            case 2:
                return 4;
            case 3:
                return 5;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 8;
            case 9:
                return 9;
            case 10:
                return 10;
            default:
                return 8;
        }
    }

    private void createRandomLettersandButtons() {
        Collections.shuffle(separatedWord);
        shuffledSeparatedWord = new ArrayList<>();
        for (int i = 0; i < separatedWord.size(); i++) {
            String letters = separatedWord.get(i);
            if (!letters.isEmpty())
                shuffledSeparatedWord.add(letters);
        }
    }

    private void fillViews() {
        ViewsAdapter viewsAdapter = new ViewsAdapter(viewObjectList);
        viewsList.setLayoutManager(new GridLayoutManager(getApplicationContext(), getSpanCountView(level)));
        viewsList.setAdapter(viewsAdapter);
    }

    private void fillButtons() {
        ButtonsAdapter buttonsAdapter = new ButtonsAdapter(shuffledSeparatedWord, this);
        buttonsList.setLayoutManager(new GridLayoutManager(getApplicationContext(), getSpanCountButton(level)));
        buttonsList.setAdapter(buttonsAdapter);
    }

    @Override
    public void buttonsClickListener(String letter) {
        ViewObject viewObject = new ViewObject(letter);
        if (viewObjectList.size() < getSpanCountView(level))
            if (viewObjectList.isEmpty()) {
                viewObject.setOpened(true);
                viewObjectList.add(viewObject);
                usersWord.add(letter);
            } else {
                if (!viewObjectList.contains(viewObject)) {
                    viewObject.setOpened(true);
                    viewObjectList.add(viewObject);
                    usersWord.add(letter);
                }
            }
        fillViews();
        if (!(viewObjectList.size() < getSpanCountView(level))) {
            if (usersWord.equals(trueWord)) {
                nextLevel();
            }
            if (!usersWord.equals(trueWord)) {
                wrongAnswer();
            }
        }
    }

    private void wrongAnswer() {
        score -= 10;
        if (score <= -100) {
            Toast.makeText(getApplicationContext(), "GOod ByE!", Toast.LENGTH_SHORT).show();
            gameOver();
            countDownTimer.cancel();
        } else {
            scoreText.setText(getString(R.string.score, score));
            usersWord.clear();
            (new Handler()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    viewObjectList.clear();
                    fillViews();
                }
            }, 100);
            Toast.makeText(getApplicationContext(), "WRONG ANSWER !", Toast.LENGTH_SHORT).show();
        }
    }

    private void nextLevel() {
        setLevelAndScore();
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (level == 11) {
                    gameOver();
                }
                if (level != 11) {
                    viewObjectList.clear();
                    fillViews();
                    usersWord.clear();
                    trueWord.clear();
                    takeWord();
                    createRandomLettersandButtons();
                    fillButtons();
                }
            }
        }, 250);
        Toast.makeText(getApplicationContext(), "TRUE ANSWER !", Toast.LENGTH_SHORT).show();
    }

    private void setLevelAndScore() {
        level++;
        score += 50;
        levelText.setText(getString(R.string.level, level));
        scoreText.setText(getString(R.string.score, score));
    }

    private int getSpanCountView(int level) {
        switch (level) {
            case 1:
                return 4;
            case 2:
                return 4;
            case 3:
                return 5;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 8;
            case 9:
                return 9;
            case 10:
                return 10;
            default:
                return 8;
        }
    }

    private int getSpanCountButton(int level) {
        switch (level) {
            case 1:
                return 2;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 3;
            case 5:
                return 3;
            case 6:
                return 3;
            case 7:
                return 4;
            case 8:
                return 4;
            case 9:
                return 5;
            case 10:
                return 5;
            default:
                return 4;
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(120000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                millisUntilFinished /= 1000;
                timeText.setText(getString(R.string.time, millisUntilFinished));
                time = millisUntilFinished;
            }

            public void onFinish() {
                timeText.setText(R.string.gameOver);
                gameOver();
            }
        };
        countDownTimer.start();
    }

    private void gameOver() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users");
        SharedPreferences sharedPreferences = getSharedPreferences("savedUserID", MODE_PRIVATE);
        SharedPreferences.Editor editor = getSharedPreferences("savedUserID", MODE_PRIVATE).edit();
        String userID = sharedPreferences.getString("userID", "");

        int timesScore = (int) ((time * 2) / 3);
        if (level == 11) {
            score += timesScore;
        }

        reference.child(userID).child("lastScore").setValue(score);
        saveScore(score);

        long highScore = sharedPreferences.getLong("highScore", 0L);

        if (highScore == 0) {
            reference.child(userID).child("highScore").setValue(score);
            editor.putLong("highScore", (long) score);
            editor.apply();
            showGameOverDialog(score, false);
        } else {
            if (score > highScore) {
                reference.child(userID).child("highScore").setValue(score);
                editor.putLong("highScore", (long) score);
                editor.apply();
                showGameOverDialog(score, true);
            }
            if (score < highScore) {
                showGameOverDialog(score, false);
            }
            if (score == highScore) {
                showGameOverDialog(score, false);
            }
        }
    }

    private void saveScore(long savedScore) {
        SharedPreferences.Editor editor = getSharedPreferences("savedUserID", MODE_PRIVATE).edit();
        editor.putLong("score", savedScore);
        editor.apply();
    }

    private void showGameOverDialog(int score, boolean isItHighscore) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(GameScreen.this);
        @SuppressLint("InflateParams") View v = getLayoutInflater().inflate(R.layout.game_over_dialog, null);
        mBuilder.setView(v);
        final AlertDialog dialog = mBuilder.create();

        Button homeBtn = v.findViewById(R.id.homeBTN);
        TextView wordTxt = v.findViewById(R.id.wordWasTXT);
        TextView scoreTxt = v.findViewById(R.id.scoreTXT);
        ImageView image = v.findViewById(R.id.dialog_image);

        image.setImageResource(R.drawable.finish_dialog);
        wordTxt.setText(getString(R.string.word_was, word));
        scoreTxt.setText(getString(R.string.dialog_score, score));

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playGame = new Intent(GameScreen.this, StartScreenActivity.class);
                startActivity(playGame);
                ActivityCompat.finishAffinity(GameScreen.this);
                dialog.dismiss();
            }
        });

        if (isItHighscore) {
            scoreTxt.setText(getString(R.string.dialog_high_score, score));
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}