package refactor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import refactoring.enumeration.LottoRank;
import refactoring.model.BonusNumber;
import refactoring.model.Lotto;
import refactoring.model.LottoRanks;
import refactoring.model.Lottos;
import refactoring.strategy.ManualLottoGenerationStrategy;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static refactoring.view.InputView.split;

public class LottoRanksTest {

    static Stream<Arguments> generateData() {
        Map<LottoRank, Integer> finalRanks = LottoRank.initialize();
        finalRanks.put(LottoRank.SECOND, 1);
        finalRanks.put(LottoRank.FOURTH, 2);
        finalRanks.put(LottoRank.FIFTH, 1);

        return Stream.of(
                Arguments.of(new Lottos(
                                List.of(
                                        new Lotto(new ManualLottoGenerationStrategy(split("34,35,36,4,5,6")).generate()),
                                        new Lotto(new ManualLottoGenerationStrategy(split("34,35,36,7,23,1")).generate()),
                                        new Lotto(new ManualLottoGenerationStrategy(split("22,11,24,43,17,3")).generate()),
                                        new Lotto(new ManualLottoGenerationStrategy(split("34,35,36,7,42,25")).generate()),
                                        new Lotto(new ManualLottoGenerationStrategy(split("34,35,36,7,45,11")).generate()))
                        ),

                        new Lotto(new ManualLottoGenerationStrategy(split("34,35,36,7,23,3")).generate()),
                        new BonusNumber(1),
                        finalRanks
                )
        );
    }

    @ParameterizedTest
    @MethodSource("generateData")
    @DisplayName("생성 된 로또의 당첨번호를 매칭하여 각 등급별 카운트 수를 리턴한다.")
    void getWinnerNumberMatchCountTest(Lottos lottos, Lotto winLotto, BonusNumber bonus, Map<LottoRank, Integer> expectedRanks) {
        LottoRanks resultRanks = LottoRanks.finalRanks(winLotto, bonus, lottos);

        assertThat(resultRanks.getRank().get(LottoRank.SECOND)).isEqualTo(expectedRanks.get(LottoRank.SECOND));
        assertThat(resultRanks.getRank().get(LottoRank.FOURTH)).isEqualTo(expectedRanks.get(LottoRank.FOURTH));
        assertThat(resultRanks.getRank().get(LottoRank.FIFTH)).isEqualTo(expectedRanks.get(LottoRank.FIFTH));
    }
}
