package com.yehuizhang.multithreading.locking;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterAtomic {

    public static void main(String[] args) {

        InventoryCounter inventoryCounter = new InventoryCounter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.increase();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.decrease();
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
        System.out.println(inventoryCounter.getItems());
    }


    private static class InventoryCounter {
        private AtomicInteger items;

        public InventoryCounter() {
            this.items = new AtomicInteger();
        }

        public void increase() {
            items.incrementAndGet();
        }

        public void decrease() {
            items.decrementAndGet();
        }

        public int getItems() {
            return items.get();
        }
    }
}
