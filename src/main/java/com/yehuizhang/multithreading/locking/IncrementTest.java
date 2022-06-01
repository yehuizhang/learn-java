package com.yehuizhang.multithreading.locking;

import java.util.Random;

public class IncrementTest {
    public static void main(String[] args) {
        SharedClass sharedClass = new SharedClass();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1 << 18; i++) {
                sharedClass.add();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1 << 18; i++) {
                sharedClass.checkForDataRace();
            }
        });

        thread1.start();
        thread2.start();
    }


    private static class SharedClass {
        private int x, y;
//        private volatile int x, y;

        public void add() {
            x++;
            y++;
        }

        public void checkForDataRace() {
            if (y > x) {
                System.out.println("y > x - Data Race is detected");
            }
        }
    }
}