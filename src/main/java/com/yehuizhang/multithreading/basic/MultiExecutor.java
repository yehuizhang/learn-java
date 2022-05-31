package com.yehuizhang.multithreading.basic;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {
    private final List<Runnable> tasks;
    /*
     * @param tasks to executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    public void executeAll() {
        List<Thread> threads = new ArrayList<>();
        for(Runnable task : tasks) {
            Thread t = new Thread(task);
            threads.add(t);
        }

        threads.stream().forEach(t -> t.start());
    }
}