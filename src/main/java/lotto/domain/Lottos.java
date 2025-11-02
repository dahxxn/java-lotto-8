package lotto.domain;

import static lotto.constant.LottoSymbol.LOTTO_NUMBER_COUNT;
import static lotto.constant.LottoSymbol.LOTTO_NUMBER_RANGE_END;
import static lotto.constant.LottoSymbol.LOTTO_NUMBER_RANGE_START;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(long lottoCount) {
        lottos = new ArrayList<>();
        generateAllLotto(lottoCount);
    }

    public void forEach(Consumer<Lotto> action) {
        lottos.forEach(action);
    }

    private void generateAllLotto(long lottoCount) {
        for (long i = 0; i < lottoCount; i++) {
            generateLotto();
        }
    }

    private void generateLotto() {
        List<Integer> lottoNumbers = generateLottoNumber();
        lottos.add(new Lotto(lottoNumbers));
    }

    private List<Integer> generateLottoNumber() {
        List<Integer> lottoNumbers = new ArrayList<>(
                Randoms.pickUniqueNumbersInRange(LOTTO_NUMBER_RANGE_START, LOTTO_NUMBER_RANGE_END, LOTTO_NUMBER_COUNT));
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }

}
