package com.benenesyildirim.wordgame;

public class ViewObject {
    private String letters;
    private boolean isOpened;

    public ViewObject (String letters){
        this.letters = letters;
    }

    public ViewObject (){

    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }
}
