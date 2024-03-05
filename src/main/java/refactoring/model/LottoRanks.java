package refactoring.model;

import refactoring.enumeration.LottoRank;

import java.util.Collections;
import java.util.Map;

public class LottoRanks {

    private final Map<LottoRank, Integer> ranks;

    private LottoRanks(Map<LottoRank, Integer> ranks) {
        this.ranks = ranks;
    }

    public static LottoRanks finalRanks(Lotto winLotto, BonusNumber bonus, Lottos lottos) {
        Map<LottoRank, Integer> ranks = LottoRank.initialize();
        for (Lotto lotto : lottos.getLottos()) {
            LottoRank lottoRank = findRank(winLotto, bonus, lotto.getLottoNumbers());
            ranks.put(lottoRank, ranks.get(lottoRank) + 1);
        }

        return new LottoRanks(ranks);
    }

    public Map<LottoRank, Integer> getRank() {
        return Collections.unmodifiableMap(this.ranks);
    }

    public static LottoRank findRank(Lotto winnerLotto, BonusNumber bonus, LottoNumbers lottoNumbers) {
        int match = lottoNumbers.getMatchCount(winnerLotto.getLottoNumbers().getNumbers());
        boolean bonusResult = bonus.isBonusMatched(lottoNumbers.getNumbers());

        return LottoRank.getRank(match, bonusResult);
    }
}
