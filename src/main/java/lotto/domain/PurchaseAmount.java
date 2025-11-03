package lotto.domain;

import static lotto.constant.LottoSymbol.LOTTO_PRICE;
import static lotto.domain.error.ErrorMessage.PURCHASE_AMOUNT_ERROR_INVALID_UNIT;
import static lotto.domain.error.ErrorMessage.PURCHASE_AMOUNT_ERROR_NOT_NUMERIC;
import static lotto.domain.error.ErrorMessage.PURCHASE_AMOUNT_ERROR_NOT_POSITIVE;
import static lotto.domain.error.ErrorMessage.PURCHASE_AMOUNT_ERROR_OUT_OF_RANGE;

public final class PurchaseAmount {
    private static final String NUMBER_REGEX = "^\\d+$";
    private static final long ZERO = 0L;
    private static final double PERCENT = 100.0;

    private final long amount;

    public PurchaseAmount(String amountInput) {
        long amount = preprocessAmountInput(amountInput);
        validateAmount(amount);
        this.amount = amount;
    }

    public long getLottoCount() {
        return amount / LOTTO_PRICE;
    }

    public double calculateProfitRate(long totalPrize) {
        return (double) totalPrize / this.amount * PERCENT;
    }

    private long preprocessAmountInput(String amountInput) {
        String strippedAmount = amountInput.strip();
        return changeToNumber(strippedAmount);
    }

    private long changeToNumber(String strippedAmount) {
        try {
            return Long.parseLong(strippedAmount);
        } catch (NumberFormatException error) {
            throw createExceptionByParseFailure(strippedAmount);
        }
    }

    private IllegalArgumentException createExceptionByParseFailure(String strippedAmount) {
        if (isNotNumeric(strippedAmount)) {
            return new IllegalArgumentException(PURCHASE_AMOUNT_ERROR_NOT_NUMERIC.getMessage());
        }
        return new IllegalArgumentException(PURCHASE_AMOUNT_ERROR_OUT_OF_RANGE.getMessage());
    }

    private boolean isNotNumeric(String strippedAmount) {
        return !strippedAmount.matches(NUMBER_REGEX);
    }

    private void validateAmount(long amount) {
        validatePositive(amount);
        validateUnit(amount);
    }

    private void validatePositive(long amount) {
        if (amount <= ZERO) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_ERROR_NOT_POSITIVE.getMessage());
        }
    }

    private void validateUnit(long amount) {
        if (amount % LOTTO_PRICE != ZERO) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_ERROR_INVALID_UNIT.getMessage());
        }
    }
}
