package com.waytoodanny.training.calculator.impl;

import com.waytoodanny.training.calculator.util.CalculatorUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoStackCalculatorTestJUnit5 {

    private final TwoStackCalculator calculator =
            new TwoStackCalculator(CalculatorUtil.BASIC_OPERATIONS);

    @ParameterizedTest
    @MethodSource("inputsForMultiplyOperations")
    public void shouldTestDifferentMultiplyOperations(String input, double expected) {
        double result = calculator.calculate(input);
        assertEquals(expected, result, "Result of " + input + " should be " + expected);
    }

    private static Stream<Arguments> inputsForMultiplyOperations() {
        return Stream.of(
                Arguments.of("((8+2)*5/2)*(3+4)", 175.00),
                Arguments.of("100/(1+5*6+(3-2))", Math.floor(3.125)),
                Arguments.of("(5+6*(2-2))", 5.00),
                Arguments.of("5+1*2+4/2-1", 8.00)
        );
    }

    @ParameterizedTest
    @MethodSource("inputsWithSpacesForMultiplyOperations")
    public void shouldTestDifferentMultiplyOperationsWithAdditionSpaces(String input, double expected) {
        double result = calculator.calculate(input);
        assertEquals(expected, result, "Result of " + input + " should be " + expected);
    }

    private static Stream<Arguments> inputsWithSpacesForMultiplyOperations() {
        return Stream.of(
                Arguments.of("((8+  2)/2)*  2 ", 10.0),
                Arguments.of("5+ 1 * 2 + 4 /2-1", 8.00)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"++9fjg+7", "++9+7", "-(5+2/2)"})
    public void shouldThrowExceptionForIncorrectOperations(String input) {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate(input);
        });
        assertEquals("Invalid expression provided", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"((4+7)", "5+6*(2-2))"})
    public void shouldThrowExceptionForIncorrectParenthesesQuantity(String input) {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate(input);
        });
        assertEquals("Invalid input, incorrect parentheses quantity", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "    "})
    public void shouldThrowExceptionForEmptyInputAndOnlySpacesInInput(String input) {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate(input);
        });
        assertEquals("Invalid input", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"gbkreg"})
    public void shouldThrowExceptionForCharactersOnly(String input) {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate(input);
        });
        assertEquals("Invalid operation: g", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionForDivideBy0() {
        String input = "5/0";

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            calculator.calculate(input);
        });
        assertEquals("/ by zero", exception.getMessage());
    }
}