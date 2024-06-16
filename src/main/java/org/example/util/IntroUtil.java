package org.example.util;

import org.example.Menu;

import java.sql.SQLOutput;
import java.util.Scanner;

import static org.example.util.DbUtil.*;
import static org.example.util.ProductSaleUtil.saleProduct;
import static org.example.util.ProductSaleUtil.transactionProductSale;
import static org.example.util.ProductUtil.*;

public class IntroUtil {
    public static void selectMenu() throws Exception{
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(Menu.generalMenu);
            System.out.print("Emeliyyati secin: ");
            int option = scanner.nextInt();
            switch (option) {
                case 0:
                    System.exit(0);
                case 1:
                    stockMenu();
                    break;
                case 2:
                    saleMenu();
                    break;
                default:
                    System.out.println("Qardas duzgun secim edin!");
            }
        }
    }

    public static void stockMenu() throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(Menu.stockMenu);
            System.out.print("Secim edin: ");
            int option = scanner.nextInt();
            switch (option) {
                case 0:
                    return;
                case 1:
                    addDbAndCloneMethod();
                    break;
                case 2:
                    addAvailableProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    printProducts();
                    break;
                default:
                    System.out.println("Qardas duzgun secim edin!");
            }
        }
    }

    public static void saleMenu() throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(Menu.saleMenu);
            System.out.print("Secim edin: ");
            int option = scanner.nextInt();
            switch (option) {
                case 0:
                    return;
                case 1:
                    saleProduct();
                    break;
                case 2:
                    transactionProductSale();
                    break;
                default:
                    System.out.println("Qardas duzgun secim edin!");
            }
        }
    }


}
