package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadExperiment extends Thread {

    private String name;

    public ThreadExperiment(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 50; i++) {
            log.info(name + "运行： " + i);
            //System.out.println(name + "运行： " + i);
            try {
                sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Main {
        public static void main(String[] args) {
            ThreadExperiment a = new ThreadExperiment("a");
            ThreadExperiment b = new ThreadExperiment("b");
            a.start();
            b.start();
        }
    }


}


