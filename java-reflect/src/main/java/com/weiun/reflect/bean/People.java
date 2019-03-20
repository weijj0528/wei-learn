package com.weiun.reflect.bean;

/**
 * @author William
 * @Date 2019/3/20
 * @Description 人
 */
public class People<T> implements Action {

    public People() {
    }

    public void say() {
        System.out.println("Hello world!");
    }

}
