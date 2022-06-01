package com.yehuizhang.multithreading.locking;

public class ProblematicExample {

    public static void main(String[] args) {

        InventoryCounter inventoryCounter = new InventoryCounter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
//                inventoryCounter.increase();
                inventoryCounter.increaseSync();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
//                inventoryCounter.decrease();
                inventoryCounter.decreaseSync();
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
        private int items = 0;

        public void increase() {
            items++;
        }

        public synchronized void increaseSync() {
            items++;
        }

        public void decrease() {
            items--;
        }

        public synchronized void decreaseSync() {
            items--;
        }

        public int getItems() {
            return items;
        }
    }
}
