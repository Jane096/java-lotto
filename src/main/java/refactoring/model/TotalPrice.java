package refactoring.model;

import refactoring.enumeration.LottoRank;

import java.util.Map;

public class TotalPrice {

    private final int total;

    private TotalPrice(int total) {
        this.total = total;
    }

    public int getTotal() {
        return this.total;
    }

    public static TotalPrice of(Map<LottoRank, Integer> ranks) {
        int price = 0;
        for (Map.Entry<LottoRank, Integer> board : ranks.entrySet()) {
            if (board.getValue() > 0) price += LottoRank.getPriceByRank(board.getKey());
        }

        return new TotalPrice(price);
    }
}
