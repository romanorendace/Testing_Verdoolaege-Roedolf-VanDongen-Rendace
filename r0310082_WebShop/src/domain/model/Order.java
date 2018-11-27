package domain.model;

public class Order {

    private String productId;
    private Product product;
    private int amount;

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
}
