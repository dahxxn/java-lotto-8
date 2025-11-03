package lotto.domain;

import static lotto.constant.LottoSymbol.LOTTO_NUMBER_COUNT;
import static lotto.constant.LottoSymbol.LOTTO_NUMBER_RANGE_END;
import static lotto.constant.LottoSymbol.LOTTO_NUMBER_RANGE_START;
import static lotto.domain.error.ErrorMessage.LOTTO_ERROR_NOT_SIX_COUNT;
import static lotto.domain.error.ErrorMessage.LOTTO_ERROR_NUMBER_DUPLICATE;
import static lotto.domain.error.ErrorMessage.LOTTO_ERROR_NUMBER_OUT_OF_RANGE;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    public List<Integer> numbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateNumberRange(numbers);
        validateNumberDuplicate(numbers);
    }

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(LOTTO_ERROR_NOT_SIX_COUNT.getMessage());
        }
    }

    private void validateNumberRange(List<Integer> numbers) {
        boolean hasOutOfRange = numbers.stream()
                .anyMatch(n -> n < LOTTO_NUMBER_RANGE_START || n > LOTTO_NUMBER_RANGE_END);
        if (hasOutOfRange) {
            throw new IllegalArgumentException(LOTTO_ERROR_NUMBER_OUT_OF_RANGE.getMessage());
        }
    }

    private void validateNumberDuplicate(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() < numbers.size()) {
            throw new IllegalArgumentException(LOTTO_ERROR_NUMBER_DUPLICATE.getMessage());
        }
    }

}
