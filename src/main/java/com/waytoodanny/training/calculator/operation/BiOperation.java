package com.waytoodanny.training.calculator.operation;

public interface BiOperation {
    int execute(int a, int b);
    int getPriority();
}
