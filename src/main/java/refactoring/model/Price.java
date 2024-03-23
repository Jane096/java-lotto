package refactoring.model;

public final class Price {

    private final int price;

    private Price(int price) {
        this.price = price;
    }

    public static Price of(int price) {
        return new Price(price);
    }

    public int getPrice() {
        return this.price;
    }
}
