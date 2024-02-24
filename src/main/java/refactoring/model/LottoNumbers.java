package refactoring.model;

import test.model.LottoNumber;

import java.util.Collections;
import java.util.Set;

public class LottoNumbers {

    private static final int MIN_LOTTO_SIZE = 6;
    private final Set<test.model.LottoNumber> numbers;

    private LottoNumbers(Set<test.model.LottoNumber> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public static LottoNumbers of(Set<test.model.LottoNumber> numbers) {
        return new LottoNumbers(numbers);
    }

    public int getMatchCount(Set<test.model.LottoNumber> winNumbers) {
        return (int) this.numbers.stream()
                .filter(winNumbers::contains)
                .count();
    }

    public boolean isBonusMatched(test.model.LottoNumber bonus) {
        return this.numbers.contains(bonus);
    }

    public Set<test.model.LottoNumber> getNumbers() {
        return Collections.unmodifiableSet(this.numbers);
    }

    private void validate(Set<LottoNumber> numbers) {
        if (numbers.size() < MIN_LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 숫자는 6개이어야 합니다.");
        }
    }
}
