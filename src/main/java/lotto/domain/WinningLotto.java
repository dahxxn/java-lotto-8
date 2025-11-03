package lotto.domain;

import static lotto.domain.error.ErrorMessage.WINNING_LOTTO_ERROR_NOT_NUMERIC;

import java.util.List;
import java.util.stream.Stream;

public class WinningLotto {
    private static final String SPLIT_REGEX = ",";

    private final Lotto winningNumbers;

    public WinningLotto(String winningNumbersInput) {
        List<Integer> winningLottoNumbers = preprocessWinningNumbersInput(winningNumbersInput);
        this.winningNumbers = new Lotto(winningLottoNumbers);
    }

    public boolean contains(int bonusNumber) {
        return winningNumbers.numbers().contains(bonusNumber);
    }

    public int countMatches(Lotto lotto) {
        return (int) lotto.numbers().stream()
                .filter(winningNumbers.numbers()::contains)
                .count();
    }

    private List<Integer> preprocessWinningNumbersInput(String winningNumbersInput) {
        return Stream.of(winningNumbersInput.split(SPLIT_REGEX))
                .map(String::strip)
                .map(this::changeToNumber)
                .toList();
    }

    private int changeToNumber(String numberToken) {
        try {
            return Integer.parseInt(numberToken);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException(WINNING_LOTTO_ERROR_NOT_NUMERIC.getMessage());
        }
    }
}
