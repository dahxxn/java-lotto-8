package lotto.view.output;

public final class OutputMessage {
    public static final String LOTTO_COUNT_OUTPUT_FORMAT = "\n%d개를 구매했습니다.\n";

    public static final String LOTTO_RESULT_HEADER = "\n당첨 통계";
    public static final String LOTTO_RESULT_LINE_BREAK = "---";
    public static final String LOTTO_RESULT_COUNT_FORMAT = "%s - %d개";

    public static final String PROFIT_RATE_FORMAT = "총 수익률은 %.1f%%입니다.\n";

    private OutputMessage() {
    }
}