package com.weiun.springtx.jdbc;

import com.weiun.springtx.jdbc.service.AcountService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AccountServiceJdbcTest {


    private ApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("applicationContext-jdbc.xml");
    }

    @Test
    public void transferSuccess() throws Exception {
        AcountService acountService = (AcountService) context.getBean("accountServiceJdbcImpl");
        acountService.transferSuccess(1, 0, 10);
    }

    @Test
    public void transferError() throws Exception {
        AcountService acountService = (AcountService) context.getBean("accountServiceJdbcImpl");
        acountService.transferError(1, 0, 10);
    }
}