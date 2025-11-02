package lotto.view;

import static lotto.view.message.OutputMessage.LOTTO_COUNT_OUTPUT_FORMAT;

import java.util.List;
import lotto.domain.Lottos;

public final class OutputView {
    public static void printErrorMessage(final IllegalArgumentException error) {
        printLine(error.getMessage());
    }

    public static void printPurchaseLottoResult(final long lottoCount, final Lottos lottos) {
        printf(LOTTO_COUNT_OUTPUT_FORMAT, lottoCount);
        lottos.forEach(lotto -> printLine(formatLottosNumbers(lotto.numbers())));
    }

    private static void printLine(final String message) {
        System.out.println(message);
    }

    private static void printf(final String format, final Object... args) {
        System.out.printf(format, args);
    }

    private static String formatLottosNumbers(final List<Integer> numbers) {
        return numbers.toString();
    }
}
