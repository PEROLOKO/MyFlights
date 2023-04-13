package br.com.fiap.myflights.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "T_MF_USERVOO")
public class UserVoo {

    @ManyToOne
    private User user;

    @ManyToOne
    private Voo voo;

}
