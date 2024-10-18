package com.example.wllserver.domain.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("permitAll")
    public String permitAllApi() {
        return "permitAllApi";
    }

    @GetMapping("authenticated")
    public String authenticatedApi() {
        return "authenticatedApi";
    }

    @GetMapping("admin")
    public String adminApi() {
        return "adminApi";
    }
}
