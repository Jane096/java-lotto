package refactoring.strategy;

import refactoring.model.LottoNumber;
import refactoring.model.LottoNumbers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.shuffle;

public class AutoLottoGenerateStrategy implements LottoGenerateStrategy {

    protected static final List<Integer> ALL_LOTTO_NUMBERS = IntStream.range(1, 46).boxed().collect(Collectors.toList());
    private static final int MAX_LOTTO_SIZE = 6;

    @Override
    public LottoNumbers generate() {
        shuffle(ALL_LOTTO_NUMBERS);

        Set<LottoNumber> lottoNumbers = ALL_LOTTO_NUMBERS.stream().limit(MAX_LOTTO_SIZE)
                .map(LottoNumber::of)
                .collect(Collectors.toSet());

        return LottoNumbers.of(lottoNumbers);
    }
}
