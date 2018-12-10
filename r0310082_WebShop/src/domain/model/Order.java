package domain.model;

import java.util.Objects;

public class Order {

    private String productId;
    private Product product;
    private int amount;
    private String date;

    public Order() {
    }

    public Order(String productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }

    public String getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getAmount() == order.getAmount() &&
                Objects.equals(getProductId(), order.getProductId()) &&
                Objects.equals(getProduct(), order.getProduct());
    }

}
