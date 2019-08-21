package com.example.hangmanwithmvp;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Random;

class GameDataManagement {
    private String wordList[] = {"aslan", "balik", "balina", "at", "zurafa", "geyik", "kaplan", "kedi", "kopek", "tilki", "timsah", "karinca", "tavsan"};
    private int remainingLife = 0;
    private ArrayList<String> currentWordCharArray = new ArrayList<>();
    private ArrayList<Integer> positionList = new ArrayList<>();
    private String chosenWord = "";


    void initGameData() {
        int random = new Random().nextInt(wordList.length);
        this.chosenWord = wordList[random];
        this.currentWordCharArray.clear();
        this.remainingLife = 5;

        for (int i = 0; i < chosenWord.length(); i++) {
            this.currentWordCharArray.add("_");
        }

    }

    String getCurrentWord() {
        return TextUtils.join(" ", currentWordCharArray);
    }

    int getRemainingLife() {
        return remainingLife;
    }

    void clearPositionList() {
        positionList.clear();
    }

    void checkIsWordContainsLetter(String letter) {
        for (int i = 0; i < chosenWord.length(); i++) {
            char currentLetter = chosenWord.charAt(i);
            if (String.valueOf(currentLetter).equals(letter)) {
                positionList.add(i);
            }
        }
    }

    ArrayList<Integer> getPositionListArray() {
        return this.positionList;
    }

    int getDecreasedRemainingLife() {
        remainingLife -= 1;
        return this.getRemainingLife();
    }

    boolean isThereAnyUnderscore() {
        return currentWordCharArray.contains("_");
    }

    void changeCurrentWord(String letter) {

        for (int i : positionList) {
            currentWordCharArray.set(i, letter);
        }
    }


}
