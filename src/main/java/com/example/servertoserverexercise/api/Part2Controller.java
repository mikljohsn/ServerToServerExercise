package com.example.servertoserverexercise.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Part2Controller {
    @GetMapping("api/name-info")
    public String nameInfoEndpoint(){
        return "hej";
    }
}
