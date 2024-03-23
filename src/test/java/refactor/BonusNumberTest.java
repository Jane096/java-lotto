package refactor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import refactoring.model.BonusNumber;
import refactoring.model.LottoNumber;
import refactoring.model.LottoNumbers;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BonusNumberTest {

    @ParameterizedTest
    @CsvSource(value = {"1:false","21:true","23:false","14:true","24:false"}, delimiter = ':')
    @DisplayName("로또 번호 중에서 보너스 번호와 일치하는 숫자가 있다면 리턴한다.")
    void isBonusMatchedTest(int bonus, boolean expectedResult) {
        Set<LottoNumber> lottoNumberTest = Set.of(LottoNumber.of(14),LottoNumber.of(21),LottoNumber.of(32),LottoNumber.of(41),LottoNumber.of(25),LottoNumber.of(5));
        LottoNumbers lottoNumbers = LottoNumbers.of(lottoNumberTest);

        boolean isBonusMated = new BonusNumber(bonus).isBonusMatched(lottoNumbers.getNumbers());
        assertThat(isBonusMated).isEqualTo(expectedResult);
    }
}
