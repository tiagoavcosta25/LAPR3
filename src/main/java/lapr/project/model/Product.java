package lapr.project.model;

import java.util.Objects;

public class Product {
    private String productId;
    private String name;
    private String description;
    private double unitaryPrice;
    private double unitaryWeight;

    public Product(String productId, String name, String description, double unitaryPrice, double unitaryWeight) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.unitaryPrice = unitaryPrice;
        this.unitaryWeight = unitaryWeight;
    }

    public Product(String name, String description, double unitaryPrice, double unitaryWeight) {
        this.name = name;
        this.description = description;
        this.unitaryPrice = unitaryPrice;
        this.unitaryWeight = unitaryWeight;
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

    public double getUnitaryPrice() {
        return unitaryPrice;
    }

    public double getUnitaryWeight() {
        return unitaryWeight;
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
}
