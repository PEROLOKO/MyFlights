package br.com.fiap.myflights.repository;

import br.com.fiap.myflights.models.User;
import br.com.fiap.myflights.models.Voo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<Object> findByEmail(String username);
}
