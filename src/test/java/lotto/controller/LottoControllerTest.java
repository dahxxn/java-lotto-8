package lotto.controller;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.service.InputService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoControllerTest extends NsTest {
    private static final String ERROR_HEADER = "[ERROR]";
    private static final String RESULT_HEADER = "당첨 통계";
    private static final String PROFIT_RATE_PHRASE = "총 수익률은";

    @DisplayName("구입 금액 입력 정상 테스트: 유효한 금액 입력 시 에러 메시지가 발생하지 않는다")
    @ParameterizedTest
    @ValueSource(strings = {"1000", "5000", "10000"})
    void 구입금액_정상_입력(String amountInput) {
        assertSimpleTest(() -> {
            run(amountInput, "1,2,3,4,5,6", "7");
            assertThat(output()).doesNotContain(ERROR_HEADER);
        });
    }

    @DisplayName("구입 금액 입력 예외 테스트: 잘못된 금액 입력 시 에러 메시지가 출력된다")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "0", "-1000", "1500", "99999999999999999999"})
    void 구입금액_잘못된_입력(String invalidInput) {
        assertSimpleTest(() -> {
            run(invalidInput, "1000", "1,2,3,4,5,6", "7");
            assertThat(output()).contains(ERROR_HEADER);
        });
    }

    @DisplayName("당첨 번호 입력 예외 테스트: 잘못된 형식의 당첨 번호 입력 시 에러 메시지가 출력된다")
    @ParameterizedTest
    @ValueSource(strings = {
            "1,2,3,4,5",
            "1,2,3,4,5,6,7",
            "1,2,3,4,5,a",
            "1,2,3,3,4,5"
    })
    void 당첨번호_입력_예외(String invalidWinningNumbers) {
        assertSimpleTest(() -> {
            run("1000", invalidWinningNumbers, "1,2,3,4,5,6", "7");
            assertThat(output()).contains(ERROR_HEADER);
        });
    }

    @DisplayName("보너스 번호 입력 예외 테스트: 보너스 번호가 당첨 번호와 중복되면 에러가 발생한다")
    @Test
    void 보너스번호_중복_입력_예외() {
        assertSimpleTest(() -> {
            run("1000", "1,2,3,4,5,6", "6", "7");
            assertThat(output()).contains(ERROR_HEADER);
        });
    }

    @DisplayName("전체 흐름 테스트: 구입 금액 -> 당첨 번호 -> 보너스 번호 입력 후 결과 출력까지 정상적으로 동작한다")
    @Test
    void 전체_로또_프로세스_정상_동작() {
        assertSimpleTest(() -> {
            run("10000", "1,2,3,4,5,6", "7");
            String out = output();
            assertThat(out).contains(RESULT_HEADER);
            assertThat(out).contains(PROFIT_RATE_PHRASE);
            assertThat(out).doesNotContain(ERROR_HEADER);
        });
    }

    @Override
    protected void runMain() {
        InputService inputService = new InputService();
        LottoController controller = new LottoController(inputService);
        controller.run();
    }
}
