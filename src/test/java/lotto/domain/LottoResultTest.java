package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.Map;
import lotto.constant.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultTest {
    @DisplayName("로또 결과 생성 테스트: 등수별 개수를 담은 Map으로 LottoResult가 생성된다")
    @Test
    void 로또_결과_생성_테스트() {
        // given
        Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);
        rankCounts.put(Rank.FIRST, 0);
        rankCounts.put(Rank.SECOND, 0);
        rankCounts.put(Rank.THIRD, 0);
        rankCounts.put(Rank.FOURTH, 0);
        rankCounts.put(Rank.FIFTH, 1);
        rankCounts.put(Rank.NONE, 7);

        // when
        LottoResult result = new LottoResult(rankCounts);

        // then
        assertThat(result).isNotNull();
    }

    @DisplayName("등수별 개수 조회 테스트: 해당 등수의 개수를 정확히 반환한다")
    @Test
    void 등수별_개수_조회_테스트() {
        // given
        Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);
        rankCounts.put(Rank.FIRST, 1);
        rankCounts.put(Rank.SECOND, 2);
        rankCounts.put(Rank.THIRD, 0);
        rankCounts.put(Rank.FOURTH, 3);
        rankCounts.put(Rank.FIFTH, 4);
        rankCounts.put(Rank.NONE, 0);

        LottoResult result = new LottoResult(rankCounts);

        // when & then
        assertThat(result.getCountByRank(Rank.FIRST)).isEqualTo(1);
        assertThat(result.getCountByRank(Rank.SECOND)).isEqualTo(2);
        assertThat(result.getCountByRank(Rank.THIRD)).isEqualTo(0);
        assertThat(result.getCountByRank(Rank.FOURTH)).isEqualTo(3);
        assertThat(result.getCountByRank(Rank.FIFTH)).isEqualTo(4);
        assertThat(result.getCountByRank(Rank.NONE)).isEqualTo(0);
    }

    @DisplayName("총 상금 계산 테스트: 모든 등수의 상금 합계를 정확히 계산한다")
    @Test
    void 총_상금_계산_테스트() {
        // given
        Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);
        rankCounts.put(Rank.FIRST, 0);
        rankCounts.put(Rank.SECOND, 0);
        rankCounts.put(Rank.THIRD, 0);
        rankCounts.put(Rank.FOURTH, 0);
        rankCounts.put(Rank.FIFTH, 1);
        rankCounts.put(Rank.NONE, 7);

        LottoResult result = new LottoResult(rankCounts);

        // when
        long totalPrize = result.calculateTotalPrize();

        // then
        assertThat(totalPrize).isEqualTo(5_000);
    }

    @DisplayName("총 상금 계산 테스트: 여러 등수의 당첨이 있을 때 상금 합계를 정확히 계산한다")
    @Test
    void 총_상금_계산_테스트_여러등수() {
        // given
        Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);
        rankCounts.put(Rank.FIRST, 0);
        rankCounts.put(Rank.SECOND, 0);
        rankCounts.put(Rank.THIRD, 1);
        rankCounts.put(Rank.FOURTH, 2);
        rankCounts.put(Rank.FIFTH, 3);
        rankCounts.put(Rank.NONE, 2);

        LottoResult result = new LottoResult(rankCounts);

        // when
        long totalPrize = result.calculateTotalPrize();

        // then
        assertThat(totalPrize).isEqualTo(1_615_000);
    }

    @DisplayName("총 상금 계산 테스트: 당첨이 하나도 없으면 0원을 반환한다")
    @Test
    void 총_상금_계산_테스트_당첨없음() {
        // given
        Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);
        rankCounts.put(Rank.FIRST, 0);
        rankCounts.put(Rank.SECOND, 0);
        rankCounts.put(Rank.THIRD, 0);
        rankCounts.put(Rank.FOURTH, 0);
        rankCounts.put(Rank.FIFTH, 0);
        rankCounts.put(Rank.NONE, 8);

        LottoResult result = new LottoResult(rankCounts);

        // when
        long totalPrize = result.calculateTotalPrize();

        // then
        assertThat(totalPrize).isEqualTo(0);
    }

    @DisplayName("총 상금 계산 테스트: 1등 당첨 시 상금을 정확히 계산한다")
    @Test
    void 총_상금_계산_테스트_1등() {
        // given
        Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);
        rankCounts.put(Rank.FIRST, 1);
        rankCounts.put(Rank.SECOND, 0);
        rankCounts.put(Rank.THIRD, 0);
        rankCounts.put(Rank.FOURTH, 0);
        rankCounts.put(Rank.FIFTH, 0);
        rankCounts.put(Rank.NONE, 7);

        LottoResult result = new LottoResult(rankCounts);

        // when
        long totalPrize = result.calculateTotalPrize();

        // then
        assertThat(totalPrize).isEqualTo(2_000_000_000);
    }

    @DisplayName("불변성 테스트: 외부에서 전달한 Map을 수정해도 LottoResult는 영향받지 않는다")
    @Test
    void 로또_결과_불변성_테스트() {
        // given
        Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);
        rankCounts.put(Rank.FIRST, 0);
        rankCounts.put(Rank.SECOND, 0);
        rankCounts.put(Rank.THIRD, 0);
        rankCounts.put(Rank.FOURTH, 0);
        rankCounts.put(Rank.FIFTH, 1);
        rankCounts.put(Rank.NONE, 7);

        LottoResult result = new LottoResult(rankCounts);

        // when
        rankCounts.put(Rank.FIFTH, 999);

        // then
        assertThat(result.getCountByRank(Rank.FIFTH)).isEqualTo(1);
    }
}