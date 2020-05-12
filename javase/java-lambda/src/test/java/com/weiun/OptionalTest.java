package com.weiun;

import org.junit.Test;

import java.util.Optional;

public class OptionalTest {

    @Test
    public void test() {
        User value = new User("william", 18);
        User defaultUser = new User("default", 0);
        // 默认值
        User user1 = Optional.ofNullable(value).orElse(defaultUser);
        value = null;
        User user2 = Optional.ofNullable(value).orElse(defaultUser);
        // 不存在创建
        User user3 = Optional.ofNullable(value).orElseGet(() -> new User("boy", 12));
        value = new User("william", 18);
        // 过滤检查
        boolean user4 = Optional.ofNullable(value).filter(user -> user.getAge() < 10).isPresent();
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);
        // 存在操作
        Optional.ofNullable(value).filter(user -> user.getAge() > 10).ifPresent(System.out::println);
        // 类型转换
        Integer integer = Optional.ofNullable(value).map(User::getAge).orElse(0);
        System.out.println(integer);
    }

}
