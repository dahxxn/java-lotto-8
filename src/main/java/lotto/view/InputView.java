package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.view.message.InputMessage;

public class InputView {
    public static String readPurchaseAmount() {
        return readRawInput(InputMessage.PURCHASE_AMOUNT);
    }

    public static String readWinningNumbers() {
        return readRawInput(InputMessage.WINNING_NUMBERS);
    }

    public static String readBonusNumber() {
        return readRawInput(InputMessage.BONUS_NUMBER);
    }

    private static String readRawInput(String inputMessage) {
        System.out.println(inputMessage);
        return Console.readLine();
    }
}
