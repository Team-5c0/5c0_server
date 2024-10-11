package com.example.wllserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MainController {

    @GetMapping("/main")
    public String main() {
        return "Hello World!";
    }

}
