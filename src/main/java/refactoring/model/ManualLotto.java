package refactoring.model;

import refactoring.strategy.ManualLottoGenerationStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ManualLotto {

    private final List<String> numbers;

    public ManualLotto(List<String> numbers) {
        this.numbers = numbers;
    }

    public List<Lotto> make() {
        List<Lotto> lottos = new ArrayList<>();
        this.numbers
                .forEach(number -> {
                    List<Integer> splitedNumbers = split(number);
                    lottos.add(new Lotto(new ManualLottoGenerationStrategy(splitedNumbers).generate()));
                });

        return lottos;
    }

    private static List<Integer> split(String value){
        return Arrays.stream(value.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
