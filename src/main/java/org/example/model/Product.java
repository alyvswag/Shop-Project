package org.example.model;

import lombok.Data;
import org.example.enums.ProductStatus;
import org.example.enums.ProductsType;

import java.io.Serializable;

@Data
public class Product implements Prototype, Serializable {
    private static final long serialVersionUID = 2L;
    public static transient int idHesablayici = 0;
    private int id;
    private ProductStatus inActive;
    private ProductsType product;//bu enumdu
    private String name;
    private String color;
    private String price;

    public Product(Product product) {
        this.id = idHesablayici++;
        this.inActive = product.inActive;
        this.product = product.product;
        this.name = product.name;
        this.color = product.color;
        this.price = product.price;
    }

    public Product(ProductStatus inActive, ProductsType product, String name, String color, String price) {
        this.id = idHesablayici++;
        this.inActive = inActive;
        this.product = product;
        this.name = name;
        this.color = color;
        this.price = price;
    }

    @Override
    public Prototype clone() {
        return new Product(this);
    }

}
