package com.example.backend.repository;

import com.example.backend.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
  boolean existsByEmail(String email);
  boolean existsByTelefone(String email);
}
