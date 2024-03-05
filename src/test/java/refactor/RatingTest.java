package refactor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import refactoring.enumeration.LottoRank;
import refactoring.model.Price;
import refactoring.model.Rating;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class RatingTest {

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
                Arguments.of(finalRanksFirst, 4000, 7513.75),
                Arguments.of(finalRanksSecond, 5000, 6001.0),
                Arguments.of(finalRanksThird, 1000, 2000000.0));
    }

    @ParameterizedTest
    @MethodSource("generateData")
    @DisplayName("등급별 결과에 따른 가격을 계산하여 총 수익률을 구한다.")
    void getRatingTest(Map<LottoRank, Integer> finalRanks, int payPrice, Double expectedRating) {
        Double rating = new Rating(Price.of(payPrice)).getRating(finalRanks);
        assertThat(rating).isEqualTo(expectedRating);
    }
}
