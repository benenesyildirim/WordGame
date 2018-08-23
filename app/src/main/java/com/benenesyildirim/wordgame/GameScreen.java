package com.benenesyildirim.wordgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class GameScreen extends AppCompatActivity {

    private AdView adViewTop,adViewBottom;
    private AdRequest adRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        MobileAds.initialize(this, "ca-app-pub-8661505007193806/5515495226");

        initBannerAds();
    }

    private void initBannerAds() {
        adViewTop = findViewById(R.id.adViewTop);
        adViewBottom = findViewById(R.id.adViewBottom);
        adRequest = new AdRequest.Builder().build();
        adViewTop.loadAd(adRequest);
        adViewBottom.loadAd(adRequest);
    }
}
