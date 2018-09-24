package com.benenesyildirim.wordgame;

import java.io.Serializable;

public class UserProperties implements Serializable {

    private String username, userEmail;
    private int lastScore, highScore;

    public UserProperties() {
    }

    public UserProperties(String username, String userEmail, int lastScore, int highScore) {
        this.username = username;
        this.userEmail = userEmail;
        this.lastScore = lastScore;
        this.highScore = highScore;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getLastScore() {
        return lastScore;
    }

    public void setLastScore(int lastScore) {
        this.lastScore = lastScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
