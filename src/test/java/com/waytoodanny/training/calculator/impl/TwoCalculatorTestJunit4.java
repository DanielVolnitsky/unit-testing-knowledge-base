package com.waytoodanny.training.calculator.impl;

import com.waytoodanny.training.calculator.util.CalculatorUtil;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class TwoCalculatorTestJunit4 {
    private String input;
    private double expected;

    public TwoCalculatorTestJunit4(String input, double expected) {
        this.input = input;
        this.expected = expected;
    }

    private final TwoStackCalculator calculator =
            new TwoStackCalculator(CalculatorUtil.BASIC_OPERATIONS);

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Parameterized.Parameters
    public static Collection<Object[]> inputsForMultiplyOperations() {
        return Arrays.asList(new Object[][]{
                {"((8+2)*5/2)*(3+4)", 175.00},
                {"100/(1+5*6+(3-2))", Math.floor(3.125)},
                {"(5+6*(2-2))", 5.00},
                {"((8+  2)/2)*  2 ", 10.0}
        });
    }

    @Test
    public void shouldTestDifferentMultiplyOperations() {
        double result = calculator.calculate(input);
        assertThat(result, equalTo(expected));
    }

    @Test(expected = ArithmeticException.class)
    public void shouldThrowExceptionForDivideBy0() {
        calculator.calculate("5/0");
    }

    @Test
    public void shouldThrowExceptionForIncorrectOperations() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Invalid expression provided");
        calculator.calculate("++9fjg+7");
    }

    @Test
    public void shouldThrowExceptionForIncorrectParenthesesQuantity() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Invalid input, incorrect parentheses quantity");
        calculator.calculate("((4+7)");
    }

    @Test
    public void shouldThrowExceptionForEmptyInput() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Invalid input");
        calculator.calculate("");
    }

    @Test
    public void shouldThrowExceptionForOnleSpacesInInput() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Invalid input");
        calculator.calculate("  ");
    }

    @Test
    public void shouldThrowExceptionForCharactersOnly() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Invalid operation: g");
        calculator.calculate("gbkreg");
    }
}
