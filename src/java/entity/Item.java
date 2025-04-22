package entity;

public class Item {
    private int id;
    private Product product;
    private int quantity;
    private double price;

    public Item() {
    }


    public int getId() {
        return product.getId();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", product=" + product + ", quantity=" + quantity + ", price=" + price + '}';
    }
    
    
    
}
