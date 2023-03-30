package br.com.fiap.myflights.exceptions;

public record RestError (
        int cod,
        String message
) {}