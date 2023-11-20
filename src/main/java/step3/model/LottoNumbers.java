package step3.model;

import step3.enumeration.LottoRank;

import java.util.List;

import static step3.enumeration.LottoRank.SECOND;

public class LottoNumbers {

    private static final int MAX_LOTTO_SIZE = 6;
    private final List<Integer> numbers;

    public LottoNumbers(LottoNumber lottoNumber) {
        List<Integer> shuffled = lottoNumber.getShufflingNumbers();
        validate(shuffled);
        this.numbers = shuffled;
    }

    public LottoNumbers(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }

    public LottoRank getLottoRank(List<Integer> winNumbers, int bonusNumber) {
        int count = (int) winNumbers.stream()
                .filter(this.numbers::contains)
                .count();

        if (SECOND.getMatch() == count && this.numbers.contains(bonusNumber)) {
            return SECOND;
        }

        return LottoRank.getRank(count);
    }

    private void validate(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("로또 번호가 없습니다.");
        }

        if (numbers.size() != MAX_LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개가 되어야 합니다.");
        }
    }
}
