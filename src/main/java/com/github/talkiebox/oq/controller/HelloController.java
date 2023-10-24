package com.github.talkiebox.oq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping(path = "/hello-world")
    public String hello() {
        return "Hello World";
    }
}
