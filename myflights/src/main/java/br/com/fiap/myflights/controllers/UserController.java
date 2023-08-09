package br.com.fiap.myflights.controllers;

import br.com.fiap.myflights.models.Credencial;
import br.com.fiap.myflights.models.Token;
import br.com.fiap.myflights.repository.UserRepository;
import br.com.fiap.myflights.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.myflights.models.User;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/myflights/api/user")
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository repository;
    // private List<User> users = new ArrayList<>();
    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenService tokenService;

    @GetMapping
    @Operation(
            summary = "Listar usuários",
            description = "Retorna todos os usuários cadastrados."
    )
    public List<User> showAll() {
        return repository.findAll();
    }

    @PostMapping("/cadastrar")
    @Operation(
            summary = "Cadastrar usuário",
            description = "Cadastra um novo usuário"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Campos inválidos")
        }
    )
    public ResponseEntity<User> cadastrar(@RequestBody @Valid @ParameterObject User user){
        user.setSenha(encoder.encode(user.getSenha()));
        repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    @Operation(
            summary = "Login",
            description = "Efetua o login de um usuário cadastrado e retorna um token para usar nas outras funções."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário logado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Campos inválidos")
        }
    )
    public ResponseEntity<Token> login(@RequestBody @ParameterObject Credencial credencial){
        manager.authenticate(credencial.toAuthentication());
        var token = tokenService.generateToken(credencial);
        return ResponseEntity.ok(token);
    }
    
}
