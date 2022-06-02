package com.yehuizhang.multithreading.lock_free;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class AtomicStack<T> {

    private AtomicReference<StackNode<T>> head = new AtomicReference<>();
    private AtomicInteger opsCount = new AtomicInteger();

    public void push(T value) {
        StackNode<T> newHeadNode = new StackNode<>(value);

        while (true) {
            StackNode<T> currentHeadNode = head.get();
            newHeadNode.next = currentHeadNode;
            if (head.compareAndSet(currentHeadNode, newHeadNode)) {
                break;
            } else {
                LockSupport.parkNanos(1);
            }
        }
        opsCount.incrementAndGet();
    }

    public T pop() {
        StackNode<T> currentHeadNode = head.get();

        while (currentHeadNode != null) {
            StackNode<T> newHeadNode = currentHeadNode.next;
            if (head.compareAndSet(currentHeadNode, newHeadNode)) {
                break;
            } else {
                LockSupport.parkNanos(1);
                currentHeadNode = head.get();
            }
        }
        opsCount.incrementAndGet();


        return currentHeadNode != null ? currentHeadNode.value : null;
    }

    public int getCounter() {
        return opsCount.get();
    }

    public static void main(String[] args) throws InterruptedException {
//        StandardStack<Integer> stack = new StandardStack<>();
        AtomicStack<Integer> stack = new AtomicStack<>();
        Random random = new Random();

        for (int i = 0; i < 100000; i++) {
            stack.push(random.nextInt());
        }

        List<Thread> threads = new ArrayList<>();

        int pushingThreads = 2;
        int poppingThreads = 2;

        for (int i = 0; i < pushingThreads; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    stack.push(random.nextInt());
                }
            });
            threads.add(thread);
        }

        for (int i = 0; i < poppingThreads; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    stack.pop();
                }
            });
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.setDaemon(true);
            thread.start();
        }

        Thread.sleep(10000);

        System.out.println(String.format("%,d operations were performed in 10 seconds ", stack.getCounter()));
    }


    public static class StandardStack<T> {
        private StackNode<T> head;
        private int opsCount = 0;

        public synchronized void push(T value) {
            StackNode<T> newHead = new StackNode<>(value);
            newHead.next = head;
            head = newHead;
            opsCount++;
        }

        public synchronized T pop() {
            if (head == null) {
                opsCount++;
                return null;
            }

            T value = head.value;
            head = head.next;
            opsCount++;
            return value;
        }

        public int getCounter() {
            return opsCount;
        }
    }

    private static class StackNode<T> {
        public T value;
        public StackNode<T> next;

        public StackNode(T value) {
            this.value = value;
        }
    }
}
