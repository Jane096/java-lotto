package refactor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import refactoring.model.Lotto;
import refactoring.model.ManualLotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ManualLottoTest {

    static Stream<Arguments> generateData() {
        List<String> numbers = new ArrayList<>();
        numbers.add("34,35,1,2,3,7");
        numbers.add("12,35,41,22,43,6");
        numbers.add("1,24,11,25,31,17");

        return Stream.of(Arguments.of(numbers, 3));
    }

    @ParameterizedTest
    @MethodSource("generateData")
    @DisplayName("자동뽑기를 시행했을 때 quantity 만큼 생성한다.")
    void generateLottoTest(List<String> numbers, int expectedSize) {
        List<Lotto> lottos = new ManualLotto(numbers).make();
        assertThat(lottos.size()).isEqualTo(expectedSize);
    }
}
