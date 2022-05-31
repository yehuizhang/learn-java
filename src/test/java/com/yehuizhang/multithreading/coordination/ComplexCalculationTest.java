package com.yehuizhang.multithreading.coordination;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class ComplexCalculationTest {

    @Test
    void calculateResult() {
        ComplexCalculation cc = new ComplexCalculation();
        assertEquals(new BigInteger("25"),
                cc.calculateResult(new BigInteger("2"), new BigInteger("4"), new BigInteger("3"), new BigInteger("2")));
    }
}