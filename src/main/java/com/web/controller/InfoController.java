package com.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/info")
public class InfoController {

    @Value("${app.version}")
    private String appVersion;

    @GetMapping
    public Map<String, String> getInfo() {
        return Map.of("version", appVersion);
    }
}