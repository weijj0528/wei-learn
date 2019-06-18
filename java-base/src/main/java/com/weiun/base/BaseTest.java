package com.weiun.base;

public class BaseTest {


    public static void main(String[] args) {
        System.out.println(addAfter(1));
        System.out.println(addBefore(1));
    }

    private static int addAfter(int i) {
        return i++;
    }

    private static int addBefore(int i) {
        return ++i;
    }
}
