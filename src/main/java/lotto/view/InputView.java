package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.view.message.InputMessage;

public class InputView {
    public static String readLottoPrice() {
        System.out.println(InputMessage.PURCHASE_AMOUNT);
        return readRawInput();
    }

    private static String readRawInput() {
        return Console.readLine();
    }
}
