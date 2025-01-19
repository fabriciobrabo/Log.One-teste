package com.teste.pratico.service.impl;

import com.teste.pratico.domain.Solicitante;
import com.teste.pratico.repository.SolicitanteRepository;
import com.teste.pratico.service.SolicitanteService;
import com.teste.pratico.service.dto.SolicitanteDTO;
import com.teste.pratico.service.mapper.SolicitanteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SolicitanteServiceImpl implements SolicitanteService {

    private static final Logger LOG = LoggerFactory.getLogger(SolicitanteServiceImpl.class);

    private final SolicitanteRepository solicitanteRepository;

    private final SolicitanteMapper solicitanteMapper;

    public SolicitanteServiceImpl(SolicitanteRepository solicitanteRepository, SolicitanteMapper solicitanteMapper) {
        this.solicitanteRepository = solicitanteRepository;
        this.solicitanteMapper = solicitanteMapper;
    }

    @Override
    @Transactional
    public SolicitanteDTO save(SolicitanteDTO solicitanteDTO) {
        LOG.debug("Request to save Solicitante : {}", solicitanteDTO);
        Solicitante solicitante = solicitanteMapper.toEntity(solicitanteDTO);
        solicitante = solicitanteRepository.save(solicitante);
        return solicitanteMapper.toDto(solicitante);
    }

    @Override
    @Transactional
    public SolicitanteDTO update(SolicitanteDTO solicitanteDTO) {
        LOG.debug("Request to update Solicitante : {}", solicitanteDTO);
        Solicitante solicitante = solicitanteMapper.toEntity(solicitanteDTO);
        solicitante = solicitanteRepository.save(solicitante);
        return solicitanteMapper.toDto(solicitante);
    }

    @Override
    @Transactional
    public Optional<SolicitanteDTO> partialUpdate(SolicitanteDTO solicitanteDTO) {
        LOG.debug("Request to partially update Solicitante : {}", solicitanteDTO);

        return solicitanteRepository
                .findById(solicitanteDTO.getId())
                .map(existingSolicitante -> {
                    solicitanteMapper.partialUpdate(existingSolicitante, solicitanteDTO);

                    return existingSolicitante;
                })
                .map(solicitanteRepository::save)
                .map(solicitanteMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<SolicitanteDTO>> findAll() {
        LOG.debug("Request to get all Solicitantes");
        return Optional.of(solicitanteRepository.findAll()).map(solicitanteMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SolicitanteDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Solicitantes Pageable");
        return solicitanteRepository.findAll(pageable).map(solicitanteMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SolicitanteDTO> findOne(Long id) {
        LOG.debug("Request to get Solicitante : {}", id);
        return solicitanteRepository.findById(id).map(solicitanteMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Solicitante : {}", id);
        solicitanteRepository.deleteById(id);
    }

    @Override
    public void deleteEntity(SolicitanteDTO solicitanteDTO) {
        LOG.debug("Request to delete Solicitante : {}");
        solicitanteRepository.delete(solicitanteMapper.toEntity(solicitanteDTO));
    }

    /**
     * métodos não genéricos abaixo
     */

    @Override
    public List<SolicitanteDTO> autocomplete(String termo) {
        if (termo != null && termo.trim().length() > 0) {
            String busca = termo.trim();
            Optional<List<Solicitante>> result = solicitanteRepository.findByNomeContainingIgnoreCase(busca);
            if (result.isPresent()) {
                return solicitanteMapper.toDto(result.get());
            } else {
                return new ArrayList<>();
            }
        } else {
            return solicitanteMapper.toDto(solicitanteRepository.findAll());
        }
    }
}
