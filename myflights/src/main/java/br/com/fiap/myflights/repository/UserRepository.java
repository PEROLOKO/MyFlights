package br.com.fiap.myflights.repository;

import br.com.fiap.myflights.models.User;
import br.com.fiap.myflights.models.Voo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}
