package lotto.controller;

import lotto.domain.PurchaseAmount;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public LottoController() {
    }

    public void run() {
        PurchaseAmount purchaseAmount = inputPurchaseAmount();
        
    }

    private PurchaseAmount inputPurchaseAmount() {
        while (true) {
            try {
                String amountInput = InputView.readPurchaseAmount();
                return new PurchaseAmount(amountInput);
            } catch (IllegalArgumentException error) {
                OutputView.printErrorMessage(error);
            }
        }
    }

}
