package step4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step4.model.LottoNumber;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumberTest {

    @Test
    @DisplayName("1부터 45의 숫자 중 랜덤한 6자리 숫자를 생선한다.")
    void getNumberTest() {
        assertThat(LottoNumber.getShufflingNumbers().size()).isEqualTo(6);
    }
}
