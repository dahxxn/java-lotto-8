package lotto.view;

import static lotto.view.message.OutputMessage.LOTTO_COUNT_OUTPUT_FORMAT;
import static lotto.view.message.OutputMessage.LOTTO_RESULT_COUNT_FORMAT;
import static lotto.view.message.OutputMessage.LOTTO_RESULT_HEADER;
import static lotto.view.message.OutputMessage.LOTTO_RESULT_LINE_BREAK;
import static lotto.view.message.OutputMessage.PROFIT_RATE_FORMAT;

import java.util.List;
import lotto.constant.Rank;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;

public final class OutputView {
    public static void printErrorMessage(final IllegalArgumentException error) {
        printLine(error.getMessage());
    }

    public static void printPurchaseLottoResult(final long lottoCount, final Lottos lottos) {
        printf(LOTTO_COUNT_OUTPUT_FORMAT, lottoCount);
        lottos.forEach(lotto -> printLine(formatLottoNumbers(lotto.numbers())));
        printLine("");
    }

    public static void printResult(final LottoResult result, final double profitRate) {
        printStatistics(result);
        printProfitRate(profitRate);
    }

    private static void printStatistics(final LottoResult result) {
        printLine(LOTTO_RESULT_HEADER);
        printLine(LOTTO_RESULT_LINE_BREAK);

        for (Rank rank : Rank.values()) {
            if (rank == Rank.NONE) {
                continue;
            }
            int count = result.getCountByRank(rank);
            printf(LOTTO_RESULT_COUNT_FORMAT + "\n", rank.getMessage(), count);
        }
    }

    private static void printProfitRate(final double profitRate) {
        printf(PROFIT_RATE_FORMAT, profitRate);
    }

    private static void printLine(final String message) {
        System.out.println(message);
    }

    private static void printf(final String format, final Object... args) {
        System.out.printf(format, args);
    }

    private static String formatLottoNumbers(final List<Integer> numbers) {
        return numbers.toString();
    }
}