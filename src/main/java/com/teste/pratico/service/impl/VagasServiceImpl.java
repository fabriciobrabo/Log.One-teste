package com.teste.pratico.service.impl;

import com.teste.pratico.domain.Agendamento;
import com.teste.pratico.domain.Vagas;
import com.teste.pratico.repository.VagasRepository;
import com.teste.pratico.service.VagasService;
import com.teste.pratico.service.dto.AgendamentoDTO;
import com.teste.pratico.service.dto.VagasDTO;
import com.teste.pratico.service.mapper.VagasMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VagasServiceImpl implements VagasService {

    private static final Logger LOG = LoggerFactory.getLogger(VagasServiceImpl.class);

    private final VagasRepository vagasRepository;

    private final VagasMapper vagasMapper;

    public VagasServiceImpl(VagasRepository vagasRepository, VagasMapper vagasMapper) {
        this.vagasRepository = vagasRepository;
        this.vagasMapper = vagasMapper;
    }

    @Override
    public VagasDTO save(VagasDTO vagasDTO) {
        LOG.debug("Request to save Vagas : {}", vagasDTO);
        Vagas vagas = vagasMapper.toEntity(vagasDTO);
        vagas = vagasRepository.save(vagas);
        return vagasMapper.toDto(vagas);
    }

    @Override
    public VagasDTO update(VagasDTO vagasDTO) {
        LOG.debug("Request to update Vagas : {}", vagasDTO);
        Vagas vagas = vagasMapper.toEntity(vagasDTO);
        vagas = vagasRepository.save(vagas);
        return vagasMapper.toDto(vagas);
    }

    @Override
    public Optional<VagasDTO> partialUpdate(VagasDTO vagasDTO) {
        LOG.debug("Request to partially update Vagas : {}", vagasDTO);

        return vagasRepository
                .findById(vagasDTO.getId())
                .map(existingVagas -> {
                    vagasMapper.partialUpdate(existingVagas, vagasDTO);

                    return existingVagas;
                })
                .map(vagasRepository::save)
                .map(vagasMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<VagasDTO>> findAll() {
        LOG.debug("Request to get all Vagas");
        return Optional.of(vagasRepository.findAll()).map(vagasMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VagasDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Vagas Pageable");
        return vagasRepository.findAll(pageable).map(vagasMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VagasDTO> findOne(Long id) {
        LOG.debug("Request to get Vagas : {}", id);
        return vagasRepository.findById(id).map(vagasMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Vagas : {}", id);
        vagasRepository.deleteById(id);
    }

    @Override
    public void deleteEntity(VagasDTO vagasDTO) {
        LOG.debug("Request to delete Agendamento : {}");
        vagasRepository.delete(vagasMapper.toEntity(vagasDTO));
    }

    /**
     * métodos não genéricos abaixo
     */

    @Override
    public List<VagasDTO> findByInicioOrFimBetween(ZonedDateTime periodoInicio, ZonedDateTime periodoFim) {
        List<Vagas> vagas = vagasRepository.findByInicioOrFimBetween(periodoInicio, periodoFim);
        return vagas.stream().map(vagasMapper::toDto).toList();
    }

    @Override
    public List<VagasDTO> findVagasByData(ZonedDateTime data) {
        List<Vagas> vagas = vagasRepository.findByDataBetweenInicioAndFim(data);
        return vagas.stream().map(vagasMapper::toDto).toList();
    }
}
