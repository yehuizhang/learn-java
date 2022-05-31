package com.yehuizhang.multithreading.basic;

public class ThreadBasic2 extends Thread {
    @Override
    public void run() {
        this.setName(ThreadBasic2.class.getSimpleName());
        System.out.println("We are now in thread " + this.getName());
    }

    public static void main(String[] args) {
        ThreadBasic2 myThread = new ThreadBasic2();
        myThread.run();
    }
}
