package br.com.aop.controller;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(path = "/greetings", produces = TEXT_PLAIN_VALUE)
    public String greetings() {
        return "Hello!";
    }

}
