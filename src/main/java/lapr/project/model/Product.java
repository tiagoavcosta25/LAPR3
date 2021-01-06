package lapr.project.model;

import lapr.project.data.ProductDB;

import java.util.Objects;

public class Product {
    private String productId;
    private String name;
    private String description;
    private Double price;

    public Product(String productId, String name, String description, Double price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    public static Product getProduct(String id) {
        return new ProductDB().getProduct(id);
    }

    public void save() {
        try {
            getProduct(this.getProductId());
            new ProductDB().updateProduct(this); //Verificar
        } catch (IllegalArgumentException ex) {
            //Of the record does not exist, save it
            new ProductDB().addProduct(this);
        }
    }

    public void delete(){
        new ProductDB().removeProduct(this.productId);
    }

}
