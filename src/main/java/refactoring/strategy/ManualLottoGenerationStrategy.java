package refactoring.strategy;

import test.model.LottoNumber;
import test.model.LottoNumbers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManualLottoGenerationStrategy implements LottoGenerateStrategy {

    private final List<Integer> lottoNumbers;

    public ManualLottoGenerationStrategy(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    @Override
    public LottoNumbers generate() {
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        this.lottoNumbers.forEach(number -> lottoNumbers.add(LottoNumber.of(number)));
        return LottoNumbers.of(lottoNumbers);
    }
}
