package calculator.model;

public class Validator {

    // "\n"이 존재하지 않으면 예외 발생
    public static void validateSeparatorFormat(int newlineIndex) {
        if (newlineIndex == -1) {
            throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다. (\\n 누락)");
        }
    }

    // 커스텀 구분자 값 검증
    public static void validateCustomSeparator(String separatorPart) {
        if (separatorPart == null || separatorPart.isEmpty()) {
            throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다. (구분자 누락)");
        }

        for (char c : separatorPart.toCharArray()) {
            if (Character.isDigit(c)) {
                throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다. (숫자 불가)");
            }
            if (Character.isWhitespace(c)) {
                throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다. (공백 불가)");
            }
        }
    }

    // 지정되지 않은 문자가 구분자로 사용되면 예외 발생
    public static void validateCustomSeparatorUsage(String currentChar, String separatorPart) {
        if (!separatorPart.contains(currentChar)) {
            throw new IllegalArgumentException("잘못된 커스텀 구분자입니다.");
        }
    }

    // 0, 음수, 숫자가 아닌 값 포함 시 예외 발생
    public static int validateAndParseNumber(String str) {
        try {
            int num = Integer.parseInt(str);

            if (num == 0) {
                throw new IllegalArgumentException("0은 입력할 수 없습니다");
            }
            if (num < 0) {
                throw new IllegalArgumentException("음수는 허용되지 않습니다");
            }

            return num;

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 숫자 입력입니다");
        }
    }
}

