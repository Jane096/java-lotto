package refactor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import refactoring.enumeration.LottoRank;
import refactoring.model.TotalPrice;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class TotalPriceTest {

    static Stream<Arguments> generateData() {
        Map<LottoRank, Integer> finalRanksFirst = LottoRank.initialize();
        finalRanksFirst.put(LottoRank.SECOND, 1);
        finalRanksFirst.put(LottoRank.FOURTH, 2);
        finalRanksFirst.put(LottoRank.FIFTH, 1);

        Map<LottoRank, Integer> finalRanksSecond = LottoRank.initialize();
        finalRanksSecond.put(LottoRank.SECOND, 2);
        finalRanksSecond.put(LottoRank.FIFTH, 3);

        Map<LottoRank, Integer> finalRanksThird = LottoRank.initialize();
        finalRanksThird.put(LottoRank.FIRST, 1);

        return Stream.of(
                Arguments.of(finalRanksFirst, 30_055_000),
                Arguments.of(finalRanksSecond, 30_005_000),
                Arguments.of(finalRanksThird, 2_000_000_000));
    }

    @ParameterizedTest
    @MethodSource("generateData")
    @DisplayName("등급별 결과에 따라 총 수익금액을 합산하여 리턴한다.")
    void ofTotalPriceTest(Map<LottoRank, Integer> finalRanks, int expectedTotalPrice) {
        TotalPrice total = TotalPrice.of(finalRanks);
        assertThat(total.getTotal()).isEqualTo(expectedTotalPrice);
    }
}
