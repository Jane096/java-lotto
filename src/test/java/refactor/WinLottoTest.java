package refactor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import refactoring.model.LottoNumber;
import refactoring.model.LottoNumbers;
import refactoring.model.WinLotto;
import refactoring.strategy.ManualLottoGenerationStrategy;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static refactoring.constants.SplitStringUtils.split;

public class WinLottoTest {

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6", "3,4,5,6,7,8", "13,25,45,36,7,24", "34,11,5,43,27,9"})
    @DisplayName("로또 숫자에 포함된 숫자를 입력할 경우 정상적으로 당첨번호 로또를 생성한다.")
    void makeTest(String numbers) {
        WinLotto winLotto = WinLotto.make(numbers);

        Set<LottoNumber> lottoNumbers = winLotto.getWinNumbers();
        List<Integer> splitedNumbers = split(numbers);
        LottoNumbers lottoNumbersExpected = new ManualLottoGenerationStrategy(splitedNumbers).generate();

        assertThat(lottoNumbers.containsAll(lottoNumbersExpected.getNumbers())).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,61", "3,47,56,6,7,8", "13,255,45,36,7,24", "71,11,5,43,27,9"})
    @DisplayName("1 ~ 45 사이의 숫자가 아닌 당첨번호를 입력할 경우 로또를 생성할 수 없다.")
    void makeLottoExceptionTest(String numbers) {
        assertThatThrownBy(() -> WinLotto.make(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또번호에 포함되지 않는 숫자입니다.");
    }
}
