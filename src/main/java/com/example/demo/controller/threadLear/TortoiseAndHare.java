package com.example.demo.controller.threadLear;

public class TortoiseAndHare implements Runnable {
    @Override
    public void run() {

    }


    public static void main(String[] args) {
        String s = "001_3516_pos3_dist175_c_left_fast_straight_wrist.mp4";
        System.out.println(s.indexOf("_"));
        System.out.println(s.substring(0, s.indexOf("_")));
    }
}
