package lotto.domain;

import static lotto.domain.error.ErrorMessage.PURCHASE_AMOUNT_ERROR_NOT_NUMERIC;

public class PurchaseAmount {
    private final int amount;

    public PurchaseAmount(String amountInput) {
        int purchaseAmount = preprocessAmountInput(amountInput);
        
        this.amount = purchaseAmount;
    }

    private int preprocessAmountInput(String amountInput) {
        String purchaseAmount = amountInput.strip();
        return changeToNumber(purchaseAmount);
    }

    private int changeToNumber(String purchaseAmount) {
        try {
            return Integer.parseInt(purchaseAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_ERROR_NOT_NUMERIC.getMessage());
        }
    }
}
