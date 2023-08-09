package br.com.fiap.myflights.models;

public record Token(
        String token,
        String type,
        String prefix
) {
}
