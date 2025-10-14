package calculator.model;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    // 기본 구분자 목록
    private static final List<String> BASIC_SEPARATORS = List.of(",", ":");

    // 입력 문자열을 숫자 List로 변환
    public static List<Integer> parse(String input) {
        List<Integer> numbers = new ArrayList<>();

        // 빈 입력 처리
        if (input == null || input.trim().isEmpty()) {
            numbers.add(0);
            return numbers;
        }

        // 기본 구분자 사용 시 처리
        return parseBasicSeparator(input);
    }

    // 기본 구분자를 기준으로 숫자 파싱
    private static List<Integer> parseBasicSeparator(String input) {
        List<Integer> numbers = new ArrayList<>();
        StringBuilder currentNumber = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            // 음수 부호(-)는 숫자의 일부로 간주
            if (currentChar == '-') {
                currentNumber.append(currentChar);
                continue;
            }

            // 기본 구분자면 숫자 추가 후 초기화
            if (BASIC_SEPARATORS.contains(String.valueOf(currentChar))) {
                handleSeparator(currentNumber, numbers);
                continue;
            }

            // 숫자 또는 기타 문자 처리
            currentNumber.append(currentChar);
        }

        // 마지막 숫자 처리
        appendNumber(currentNumber, numbers);
        return numbers;
    }

    // 구분자를 만났을 때 현재 숫자를 리스트에 추가
    private static void handleSeparator(StringBuilder currentNumber, List<Integer> numbers) {
        if (currentNumber.isEmpty()) {
            return;
        }
        numbers.add(Validator.validateAndParseNumber(currentNumber.toString()));
        currentNumber.setLength(0);
    }

    // 입력의 마지막 숫자 처리
    private static void appendNumber(StringBuilder currentNumber, List<Integer> numbers) {
        if (currentNumber.isEmpty()) {
            return;
        }
        numbers.add(Validator.validateAndParseNumber(currentNumber.toString()));
    }
}