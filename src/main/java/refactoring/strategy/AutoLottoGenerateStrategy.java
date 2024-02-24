package refactoring.strategy;

import test.model.LottoNumber;
import test.model.LottoNumbers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.shuffle;

public class AutoLottoGenerateStrategy implements LottoGenerateStrategy {

    protected final List<Integer> allLottoNumbers = IntStream.range(1, 46).boxed().collect(Collectors.toList());
    private static final int MAX_LOTTO_SIZE = 6;

    @Override
    public LottoNumbers generate() {
        shuffle(this.allLottoNumbers);

        Set<LottoNumber> lottoNumbers = this.allLottoNumbers.stream().limit(MAX_LOTTO_SIZE)
                .map(LottoNumber::of)
                .collect(Collectors.toSet());

        return LottoNumbers.of(lottoNumbers);
    }
}
