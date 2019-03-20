package com.weiun.reflect;

import java.lang.reflect.Field;

/**
 * @author William
 * @Date 2019/3/19
 * @Description https://mp.weixin.qq.com/s?src=11&timestamp=1553052851&ver=1495&signature=EE1DKExrbNksPgto-khqr*bmUCZKGV-Tcrvoy93fX5arcWPaPavYwLBnc6RfDa750FRrCLdWlebktbpwht0tr4IIDPz1siM3OoxRGVv5-r5kavxY193YJ31*6-GP4GDh&new=1
 */
public class ReflectFieldTest {


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // 获取Public属性,能获取到父类的属性
        Field[] fields = User.class.getFields();
        printField(fields);
        System.out.println("-----------------------------");
        // 获取所有属性，不能获取父类的属性
        Field[] declaredFields = User.class.getDeclaredFields();
        printField(declaredFields);
        // 值设置
        System.out.println("-----------------------------");
        User user = new User();
        Field a = User.class.getField("a");
        a.set(user, "你好");
        System.out.println(User.getA());
    }

    private static void printField(Field[] declaredFields) {
        for (Field field : declaredFields) {
            Class<?> type = field.getType();
            System.out.println(field.getName() + " type " + type.getName());
        }
    }

    public static class User extends People {

        public static String a;

        private static int b;

        public String c;

        private boolean d;

        private int[] e = null;

        private boolean[] f = null;

        public static String getA() {
            return a;
        }

        public static void setA(String a) {
            User.a = a;
        }
    }

    public static class People {

        public static String a1;

        private static int b1;

        public String c1;

        private boolean d1;

        private int[] e1 = null;

        private boolean[] f1 = null;
    }
}
