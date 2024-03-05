package refactoring.model;

import refactoring.enumeration.LottoRank;

import java.util.Collections;
import java.util.Map;

public class LottoRanks {

    private final Map<LottoRank, Integer> ranks;

    private LottoRanks(Map<LottoRank, Integer> ranks) {
        this.ranks = ranks;
    }

    public static LottoRanks of(Map<LottoRank, Integer> ranks) {
        return new LottoRanks(ranks);
    }

    public Map<LottoRank, Integer> getRank() {
        return Collections.unmodifiableMap(this.ranks);
    }

    public Double getRating(int payPrice, Map<LottoRank, Integer> winnerBoard) {
        Price price = Price.ofTotalPrice(winnerBoard);
        return Math.floor(price.getPrice() * 100.0 / payPrice) / 100;
    }

    public static LottoRank findRank(Lotto winnerLotto, LottoNumber bonus, LottoNumbers lottoNumbers) {
        int match = lottoNumbers.getMatchCount(winnerLotto.getLottoNumbers().getNumbers());
        boolean bonusResult = lottoNumbers.isBonusMatched(bonus);

        return LottoRank.getRank(match, bonusResult);
    }
}
