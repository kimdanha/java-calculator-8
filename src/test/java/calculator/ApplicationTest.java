package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }

    @Test
    void 기본_구분자_사용() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1;2;3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 여러_커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//#@\\n1#2@3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 빈문자열_입력() {
        assertSimpleTest(() -> {
            run("");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 음수_예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("음수는 허용되지 않습니다")
        );
    }

    @Test
    void 숫자아닌값_예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,a,3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("유효하지 않은 숫자 입력입니다")
        );
    }

    @Test
    void 커스텀_구분자_형식_예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;1;2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("잘못된 커스텀 구분자 형식입니다")
        );
    }

    @Test
    void 커스텀_구분자_공백() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("// \\n1 2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자_숫자() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//1\\n1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 기본_구분자_아닌문자() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1@2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자_이외문자() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//@\\n1#2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자_음수_예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\\\n1;-2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }
}
