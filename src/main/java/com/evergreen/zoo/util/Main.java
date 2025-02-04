package com.evergreen.zoo.util;

public class Main {
    public static void main(String[] args) {
        int x = 1;
        int y = 2;

        x = y+x;
        y = x-y;
        x = x-y;
        System.out.println("X : "+x);
        System.out.println("y : "+y);
    }
}
