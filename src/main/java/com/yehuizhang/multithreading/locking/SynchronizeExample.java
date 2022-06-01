package com.yehuizhang.multithreading.locking;

public class SynchronizeExample {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.decrement();
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

        System.out.println("We currently have " + inventoryCounter.getItems() + " items");
    }

    private static class InventoryCounter {
        private int items = 0;

        final Object lock = new Object();

        public void increment() {
            synchronized (this.lock) {
                items++;
            }
        }

        public void decrement() {
            synchronized (this.lock) {
                items--;
            }
        }

        public int getItems() {
            synchronized (this.lock) {
                return items;
            }
        }
    }
}
