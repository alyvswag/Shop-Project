package org.example.util;

import org.example.enums.FileAddress;
import org.example.enums.ProductStatus;
import org.example.file.FileUtil;
import org.example.model.Product;
import org.example.enums.ProductsType;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.example.model.Db.productList;
import static org.example.model.Db.productNumber;
import static org.example.model.Product.idHesablayici;

public class ProductUtil {
    public static int number = 0;

    public static Product addProduct() { //case1
        Scanner scanner = new Scanner(System.in);
        ProductsType product;
        try {
            product = chooseProductType();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Xahiş olunur , düzgün daxil edin!");
            return null;
        }
        System.out.print("Məhsulun sayın daxil et: ");
        number = scanner.nextInt();
        scanner.nextLine();//problem is nextLine
        System.out.print("Məhsulun istehsalçısını ve adını daxil edin: ");
        String producer = scanner.nextLine();
        System.out.print("Məhsulun rəngin daxil et: ");
        String color = scanner.next();
        System.out.print("Məhsulun qiymətin daxil et: ");
        String price = scanner.next();
        return new Product(ProductStatus.ACTIVE, product, producer, color, price);
    }

    public static ProductsType chooseProductType() throws ArrayIndexOutOfBoundsException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1.Elektronik\n" +
                "2.Məişət\n" +
                "3.Mebel\n" +
                "Məhsulun tipin seç: ");
        int option = scanner.nextInt();
        return ProductsType.values()[--option];
    }

    public static void printProducts() {//case 4
        System.out.println("Bütün məhsullar: ");
        productNumber.forEach((product, count) ->
                System.out.println("Məhsulun adı: " + product.getName() + " -> Sayı: " + count)
        );
    }

    public static void addAvailableProduct() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println(productNumber);
        System.out.print("Əlavə etmək istədiyiniz məhsulun id-sin daxil edin: ");
        int id = scanner.nextInt();
        System.out.print("Məhsulun sayın daxil edin: ");
        int newNumber = scanner.nextInt();
        if(newNumber<=0){
            System.out.println("Sayı düzgün daxil edin!");
            return;
        }
        productNumber.keySet().stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .ifPresentOrElse(
                        product -> {
                            int oldNumber = productNumber.get(product);
                            productNumber.put(product, oldNumber + newNumber);
                            for (int i = 0; i < newNumber; i++) {
                                productList.add((Product) product.clone());
                            }
                            try {
                                FileUtil.writeFileObject(FileAddress.PRODUCT_ADDRESS,productList,idHesablayici);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                FileUtil.writeFileObject(FileAddress.PRODUCT_NUMBER_ADDRESS,productNumber);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("Məhsulun sayı yeniləndi.");
                        },
                        () -> System.out.println("Məhsul tapılmadı.")
                );
    }

    public static void deleteProduct() throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<Product> filteredProducts = searchProductType();
        if (filteredProducts.isEmpty()) {
            System.out.println("Məlumat tapılmadı.");
            return;
        } else {
            filteredProducts.forEach(System.out::println);
        }
        System.out.print("Silmək istədiyiniz məhsulun id sın daxil edin: ");
        int id = scanner.nextInt();
        boolean found = filteredProducts.stream()
                .filter(p -> p.getId() == id && p.getInActive() == ProductStatus.ACTIVE)
                .peek(p -> {
                    mapReduceNumber(p);
                    p.setInActive(ProductStatus.INACTIVE);
                    try {
                        FileUtil.writeFileObject(FileAddress.PRODUCT_ADDRESS,productList,idHesablayici);
                        FileUtil.writeFileObject(FileAddress.PRODUCT_NUMBER_ADDRESS,productNumber);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Mehsul ugurla silindi.");

                })
                .findAny()
                .isPresent();

        if (!found) {
            System.out.println("Məhsul tapılmadı.");
        }
    }

    public static void mapReduceNumber(Product p){
        productNumber.keySet().stream()
                .filter(product -> product.getName()==p.getName() &&
                        product.getColor()==p.getColor() && product.getPrice()==p.getPrice() &&
                        product.getProduct() ==p.getProduct() )
                .findFirst()
                .ifPresent(
                        product -> {
                            int oldNumber = productNumber.get(product);
                            productNumber.put(product, oldNumber-1);
                        }
                );
    }

    public static List<Product> searchProductType(){
        ProductsType typeEnum;
        try {
            typeEnum = chooseProductType();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Xahiş olunur , düzgün daxil edin!");
            return null;
        }
        List<Product> filteredProducts = productList.stream()
                .filter(product -> product.getProduct() == typeEnum && product.getInActive() == ProductStatus.ACTIVE)
                .collect(Collectors.toList());

       return filteredProducts;
    }



//    public static void addAvailableProduct() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println(productNumber);
//        System.out.print("Əlavə etmək istədiyiniz məhsulun id-sin daxil edin: ");
//        int id = scanner.nextInt();
//        System.out.print("Məhsulun sayın daxil edin: ");
//        int newNumber = scanner.nextInt();
//        for (Product product : productNumber.keySet()) {
//            int oldNumber = (int) productNumber.values().toArray()[0];
//            if (product.getId() == id) {
//                productNumber.put(product, oldNumber + newNumber);
//                System.out.println("Məhsulun sayı yeniləndi.");
//                for (int i = 0; i < newNumber; i++) {
//                    productList.add((Product) product.clone());
//                }
//                break;
//            }
//        }
//    }

//    public static void deleteProduct1() {
//        Scanner scanner = new Scanner(System.in);
//        Productss typeEnum;
//        try {
//            typeEnum = chooseProductType();
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("Xahiş olunur , düzgün daxil edin!");
//            return;
//        }
//        for (Product p : productList) {
//            if (p.getProduct() == typeEnum && p.getInActive() == ProductStatus.ACTIVE) {
//                System.out.println(p);
//            }
//        }
//        System.out.print("Silmek istediyiniz mehsulun id sin daxil edin: ");
//        int id = scanner.nextInt();
//        for (Product p : productList) {
//            if (p.getId() == id && p.getInActive() == ProductStatus.ACTIVE) {
//                p.setInActive(ProductStatus.INACTIVE);
//                System.out.println("Mehsul ugurla silindi.");
//            }
//        }
//    }
}
