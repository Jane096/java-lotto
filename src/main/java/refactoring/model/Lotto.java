package refactoring.model;

import java.util.Arrays;

public class Lotto {

    private final LottoNumbers lottoNumbers;

    public Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public LottoNumbers getLottoNumbers() {
        return this.lottoNumbers;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.lottoNumbers.getNumbers().stream().map(LottoNumber::getNumber).toArray());
    }
}
