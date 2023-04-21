package br.com.fiap.myflights.config;

import br.com.fiap.myflights.models.User;
import br.com.fiap.myflights.models.Voo;
import br.com.fiap.myflights.repository.UserRepository;
import br.com.fiap.myflights.repository.VooRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VooRepository vooRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = User.builder().name("João").email("joao@email.com").senha("joao1212").build();
        User u2 = User.builder().name("Maria").email("maria@email.com").senha("mariasenha").build();
        User u3 = User.builder().name("Paulo").email("paulo@email.com").senha("paulopaulo").build();

        userRepository.saveAll(List.of(u1,u2,u3));

        vooRepository.saveAll(List.of(
                Voo.builder().numVoo(1234).portao(5).destino("Londres").horario(LocalDateTime.now()).users(List.of(u1,u2)).build(),
                Voo.builder().numVoo(2345).portao(12).destino("Rio").horario(LocalDateTime.now()).users(List.of(u1)).build(),
                Voo.builder().numVoo(3456).portao(3).destino("Nova Iorque").horario(LocalDateTime.now()).users(List.of(u3)).build(),
                Voo.builder().numVoo(8477).portao(1).destino("Paris").horario(LocalDateTime.now()).users(List.of(u3,u2)).build(),
                Voo.builder().numVoo(1212).portao(11).destino("Berlim").horario(LocalDateTime.now()).users(List.of(u2)).build(),
                Voo.builder().numVoo(7633).portao(6).destino("Tóquio").horario(LocalDateTime.now()).users(List.of(u1,u3)).build(),
                Voo.builder().numVoo(1654).portao(8).destino("Stockholm").horario(LocalDateTime.now()).users(List.of(u1,u2,u3)).build()
        ));

    }

}
