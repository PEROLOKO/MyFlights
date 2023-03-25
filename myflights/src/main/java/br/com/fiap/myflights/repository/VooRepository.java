package br.com.fiap.myflights.repository;

import br.com.fiap.myflights.models.Voo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VooRepository extends JpaRepository<Voo, Long>{
}
