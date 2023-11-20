package step3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import step3.enumeration.LottoRank;
import step3.model.Lotto;
import step3.model.LottoNumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static step3.enumeration.LottoRank.*;

public class LottoTest {

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(FIFTH, Arrays.asList(1, 2, 3, 7, 8, 9), Arrays.asList(1, 2, 3, 4, 5, 6), 45),
                Arguments.of(ZERO,  Arrays.asList(1, 2, 3, 7, 8, 9), Arrays.asList(1, 35, 23, 11, 12, 21), 30),
                Arguments.of(FIRST,  Arrays.asList(1, 2, 3, 7, 8, 9), Arrays.asList(1, 2, 3, 7, 8, 9), 30),
                Arguments.of(SECOND,  Arrays.asList(1, 2, 3, 7, 8, 9), Arrays.asList(1, 2, 3, 7, 8, 10), 9)
        );
    }

    @ParameterizedTest
    @MethodSource("generateData")
    @DisplayName("로또 당첨번호와 일치하는 숫자의 등급을 리턴한다.")
    void lottoWinnerTest(LottoRank rank, List<Integer> numbers, List<Integer> winNumbers, int bonus) {
        Lotto lotto = new Lotto(new LottoNumbers(numbers));

        LottoRank rankName = lotto.getLottoNumbers().getLottoRank(winNumbers, bonus);
        assertThat(rankName).isEqualTo(rank);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("로또번호가 없거나 비어있다면 예외를 발생시킨다.")
    void lottoValidateTest(List<Integer> numbers) {
        assertThatThrownBy(() -> new Lotto(new LottoNumbers(numbers)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호가 없습니다.");
    }

    @Test
    @DisplayName("로또 숫자가 6개가 아닌경우 예외를 발생시킨다.")
    void lottoValidateOverSixTest() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        assertThatThrownBy(() -> new Lotto(new LottoNumbers(numbers)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개가 되어야 합니다.");
    }
}
