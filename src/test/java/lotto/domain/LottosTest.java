package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottosTest {
    @DisplayName("생성 정상테스트: 전달한 수만큼 Lotto를 생성해도 예외가 발생하지 않는다")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 8})
    void 로또들_생성_정상_예외없음(int lottoCount) {
        // when & then
        assertThatCode(() -> new Lottos(lottoCount))
                .doesNotThrowAnyException();
    }
}
