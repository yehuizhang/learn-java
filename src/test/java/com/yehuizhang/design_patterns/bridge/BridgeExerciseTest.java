package com.yehuizhang.design_patterns.bridge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BridgeExerciseTest {
    @Test
    void SquareTest() {
        assertEquals(new Triangle(new RasterRenderer()).toString(), "Drawing Triangle as pixels");
        assertEquals(new Square(new VectorRenderer()).toString(), "Drawing Square as lines");
    }
}