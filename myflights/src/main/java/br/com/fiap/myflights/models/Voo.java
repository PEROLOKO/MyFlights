package br.com.fiap.myflights.models;

import br.com.fiap.myflights.controllers.UserController;
import br.com.fiap.myflights.controllers.VooController;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "T_MF_VOO")
public class Voo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Integer numVoo;
    private LocalDateTime horario;
    @NotBlank(message = "Destino é obrigatório")
    private String destino;
    private Integer portao;

    @ManyToMany
    @JoinTable(
            name="T_MF_VOO_USER",
            joinColumns = @JoinColumn(name = "voo_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private List<User> users;

    public Voo(Long id, int numVoo, LocalDateTime horario, String destino) {
        this.id = id;
        this.numVoo = numVoo;
        this.horario = horario;
        this.destino = destino;
    }

    public EntityModel<Voo> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(VooController.class).show(id)).withSelfRel(),
                linkTo(methodOn(VooController.class).delete(id)).withRel("delete"),
                linkTo(methodOn(VooController.class).index(null, Pageable.unpaged())).withRel("all")
        );
    }

}
