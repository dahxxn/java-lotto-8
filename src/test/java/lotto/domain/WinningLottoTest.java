package lotto.domain;

import static lotto.domain.error.ErrorMessage.LOTTO_ERROR_NOT_SIX_COUNT;
import static lotto.domain.error.ErrorMessage.LOTTO_ERROR_NUMBER_DUPLICATE;
import static lotto.domain.error.ErrorMessage.LOTTO_ERROR_NUMBER_OUT_OF_RANGE;
import static lotto.domain.error.ErrorMessage.WINNING_LOTTO_ERROR_NOT_NUMERIC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @DisplayName("전처리 정상테스트: 쉼표와 공백을 포함한 입력도 정상 파싱된다")
    @Test
    void 당첨번호_전처리_정상_공백허용() {
        // given
        String winningNumbersInput = "1, 2, 3, 4, 5, 6";

        // when
        WinningLotto winningLotto = new WinningLotto(winningNumbersInput);

        // then
        assertThat(winningLotto).isNotNull();
    }

    @DisplayName("전처리 예외테스트: 숫자가 아닌 값이 포함되면 IllegalArgumentException이 발생한다")
    @Test
    void 당첨번호_전처리_예외_숫자아님() {
        // given
        String winningNumbersInput = "1, 2, a, 4, 5, 6";

        // when & then
        assertThatThrownBy(() -> new WinningLotto(winningNumbersInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(WINNING_LOTTO_ERROR_NOT_NUMERIC.getMessage());
    }

    @DisplayName("도메인 검증 위임: 개수/범위/중복 에러는 Lotto 생성 과정에서 예외가 발생한다")
    @Test
    void 당첨번호_검증_위임_Lotto_개수부족_예외() {
        // given
        String winningNumbersInput = "1,2,3,4,5";

        // when & then
        assertThatThrownBy(() -> new WinningLotto(winningNumbersInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_ERROR_NOT_SIX_COUNT.getMessage());
    }

    @DisplayName("도메인 검증 위임: 개수/범위/중복 에러는 Lotto 생성 과정에서 예외가 발생한다")
    @Test
    void 당첨번호_검증_위임_Lotto_범위_예외() {
        // given
        String winningNumbersInput = "0,2,3,4,5,6";

        // when & then
        assertThatThrownBy(() -> new WinningLotto(winningNumbersInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_ERROR_NUMBER_OUT_OF_RANGE.getMessage());
    }

    @DisplayName("도메인 검증 위임: 개수/범위/중복 에러는 Lotto 생성 과정에서 예외가 발생한다")
    @Test
    void 당첨번호_검증_위임_Lotto_중복_예외() {
        // given
        String winningNumbersInput = "1,2,3,4,5,5";

        // when & then
        assertThatThrownBy(() -> new WinningLotto(winningNumbersInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_ERROR_NUMBER_DUPLICATE.getMessage());
    }

    @DisplayName("번호 포함 확인 테스트: 해당 번호가 포함되어 있을때 true를 반환한다.")
    @Test
    void 당첨번호_번호_포함_확인_테스트_있을때() {
        // given
        String winningNumbersInput = "1,2,3,4,5,6";
        WinningLotto winningLotto = new WinningLotto(winningNumbersInput);
        int number = 5;

        // when & then
        assertThat(winningLotto.contains(number)).isTrue();
    }

    @DisplayName("번호 포함 확인 테스트: 해당 번호가 포함되어 있지 않을때 false를 반환한다.")
    @Test
    void 당첨번호_번호_포함_확인_테스트_없을때() {
        // given
        String winningNumbersInput = "1,2,3,4,5,6";
        WinningLotto winningLotto = new WinningLotto(winningNumbersInput);
        int number = 7;

        // when & then
        assertThat(winningLotto.contains(number)).isFalse();
    }
}
