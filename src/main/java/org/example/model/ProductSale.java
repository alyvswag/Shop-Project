package org.example.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ProductSale implements Serializable {
    private static final long serialVersionUID = 1L;
    private String customerInfo;
    private String customerFin;
    private LocalDate saleDate;
    private Product soldProduct;

    public ProductSale(String customerInfo,String customerFin ,LocalDate saleDate, Product soldProduct) {
        this.customerInfo = customerInfo;
        this.customerFin = customerFin;
        this.saleDate = saleDate;
        this.soldProduct = soldProduct;
    }
}
