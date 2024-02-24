package refactoring.model;

import java.util.Objects;

public class LottoNumber {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
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
