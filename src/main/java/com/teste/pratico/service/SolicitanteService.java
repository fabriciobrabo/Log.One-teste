package com.teste.pratico.service;

import com.teste.generico.bean.RestServiceRepository;
import com.teste.pratico.service.dto.SolicitanteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SolicitanteService extends RestServiceRepository<SolicitanteDTO, Long> {

    /**
     * Save a solicitante.
     *
     * @param solicitanteDTO the entity to save.
     * @return the persisted entity.
     */
    SolicitanteDTO save(SolicitanteDTO solicitanteDTO);

    /**
     * Updates a solicitante.
     *
     * @param solicitanteDTO the entity to update.
     * @return the persisted entity.
     */
    SolicitanteDTO update(SolicitanteDTO solicitanteDTO);

    /**
     * Partially updates a solicitante.
     *
     * @param solicitanteDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SolicitanteDTO> partialUpdate(SolicitanteDTO solicitanteDTO);

    /**
     * Get all the solicitanteDTOs.
     *
     * @return the list of entities.
     */

    Optional<List<SolicitanteDTO>> findAll();

    /**
     * Get all the solicitantes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SolicitanteDTO> findAll(Pageable pageable);

    /**
     * Get the "id" solicitante.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SolicitanteDTO> findOne(Long id);

    /**
     * Delete the "id" solicitante.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Delete the solicitante.
     *
     * @param solicitanteDTO the DTO of the entity.
     */
    void deleteEntity(SolicitanteDTO solicitanteDTO);

    /**
     * Abaixo métodos implementados declarados em interface que não são genéricos
     */
    List<SolicitanteDTO> autocomplete(String termo);
}
