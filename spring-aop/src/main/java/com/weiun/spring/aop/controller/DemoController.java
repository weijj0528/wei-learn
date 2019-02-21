package com.weiun.spring.aop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author William
 * @Date 2019/2/20
 * @Description
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @ResponseBody
    @RequestMapping("/test")
    public Map<String, String> test() {
        System.out.println("test");
        Map<String, String> map = new HashMap<>();
        map.put("name", "test");
        return map;
    }

}
