package refactoring.view;

import refactoring.model.Lottos;
import refactoring.enumeration.LottoRank;
import refactoring.model.Quantity;

import java.util.Map;

public class OutputView {

    public void viewLottoCount(Quantity autoQuantity, Quantity manualQuantity) {
        System.out.println("수동으로 " + manualQuantity.getQuantity() + ", 자동으로 " + autoQuantity.getQuantity() + "개를 구매했습니다.");
    }

    public void viewLotto(Lottos lottos) {
        lottos.getLottos().forEach(lotto -> System.out.println(lotto.toString()));
    }

    public void viewLottoRating(Map<LottoRank, Integer> rank) {
        System.out.println("당첨 통계\n" + "---------");

        for (Map.Entry<LottoRank, Integer> board : rank.entrySet()) {
            System.out.printf((board.getKey().getMessage()) + "%n", board.getValue());
        }
    }

    public void viewRating(Double rating) {
        System.out.println("총 수익률은 " + rating + "입니다.");
    }
}
