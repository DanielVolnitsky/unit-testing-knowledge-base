package com.waytoodanny.calculator.operation;

import com.waytoodanny.calculator.operation.BiOperation;

public class MultiplyOperation implements BiOperation {

    @Override
    public int execute(int a, int b) {
        return a * b;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
