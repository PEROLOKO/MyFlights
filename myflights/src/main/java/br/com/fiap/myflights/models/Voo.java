package br.com.fiap.myflights.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
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

    public Voo(int numVoo, LocalDateTime horario, String destino) {
        this.numVoo = numVoo;
        this.horario = horario;
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "Voo [numVoo=" + numVoo + ", horario=" + horario + ", destino=" + destino + ", portao=" + portao + "]";
    }

}
