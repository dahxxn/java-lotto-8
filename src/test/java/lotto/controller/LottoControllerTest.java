package lotto.controller;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoControllerTest extends NsTest {
    private static final String PURCHASE_AMOUNT_ERROR_HEADER = "[ERROR] 구입 금액은";

    @DisplayName("구입 금액 입력 정상테스트: 유효한 입력이면 PurchaseAmount 객체를 생성한다")
    @ParameterizedTest
    @ValueSource(strings = {"1000", "5000", "10000"})
    void 구입_금액_입력_정상_유효한입력(String amountInput) {
        // when & then
        assertSimpleTest(() -> {
            run(amountInput);
            assertThat(output()).doesNotContain(PURCHASE_AMOUNT_ERROR_HEADER);
        });
    }

    @DisplayName("구입 금액 입력 예외테스트: 잘못된 입력이면 구입 금액 에러 메시지가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {
            "abc",
            "1.5",
            "0",
            "-1000",
            "1500",
            " ",
            "   ",
            "99999999999999999999"
    })
    void 구입_금액_입력_예외_잘못된입력(String amountInput) {
        // when & then
        int inputForStop = 1000;
        assertSimpleTest(() -> {
            runException(amountInput);
            assertThat(output()).contains(PURCHASE_AMOUNT_ERROR_HEADER);
        });
    }

    @Override
    protected void runMain() {
        LottoController lottoController = new LottoController();
        lottoController.run();
    }
}