package com.weiun.springboot.base.controller;

import com.weiun.springboot.base.bean.Response;
import org.springframework.http.MediaType;
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

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response jsonResponse() {
        return new Response();
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Response xmlResponse() {
        return new Response();
    }

}
