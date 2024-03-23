package refactoring.model;

import java.util.Set;

import static refactoring.constants.LottoNumberConstants.MAX_LOTTO_NUMBER;
import static refactoring.constants.LottoNumberConstants.MIN_LOTTO_NUMBER;

public class BonusNumber {

    private final int number;

    public BonusNumber(int number) {
        validate(number);
        this.number = number;
    }

    private static void validate(int numbers) {
        if (numbers < MIN_LOTTO_NUMBER || numbers > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또번호에 포함되지 않는 숫자입니다.");
        }
    }

    public int getNumber() {
        return this.number;
    }

    public boolean isBonusMatched(Set<LottoNumber> numbers) {
        return numbers.stream()
                .anyMatch(number -> number.getNumber() == this.number);
    }
}
