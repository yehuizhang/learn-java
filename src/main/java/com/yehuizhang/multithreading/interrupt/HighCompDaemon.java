package com.yehuizhang.multithreading.interrupt;

import java.math.BigInteger;

public class HighCompDaemon {

    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputationTask(new BigInteger("2000000"), new BigInteger("100000000000000000")));
        thread.setDaemon(true);
        thread.start();

        try {
            Thread.sleep(10000);
            thread.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private record LongComputationTask(BigInteger base, BigInteger power) implements Runnable {

        private BigInteger pow() {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }
            return result;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + pow());
            //            System.out.println(base + "^" + power + " = " + powWithFlaw());
        }
    }
}
