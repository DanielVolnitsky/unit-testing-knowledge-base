package com.waytoodanny.training.calculator.impl;


import com.waytoodanny.training.calculator.util.CalculatorUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TwoStackCalculatorTest {

    private final TwoStackCalculator calculator =
            new TwoStackCalculator(CalculatorUtil.BASIC_OPERATIONS);

    @ParameterizedTest
    @ValueSource(strings = {"1+5", "9-3", "2*3", "36/6", "2+2*2"})
    public void calculateTest(String input) {
        Assertions.assertEquals(6, calculator.calculate(input));
    }
}