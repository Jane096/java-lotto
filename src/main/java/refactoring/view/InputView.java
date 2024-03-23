package refactoring.view;

import refactoring.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public List<Lotto> manualLottoNumberInput(Quantity quantity) {
        scanner.nextLine();
        System.out.println(MANUAL_LOTTO_NUMBERS);

        List<String> numbers = new ArrayList<>();

        IntStream.range(0, quantity.getQuantity())
                .forEach(e -> numbers.add(scanner.nextLine()));

        return new ManualLotto(numbers).make();
    }

    private Lotto generateWinnerLotto(String value){
        return WinLotto.make(value);
    }
}
