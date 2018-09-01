package com.benenesyildirim.wordgame;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

public class InfoDialog extends Dialog implements View.OnClickListener {

    Button twitterBtn, instagramBtn, linkedinBtn, youtubeBtn, playstoreBtn, internetBTN;

    public InfoDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_dialog);
        initViews();
    }

    private void initViews() {
        twitterBtn = findViewById(R.id.twitterBTN);
        instagramBtn = findViewById(R.id.instagramBTN);
        linkedinBtn = findViewById(R.id.linkedinBTN);
        youtubeBtn = findViewById(R.id.youtubeBTN);
        playstoreBtn = findViewById(R.id.playstoreBTN);
        internetBTN = findViewById(R.id.internetBTN);

        twitterBtn.setOnClickListener(this);
        instagramBtn.setOnClickListener(this);
        linkedinBtn.setOnClickListener(this);
        youtubeBtn.setOnClickListener(this);
        playstoreBtn.setOnClickListener(this);
        internetBTN.setOnClickListener(this);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == twitterBtn.getId()) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitter.com/benenesyildirim"));
            getContext().startActivity(intent);
        }
        if (view.getId() == instagramBtn.getId()) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/benenesyildirim"));
            getContext().startActivity(intent);
        }
        if (view.getId() == linkedinBtn.getId()) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/enes-yıldırım-54aaa2114"));
            getContext().startActivity(intent);
        }
        if (view.getId() == youtubeBtn.getId()) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/enesyildirim"));
            getContext().startActivity(intent);
        }
        if (view.getId() == playstoreBtn.getId()) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=5520947670843912820"));
            getContext().startActivity(intent);
        }
        if (view.getId() == internetBTN.getId()) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://benenesyildirim.com"));
            getContext().startActivity(intent);
        }
    }
}
