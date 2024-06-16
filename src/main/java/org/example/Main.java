package org.example;

import org.example.util.IntroUtil;

public class Main {
    public static void main(String[] args) throws Exception {
        Class.forName("org.example.model.Db");
        IntroUtil.selectMenu();
    }
}