package com.waytoodanny.training.calculator.impl;


import com.waytoodanny.training.calculator.util.CalculatorUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TwoStackCalculatorTest {

  private final TwoStackCalculator calculator =
          new TwoStackCalculator(CalculatorUtil.BASIC_OPERATIONS);

  @Test
  void shouldAddTwoNumbers() {
    double result = calculator.calculate("1+1");
    assertEquals(2, result);
  }

  @Test
  void shouldDeductTwoNumbers() {
    double result = calculator.calculate("10-2");
    assertEquals(8, result);
  }

  @Test
  void shouldDivideTwoNumbers() {
    double result = calculator.calculate("10/2");
    assertEquals(5, result);
  }

  @Test
  void shouldNotDivideByZero() {
    try {
      calculator.calculate("1/0");
      fail("No division by zero exception");
    } catch (ArithmeticException e) {
      assertEquals("/ by zero", e.getMessage());
    }
  }

  @Test
  void shouldMultiplyTwoNumbers() {
    double result = calculator.calculate("2*5");
    assertEquals(10, result);

    result = calculator.calculate("2*0");
    assertEquals(0, result);
  }

  @Test
  void shouldNotCalculateInvalidOperation() {
    String operation = "q";
    try {
      calculator.calculate(operation);
      fail("No invalid operation exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid operation: " + operation, e.getMessage());
    }
  }

  @Test
  void shouldCalculateWhenSurroundedByParentheses() {
    double result = calculator.calculate("(2*5)");
    assertEquals(10, result);
  }

  @Test
  void shouldNotCalculateInvalidExpression() {
    try {
      calculator.calculate("+");
      fail("No invalid expression exception");
    } catch (Exception e) {
      assertEquals("Invalid expression provided", e.getMessage());
    }
  }

  @ParameterizedTest
  @CsvSource({
          "(2+4)*5, 30",
          "2*2*2*2*2, 32",
          "2/2*2/2, 1",
          "5*(5-5+1), 5"
  })
  void shouldCalculateComplexExpressions(String expression, double expected) {
    double result = calculator.calculate(expression);
    assertEquals(expected, result);
  }
}