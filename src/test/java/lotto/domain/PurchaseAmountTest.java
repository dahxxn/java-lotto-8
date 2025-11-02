package lotto.domain;

import static lotto.domain.error.ErrorMessage.PURCHASE_AMOUNT_ERROR_INVALID_UNIT;
import static lotto.domain.error.ErrorMessage.PURCHASE_AMOUNT_ERROR_NOT_NUMERIC;
import static lotto.domain.error.ErrorMessage.PURCHASE_AMOUNT_ERROR_NOT_POSITIVE;
import static lotto.domain.error.ErrorMessage.PURCHASE_AMOUNT_ERROR_OUT_OF_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {
    @DisplayName("전처리 정상테스트: 유효한 구입 금액이면 객체를 생성한다")
    @Test
    void 구입_금액_입력값_전처리_정상_유효한금액() {
        // given
        String amountInput = "8000";

        // when
        PurchaseAmount purchaseAmount = new PurchaseAmount(amountInput);

        // then
        assertThat(purchaseAmount).isNotNull();
    }

    @DisplayName("전처리 정상테스트: 앞뒤 공백이 있어도 정상 처리한다")
    @Test
    void 구입_금액_입력값_전처리_정상_공백포함() {
        // given
        String amountInput = "  8000  ";

        // when
        PurchaseAmount purchaseAmount = new PurchaseAmount(amountInput);

        // then
        assertThat(purchaseAmount).isNotNull();
    }

    @DisplayName("전처리 예외테스트: 숫자가 아니면 IllegalArgumentException이 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "12a34", "천원", "1.5"})
    void 구입_금액_입력값_전처리_예외_숫자아님(String amountInput) {
        // when & then
        assertThatThrownBy(() -> new PurchaseAmount(amountInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PURCHASE_AMOUNT_ERROR_NOT_NUMERIC.getMessage());
    }

    @DisplayName("전처리 예외테스트: 빈 문자열이나 공백만 있으면 IllegalArgumentException이 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "  "})
    void 구입_금액_입력값_전처리_예외_빈값(String amountInput) {
        // when & then
        assertThatThrownBy(() -> new PurchaseAmount(amountInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PURCHASE_AMOUNT_ERROR_NOT_NUMERIC.getMessage());
    }

    @DisplayName("전처리 예외테스트: Long 범위를 초과하면 IllegalArgumentException이 발생한다")
    @Test
    void 구입_금액_입력값_전처리_예외_범위초과() {
        // given
        String amountInput = "99999999999999999999";

        // when & then
        assertThatThrownBy(() -> new PurchaseAmount(amountInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PURCHASE_AMOUNT_ERROR_OUT_OF_RANGE.getMessage());
    }

    @DisplayName("검증 정상테스트: 양수이면 정상 처리한다")
    @ParameterizedTest
    @ValueSource(strings = {"1000", "10000"})
    void 구입_금액_검증_양수_정상_양수값(String amountInput) {
        // when
        PurchaseAmount purchaseAmount = new PurchaseAmount(amountInput);

        // then
        assertThat(purchaseAmount).isNotNull();
    }

    @DisplayName("검증 예외테스트: 0이면 IllegalArgumentException이 발생한다")
    @Test
    void 구입_금액_검증_양수_예외_0() {
        // when & then
        assertThatThrownBy(() -> new PurchaseAmount("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PURCHASE_AMOUNT_ERROR_NOT_POSITIVE.getMessage());
    }

    @DisplayName("검증 예외테스트: 음수이면 IllegalArgumentException이 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "-100", "-1000"})
    void 구입_금액_검증_양수_예외_음수(String amountInput) {
        // when & then
        assertThatThrownBy(() -> new PurchaseAmount(amountInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PURCHASE_AMOUNT_ERROR_NOT_POSITIVE.getMessage());
    }

    @DisplayName("검증 정상테스트: 1000원 단위면 정상 처리한다")
    @ParameterizedTest
    @ValueSource(strings = {"1000", "2000", "10000", "12000"})
    void 구입_금액_검증_1000원_단위_정상(String amountInput) {
        // when
        PurchaseAmount purchaseAmount = new PurchaseAmount(amountInput);

        // then
        assertThat(purchaseAmount).isNotNull();
    }

    @DisplayName("검증 예외테스트: 1000원 단위가 아니면 IllegalArgumentException이 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "1100", "1200"})
    void 구입_금액_검증_1000원_단위_예외(String amountInput) {
        // when & then
        assertThatThrownBy(() -> new PurchaseAmount(amountInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PURCHASE_AMOUNT_ERROR_INVALID_UNIT.getMessage());
    }

    @DisplayName("로또 수량 발행 테스트: 구매할 로또 수량을 계산한다")
    @ParameterizedTest
    @CsvSource({
            "1000, 1",
            "2000, 2",
            "10000, 10"
    })
    void 로또_수량_발행_테스트(String amountInput, long expectedLottoCount) {
        // given
        PurchaseAmount purchaseAmount = new PurchaseAmount(amountInput);

        // when
        long lottoCount = purchaseAmount.getLottoCount();

        // then
        assertThat(lottoCount).isEqualTo(expectedLottoCount);
    }

    @DisplayName("수익률 계산 테스트: 총 상금이 0원이면 수익률은 0%다")
    @Test
    void 수익률_계산_0원() {
        // given
        PurchaseAmount purchaseAmount = new PurchaseAmount("8000");
        long totalPrize = 0;

        // when
        double profitRate = purchaseAmount.calculateProfitRate(totalPrize);

        // then
        assertThat(profitRate).isEqualTo(0.0);
    }

    @DisplayName("수익률 계산 테스트: 구입 금액과 상금이 같으면 수익률은 100%다")
    @Test
    void 수익률_계산_100프로() {
        // given
        PurchaseAmount purchaseAmount = new PurchaseAmount("8000");
        long totalPrize = 8000;

        // when
        double profitRate = purchaseAmount.calculateProfitRate(totalPrize);

        // then
        assertThat(profitRate).isEqualTo(100.0);
    }

    @DisplayName("수익률 계산 테스트: 상금이 구입 금액의 절반이면 수익률은 50%다")
    @Test
    void 수익률_계산_50프로() {
        // given
        PurchaseAmount purchaseAmount = new PurchaseAmount("8000");
        long totalPrize = 4000;

        // when
        double profitRate = purchaseAmount.calculateProfitRate(totalPrize);

        // then
        assertThat(profitRate).isEqualTo(50.0);
    }

    @DisplayName("수익률 계산 테스트: 상금이 구입 금액보다 많으면 100%를 초과한다")
    @Test
    void 수익률_계산_100프로_초과() {
        // given
        PurchaseAmount purchaseAmount = new PurchaseAmount("8000");
        long totalPrize = 16000;

        // when
        double profitRate = purchaseAmount.calculateProfitRate(totalPrize);

        // then
        assertThat(profitRate).isEqualTo(200.0);
    }
}