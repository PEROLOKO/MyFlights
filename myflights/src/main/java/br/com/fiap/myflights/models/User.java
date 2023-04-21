package br.com.fiap.myflights.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "T_MF_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 5, max = 255)
    private String senha;

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private List<Voo> voos;

    public User(Long id, String name, String email, String senha) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.senha = senha;
    }
}
