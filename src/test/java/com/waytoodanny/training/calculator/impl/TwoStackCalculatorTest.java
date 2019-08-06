package com.waytoodanny.training.calculator.impl;


import com.waytoodanny.training.calculator.util.CalculatorUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


class TwoStackCalculatorTest {

    private final TwoStackCalculator calculator =
            new TwoStackCalculator(CalculatorUtil.BASIC_OPERATIONS);


    @ParameterizedTest
    @CsvSource(value = {"2+2=4", "7+3=10", "100+450=550", "2+0+3+3=8", "0+0+0+0+10+45+11=66"}, delimiter = '=')
    void shouldReturnResultOfPlusOperations(String input, String expected) {
        assertEquals(Double.parseDouble(expected), calculator.calculate(input));
    }

    @ParameterizedTest
    @CsvSource(value = {"2-2=0", "7-3=4", "100-10-150=-60", "0-0=0", "56-40=16"}, delimiter = '=')
    void shouldReturnResultOfMinusOperations(String input, String expected) {
        assertEquals(Double.parseDouble(expected), calculator.calculate(input));
    }

    @ParameterizedTest
    @CsvSource(value = {"2*2=4", "7*3=21", "5*5=25", "2*0=0", "10*10*10*10=10000"}, delimiter = '=')
    void shouldReturnResultOfMultiplyOperations(String input, String expected) {
        assertEquals(Double.parseDouble(expected), calculator.calculate(input));
    }

    @ParameterizedTest
    @CsvSource(value = {"2/2=1", "8/2/2=2", "10/2=5", "12/2=6", "998/1=998"}, delimiter = '=')
    void shouldReturnResultOfDivideOperations(String input, String expected) {
        assertEquals(Double.parseDouble(expected), calculator.calculate(input));
    }

    @ParameterizedTest
    @CsvSource(value = {"2=2", "5=5", "6=6", "99=99", "3=3"}, delimiter = '=')
    void shouldReturnSameNumber(String input, String expected) {
        assertEquals(Double.parseDouble(expected), calculator.calculate(input));
    }

    @ParameterizedTest
    @CsvSource(value = {"2+2/2=3", "2*2/2+1-1=2", "5-15+5*2+10/5=2", "18/2*4=36", "2*2+2/2+5-1=9"}, delimiter = '=')
    void shouldReturnResultOfUsingAllOperationsWithoutParenthesis(String input, String expected) {
        assertEquals(Double.parseDouble(expected), calculator.calculate(input));
    }

    @ParameterizedTest
    @CsvSource(value = {"2+2*(2+2)=10", "2*(9/(2+1))-1=5", "(5-15+5)*(2+10/5)=-20", "64/(2*4)=8", "(2*2+2)/2+5-1=7"}, delimiter = '=')
    void shouldReturnResultOfUsingAllOperationsWithUsingParenthesis(String input, String expected) {
        assertEquals(Double.parseDouble(expected), calculator.calculate(input));
    }

    @Test
    void shouldThrowArithmeticException() {
        assertThrows(ArithmeticException.class, () -> calculator.calculate("1/0"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a+b", "2+3a", "4*(a)", "2/a"})
    void shouldThrowIllegalArgumentException(String input) {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(input));
    }


}