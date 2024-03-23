package refactor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import refactoring.model.AutoLotto;
import refactoring.model.Lotto;
import refactoring.model.Lottos;
import refactoring.model.ManualLotto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    @Test
    @DisplayName("수동 로또와 자동로또는 합친다.")
    void sumAllLottosTest() {
        List<Lotto> autos = AutoLotto.make(4);
        List<Lotto> manuals = new ManualLotto(List.of("34,35,1,2,3,7")).make();

        Lottos lottos = Lottos.sumAllLottos(autos, manuals);
        assertThat(lottos.getLottos().size()).isEqualTo(5);
    }
}
