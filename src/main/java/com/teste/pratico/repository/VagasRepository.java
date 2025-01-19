package com.teste.pratico.repository;

import com.teste.pratico.domain.Vagas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@SuppressWarnings("unused")
@Repository
public interface VagasRepository extends JpaRepository<Vagas, Long> {

    @Query("select v from Vagas v " +
            "where (v.inicio between :dataInicio and :dataFim) " +
            "or (v.fim between :dataInicio and :dataFim)")
    List<Vagas> findByInicioOrFimBetween(
            @Param("dataInicio") ZonedDateTime dataInicio,
            @Param("dataFim") ZonedDateTime dataFim);

    @Query("select v from Vagas v " +
            "where :data between v.inicio and v.fim")
    List<Vagas> findByDataBetweenInicioAndFim(@Param("data") ZonedDateTime data);
}
