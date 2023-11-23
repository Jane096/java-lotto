package step4.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {

    private static final List<Integer> allLottoNumbers = IntStream.range(1, 46).boxed().collect(Collectors.toList());

    public static List<Integer> getShufflingNumbers() {
        Collections.shuffle(allLottoNumbers);
        return allLottoNumbers.subList(0, 6);
    }
}
