package com.waytoodanny.homework.calculator.operation;

public interface BiOperation {
    int execute(int a, int b);
    int getPriority();
}
