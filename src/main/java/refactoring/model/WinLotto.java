package refactoring.model;

import refactoring.strategy.ManualLottoGenerationStrategy;

import java.util.List;

import static refactoring.constants.SplitStringUtils.split;

public class WinLotto {

    private final Lotto lotto;

    public WinLotto(Lotto lotto) {
        this.lotto = lotto;
    }

    public static Lotto make(String numbers) {
        List<Integer> splitedNumbers = split(numbers);
        return new Lotto(new ManualLottoGenerationStrategy(splitedNumbers).generate());
    }

    public Lotto getLotto() {
        return this.lotto;
    }
}
