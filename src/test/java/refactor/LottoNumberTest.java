package refactor;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import refactoring.model.LottoNumber;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1,45,23,24,30,35,39,19,28})
    @DisplayName("1부터 45의 숫자 중 랜덤한 6자리 숫자를 생성한다.")
    void getNumberTest(int number) {
        Assertions.assertThat(LottoNumber.of(number)).isEqualTo(LottoNumber.of(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {46,48,50,100,1001,99,85})
    @DisplayName("1부터 45의 숫자에 포함되지 않는다면 예외를 발생시킨다.")
    void getNumberExceptionTest(int number) {
        assertThatThrownBy(() -> LottoNumber.of(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또번호에 포함되지 않는 숫자입니다.");
    }
}