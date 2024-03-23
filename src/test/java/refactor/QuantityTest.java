package refactor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import refactoring.model.Price;
import refactoring.model.Quantity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuantityTest {

    @ParameterizedTest
    @ValueSource(ints = {3500, 3578, 89877, 109493, 2450})
    @DisplayName("금액을 나누었을 때 나머지 값이 있는 경우 예외를 발생한다.")
    void invalidManualLottoCountTest(int price) {
        assertThatThrownBy(() -> Quantity.of(Price.of(price)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액을 잘못 입력 하였습니다. 로또 한장의 가격은 1000원 입니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -5, -7, -25, -1})
    @DisplayName("수동 로또 갯수를 음수로 입력했을 경우 예외가 발생한다.")
    void manualLottoCountNegativeTest(int manualLottoCount) {
        assertThatThrownBy(() -> Quantity.of(manualLottoCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음수는 입력할 수 없습니다.");
    }
}
