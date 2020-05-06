package com.weiun.round;

import java.math.BigDecimal;

/**
 * @author William
 * @Date 2019/4/23
 * @Description 4舍5入
 */
public class RoundTest {

    public static void main(String[] args) {
        // Math 相关方法进行4舍5入
        // Math.ceil 相上取整
        System.out.println("----------Math.ceil----------");
        System.out.println(Math.ceil(5.4));
        System.out.println(Math.ceil(5.5));
        System.out.println(Math.ceil(5.6));
        System.out.println(Math.ceil(-5.4));
        System.out.println(Math.ceil(-5.5));
        System.out.println(Math.ceil(-5.6));
        // Math.round 标准4舍5入
        System.out.println("----------Math.round----------");
        System.out.println(Math.round(5.4));
        System.out.println(Math.round(5.5));
        System.out.println(Math.round(5.6));
        System.out.println(Math.round(-5.4));
        System.out.println(Math.round(-5.5));
        System.out.println(Math.round(-5.6));
        // Math.floor 相下取整
        System.out.println("----------Math.floor----------");
        System.out.println(Math.floor(5.4));
        System.out.println(Math.floor(5.5));
        System.out.println(Math.floor(5.6));
        System.out.println(Math.floor(-5.4));
        System.out.println(Math.floor(-5.5));
        System.out.println(Math.floor(-5.6));
        // BigDecimal 相关方法进行4舍5入
        // BigDecimal.ROUND_UP
        System.out.println("----------BigDecimal.ROUND_UP----------");
        System.out.println(new BigDecimal(5.4).setScale(0, BigDecimal.ROUND_UP));
        System.out.println(new BigDecimal(5.5).setScale(0, BigDecimal.ROUND_UP));
        System.out.println(new BigDecimal(5.6).setScale(0, BigDecimal.ROUND_UP));
        System.out.println(new BigDecimal(-5.4).setScale(0, BigDecimal.ROUND_UP));
        System.out.println(new BigDecimal(-5.5).setScale(0, BigDecimal.ROUND_UP));
        System.out.println(new BigDecimal(-5.6).setScale(0, BigDecimal.ROUND_UP));
        // BigDecimal.ROUND_UP
        System.out.println("----------BigDecimal.ROUND_DOWN----------");
        System.out.println(new BigDecimal(5.4).setScale(0, BigDecimal.ROUND_DOWN));
        System.out.println(new BigDecimal(5.5).setScale(0, BigDecimal.ROUND_DOWN));
        System.out.println(new BigDecimal(5.6).setScale(0, BigDecimal.ROUND_DOWN));
        System.out.println(new BigDecimal(-5.4).setScale(0, BigDecimal.ROUND_DOWN));
        System.out.println(new BigDecimal(-5.5).setScale(0, BigDecimal.ROUND_DOWN));
        System.out.println(new BigDecimal(-5.6).setScale(0, BigDecimal.ROUND_DOWN));
        System.out.println("----------BigDecimal.ROUND_CEILING----------");
        System.out.println(new BigDecimal(5.4).setScale(0, BigDecimal.ROUND_CEILING));
        System.out.println(new BigDecimal(5.5).setScale(0, BigDecimal.ROUND_CEILING));
        System.out.println(new BigDecimal(5.6).setScale(0, BigDecimal.ROUND_CEILING));
        System.out.println(new BigDecimal(-5.4).setScale(0, BigDecimal.ROUND_CEILING));
        System.out.println(new BigDecimal(-5.5).setScale(0, BigDecimal.ROUND_CEILING));
        System.out.println(new BigDecimal(-5.6).setScale(0, BigDecimal.ROUND_CEILING));
        System.out.println("----------BigDecimal.ROUND_FLOOR----------");
        System.out.println(new BigDecimal(5.4).setScale(0, BigDecimal.ROUND_FLOOR));
        System.out.println(new BigDecimal(5.5).setScale(0, BigDecimal.ROUND_FLOOR));
        System.out.println(new BigDecimal(5.6).setScale(0, BigDecimal.ROUND_FLOOR));
        System.out.println(new BigDecimal(-5.4).setScale(0, BigDecimal.ROUND_FLOOR));
        System.out.println(new BigDecimal(-5.5).setScale(0, BigDecimal.ROUND_FLOOR));
        System.out.println(new BigDecimal(-5.6).setScale(0, BigDecimal.ROUND_FLOOR));
        System.out.println("----------BigDecimal.ROUND_HALF_UP----------");
        System.out.println(new BigDecimal(5.4).setScale(0, BigDecimal.ROUND_HALF_UP));
        System.out.println(new BigDecimal(5.5).setScale(0, BigDecimal.ROUND_HALF_UP));
        System.out.println(new BigDecimal(5.6).setScale(0, BigDecimal.ROUND_HALF_UP));
        System.out.println(new BigDecimal(-5.4).setScale(0, BigDecimal.ROUND_HALF_UP));
        System.out.println(new BigDecimal(-5.5).setScale(0, BigDecimal.ROUND_HALF_UP));
        System.out.println(new BigDecimal(-5.6).setScale(0, BigDecimal.ROUND_HALF_UP));
        System.out.println("----------BigDecimal.ROUND_HALF_DOWN----------");
        System.out.println(new BigDecimal(5.4).setScale(0, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(new BigDecimal(5.5).setScale(0, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(new BigDecimal(5.6).setScale(0, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(new BigDecimal(-5.4).setScale(0, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(new BigDecimal(-5.5).setScale(0, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(new BigDecimal(-5.6).setScale(0, BigDecimal.ROUND_HALF_DOWN));
        System.out.println("----------BigDecimal.ROUND_HALF_EVEN 银行家舍入法----------");
        System.out.println(new BigDecimal(5.4).setScale(0, BigDecimal.ROUND_HALF_EVEN));
        System.out.println(new BigDecimal(5.5).setScale(0, BigDecimal.ROUND_HALF_EVEN));
        System.out.println(new BigDecimal(5.6).setScale(0, BigDecimal.ROUND_HALF_EVEN));
        System.out.println(new BigDecimal(6.4).setScale(0, BigDecimal.ROUND_HALF_EVEN));
        System.out.println(new BigDecimal(6.5).setScale(0, BigDecimal.ROUND_HALF_EVEN));
        System.out.println(new BigDecimal(6.6).setScale(0, BigDecimal.ROUND_HALF_EVEN));
        System.out.println(new BigDecimal(-5.4).setScale(0, BigDecimal.ROUND_HALF_EVEN));
        System.out.println(new BigDecimal(-5.5).setScale(0, BigDecimal.ROUND_HALF_EVEN));
        System.out.println(new BigDecimal(-5.6).setScale(0, BigDecimal.ROUND_HALF_EVEN));
        System.out.println(new BigDecimal(-6.4).setScale(0, BigDecimal.ROUND_HALF_EVEN));
        System.out.println(new BigDecimal(-6.5).setScale(0, BigDecimal.ROUND_HALF_EVEN));
        System.out.println(new BigDecimal(-6.6).setScale(0, BigDecimal.ROUND_HALF_EVEN));

    }

}
