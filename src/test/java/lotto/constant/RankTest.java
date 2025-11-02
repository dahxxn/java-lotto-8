package lotto.constant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @DisplayName("등수 판단 테스트: 6개 일치하면 1등이다")
    @Test
    void of_1등() {
        // given
        int matchCount = 6;
        boolean matchBonus = false;

        // when
        Rank rank = Rank.of(matchCount, matchBonus);

        // then
        assertThat(rank).isEqualTo(Rank.FIRST);
        assertThat(rank.getPrize()).isEqualTo(2_000_000_000);
    }

    @DisplayName("등수 판단 테스트: 5개 일치 + 보너스 일치하면 2등이다")
    @Test
    void of_2등() {
        // given
        int matchCount = 5;
        boolean matchBonus = true;

        // when
        Rank rank = Rank.of(matchCount, matchBonus);

        // then
        assertThat(rank).isEqualTo(Rank.SECOND);
        assertThat(rank.getPrize()).isEqualTo(30_000_000);
    }

    @DisplayName("등수 판단 테스트: 5개 일치하면 3등이다")
    @Test
    void of_3등() {
        // given
        int matchCount = 5;
        boolean matchBonus = false;

        // when
        Rank rank = Rank.of(matchCount, matchBonus);

        // then
        assertThat(rank).isEqualTo(Rank.THIRD);
        assertThat(rank.getPrize()).isEqualTo(1_500_000);
    }

    @DisplayName("등수 판단 테스트: 4개 일치하면 4등이다")
    @Test
    void of_4등() {
        // given
        int matchCount = 4;
        boolean matchBonus = false;

        // when
        Rank rank = Rank.of(matchCount, matchBonus);

        // then
        assertThat(rank).isEqualTo(Rank.FOURTH);
        assertThat(rank.getPrize()).isEqualTo(50_000);
    }

    @DisplayName("등수 판단 테스트: 3개 일치하면 5등이다")
    @Test
    void of_5등() {
        // given
        int matchCount = 3;
        boolean matchBonus = false;

        // when
        Rank rank = Rank.of(matchCount, matchBonus);

        // then
        assertThat(rank).isEqualTo(Rank.FIFTH);
        assertThat(rank.getPrize()).isEqualTo(5_000);
    }

    @DisplayName("등수 판단 테스트: 2개 이하 일치하면 꽝이다")
    @Test
    void of_꽝() {
        // given
        int matchCount = 2;
        boolean matchBonus = false;

        // when
        Rank rank = Rank.of(matchCount, matchBonus);

        // then
        assertThat(rank).isEqualTo(Rank.NONE);
        assertThat(rank.getPrize()).isEqualTo(0);
    }

}