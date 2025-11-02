package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @DisplayName("forEach 순회 테스트: 각 Lotto는 1~45 범위의 중복 없는 6개 번호이며 오름차순이다")
    @Test
    void forEach_로또_유효성_검증() {
        // given
        Lottos lottos = new Lottos(7);

        // when & then
        lottos.forEach(lotto -> {
            var nums = lotto.numbers();
            assertThat(nums).hasSize(6);
            assertThat(nums).doesNotHaveDuplicates();
            assertThat(nums).allMatch(n -> n >= 1 && n <= 45);
            assertThat(nums).isSorted();
        });
    }
}
