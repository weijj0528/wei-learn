package com.weiun.spring.ioc;

import com.weiun.spring.ioc.bean.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIocTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");

        User user = context.getBean(User.class);
        System.out.println(user.getName());
    }

}
