package com.teste.pratico.repository;

import com.teste.pratico.domain.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SuppressWarnings("unused")
@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query("select distinct agendamento from Agendamento agendamento left join fetch agendamento.solicitante")
    List<Agendamento> findAllWithEagerRelationships();

    @Query("select agendamento from Agendamento agendamento " +
            "where (:solicitanteId is null or agendamento.solicitante.id = :solicitanteId) " +
            "and agendamento.data between :dataInicio and :dataFim")
    List<Agendamento> findByDateBetweenAndSolicitanteId(
            @Param("dataInicio") ZonedDateTime dataInicio,
            @Param("dataFim") ZonedDateTime dataFim,
            @Param("solicitanteId") Long solicitanteId);
}
