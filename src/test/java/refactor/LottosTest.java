package refactor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import refactoring.enumeration.LottoRank;
import refactoring.model.Lotto;
import refactoring.model.LottoNumber;
import refactoring.model.Lottos;
import refactoring.strategy.AutoLottoGenerateStrategy;
import refactoring.strategy.ManualLottoGenerationStrategy;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static refactoring.view.InputView.split;

public class LottosTest {

    static Stream<Arguments> generateData() {
        Map<LottoRank, Integer> finalRanks = LottoRank.initialize();
        finalRanks.put(LottoRank.SECOND, 1);
        finalRanks.put(LottoRank.FOURTH, 2);
        finalRanks.put(LottoRank.FIFTH, 1);

        return Stream.of(
            Arguments.of(Lottos.of(
                List.of(
                    Lotto.of(new ManualLottoGenerationStrategy(split("34,35,36,4,5,6")).generate()),
                    Lotto.of(new ManualLottoGenerationStrategy(split("34,35,36,7,23,1")).generate()),
                    Lotto.of(new ManualLottoGenerationStrategy(split("22,11,24,43,17,3")).generate()),
                    Lotto.of(new ManualLottoGenerationStrategy(split("34,35,36,7,42,25")).generate()),
                    Lotto.of(new ManualLottoGenerationStrategy(split("34,35,36,7,45,11")).generate()))
                ),

                Lotto.of(new ManualLottoGenerationStrategy(split("34,35,36,7,23,3")).generate()),
                LottoNumber.of(1),
                finalRanks
            )
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,5,6})
    @DisplayName("자동뽑기를 시행했을 때 quantity 만큼 생성한다.")
    void generateLottoTest(int quantity) {
        Lottos lottos = Lottos.of(new AutoLottoGenerateStrategy(), quantity);
        assertThat(lottos.getLottos().size()).isEqualTo(quantity);
    }

    @ParameterizedTest
    @MethodSource("generateData")
    @DisplayName("생성 된 로또의 당첨번호를 매칭하여 각 등급별 카운트 수를 리턴한다.")
    void getWinnerNumberMatchCountTest(Lottos lottos, Lotto winLotto, LottoNumber bonus, Map<LottoRank, Integer> expectedRanks) {
        Map<LottoRank, Integer> resultRanks = lottos.getWinnerNumberMatchCount(winLotto, bonus);

        assertThat(resultRanks.get(LottoRank.SECOND)).isEqualTo(expectedRanks.get(LottoRank.SECOND));
        assertThat(resultRanks.get(LottoRank.FOURTH)).isEqualTo(expectedRanks.get(LottoRank.FOURTH));
        assertThat(resultRanks.get(LottoRank.FIFTH)).isEqualTo(expectedRanks.get(LottoRank.FIFTH));
    }
}
