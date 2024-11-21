package com.example.myapplication;
public class LottieItem {
    private int animationFile;
    private String description;

    public LottieItem(int animationFile, String description) {
        this.animationFile = animationFile;
        this.description = description;
    }

    public int getAnimationFile() {
        return animationFile;
    }

    public String getDescription() {
        return description;
    }
}

