package com.example.selenide_demo.utils;

public class Timer {

    public static void wait(long mills, String msg) {
        try {
            System.out.println(msg);
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
