package com.waytoodanny.homework.calculator.util;

import com.waytoodanny.homework.calculator.operation.*;

import java.util.HashMap;
import java.util.Map;

public interface CalculatorUtil {

    Map<Character, BiOperation> BASIC_OPERATIONS = new HashMap<Character, BiOperation>() {{
        put('+', new PlusOperation());
        put('-', new MinusOperation());
        put('*', new MultiplyOperation());
        put('/', new DivideOperation());
    }};
}
