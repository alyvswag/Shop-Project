package org.example.util;

import org.example.enums.FileAddress;
import org.example.file.FileUtil;
import org.example.model.Product;
import org.example.model.ProductSale;

import static org.example.model.Db.*;
import static org.example.model.Product.idHesablayici;
import static org.example.util.ProductUtil.addProduct;
import static org.example.util.ProductUtil.number;

public class DbUtil  {
    public static void addDbAndCloneMethod() throws Exception{
        Product p = addProduct();
        if(p==null){
            return;
        }
        productList.add(p);
        for(int i=0;i<number-1;i++){
            productList.add((Product)p.clone());
        }
        productNumber.put(p,number);
        FileUtil.writeFileObject(FileAddress.PRODUCT_ADDRESS,productList,idHesablayici);
        FileUtil.writeFileObject(FileAddress.PRODUCT_NUMBER_ADDRESS,productNumber);
        System.out.println("Məhsul uğurla əlavə edildi.");

    }
    public static void addProductSale(ProductSale ps) throws Exception{
        productSales.add(ps);
        FileUtil.writeFileObject(FileAddress.TRANSACTION_ADDRESS,productSales);
        System.out.println("Məhsulun satışı tamamlandı.");
    }


}
