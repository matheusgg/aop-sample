package br.com.aop.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aop.model.User;
import br.com.aop.service.UserService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/greetings", produces = TEXT_PLAIN_VALUE)
    public String greetings(final boolean raiseError) {
        if (raiseError) {
            throw new RuntimeException("Some exception!");
        }
        return "Hello!";
    }

    @GetMapping(path = "/user", produces = APPLICATION_JSON_VALUE)
    public User user() {
        return this.userService.createUser();
    }
}
