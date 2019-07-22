package com.weiun.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author William
 * @Date 2019/3/19
 * @Description https://mp.weixin.qq.com/s?src=11&timestamp=1553052851&ver=1495&signature=EE1DKExrbNksPgto-khqr*bmUCZKGV-Tcrvoy93fX5arcWPaPavYwLBnc6RfDa750FRrCLdWlebktbpwht0tr4IIDPz1siM3OoxRGVv5-r5kavxY193YJ31*6-GP4GDh&new=1
 */
public class ReflectTest {


    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.weiun.reflect.bean.User");
        /**获取父类相关*/
        // 获取父类型(同接口)
        Class<?> superclass = clazz.getSuperclass();
        // 获取带参数的父类型(同接口)
        Type genericSuperclass = clazz.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        System.out.println(actualTypeArguments[0]);
        System.out.println(genericSuperclass);
    }


}
