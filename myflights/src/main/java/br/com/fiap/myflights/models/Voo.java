package br.com.fiap.myflights.models;

import java.time.LocalDate;

public class Voo {
    private int numVoo;
    private LocalDate horario;
    private String destino;
    private int portao;
    
    public Voo(int numVoo, LocalDate horario, String destino, int portao) {
        this.numVoo = numVoo;
        this.horario = horario;
        this.destino = destino;
        this.portao = portao;
    }

    public Voo(int numVoo, LocalDate horario, String destino) {
        this.numVoo = numVoo;
        this.horario = horario;
        this.destino = destino;
    }

    public Voo() {
    }

    public int getNumVoo() {
        return numVoo;
    }

    public void setNumVoo(int numVoo) {
        this.numVoo = numVoo;
    }

    public LocalDate getHorario() {
        return horario;
    }

    public void setHorario(LocalDate horario) {
        this.horario = horario;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getPortao() {
        return portao;
    }

    public void setPortao(int portao) {
        this.portao = portao;
    }

    @Override
    public String toString() {
        return "Voo [numVoo=" + numVoo + ", horario=" + horario + ", destino=" + destino + ", portao=" + portao + "]";
    }

}
