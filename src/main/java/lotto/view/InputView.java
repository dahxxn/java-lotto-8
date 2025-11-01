package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.view.message.InputMessage;

public class InputView {
    public static String readPurchaseAmount() {
        System.out.println(InputMessage.PURCHASE_AMOUNT);
        return readRawInput();
    }

    public static String readWinningNumbers() {
        System.out.println(InputMessage.WINNING_NUMBERS);
        return readRawInput();
    }

    public static String readBonusNumber() {
        System.out.println(InputMessage.BONUS_NUMBER);
        return readRawInput();
    }

    private static String readRawInput() {
        return Console.readLine();
    }
}
