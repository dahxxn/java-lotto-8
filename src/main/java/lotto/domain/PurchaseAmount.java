package lotto.domain;

import static lotto.domain.error.ErrorMessage.PURCHASE_AMOUNT_ERROR_INVALID_UNIT;
import static lotto.domain.error.ErrorMessage.PURCHASE_AMOUNT_ERROR_NOT_NUMERIC;
import static lotto.domain.error.ErrorMessage.PURCHASE_AMOUNT_ERROR_NOT_POSITIVE;

public class PurchaseAmount {
    private final int amount;
    private static final int ZERO = 0;
    private static final int LOTTO_UNIT = 1000;

    public PurchaseAmount(String amountInput) {
        int amount = preprocessAmountInput(amountInput);
        validateAmount(amount);
        this.amount = amount;
    }

    private int preprocessAmountInput(String amountInput) {
        String stripedAmount = amountInput.strip();
        return changeToNumber(stripedAmount);
    }

    private int changeToNumber(String stripedAmount) {
        try {
            return Integer.parseInt(stripedAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_ERROR_NOT_NUMERIC.getMessage());
        }
    }

    private void validateAmount(int amount) {
        validatePositive(amount);
        validateUnit(amount);
    }

    private void validatePositive(int amount) {
        if (amount <= ZERO) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_ERROR_NOT_POSITIVE.getMessage());
        }
    }

    private void validateUnit(int amount) {
        if (amount % LOTTO_UNIT != ZERO) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_ERROR_INVALID_UNIT.getMessage());
        }
    }
}