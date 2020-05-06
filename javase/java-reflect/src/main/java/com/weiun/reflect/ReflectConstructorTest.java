package com.weiun.reflect;

import com.weiun.reflect.bean.User;

import java.lang.reflect.Constructor;

/**
 * @author William
 * @Date 2019/3/19
 * @Description 构造方法操作
 */
public class ReflectConstructorTest {


    public static void main(String[] args) {
        // 获取Public构造方法
        Constructor<?>[] constructors = User.class.getConstructors();
        printConstructor(constructors);
        System.out.println("------------------------------");
        Constructor<?>[] declaredConstructors = User.class.getDeclaredConstructors();
        printConstructor(declaredConstructors);
    }

    private static void printConstructor(Constructor[] constructors) {
        for (int i = 0; i < constructors.length; i++) {
            printConstructor(constructors[i]);
        }
    }

    private static void printConstructor(Constructor constructor) {
        System.out.println(constructor.toString());
    }


}
