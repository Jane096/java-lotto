package refactoring.model;

import refactoring.enumeration.LottoRank;

import java.util.Map;

public class Rating {

    private final Price price;

    public Rating(Price price) {
        this.price = price;
    }

    public Double getRating(Map<LottoRank, Integer> winnerBoard) {
        TotalPrice totalPrice = TotalPrice.of(winnerBoard);
        return Math.floor(totalPrice.getTotal() * 100.0 / this.price.getPrice()) / 100;
    }
}
