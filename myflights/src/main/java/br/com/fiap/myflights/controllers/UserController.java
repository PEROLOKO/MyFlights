package br.com.fiap.myflights.controllers;

import br.com.fiap.myflights.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.myflights.models.User;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository repository;
    // private List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> showAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        log.info("cadastrar user: " + user);
        repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    
}
