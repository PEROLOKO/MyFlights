package br.com.fiap.myflights.controllers;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.myflights.models.Voo;


@RestController
public class VooController {

    Voo voo = new Voo(1567, LocalDate.now(), "São Paulo", 12);
    
    @GetMapping("/api/voo")
    public Voo show() {
        return voo;
    }
    
}
