package lotto.domain;

import static lotto.constant.LottoSymbol.LOTTO_NUMBER_RANGE_END;
import static lotto.domain.error.ErrorMessage.BONUS_NUMBER_ERROR_DUPLICATE;
import static lotto.domain.error.ErrorMessage.BONUS_NUMBER_ERROR_NOT_NUMERIC;
import static lotto.domain.error.ErrorMessage.BONUS_NUMBER_ERROR_OUT_OF_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WinningNumbersTest {

    @DisplayName("생성 정상테스트: 보너스 번호가 유효하고 당첨번호와 중복되지 않으면 생성된다")
    @Test
    void 보너스번호_정상_생성() {
        // given
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");
        String bonusInput = String.valueOf(LOTTO_NUMBER_RANGE_END);

        // when
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusInput);

        // then
        assertThat(winningNumbers).isNotNull();
    }

    @DisplayName("전처리 예외테스트: 보너스 번호가 숫자가 아니면 IllegalArgumentException이 발생한다")
    @Test
    void 보너스번호_전처리_예외_숫자아님() {
        // given
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");
        String bonusInput = "a";

        // when & then
        assertThatThrownBy(() -> new WinningNumbers(winningLotto, bonusInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_NUMBER_ERROR_NOT_NUMERIC.getMessage());
    }

    @DisplayName("검증 예외테스트: 보너스 번호가 1~45 범위를 벗어나면 IllegalArgumentException이 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"0", "46"})
    void 보너스번호_검증_예외_범위(String bonusNumberInput) {
        // given
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");

        // when & then
        assertThatThrownBy(() -> new WinningNumbers(winningLotto, bonusNumberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_NUMBER_ERROR_OUT_OF_RANGE.getMessage());
    }

    @DisplayName("검증 예외테스트: 보너스 번호가 당첨 번호와 중복되면 IllegalArgumentException이 발생한다")
    @Test
    void 보너스번호_검증_예외_중복() {
        // given
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");
        String bonusNumberInput = "6";

        // when & then
        assertThatThrownBy(() -> new WinningNumbers(winningLotto, bonusNumberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_NUMBER_ERROR_DUPLICATE.getMessage());
    }
}
