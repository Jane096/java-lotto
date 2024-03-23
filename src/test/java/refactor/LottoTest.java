package refactor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import refactoring.enumeration.LottoRank;
import refactoring.model.BonusNumber;
import refactoring.model.Lotto;
import refactoring.model.LottoRanks;
import refactoring.model.WinLotto;
import refactoring.strategy.ManualLottoGenerationStrategy;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static refactoring.constants.SplitStringUtils.split;
import static refactoring.enumeration.LottoRank.*;

public class LottoTest {

    static Stream<Arguments> generateData() {
        return Stream.of(
            Arguments.of(new WinLotto(new Lotto(new ManualLottoGenerationStrategy(split("1,2,3,4,5,6")).generate())), new Lotto(new ManualLottoGenerationStrategy(split("1,2,3,11,45,23")).generate()), new BonusNumber(24), FIFTH),
            Arguments.of(new WinLotto(new Lotto(new ManualLottoGenerationStrategy(split("34,35,36,7,23,1")).generate())), new Lotto(new ManualLottoGenerationStrategy(split("34,35,36,7,24,9")).generate()), new BonusNumber(30), FOURTH),
            Arguments.of(new WinLotto(new Lotto(new ManualLottoGenerationStrategy(split("22,11,24,43,17,3")).generate())), new Lotto(new ManualLottoGenerationStrategy(split("22,11,24,43,17,8")).generate()), new BonusNumber(13), THIRD),
            Arguments.of(new WinLotto(new Lotto(new ManualLottoGenerationStrategy(split("1,9,29,33,42,25")).generate())), new Lotto(new ManualLottoGenerationStrategy(split("1,9,29,33,42,17")).generate()), new BonusNumber(17), SECOND)
        );
    }

    @ParameterizedTest
    @MethodSource("generateData")
    @DisplayName("생성된 로또와 당첨번호가 서로 일치하는지 확인하고 등급을 리턴한다.")
    void lottoFindRankTest(WinLotto winLotto, Lotto lotto, BonusNumber bonus, LottoRank expectedRank) {
        LottoRank lottoRank = LottoRanks.findRank(winLotto, bonus, lotto.getLottoNumbers());
        assertThat(lottoRank).isEqualTo(expectedRank);
    }
}
