package com.waytoodanny.training.calculator.impl;


import com.waytoodanny.training.calculator.util.CalculatorUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class TwoStackCalculatorParameterizedTest {

    private final TwoStackCalculator calculator =
            new TwoStackCalculator(CalculatorUtil.BASIC_OPERATIONS);

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "(20+20-10*2)/2", 10 },
                { "20+20", 40 },
                { "20-15", 5 },
                { "20/2", 10 },
                { "20*15", 300 }
        });
    }

    private String input;
    private double expected;

    public TwoStackCalculatorParameterizedTest(String input, double expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void testCalculate() {
            assertEquals(expected, calculator.calculate(input));
        }

}