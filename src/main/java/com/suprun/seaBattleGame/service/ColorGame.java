package com.suprun.seaBattleGame.service;

public class ColorGame {
    public static String blue = "\033[1;34m";
    public static String red = "\033[1;31m";
    public static String yellow = "\033[1;33m";
    public static String white = "\033[1;37m";
    static String reset = "\033[0m";

    public static String setColor(String text, String color) {
        return color + text + reset;
    }
}
