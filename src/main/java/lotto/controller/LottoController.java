package lotto.controller;

import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public LottoController() {
    }

    public void run() {
        PurchaseAmount purchaseAmount = inputPurchaseAmount();
        Lottos lottos = new Lottos(purchaseAmount.getLottoCount());
        OutputView.printPurchaseLottoResult(purchaseAmount.getLottoCount(), lottos);

        WinningLotto winningLotto = inputWinningLotto();
        WinningNumbers winningNumbers = inputWinningNumbers(winningLotto);
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

    private WinningLotto inputWinningLotto() {
        while (true) {
            try {
                String winningNumbersInput = InputView.readWinningLotto();
                return new WinningLotto(winningNumbersInput);
            } catch (IllegalArgumentException error) {
                OutputView.printErrorMessage(error);
            }
        }
    }

    private WinningNumbers inputWinningNumbers(WinningLotto winningLotto) {
        while (true) {
            try {
                String bonusNumberInput = InputView.readBonusNumber();
                return new WinningNumbers(winningLotto, bonusNumberInput);
            } catch (IllegalArgumentException error) {
                OutputView.printErrorMessage(error);
            }
        }
    }


}
