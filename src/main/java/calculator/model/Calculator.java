package calculator.model;

import java.util.List;

public class Calculator {

    public static int sum(List<Integer> numbers) {
        int result = 0;
        for (int number : numbers) {
            result += number;
        }
        return result;
    }
}
