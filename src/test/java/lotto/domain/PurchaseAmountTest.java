package lotto.domain;

import static lotto.domain.error.ErrorMessage.PURCHASE_AMOUNT_ERROR_NOT_NUMERIC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {

    @DisplayName("전처리 정상테스트: 유효한 구입 금액이면 객체를 생성한다")
    @Test
    void 구입_금액_입력값_전처리_정상_유효한금액() {
        // given
        String input = "8000";

        // when
        PurchaseAmount purchaseAmount = new PurchaseAmount(input);

        // then
        assertThat(purchaseAmount).isNotNull();
    }

    @DisplayName("전처리 정상테스트: 앞뒤 공백이 있어도 정상 처리한다")
    @Test
    void 구입_금액_입력값_전처리_정상_공백포함() {
        // given
        String input = "  8000  ";

        // when
        PurchaseAmount purchaseAmount = new PurchaseAmount(input);

        // then
        assertThat(purchaseAmount).isNotNull();
    }

    @DisplayName("전처리 예외테스트: 숫자가 아니면 IllegalArgumentException이 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "12a34", "천원", "1.5"})
    void 구입_금액_입력값_전처리_예외_숫자아님(String input) {
        // when & then
        assertThatThrownBy(() -> new PurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PURCHASE_AMOUNT_ERROR_NOT_NUMERIC.getMessage());
    }

    @DisplayName("전처리 예외테스트: 빈 문자열이나 공백만 있으면 IllegalArgumentException이 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "  "})
    void 구입_금액_입력값_전처리_예외_빈값(String input) {
        // when & then
        assertThatThrownBy(() -> new PurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PURCHASE_AMOUNT_ERROR_NOT_NUMERIC.getMessage());
    }
}