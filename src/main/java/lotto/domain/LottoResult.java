package lotto.domain;

import java.util.Map;
import lotto.constant.Rank;

public class LottoResult {
    private final Map<Rank, Integer> rankCounts;

    public LottoResult(Map<Rank, Integer> rankCounts) {
        this.rankCounts = Map.copyOf(rankCounts);
    }

    public int getCountByRank(Rank rank) {
        return rankCounts.getOrDefault(rank, 0);
    }

    public long calculateTotalPrize() {
        return rankCounts.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrize() * entry.getValue())
                .sum();
    }
}