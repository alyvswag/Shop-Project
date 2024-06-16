package org.example.enums;

public enum FileAddress {
    PRODUCT_ADDRESS("C:\\Users\\Talib\\Documents\\ShopProject\\product.txt"),
    PRODUCT_NUMBER_ADDRESS("C:\\Users\\Talib\\Documents\\ShopProject\\productNumber.txt"),
    TRANSACTION_ADDRESS("C:\\Users\\Talib\\Documents\\ShopProject\\transaction.txt");

    private final String address;

    FileAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

}
