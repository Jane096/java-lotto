package refactoring.model;

import refactoring.enumeration.LottoRank;

import java.util.Map;

public class Rating {

    private final Map<LottoRank, Integer> winnerBoard;

    public Rating(Map<LottoRank, Integer> winnerBoard) {
        this.winnerBoard = winnerBoard;
    }

    public Double getRating(Price price) {
        TotalPrice totalPrice = TotalPrice.of(this.winnerBoard);
        return Math.floor(totalPrice.getTotal() * 100.0 / price.getPrice()) / 100;
    }
}
