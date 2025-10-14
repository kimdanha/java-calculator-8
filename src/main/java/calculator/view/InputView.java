package calculator.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputView {

    public static String readInput() {
        System.out.println("덧셈할 문자열을 입력해주세요.");
        return readLine();
    }
}
