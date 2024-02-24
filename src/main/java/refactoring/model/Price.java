package refactoring.model;

import refactoring.enumeration.LottoRank;

import java.util.Map;

public class Price {

    private final int price;

    private Price(int price) {
        this.price = price;
    }

    public static Price of(int price) {
        return new Price(price);
    }

    public static Price ofTotalPrice(Map<LottoRank, Integer> ranks) {
        int price = 0;
        for (Map.Entry<LottoRank, Integer> board : ranks.entrySet()) {
            if (board.getValue() > 0) price += LottoRank.getPriceByRank(board.getKey());
        }

        return new Price(price);
    }

    public int getPrice() {
        return this.price;
    }
}
