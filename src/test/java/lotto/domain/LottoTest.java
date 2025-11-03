package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다")
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        // given & when & then
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호의 개수가 6개 미만이면 예외가 발생한다")
    @Test
    void 로또_번호의_개수가_6개가_미만이면_예외가_발생한다() {
        // given & when & then
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        // given & when & then
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {
            "0,2,3,4,5,6",
            "1,2,3,4,5,46"
    })
    void 로또_번호가_1에서_45_범위를_벗어나면_예외가_발생한다(String csvNumbers) {
        // given
        List<Integer> numbers = Stream.of(csvNumbers.split(","))
                .map(Integer::parseInt)
                .toList();

        // when & then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 유효할 경우 정상적으로 Lotto 객체가 생성된다")
    @Test
    void 로또_번호가_유효하면_Lotto_객체가_생성된다() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // when
        Lotto lotto = new Lotto(numbers);

        // then
        assertThat(lotto).isNotNull();
    }

    @DisplayName("numbers()는 생성된 번호를 값 기반으로 그대로 반환한다")
    @Test
    void numbers_값기반으로_정상반환한다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when
        List<Integer> numbersCopy = lotto.numbers();

        // then
        assertThat(numbersCopy).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("numbers()로 반환된 리스트는 불변이다")
    @Test
    void numbers_불변성_검증() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when
        List<Integer> numbersCopy = lotto.numbers();

        // then
        assertThatThrownBy(() -> numbersCopy.add(7))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
