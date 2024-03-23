package refactor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import refactoring.model.AutoLotto;
import refactoring.model.Lotto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoLottoTest {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,5,6})
    @DisplayName("자동뽑기를 시행했을 때 quantity 만큼 생성한다.")
    void generateLottoTest(int quantity) {
        List<Lotto> lottos = AutoLotto.make(quantity);
        assertThat(lottos.size()).isEqualTo(quantity);
    }
}
