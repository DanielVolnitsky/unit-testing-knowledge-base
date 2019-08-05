package com.waytoodanny.training.calculator.impl;

import com.waytoodanny.training.calculator.operation.BiOperation;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * TwoStackCalculator implementation based on two stacks (operands and operations)
 */
public class TwoStackCalculator {

    private final Map<Character, BiOperation> supportedOperations;

    private final Deque<Integer> operands = new ArrayDeque<>();
    private final Deque<Character> operations = new ArrayDeque<>();

    private int currentReadPosition;

    public TwoStackCalculator(Map<Character, BiOperation> supportedOperations) {
        this.supportedOperations = supportedOperations;
    }

    public double calculate(String s) throws IllegalArgumentException {
        try {

            s = '(' + s + ')';
            Object token;

            do {
                token = getToken(s);

                if (token instanceof Integer)
                    operands.push((int) token);

                else if (token instanceof Character) {

                    if ((char) token == ')') {
                        while (operations.size() > 0 && operations.peek() != '(')
                            popOperation();

                        operations.pop(); //get rid of initial '('

                    } else {
                        while (canPop((char) token))
                            popOperation();

                        operations.push((char) token);
                    }
                }
            } while (token != null);

            return operands.pop();

        } finally {
            cleanup();
        }
    }

    /**
     * Gets a token at a current position of pointer (pos)
     * Specify to read it either as digit or operand
     */
    private Object getToken(String s) {
        if (currentReadPosition == s.length()) // end of a line
            return null;
        if (Character.isDigit(s.charAt(currentReadPosition)))
            return Integer.parseInt(readInteger(s));
        else
            return readOperation(s);
    }

    /**
     * Extracts an integer beginning from a current position
     */
    private String readInteger(String s) {
        StringBuilder result = new StringBuilder();

        while (currentReadPosition < s.length() &&
                (Character.isDigit(s.charAt(currentReadPosition))))
            result.append(s.charAt(currentReadPosition++));

        return result.toString();
    }

    /**
     * Reads an operation at a current position (pos)
     */
    private char readOperation(String s) {
        return s.charAt(currentReadPosition++);
    }

    /**
     * Pops operation and pushes result of operation applying to an operands stack
     */
    private void popOperation() throws IllegalArgumentException {
        try {
            int b = operands.pop();
            int a = operands.pop();

            BiOperation operation = this.supportedOperations.get(operations.pop());
            operands.push(operation.execute(a, b));
        } catch (NoSuchElementException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid expression provided");
        }
    }

    /**
     * Compares current operation with last found operation by priority
     * and decides if we can pop an operation from stack
     */
    private boolean canPop(char op1) {
        if (operations.size() == 0)
            return false;

        int p1 = getPriority(op1);
        int p2 = getPriority(operations.peek());

        return p1 >= 0 && p2 >= 0 && p1 >= p2;
    }

    private int getPriority(char operation) throws IllegalArgumentException {
        try {

            if (operation == '(') {
                return -1;
            }

            return this.supportedOperations.get(operation).getPriority();

        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid operation: " + operation);
        }
    }

    private void cleanup() {
        currentReadPosition = 0;
        operands.clear();
        operations.clear();
    }
}
