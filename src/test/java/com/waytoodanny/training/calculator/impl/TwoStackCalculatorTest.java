package com.waytoodanny.training.calculator.impl;

import com.waytoodanny.training.calculator.operation.BiOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TwoStackCalculatorTest {

    private final TwoStackCalculator calculator = new TwoStackCalculator(
            new HashMap<Character, BiOperation>(){{
                put('+', new TestPlusOperation());
                put('-', new TestMinusOperation());
            }}
    );

    @Parameter
    public String input;

    @Parameter(1)
    public double expected;

    @Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"1+1", 2d},
                {"1-1", 0d},
                {"1-1+11", 11d},
        });
    }


    @Test
    public void calculate() {
        assertEquals(0, Double.compare(expected, calculator.calculate(input)));
    }

    private static class TestPlusOperation implements BiOperation {

        @Override
        public int execute(int a, int b) {
            return a + b;
        }

        @Override
        public int getPriority() {
            return 1;
        }
    }

    private static class TestMinusOperation implements BiOperation {

        @Override
        public int execute(int a, int b) {
            return a - b;
        }

        @Override
        public int getPriority() {
            return 1;
        }
    }
}