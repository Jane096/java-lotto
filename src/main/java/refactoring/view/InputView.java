package refactoring.view;

import refactoring.model.Lottos;
import refactoring.model.Lotto;
import refactoring.model.Price;
import refactoring.model.Quantity;
import refactoring.strategy.ManualLottoGenerationStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String PUT_PAY_PRICE = "구입금액을 입력해 주세요.";
    private static final String PUT_LAST_WIN_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String PUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";


    public Price payPriceInput() {
        System.out.println(PUT_PAY_PRICE);
        return Price.of(scanner.nextInt());
    }

    public Lotto putLastWinNumbers() {
        System.out.println(PUT_LAST_WIN_NUMBERS);
        return generateWinnerLotto(scanner.nextLine());
    }

    public int bonusNumberInput() {
        System.out.println(PUT_BONUS_NUMBER);
        return scanner.nextInt();
    }

    public static List<Integer> split(String value){
        return Arrays.stream(value.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }


    public Quantity manualLottoQuantityInput() {
        scanner.nextLine();
        System.out.println(MANUAL_LOTTO_COUNT);
        int quantity = scanner.nextInt();
        return Quantity.of(quantity);
    }

    public Lottos manualLottoNumberInput(Quantity quantity) {
        scanner.nextLine();
        System.out.println(MANUAL_LOTTO_NUMBERS);

        return Lottos.of(new ManualLottoGenerationStrategy(split(scanner.nextLine())), quantity.getQuantity());
    }

    private Lotto generateWinnerLotto(String value){
        return new Lotto(new ManualLottoGenerationStrategy(split(value)).generate());
    }
}
