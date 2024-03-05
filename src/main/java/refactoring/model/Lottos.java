package refactoring.model;

import refactoring.enumeration.LottoRank;
import refactoring.strategy.LottoGenerateStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos of(LottoGenerateStrategy lottoGenerateStrategy, int quantity) {
        List<Lotto> lottos = new ArrayList<>();
        IntStream.range(0, quantity)
                .forEach(e -> lottos.add(new Lotto(lottoGenerateStrategy.generate())));

        return new Lottos(lottos);
    }

    public static Lottos of(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public static Lottos getAll(Lottos autoLottos, Lottos manualLottos) {
        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(autoLottos.getLottos());
        lottos.addAll(manualLottos.getLottos());

        return new Lottos(lottos);
    }

    public Map<LottoRank, Integer> getWinnerNumberMatchCount(Lotto winLotto, LottoNumber bonus) {
        Map<LottoRank, Integer> ranks = LottoRank.initialize();
        for (Lotto lotto : this.lottos) {
            LottoRank lottoRank = lotto.findRank(winLotto, bonus);
            ranks.put(lottoRank, ranks.get(lottoRank) + 1);
        }

        return ranks;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(this.lottos);
    }
}
