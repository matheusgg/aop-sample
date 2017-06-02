package br.com.aop.service;

import org.springframework.stereotype.Service;

import java.util.Random;

import br.com.aop.model.User;

@Service
public class UserService {

    private static final Random RANDOM = new Random();

    public User createUser() {
        final int id = RANDOM.nextInt(9999);
        return new User(id, "User " + id, null);
    }

}
