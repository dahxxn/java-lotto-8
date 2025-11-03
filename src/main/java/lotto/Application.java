package lotto;

import lotto.controller.LottoController;
import lotto.service.InputService;

public class Application {
    public static void main(String[] args) {
        InputService inputService = new InputService();
        LottoController controller = new LottoController(inputService);
        controller.run();
    }
}
