package calculator.controller;

import calculator.model.Calculator;
import calculator.model.Parser;
import calculator.view.InputView;
import calculator.view.OutputView;

import java.util.List;
import java.util.NoSuchElementException;

public class CalculatorController {

    public void run() {
        try {
            String input = InputView.readInput();
            List<Integer> numbers = Parser.parse(input);
            int result = Calculator.sum(numbers);
            OutputView.printResult(result);
        } catch (NoSuchElementException e) {
            OutputView.printResult(0);
        }
    }
}

