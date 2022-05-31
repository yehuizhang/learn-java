package com.yehuizhang.multithreading.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadInheritance {

    public static final int MAX_PASSWORD = (int)1e4;
    public static final int MAX_CHECK = (int)  1e6;
    public static final int MAX_HACKER_THREADS = (int)  10;

    public static void main(String[] args) {
        Random random = new Random();

        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

        List<Thread> threads = new ArrayList<>();
        threads.add(new PoliceThread());
        for(int i = 0; i < MAX_HACKER_THREADS; i++) {
            threads.add(new HackerThread(vault, i+1));
        }

        for(Thread thread : threads) {
            thread.start();
        }
    }

    private static class Vault {
        private int password;

        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int guess) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return this.password == guess;
        }
    }


    private static abstract class AbstractHackerThread extends Thread {
        protected Vault vault;
        protected int threadIndex;

        public AbstractHackerThread(Vault vault, int threadIndex) {
            this.vault = vault;
            this.threadIndex = threadIndex;
            this.setName(this.getClass().getSimpleName() + " " + threadIndex);
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public synchronized void start() {
            System.out.println("Starting thread " + this.getName());
            super.start();
        }
    }

    private static class HackerThread extends AbstractHackerThread {
        public HackerThread(Vault vault, int threadIndex) {
            super(vault, threadIndex);
        }

        @Override
        public void run() {
            Random random = new Random();
            int guess;
            int count = 0;
            do {
                guess = random.nextInt(MAX_PASSWORD + 1);
                count++;
            } while (!vault.isCorrectPassword(guess) && count < MAX_CHECK);

            if(vault.isCorrectPassword(guess)){
                System.out.println(this.getName() + " guessed the password " + guess);
            } else {
                System.out.println(this.getName() + " could not guess the password");
            }
            System.exit(0);
        }
    }

    private static class PoliceThread extends Thread {
        @Override
        public void run() {
            for(int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Time left " + i);
            }
            System.out.println("Game over, hackers~");
            System.exit(0);
        }
    }

}
