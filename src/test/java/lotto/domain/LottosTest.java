package lotto.domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import lotto.constant.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottosTest extends NsTest {

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
    void 로또들_순회_각로또_유효성검증() {
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

    @DisplayName("당첨 결과 계산 테스트: 5등 1개를 정확히 집계한다")
    @Test
    void 당첨통계_5등_1개() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    // given
                    Lottos lottos = new Lottos(2);
                    WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");
                    WinningNumbers winningNumbers = new WinningNumbers(winningLotto, "7");

                    // when
                    LottoResult result = lottos.calculateResult(winningNumbers);

                    // then
                    assertThat(result.getCountByRank(Rank.FIFTH)).isEqualTo(1);
                    assertThat(result.getCountByRank(Rank.NONE)).isEqualTo(1);
                },
                List.of(1, 2, 3, 10, 11, 12),
                List.of(20, 21, 22, 23, 24, 25)
        );
    }

    @DisplayName("당첨 결과 계산 테스트: 4등 2개를 정확히 집계한다")
    @Test
    void 당첨통계_4등_2개() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    // given
                    Lottos lottos = new Lottos(2);
                    WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");
                    WinningNumbers winningNumbers = new WinningNumbers(winningLotto, "7");

                    // when
                    LottoResult result = lottos.calculateResult(winningNumbers);

                    // then
                    assertThat(result.getCountByRank(Rank.FOURTH)).isEqualTo(2);
                },
                List.of(1, 2, 3, 4, 10, 11),
                List.of(1, 2, 3, 5, 12, 13)
        );
    }

    @DisplayName("당첨 결과 계산 테스트: 3등을 정확히 집계한다")
    @Test
    void 당첨통계_3등() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    // given
                    Lottos lottos = new Lottos(1);
                    WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");
                    WinningNumbers winningNumbers = new WinningNumbers(winningLotto, "7");

                    // when
                    LottoResult result = lottos.calculateResult(winningNumbers);

                    // then
                    assertThat(result.getCountByRank(Rank.THIRD)).isEqualTo(1);
                },
                List.of(1, 2, 3, 4, 5, 10)
        );
    }

    @DisplayName("당첨 결과 계산 테스트: 2등을 정확히 집계한다")
    @Test
    void 당첨통계_2등() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    // given
                    Lottos lottos = new Lottos(1);
                    WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");
                    WinningNumbers winningNumbers = new WinningNumbers(winningLotto, "7");

                    // when
                    LottoResult result = lottos.calculateResult(winningNumbers);

                    // then
                    assertThat(result.getCountByRank(Rank.SECOND)).isEqualTo(1);
                },
                List.of(1, 2, 3, 4, 5, 7)
        );
    }

    @DisplayName("당첨 결과 계산 테스트: 1등을 정확히 집계한다")
    @Test
    void 당첨통계_1등() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    // given
                    Lottos lottos = new Lottos(1);
                    WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");
                    WinningNumbers winningNumbers = new WinningNumbers(winningLotto, "7");

                    // when
                    LottoResult result = lottos.calculateResult(winningNumbers);

                    // then
                    assertThat(result.getCountByRank(Rank.FIRST)).isEqualTo(1);
                },
                List.of(1, 2, 3, 4, 5, 6)
        );
    }

    @DisplayName("당첨 결과 계산 테스트: 여러 등수가 복합적으로 있을 때 정확히 집계한다")
    @Test
    void 당첨통계_복합당첨() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    // given
                    Lottos lottos = new Lottos(7);
                    WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");
                    WinningNumbers winningNumbers = new WinningNumbers(winningLotto, "7");

                    // when
                    LottoResult result = lottos.calculateResult(winningNumbers);

                    // then
                    assertThat(result.getCountByRank(Rank.FIRST)).isEqualTo(1);
                    assertThat(result.getCountByRank(Rank.SECOND)).isEqualTo(1);
                    assertThat(result.getCountByRank(Rank.THIRD)).isEqualTo(1);
                    assertThat(result.getCountByRank(Rank.FOURTH)).isEqualTo(1);
                    assertThat(result.getCountByRank(Rank.FIFTH)).isEqualTo(1);
                    assertThat(result.getCountByRank(Rank.NONE)).isEqualTo(2);
                },
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 7),
                List.of(1, 2, 3, 4, 5, 10),
                List.of(1, 2, 3, 4, 11, 12),
                List.of(1, 2, 3, 13, 14, 15),
                List.of(1, 2, 16, 17, 18, 19),
                List.of(20, 21, 22, 23, 24, 25)
        );
    }

    @DisplayName("당첨 결과 계산 테스트: 같은 등수가 여러 개일 때 정확히 집계한다")
    @Test
    void 당첨통계_동일등수_여러개() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    // given
                    Lottos lottos = new Lottos(3);
                    WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");
                    WinningNumbers winningNumbers = new WinningNumbers(winningLotto, "7");

                    // when
                    LottoResult result = lottos.calculateResult(winningNumbers);

                    // then
                    assertThat(result.getCountByRank(Rank.FIFTH)).isEqualTo(3);
                },
                List.of(1, 2, 3, 10, 11, 12),
                List.of(1, 2, 4, 13, 14, 15),
                List.of(1, 3, 5, 16, 17, 18)
        );
    }

    @DisplayName("당첨 결과 계산 테스트: 당첨이 하나도 없으면 모두 꽝으로 집계된다")
    @Test
    void 당첨통계_전부꽝() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    // given
                    Lottos lottos = new Lottos(3);
                    WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");
                    WinningNumbers winningNumbers = new WinningNumbers(winningLotto, "7");

                    // when
                    LottoResult result = lottos.calculateResult(winningNumbers);

                    // then
                    assertThat(result.getCountByRank(Rank.FIRST)).isEqualTo(0);
                    assertThat(result.getCountByRank(Rank.SECOND)).isEqualTo(0);
                    assertThat(result.getCountByRank(Rank.THIRD)).isEqualTo(0);
                    assertThat(result.getCountByRank(Rank.FOURTH)).isEqualTo(0);
                    assertThat(result.getCountByRank(Rank.FIFTH)).isEqualTo(0);
                    assertThat(result.getCountByRank(Rank.NONE)).isEqualTo(3);
                },
                List.of(10, 11, 12, 13, 14, 15),
                List.of(20, 21, 22, 23, 24, 25),
                List.of(30, 31, 32, 33, 34, 35)
        );
    }


    @Override
    protected void runMain() {
    }
}