package lotto.domain;

import static lotto.domain.error.ErrorMessage.WINNING_LOTTO_ERROR_NOT_NUMERIC;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<Integer> totalNumbers = new HashSet<>(lotto.numbers());
        totalNumbers.addAll(winningNumbers.numbers());

        int lottoNumberCount = lotto.numbers().size();
        int winningNumberCount = winningNumbers.numbers().size();
        return (lottoNumberCount + winningNumberCount) - totalNumbers.size();
    }

    private List<Integer> preprocessWinningNumbersInput(String winningNumbersInput) {
        List<Integer> numbers = new ArrayList<>();
        for (String numberToken : winningNumbersInput.split(SPLIT_REGEX)) {
            String strippedNumberToken = numberToken.strip();
            int number = changeToNumber(strippedNumberToken);
            numbers.add(number);
        }
        return numbers;
    }

    private int changeToNumber(String numberToken) {
        try {
            return Integer.parseInt(numberToken);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException(WINNING_LOTTO_ERROR_NOT_NUMERIC.getMessage());
        }
    }
}
