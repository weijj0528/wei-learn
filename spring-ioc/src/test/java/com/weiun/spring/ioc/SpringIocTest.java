package com.weiun.spring.ioc;

import com.weiun.spring.ioc.bean.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIocTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");

        User user = context.getBean(User.class);
        System.out.println(user.getName());
    }

}
