package lotto.controller;

import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.domain.WinningNumbers;
import lotto.service.InputService;
import lotto.view.output.OutputView;

public class LottoController {
    private final InputService inputService;

    public LottoController(InputService inputService) {
        this.inputService = inputService;
    }

    public void run() {
        PurchaseAmount purchaseAmount = inputService.readPurchaseAmount();
        long lottoCount = purchaseAmount.getLottoCount();
        Lottos lottos = new Lottos(lottoCount);
        OutputView.printPurchaseLottoResult(lottoCount, lottos);

        WinningLotto winningLotto = inputService.readWinningLotto();
        WinningNumbers winningNumbers = inputService.readWinningNumbers(winningLotto);

        LottoResult lottoResult = lottos.calculateResult(winningNumbers);
        long totalPrize = lottoResult.calculateTotalPrize();
        double profitRate = purchaseAmount.calculateProfitRate(totalPrize);
        OutputView.printResult(lottoResult, profitRate);
    }
}