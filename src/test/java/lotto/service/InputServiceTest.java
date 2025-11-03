package lotto.service;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.domain.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputServiceTest extends NsTest {
    private final InputService inputService = new InputService();

    @DisplayName("구입 금액 입력 테스트: 정상 입력 시 PurchaseAmount를 반환한다")
    @Test
    void 구입금액_입력_정상() {
        assertSimpleTest(() -> {
            run("8000");
            PurchaseAmount result = inputService.readPurchaseAmount();
            assertThat(result).isNotNull();
        });
    }

    @DisplayName("구입 금액 입력 테스트: 잘못된 입력 시 재입력을 요구한다")
    @Test
    void 구입금액_입력_재입력() {
        assertSimpleTest(() -> {
            runException("abc", "8000");
            PurchaseAmount result = inputService.readPurchaseAmount();

            assertThat(output()).contains("[ERROR]");
            assertThat(result).isNotNull();
        });
    }

    @DisplayName("구입 금액 입력 테스트: 여러 번 실패 후 성공")
    @Test
    void 구입금액_입력_여러번실패() {
        assertSimpleTest(() -> {
            runException("abc", "0", "-1000", "8000");
            PurchaseAmount result = inputService.readPurchaseAmount();

            String output = output();
            assertThat(output).contains("[ERROR]");
            assertThat(result).isNotNull();
        });
    }

    @DisplayName("당첨 번호 입력 테스트: 정상 입력 시 WinningLotto를 반환한다")
    @Test
    void 당첨번호_입력_정상() {
        assertSimpleTest(() -> {
            run("1,2,3,4,5,6");
            WinningLotto result = inputService.readWinningLotto();
            assertThat(result).isNotNull();
        });
    }

    @DisplayName("당첨 번호 입력 테스트: 잘못된 입력 시 재입력을 요구한다")
    @Test
    void 당첨번호_입력_재입력() {
        assertSimpleTest(() -> {
            runException("1,2,3,4,5", "1,2,3,4,5,6");
            WinningLotto result = inputService.readWinningLotto();

            assertThat(output()).contains("[ERROR]");
            assertThat(result).isNotNull();
        });
    }

    @DisplayName("보너스 번호 입력 테스트: 정상 입력 시 WinningNumbers를 반환한다")
    @Test
    void 보너스번호_입력_정상() {
        assertRandomUniqueNumbersInRangeTest(() -> {
            run("1,2,3,4,5,6", "7");

            WinningLotto winningLotto = inputService.readWinningLotto();
            WinningNumbers result = inputService.readWinningNumbers(winningLotto);

            assertThat(result).isNotNull();
        }, List.of(1, 2, 3, 4, 5, 6)); // 랜덤 고정 (필요시)
    }

    @DisplayName("보너스 번호 입력 테스트: 중복된 입력 시 재입력을 요구한다")
    @Test
    void 보너스번호_입력_중복() {
        assertRandomUniqueNumbersInRangeTest(() -> {
            run("1,2,3,4,5,6", "6", "7");

            WinningLotto winningLotto = inputService.readWinningLotto();
            WinningNumbers result = inputService.readWinningNumbers(winningLotto);

            assertThat(output()).contains("[ERROR]");
            assertThat(result).isNotNull();
        }, List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("보너스 번호 입력 테스트: 범위 밖 입력 시 재입력을 요구한다")
    @Test
    void 보너스번호_입력_범위밖() {
        assertRandomUniqueNumbersInRangeTest(() -> {
            run("1,2,3,4,5,6", "46", "7");

            WinningLotto winningLotto = inputService.readWinningLotto();
            WinningNumbers result = inputService.readWinningNumbers(winningLotto);

            assertThat(output()).contains("[ERROR]");
            assertThat(result).isNotNull();
        }, List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("전체 흐름 테스트: 모든 입력을 정상적으로 받는다")
    @Test
    void 전체흐름_정상() {
        assertRandomUniqueNumbersInRangeTest(() -> {
            run("8000", "1,2,3,4,5,6", "7");

            PurchaseAmount purchaseAmount = inputService.readPurchaseAmount();
            WinningLotto winningLotto = inputService.readWinningLotto();
            WinningNumbers winningNumbers = inputService.readWinningNumbers(winningLotto);

            assertThat(purchaseAmount).isNotNull();
            assertThat(winningLotto).isNotNull();
            assertThat(winningNumbers).isNotNull();
        }, List.of(8, 21, 23, 41, 42, 43));
    }

    @DisplayName("전체 흐름 테스트: 여러 번 실패 후 성공")
    @Test
    void 전체흐름_재입력() {
        assertRandomUniqueNumbersInRangeTest(() -> {
            run("abc", "8000", "1,2,3", "1,2,3,4,5,6", "6", "7");

            PurchaseAmount purchaseAmount = inputService.readPurchaseAmount();

            WinningLotto winningLotto = inputService.readWinningLotto();

            WinningNumbers winningNumbers = inputService.readWinningNumbers(winningLotto);

            String output = output();
            assertThat(output).contains("[ERROR]");
            assertThat(purchaseAmount).isNotNull();
            assertThat(winningLotto).isNotNull();
            assertThat(winningNumbers).isNotNull();
        }, List.of(8, 21, 23, 41, 42, 43));
    }

    @Override
    protected void runMain() {
    }
}