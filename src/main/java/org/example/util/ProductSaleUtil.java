package org.example.util;

import org.example.enums.ProductStatus;
import org.example.enums.ProductsType;
import org.example.model.Product;
import org.example.model.ProductSale;
import org.example.util.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.example.model.Db.productList;
import static org.example.model.Db.productSales;
import static org.example.util.DbUtil.*;
import static org.example.util.ProductUtil.*;

public class ProductSaleUtil {

    public static void saleProduct() throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<Product> filteredProducts = searchProductType();
        if (filteredProducts.isEmpty()) {
            System.out.println("Məlumat tapılmadı.");
          return;
        } else {
            filteredProducts.forEach(System.out::println);
        }
        System.out.print("Satılan məhsulun id-sın daxil edin: ");
        int id = scanner.nextInt();
        AtomicReference<Product> soldProductLambda = new AtomicReference<>();
        boolean found = filteredProducts.stream()
                .filter(p -> p.getId() == id && p.getInActive() == ProductStatus.ACTIVE)
                .peek(p -> {
                    mapReduceNumber(p);
                    soldProductLambda.set(p);
                    p.setInActive(ProductStatus.SOLD);
                })
                .findAny()
                .isPresent();

        if (!found) {
            System.out.println("Məhsul tapılmadı.");
            return ;
        }
        System.out.print("Alıcının adın daxil edin: ");
        String customerInfo = scanner.next();
        System.out.print("Alıcının fin-in daxil edin: ");
        String customerFin = scanner.next();
        LocalDate saleDate = LocalDate.now();
        Product soldProduct = soldProductLambda.get();
        addProductSale(new ProductSale(customerInfo,customerFin,saleDate,soldProduct));
    }

    public static void transactionProductSale(){
        if(productSales.isEmpty()){
            System.out.println("Satış yoxdur.");
        }else{
            System.out.println(productSales);
        }
    }


}
