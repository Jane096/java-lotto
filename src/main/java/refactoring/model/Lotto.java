package refactoring.model;

import refactoring.enumeration.LottoRank;

import java.util.Arrays;

public class Lotto {

    private final LottoNumbers lottoNumbers;

    private Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static Lotto of(LottoNumbers lottoNumbers) {
        return new Lotto(lottoNumbers);
    }

    public LottoRank findRank(Lotto winnerLotto, LottoNumber bonus) {
        int match = this.lottoNumbers.getMatchCount(winnerLotto.lottoNumbers.getNumbers());
        boolean bonusResult = this.lottoNumbers.isBonusMatched(bonus);

        return LottoRank.getRank(match, bonusResult);
    }

    @Override
    public String toString() {
        return Arrays.toString(this.lottoNumbers.getNumbers().stream().map(LottoNumber::getNumber).toArray());
    }
}
