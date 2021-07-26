package com.exclouds;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicInitController {

    @GetMapping("/health-check")
    public String index() {
        return "success";
    }

    @GetMapping("/heartbeat")
    public String heartbeat() {
        return "OK";
    }
}
