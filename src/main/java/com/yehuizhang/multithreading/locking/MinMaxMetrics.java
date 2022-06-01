package com.yehuizhang.multithreading.locking;

import java.util.Random;

public class MinMaxMetrics {

    private volatile long min, max;

    public MinMaxMetrics() {
        min = Long.MAX_VALUE;
        max = Long.MIN_VALUE;
    }

    public synchronized void addSample(long newSample) {
        if (newSample < min) {
            min = newSample;
        }
        if (newSample > max) {
            max = newSample;
        }
    }

    public long getMin() {
        return min;
    }

    public long getMax() {
        return max;
    }

    public static void main(String[] args) {
        MinMaxMetrics minMaxMetrics = new MinMaxMetrics();

        Thread thread1 = new Thread(() -> {
            Random r = new Random();
            for(int i = 0; i < 10; i++) {
                long v = r.nextLong(-2000, 2000);
                minMaxMetrics.addSample(v);
                System.out.println(Thread.currentThread().getId() + " " + v + " " + minMaxMetrics.getMin() + " " + minMaxMetrics.getMax());
            }
        });

        Thread thread2 = new Thread(() -> {
            Random r = new Random();
            for(int i = 0; i < 10; i++) {
                long v = r.nextLong(-2000, 2000);
                minMaxMetrics.addSample(v);
                System.out.println(Thread.currentThread().getId() + " " + v + " " + minMaxMetrics.getMin() + " " + minMaxMetrics.getMax());
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}