package com.yehuizhang.design_patterns.composite;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompositeExerciseTest {

    @Test
    void SumFn() {

        SingleValue sv = new SingleValue(10);
        ManyValues mv = new ManyValues();
        mv.add(20);
        mv.add(5);
        MyList myList = new MyList(List.of(sv, mv));
        assertEquals(myList.sum(), 35);
    }
}