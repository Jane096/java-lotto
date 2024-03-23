package refactoring.controller;

import refactoring.model.*;
import refactoring.view.InputView;
import refactoring.view.OutputView;

import java.util.List;

public class LottoRefactoringController {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        Price payPrice = inputView.payPriceInput();

        Quantity manualQuantity = inputView.manualLottoQuantityInput();
        List<Lotto> manualLottos = inputView.manualLottoNumberInput(manualQuantity);

        Quantity autoQuantity = Quantity.of(payPrice);
        outputView.viewLottoCount(autoQuantity, manualQuantity);

        List<Lotto> autoLottos = AutoLotto.make(autoQuantity.getQuantity());

        Lottos allLottos = Lottos.sumAllLottos(autoLottos, manualLottos);
        outputView.viewLotto(allLottos);

        WinLotto winLotto = inputView.putLastWinNumbers();
        BonusNumber bonus = new BonusNumber(inputView.bonusNumberInput());
        LottoRanks lottoRanks = LottoRanks.finalRanks(winLotto, bonus, allLottos);
        outputView.viewLottoRating(lottoRanks.getRank());

        outputView.viewRating(new Rating(lottoRanks.getRank()).getRating(payPrice));
    }
}
