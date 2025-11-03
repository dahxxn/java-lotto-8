package lotto.controller;

import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        PurchaseAmount purchaseAmount = inputPurchaseAmount();

        long lottoCount = purchaseAmount.getLottoCount();
        Lottos lottos = new Lottos(lottoCount);
        OutputView.printPurchaseLottoResult(lottoCount, lottos);

        WinningNumbers winningNumbers = readWinningNumbers();

        LottoResult lottoResult = lottos.calculateResult(winningNumbers);
        long totalPrize = lottoResult.calculateTotalPrize();
        double profitRate = purchaseAmount.calculateProfitRate(totalPrize);

        OutputView.printResult(lottoResult, profitRate);
    }

    private WinningNumbers readWinningNumbers() {
        WinningLotto winningLotto = inputWinningLotto();
        return inputWinningNumbers(winningLotto);
    }

    private PurchaseAmount inputPurchaseAmount() {
        while (true) {
            try {
                return new PurchaseAmount(InputView.readPurchaseAmount());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }

    private WinningLotto inputWinningLotto() {
        while (true) {
            try {
                return new WinningLotto(InputView.readWinningLotto());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }

    private WinningNumbers inputWinningNumbers(WinningLotto winningLotto) {
        while (true) {
            try {
                return new WinningNumbers(winningLotto, InputView.readBonusNumber());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }
}

