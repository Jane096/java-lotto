package refactoring.model;

import refactoring.strategy.ManualLottoGenerationStrategy;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static refactoring.constants.SplitStringUtils.split;

public class WinLotto {

    private final Lotto lotto;

    public WinLotto(Lotto lotto) {
        this.lotto = lotto;
    }

    public static WinLotto make(String numbers) {
        List<Integer> splitedNumbers = split(numbers);
        Lotto lotto = new Lotto(new ManualLottoGenerationStrategy(splitedNumbers).generate());
        return new WinLotto(lotto);
    }

    public Lotto getLotto() {
        return this.lotto;
    }

    public Set<LottoNumber> getWinNumbers() {
        return Collections.unmodifiableSet(this.lotto.getLottoNumbers().getNumbers());
    }
}
