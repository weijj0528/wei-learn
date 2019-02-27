package com.weiun.springboot.base.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author William
 * @Date 2019/2/27
 * @Description Rest风格
 */
@RestController
@RequestMapping("/rest")
public class RestDemoController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello world!";
    }

}
