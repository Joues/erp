package com.yihang.serp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/2/22 11:24
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String Hello() {
        return "Hello login man!";
    }
}
