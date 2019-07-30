package com.waytoodanny.calculator.operation;

public interface BiOperation {
    int execute(int a, int b);
    int getPriority();
}
