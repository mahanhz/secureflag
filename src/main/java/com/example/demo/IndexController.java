package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

@RestController
public class IndexController {

    @GetMapping("/index")
    public String index(final WebSession webSession) {
        webSession.getAttributes().put("SomeKey", "SomeValue");

        return "Started session";
    }
}
