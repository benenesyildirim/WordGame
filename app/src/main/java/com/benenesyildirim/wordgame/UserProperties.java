package com.benenesyildirim.wordgame;

import java.io.Serializable;

public class UserProperties implements Serializable {

    private String username, userEmail, lastScore, totalScore, highScore;

    public UserProperties() {

    }

    public UserProperties(String username, String userEmail, String lastScore, String totalScore, String highScore) {
        this.username = username;
        this.userEmail = userEmail;
        this.lastScore = lastScore;
        this.totalScore = totalScore;
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

    public String getLastScore() {
        return lastScore;
    }

    public void setLastScore(String lastScore) {
        this.lastScore = lastScore;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getHighScore() {
        return highScore;
    }

    public void setHighScore(String highScore) {
        this.highScore = highScore;
    }
}
