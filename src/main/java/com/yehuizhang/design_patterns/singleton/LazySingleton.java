package com.yehuizhang.design_patterns.singleton;

public class LazySingleton {

    private static LazySingleton instance;

    public LazySingleton() {
        System.out.println("Initializing a lazy singleton");
    }

    public static LazySingleton getInstance() {
        // Double-checked locking; Thread-safe
        //noinspection DoubleCheckedLocking
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    //noinspection InstantiationOfUtilityClass
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}

// Better approach than LazySingleton
class InnerStaticSingleton {

    private InnerStaticSingleton() {
    }

    private static class Impl {
        private static final InnerStaticSingleton instance = new InnerStaticSingleton();
    }

    public InnerStaticSingleton getInstance() {
        return Impl.instance;
    }
}
