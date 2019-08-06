package com.waytoodanny.training.calculator.impl;


import com.waytoodanny.training.calculator.util.CalculatorUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


class TwoStackCalculatorTest {

    private final TwoStackCalculator calculator =
            new TwoStackCalculator(CalculatorUtil.BASIC_OPERATIONS);


    @DisplayName("all basic operations parametrized")
    @ParameterizedTest
    @CsvSource(value = {"1+1=2", "1-2=-1", "3*3=9", "9/3=3"}, delimiter = '=')
    void Given_Parametrized_When_ProceedingAllKindsOperations_Then_ShouldCalculateCorrectly(String sInput, String sExpected) {
        double dResult =  calculator.calculate(sInput);
        double dExpected = Double.parseDouble(sExpected);
        assertEquals(dExpected, dResult);
    }

    @Test
    void Given_ZeroDivider_When_ProceedingDivision_Then_ShouldThrowArithmeticException(){
        assertThrows(ArithmeticException.class, () -> calculator.calculate("3/0"));
    }


    @Test
    void Given_PolynomialWithoutParentheses_When_ProceedingDifferentPriorityOperations_Then_ShouldMultiplyThenSubtract(){
        double result = calculator.calculate("3-7*2");
        assertEquals(result, -11);
    }

    @Test
    void Given_PolynomialWithParentheses_When_ProceedingDifferentPriorityOperations_Then_ShouldFirstlySubtractExpressionInParentheses(){
        double result = calculator.calculate("(3-7)*2");
        assertEquals(result, -8);
    }

    @Test
    void Given_IntegerOperands_When_ProceedingSum_Then_ShouldReturnSum() {
        double result = calculator.calculate("3+7");
        assertEquals(result, 10.0);
    }

    @Test
    void Given_IntegerOperands_When_ProceedingSubtract_Then_ShouldReturnDifference() {
        double result = calculator.calculate("3-7");
        assertEquals(result, -4.0);
    }

    @Test
    void Given_IntegerOperands_When_ProceedingMultiply_Then_ShouldReturnProduct() {
        double result = calculator.calculate("3*7");
        assertEquals(21.0, result);
    }

    @Test
    void Given_IntegerOperands_When_ProceedingDivision_Then_ShouldReturnFraction() {
        double result =  calculator.calculate("10/2");
        assertEquals(5, result);
    }





}


//
//    @Test
//    void testAddDouble() {
//        double result = calculator.calculate("3,5+7,3");
//        assertEquals(result, 10.8, 0.1);
//    }
//
//    @Test
//    void testDeductDouble() {
//        double result = calculator.calculate("3.5-7.3");
//        assertEquals(result, -4.2, 0.1);
//    }
//
//    @Test
//    void testMultiplyDouble() {
//        double result = calculator.calculate("3.5*7.3");
//        assertEquals(25.55, result, 0.1);
//    }
//
//    @Test
//    void testDivideDouble() {
//        double result =  calculator.calculate("10.6/2.3");
//        assertEquals(4.6, result, 0.1);
//    }
