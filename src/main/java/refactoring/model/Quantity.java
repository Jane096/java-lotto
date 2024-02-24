package refactoring.model;

public class Quantity {

    private static final int EACH_LOTTO_PRICE = 1000;
    private final int quantity;

    private Quantity(int quantity) {
        this.quantity = quantity;
    }

    public static Quantity of(Price payPrice) {
        if (payPrice.getPrice() % EACH_LOTTO_PRICE != 0 || payPrice.getPrice() / EACH_LOTTO_PRICE < 0) {
            throw new IllegalArgumentException("금액을 잘못 입력 하였습니다. 로또 한장의 가격은 " + EACH_LOTTO_PRICE + "원 입니다.");
        }

        return new Quantity(payPrice.getPrice() / EACH_LOTTO_PRICE);
    }

    public static Quantity of(int quantity) {
        return new Quantity(quantity);
    }

    public int getQuantity() {
        return this.quantity;
    }
}
