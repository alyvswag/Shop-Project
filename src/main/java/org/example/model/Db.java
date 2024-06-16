package org.example.model;

import org.example.enums.FileAddress;
import org.example.file.FileUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.file.FileUtil.readFileObject;
import static org.example.file.FileUtil.readFileObject2;
import static org.example.model.Product.idHesablayici;

public abstract class Db {
    public static List<Product> productList = new ArrayList<>();
    public static Map<Product, Integer> productNumber = new HashMap<>();
    public static List<ProductSale> productSales = new ArrayList<>();

    static {
        try {
            Integer id = (Integer) readFileObject(FileAddress.PRODUCT_ADDRESS)[1];
            idHesablayici = (id == null) ? 0 : (int) id;

            List<Product> p = (List<Product>) readFileObject(FileAddress.PRODUCT_ADDRESS)[0];
            productList = p == null ? productList : p;

            Map<Product, Integer> m = (Map<Product, Integer>) readFileObject2(FileAddress.PRODUCT_NUMBER_ADDRESS);
            productNumber = m == null ? productNumber : m;

            List<ProductSale> ps = (List<ProductSale>) readFileObject2(FileAddress.TRANSACTION_ADDRESS);
            productSales = ps == null ? productSales : ps;
        } catch (Exception e) {

        }


    }
}
