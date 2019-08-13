package com.waytoodanny.training.calculator.impl;


import com.waytoodanny.training.calculator.util.CalculatorUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;


public class TwoStackCalculatorTest {

    private final TwoStackCalculator calculator =
            new TwoStackCalculator(CalculatorUtil.BASIC_OPERATIONS);

    @Test
    void calculateAdding() {
        String operation1 = "1+2";

        Assertions.assertEquals(3.0, calculator.calculate(operation1));
    }

    @Test
    public void calculateSubtraction() {
        String operation2 = "5-2";

        Assertions.assertEquals(3, calculator.calculate(operation2));
    }

    @Test
    public void calculateMultiply() {
        String operation4 = "6*2";

        Assertions.assertEquals(12.0, calculator.calculate(operation4));
    }

    @Test
    public void calculateDivision() {
        String operation3 = "6/2";

        Assertions.assertEquals(3.0, calculator.calculate(operation3));
    }

    @Test
    public void calculateZeroDivision() {
        String operation3 = "6/0";

        Assertions.assertThrows(ArithmeticException.class, () -> calculator.calculate(operation3));
    }

    @Test
    public void calculateWithIllegalArgumentException() {
        String operation = "A+10";

        Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.calculate(operation),
                "IllegalArgumentException throwed");
    }

    @Test
    public void calculateEmptyString() {
        String emptyString = "";

        Assertions.assertThrows(NoSuchElementException.class, () -> calculator.calculate(emptyString),
                "NoSuchElementsException throwed");
    }
}