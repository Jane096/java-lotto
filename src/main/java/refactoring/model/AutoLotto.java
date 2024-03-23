package refactoring.model;

import refactoring.strategy.AutoLottoGenerateStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class AutoLotto {

    private final List<Lotto> lottos;

    private AutoLotto(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static List<Lotto> make(int quantity) {
        List<Lotto> lottos = new ArrayList<>();
        IntStream.range(0, quantity)
                .forEach(e -> lottos.add(new Lotto(new AutoLottoGenerateStrategy().generate())));

        return lottos;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(this.lottos);
    }
}
