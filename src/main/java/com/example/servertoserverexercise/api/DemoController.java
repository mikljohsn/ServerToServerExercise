package com.example.servertoserverexercise.api;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private final int SLEEP_TIME = 1000*3; //sætter sleep time til 3 sek, for at illustrere at endpoints kan have svartid

    @GetMapping(value = "/random-string-slow")
    public String slowEndpoint() throws InterruptedException {
        Thread.sleep(SLEEP_TIME); //sætter 3 sekunder på tråden
        return RandomStringUtils.randomAlphanumeric(10);
    }

}
