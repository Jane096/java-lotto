package refactor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import refactoring.model.LottoNumber;
import refactoring.model.LottoNumbers;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumbersTest {

    static Stream<Arguments> generateData() {
        Set<LottoNumber> lottoNumbers1 = Set.of(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6));
        Set<LottoNumber> lottoNumbers2 = Set.of(LottoNumber.of(11),LottoNumber.of(22),LottoNumber.of(35),LottoNumber.of(41),LottoNumber.of(25),LottoNumber.of(6));
        Set<LottoNumber> lottoNumbers3 = Set.of(LottoNumber.of(14),LottoNumber.of(21),LottoNumber.of(32),LottoNumber.of(44),LottoNumber.of(15),LottoNumber.of(26));



        return Stream.of(
                Arguments.of(lottoNumbers1, 1),
                Arguments.of(lottoNumbers2, 2),
                Arguments.of(lottoNumbers3, 3));
    }

    @ParameterizedTest
    @MethodSource("generateData")
    @DisplayName("당첨번호와 일치하는 숫자를 세어 리턴한다.")
    void getMatchCountTest(Set<LottoNumber> lottoNumber, int expectedMatchCount) {
        Set<LottoNumber> winNumbers = Set.of(LottoNumber.of(14),LottoNumber.of(21),LottoNumber.of(32),LottoNumber.of(41),LottoNumber.of(25),LottoNumber.of(5));
        LottoNumbers lottoNumbers = LottoNumbers.of(lottoNumber);
        int count = lottoNumbers.getMatchCount(winNumbers);

        assertThat(count).isEqualTo(expectedMatchCount);
    }

    @ParameterizedTest
    @MethodSource("generateData")
    @DisplayName("로또숫자가 6개 이상이라면 예외를 발생시키지 않는다.")
    void validateTest(Set<LottoNumber> numbers) {
        LottoNumbers lottoNumbers = LottoNumbers.of(numbers);
        assertThat(lottoNumbers.getNumbers().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("로또숫자가 6개 이하라면 예외를 발생시킨다. (5개일 때)")
    void validateExceptionFiveNumbersTest() {
        Set<LottoNumber> lottoNumberTest = Set.of(LottoNumber.of(14),LottoNumber.of(21),LottoNumber.of(32),LottoNumber.of(41),LottoNumber.of(25));
        assertThatThrownBy(() -> LottoNumbers.of(lottoNumberTest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자는 6개이어야 합니다.");
    }

    @Test
    @DisplayName("로또숫자가 6개 이하라면 예외를 발생시킨다. (4개일 때)")
    void validateExceptionFourNumbersTest() {
        Set<LottoNumber> lottoNumberTestSecond = Set.of(LottoNumber.of(14),LottoNumber.of(21),LottoNumber.of(32),LottoNumber.of(41));
        assertThatThrownBy(() -> LottoNumbers.of(lottoNumberTestSecond))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자는 6개이어야 합니다.");
    }
}
