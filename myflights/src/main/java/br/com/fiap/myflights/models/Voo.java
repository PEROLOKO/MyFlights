package br.com.fiap.myflights.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity(name = "T_MF_VOO")
public class Voo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Integer numVoo;
    private LocalDateTime horario;
    @NotBlank(message = "Destino é obrigatório")
    @Size(min = 5, max = 255)
    private String destino;
    private Integer portao;
    
    public Voo(int numVoo, LocalDateTime horario, String destino, int portao) {
        this.numVoo = numVoo;
        this.horario = horario;
        this.destino = destino;
        this.portao = portao;
    }

    public Voo(int numVoo, LocalDateTime horario, String destino) {
        this.numVoo = numVoo;
        this.horario = horario;
        this.destino = destino;
    }

    public Voo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumVoo() {
        return numVoo;
    }

    public void setNumVoo(int numVoo) {
        this.numVoo = numVoo;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
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
