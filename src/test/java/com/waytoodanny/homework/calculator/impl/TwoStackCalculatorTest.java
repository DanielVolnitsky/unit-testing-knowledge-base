package com.waytoodanny.homework.calculator.impl;


import com.waytoodanny.homework.calculator.util.CalculatorUtil;
import org.junit.jupiter.api.Test;

/*
 * HOMEWORK TASK:
 *
 * cover all possible TwoStackCalculator scenarios with unit tests;
 *
 * HINTS:
 * make sure that exceptions are being thrown with valid messages;
 * choose one naming convention and follow it throughout your tests;
 * write tests so other person could understand possibilities and boundaries of the SUT calculator without looking at its code;
 * make a decision whether to test private methods or not;
 * consider combining parameterized testing approach with the common one;
 * check your tests coverage percentage with jacoco, increase it if needed.
 * */
class TwoStackCalculatorTest {

    private final TwoStackCalculator calculator =
            new TwoStackCalculator(CalculatorUtil.BASIC_OPERATIONS);

    @Test
    void calculate() {

    }
}