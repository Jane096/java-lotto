package refactoring.controller;

import refactoring.model.*;
import refactoring.strategy.AutoLottoGenerateStrategy;
import refactoring.view.InputView;
import refactoring.view.OutputView;

public class LottoRefactoringController {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        Price payPrice = inputView.payPriceInput();

        Quantity manualQuantity = inputView.manualLottoQuantityInput();
        Lottos manualLottos = inputView.manualLottoNumberInput(manualQuantity);

        Quantity autoQuantity = Quantity.of(payPrice);
        outputView.viewLottoCount(autoQuantity, manualQuantity);

        Lottos autoLottos = Lottos.of(new AutoLottoGenerateStrategy(), autoQuantity.getQuantity());

        Lottos allLottos = Lottos.getAll(autoLottos, manualLottos);
        outputView.viewLotto(allLottos);

        Lotto winLotto = inputView.putLastWinNumbers();
        BonusNumber bonus = new BonusNumber(inputView.bonusNumberInput());
        LottoRanks lottoRanks = LottoRanks.finalRanks(winLotto, bonus, allLottos);
        outputView.viewLottoRating(lottoRanks.getRank());

        outputView.viewRating(new Rating(payPrice).getRating(lottoRanks.getRank()));
    }
}
