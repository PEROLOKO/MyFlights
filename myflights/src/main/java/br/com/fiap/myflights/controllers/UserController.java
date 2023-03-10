package br.com.fiap.myflights.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.myflights.models.User;


@RestController
public class UserController {

    User user = new User("kinoshitahenry@gmail.com", "password");
    
    @GetMapping("/api/user")
    public User show() {
        return user;
    }
    
}
