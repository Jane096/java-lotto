package refactoring.model;

import java.util.Objects;

import static refactoring.constants.LottoNumberConstants.MAX_LOTTO_NUMBER;
import static refactoring.constants.LottoNumberConstants.MIN_LOTTO_NUMBER;

public class LottoNumber {

    private final int number;

    private LottoNumber(int number) {
        validate(number);
        this.number = number;
    }

    public static LottoNumber of(int number) {
        return new LottoNumber(number);
    }

    private static void validate(int numbers) {
        if (numbers < MIN_LOTTO_NUMBER || numbers > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또번호에 포함되지 않는 숫자입니다.");
        }
    }

    public int getNumber() {
        return this.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LottoNumber that = (LottoNumber) obj;
        return number == that.number;
    }
}
