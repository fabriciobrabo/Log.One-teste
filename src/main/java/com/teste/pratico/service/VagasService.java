package com.teste.pratico.service;

import com.teste.generico.bean.RestServiceRepository;
import com.teste.pratico.domain.enumerations.TipoVeiculo;
import com.teste.pratico.service.dto.VagasDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VagasService extends RestServiceRepository<VagasDTO, Long> {

    /**
     * Save a vagas.
     *
     * @param vagasDTO the entity to save.
     * @return the persisted entity.
     */
    VagasDTO save(VagasDTO vagasDTO);

    /**
     * Updates a vagas.
     *
     * @param vagasDTO the entity to update.
     * @return the persisted entity.
     */
    VagasDTO update(VagasDTO vagasDTO);

    /**
     * Partially updates a vagas.
     *
     * @param vagasDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<VagasDTO> partialUpdate(VagasDTO vagasDTO);

    /**
     * Get all the vagasDTOs.
     *
     * @return the list of entities.
     */
    Optional<List<VagasDTO>> findAll();

    /**
     * Get all the vagas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VagasDTO> findAll(Pageable pageable);

    /**
     * Get the "id" vagas.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VagasDTO> findOne(Long id);

    /**
     * Delete the "id" vagas.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Delete the vagas.
     *
     * @param vagasDTO the DTO of the entity.
     */
    void deleteEntity(VagasDTO vagasDTO);

    /**
     * Abaixo métodos implementados declarados em interface que não são genéricos
     */

    List<VagasDTO> findByInicioOrFimBetween(LocalDateTime periodoInicio, LocalDateTime periodoFim);

    List<VagasDTO> findVagasByDataAndTipo(LocalDateTime data, TipoVeiculo tipo);
}
