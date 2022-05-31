package com.yehuizhang.design_patterns.singleton;

import java.util.function.Supplier;

public class SingletonExercise {
    public static boolean isSingleton(Supplier<Object> func)
    {
        return func.get() == func.get();
    }
}
