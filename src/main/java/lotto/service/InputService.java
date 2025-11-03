package lotto.service;

import java.util.function.Supplier;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.domain.WinningNumbers;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

public class InputService {

    public PurchaseAmount readPurchaseAmount() {
        return repeatUntilValid(() ->
                new PurchaseAmount(InputView.readPurchaseAmount())
        );
    }

    public WinningLotto readWinningLotto() {
        return repeatUntilValid(() ->
                new WinningLotto(InputView.readWinningLotto())
        );
    }

    public WinningNumbers readWinningNumbers(WinningLotto winningLotto) {
        return repeatUntilValid(() ->
                new WinningNumbers(winningLotto, InputView.readBonusNumber())
        );
    }

    private <T> T repeatUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException error) {
                OutputView.printErrorMessage(error);
            }
        }
    }
}