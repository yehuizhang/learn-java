package com.yehuizhang.multithreading.inter_thread_communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleCountDownLatch {
    private ReentrantLock reentrantLock;
    Condition condition;
    private int count;

    public SimpleCountDownLatch(int count) {
        this.count = count;
        if (count < 0) {
            throw new IllegalArgumentException("count cannot be negative");
        }
        reentrantLock = new ReentrantLock();
        condition = reentrantLock.newCondition();
    }

    /**
     * Causes the current thread to wait until the latch has counted down to zero.
     * If the current count is already zero then this method returns immediately.
     */
    public void await() throws InterruptedException {
        try {
            reentrantLock.lock();
            while (count > 0) {
                condition.await();
            }
        } finally {
            reentrantLock.unlock();
        }

    }

    /**
     * Decrements the count of the latch, releasing all waiting threads when the count reaches zero.
     * If the current count already equals zero then nothing happens.
     */
    public void countDown() {
        try {
            reentrantLock.lock();
            if(count > 0) count--;
            if (count == 0) {
                condition.signalAll();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    /**
     * Returns the current count.
     */
    public int getCount() {
        return count;
    }
}
