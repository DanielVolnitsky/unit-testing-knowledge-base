package com.waytoodanny.training.calculator.operation;

public class MinusOperation implements BiOperation {

    @Override
    public int execute(int a, int b) {
        return a - b;
    }

    @Override
    public int getPriority() {
        return 2;
    }
}
