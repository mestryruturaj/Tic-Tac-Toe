package com.tic_tac_toe.enums;

public enum MessageColor {
    RED_TEXT("\u001B[31m"),
    RESET("\u001B[0m");

    MessageColor(String color) {
        this.color = color;
    }

    private String color;

    public String getColor() {
        return this.color;
    }
}
