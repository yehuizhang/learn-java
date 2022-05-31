package com.yehuizhang.multithreading.coordination;

import java.math.BigInteger;

public class ComplexCalculation {
    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        BigInteger result;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */

        PowerCalculatingThread left = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread right = new PowerCalculatingThread(base2, power2);

        left.start();
        right.start();

        try {
            left.join();
            right.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result = left.getResult().add(right.getResult());

        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }
        }

        public BigInteger getResult() {
            return result;
        }
    }
}