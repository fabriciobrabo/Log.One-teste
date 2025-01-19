package com.teste.pratico.repository;

import com.teste.pratico.domain.Solicitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Repository
public interface SolicitanteRepository extends JpaRepository<Solicitante, Long> {

    Optional<List<Solicitante>> findByNomeContainingIgnoreCase(String termo1);
}
