package lotto.domain;

import static lotto.constant.LottoSymbol.LOTTO_NUMBER_RANGE_END;
import static lotto.constant.LottoSymbol.LOTTO_NUMBER_RANGE_START;
import static lotto.domain.error.ErrorMessage.BONUS_NUMBER_ERROR_DUPLICATE;
import static lotto.domain.error.ErrorMessage.BONUS_NUMBER_ERROR_NOT_NUMERIC;
import static lotto.domain.error.ErrorMessage.BONUS_NUMBER_ERROR_OUT_OF_RANGE;

public class WinningNumbers {
    private final WinningLotto winningLotto;
    private final int bonusNumber;

    public WinningNumbers(WinningLotto winningLotto, String bonusNumberInput) {
        this.winningLotto = winningLotto;
        int bonusNumber = preprocessBonusNumberInput(bonusNumberInput);
        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private int preprocessBonusNumberInput(String bonusNumberInput) {
        String strippedBonusNumberInput = bonusNumberInput.strip();
        return changeToNumber(strippedBonusNumberInput);
    }

    private int changeToNumber(String numberToken) {
        try {
            return Integer.parseInt(numberToken);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException(BONUS_NUMBER_ERROR_NOT_NUMERIC.getMessage());
        }
    }

    private void validateBonusNumber(int bonusNumber) {
        validateBonusNumberRange(bonusNumber);
        validateBonusNumberDuplicate(bonusNumber);
    }

    private void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < LOTTO_NUMBER_RANGE_START || bonusNumber > LOTTO_NUMBER_RANGE_END) {
            throw new IllegalArgumentException(BONUS_NUMBER_ERROR_OUT_OF_RANGE.getMessage());
        }
    }

    private void validateBonusNumberDuplicate(int bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_ERROR_DUPLICATE.getMessage());
        }
    }
}
