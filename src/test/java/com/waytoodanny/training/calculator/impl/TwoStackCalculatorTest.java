package com.waytoodanny.training.calculator.impl;

import com.waytoodanny.training.calculator.util.CalculatorUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoStackCalculatorTest {

    private final TwoStackCalculator calculator =
            new TwoStackCalculator(CalculatorUtil.BASIC_OPERATIONS);

    static class ExpressionPackage{
        String expression;
        double result;

        public ExpressionPackage(String expression, Double result) {
            this.expression = expression;
            this.result = result;
        }
    }

    private static Stream<Arguments> testCases() {
        Queue<Arguments> args = new LinkedList<>();
        String a, b, c, d;
        Properties props = new Properties();

        try (InputStream input = TwoStackCalculatorTest.class.
                getResourceAsStream("operands.properties")) {

            props.load(input);
            a = props.getProperty("operands.a");
            b = props.getProperty("operands.b");
            c = props.getProperty("operands.c");
            d = props.getProperty("operands.d");

            ExpressionPackage[] expressions={
                    /*single priority operations*/
                    new ExpressionPackage(String.format("%s+%s", a, b),
                            Double.parseDouble(props.getProperty("result.plus"))),
                    new ExpressionPackage(String.format("%s-%s", b, a),
                            Double.parseDouble(props.getProperty("result.minus"))),
                    new ExpressionPackage(String.format("%s*%s", a, b),
                            Double.parseDouble(props.getProperty("result.multiply"))),
                    new ExpressionPackage(String.format("%s/%s", a, b),
                            Double.parseDouble(props.getProperty("result.divide"))),

                    /*different priority operations*/
                    new ExpressionPackage(String.format("%s+%s*%s+%s", a, b, c, d),
                            Double.parseDouble(props.getProperty("result.plus_multiply"))),
                    new ExpressionPackage(String.format("%s*%s+%s*%s", a, b, c, d),
                            Double.parseDouble(props.getProperty("result.multiply_plus"))),

                    /*different priority operations with parenthesis*/
                    new ExpressionPackage(String.format("(%s+%s)*(%s+%s)", a, b, c, d),
                            Double.parseDouble(props.getProperty("result.plus_in_braces"))),

                    /*prioritize options in the braces*/
                    new ExpressionPackage(String.format("(%s*%s)+(%s*%s)", a, b, c, d),
                            Double.parseDouble(props.getProperty("result.multiply_in_braces")))
            };

            args.add(Arguments.of(expressions));
            return args.stream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void calculateCases(ExpressionPackage expression) {
        assertEquals(calculator.calculate(expression.expression),
                expression.result);
    }

}