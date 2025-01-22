package com.teste.pratico.service;

import com.teste.generico.bean.RestServiceRepository;
import com.teste.pratico.service.dto.AgendamentoDTO;
import com.teste.pratico.service.dto.wrapper.AgendamentoFiltroWrapper;
import com.teste.pratico.service.dto.wrapper.ConsultaAgendamentoSolicitanteWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoService extends RestServiceRepository<AgendamentoDTO, Long> {

    /**
     * Save a agendamento.
     *
     * @param agendamentoDTO the entity to save.
     * @return the persisted entity.
     */
    AgendamentoDTO save(AgendamentoDTO agendamentoDTO);

    /**
     * Updates a agendamento.
     *
     * @param agendamentoDTO the entity to update.
     * @return the persisted entity.
     */
    AgendamentoDTO update(AgendamentoDTO agendamentoDTO);

    /**
     * Partially updates a agendamento.
     *
     * @param agendamentoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AgendamentoDTO> partialUpdate(AgendamentoDTO agendamentoDTO);

    /**
     * Get all the agendamentoDTOs.
     *
     * @return the list of entities.
     */
    Optional<List<AgendamentoDTO>> findAll();

    /**
     * Get all the agendamentos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AgendamentoDTO> findAll(Pageable pageable);

    /**
     * Get the "id" agendamento.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AgendamentoDTO> findOne(Long id);

    /**
     * Delete the "id" agendamento.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Delete the agendamento.
     *
     * @param agendamentoDTO the DTO of the entity.
     */
    void deleteEntity(AgendamentoDTO agendamentoDTO);

    /**
     * Abaixo métodos implementados declarados em interface que não são genéricos
     */

    List<AgendamentoDTO> findAllWithEagerRelationships();

    List<AgendamentoDTO> findByDateBetweenAndSolicitanteId(AgendamentoFiltroWrapper filtro);

    String validarCadastroAgendamento(AgendamentoDTO agendamentoDTO);

    List<ConsultaAgendamentoSolicitanteWrapper> consultarAgendamentosSolicitantes(AgendamentoFiltroWrapper filtro);
}
