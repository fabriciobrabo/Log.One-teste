package com.teste.pratico.service.impl;

import com.teste.pratico.domain.Agendamento;
import com.teste.pratico.repository.AgendamentoRepository;
import com.teste.pratico.service.AgendamentoService;
import com.teste.pratico.service.VagasService;
import com.teste.pratico.service.dto.AgendamentoDTO;
import com.teste.pratico.service.dto.VagasDTO;
import com.teste.pratico.service.dto.wrapper.AgendamentoFiltroWrapper;
import com.teste.pratico.service.dto.wrapper.ConsultaAgendamentoSolicitanteWrapper;
import com.teste.pratico.service.mapper.AgendamentoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link com.teste.pratico.domain.Agendamento}.
 */
@Service
@Transactional
public class AgendamentoServiceImpl implements AgendamentoService {

    private static final Logger LOG = LoggerFactory.getLogger(AgendamentoServiceImpl.class);

    @Autowired
    private VagasService vagasService;

    private final AgendamentoRepository agendamentoRepository;

    private final AgendamentoMapper agendamentoMapper;

    public AgendamentoServiceImpl(AgendamentoRepository agendamentoRepository, AgendamentoMapper agendamentoMapper) {
        this.agendamentoRepository = agendamentoRepository;
        this.agendamentoMapper = agendamentoMapper;
    }

    @Override
    public AgendamentoDTO save(AgendamentoDTO agendamentoDTO) {
        LOG.debug("Request to save Agendamento : {}", agendamentoDTO);
        Agendamento agendamento = agendamentoMapper.toEntity(agendamentoDTO);
        agendamento = agendamentoRepository.save(agendamento);
        return agendamentoMapper.toDto(agendamento);
    }

    @Override
    public AgendamentoDTO update(AgendamentoDTO agendamentoDTO) {
        LOG.debug("Request to update Agendamento : {}", agendamentoDTO);
        Agendamento agendamento = agendamentoMapper.toEntity(agendamentoDTO);
        agendamento = agendamentoRepository.save(agendamento);
        return agendamentoMapper.toDto(agendamento);
    }

    @Override
    public Optional<AgendamentoDTO> partialUpdate(AgendamentoDTO agendamentoDTO) {
        LOG.debug("Request to partially update Agendamento : {}", agendamentoDTO);

        return agendamentoRepository
            .findById(agendamentoDTO.getId())
            .map(existingAgendamento -> {
                agendamentoMapper.partialUpdate(existingAgendamento, agendamentoDTO);

                return existingAgendamento;
            })
            .map(agendamentoRepository::save)
            .map(agendamentoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AgendamentoDTO>> findAll() {
        LOG.debug("Request to get all Agendamentos");
        return Optional.of(agendamentoRepository.findAll()).map(agendamentoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AgendamentoDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Agendamentos");
        return agendamentoRepository.findAll(pageable).map(agendamentoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AgendamentoDTO> findOne(Long id) {
        LOG.debug("Request to get Agendamento : {}", id);
        return agendamentoRepository.findById(id).map(agendamentoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Agendamento : {}", id);
        agendamentoRepository.deleteById(id);
    }

    @Override
    public void deleteEntity(AgendamentoDTO agendamentoDTO) {
        LOG.debug("Request to delete Agendamento : {}");
        agendamentoRepository.delete(agendamentoMapper.toEntity(agendamentoDTO));
    }

    /**
     * métodos não genéricos abaixo
     */

    @Override
    public List<AgendamentoDTO> findAllWithEagerRelationships() {
        List<Agendamento> agendamentolist = agendamentoRepository.findAllWithEagerRelationships();
        return agendamentolist.stream().map(agendamentoMapper::toDto).toList();
    }

    @Override
    public List<AgendamentoDTO> findByDateBetweenAndSolicitanteId(AgendamentoFiltroWrapper filtro) {
        List<Agendamento> agendamentolist = agendamentoRepository.findByDateBetweenAndSolicitanteId(filtro.getDataInicio(), filtro.getDataFim(), filtro.getSolicitanteId());
        return agendamentolist.stream().map(agendamentoMapper::toDto).toList();
    }

    @Override
    public List<ConsultaAgendamentoSolicitanteWrapper> consultarAgendamentosSolicitantes(AgendamentoFiltroWrapper filtro){
        List<ConsultaAgendamentoSolicitanteWrapper> agendamentosTratados = new ArrayList<>();
        List<AgendamentoDTO> agendamentosBrutos = this.findByDateBetweenAndSolicitanteId(filtro);
        List<VagasDTO> vagasDoIntervalo = vagasService.findByInicioOrFimBetween(filtro.getDataInicio(), filtro.getDataFim());

        List<AgendamentoDTO> agendamentosFiltrados = agendamentosBrutos.stream()
                .filter(agendamento -> vagasDoIntervalo.stream()
                        .anyMatch(vaga -> !agendamento.getData().isBefore(vaga.getInicio()) && !agendamento.getData().isAfter(vaga.getFim())))
                .collect(Collectors.toList());

        Map<String, List<AgendamentoDTO>> agendamentosPorSolicitante = agendamentosFiltrados.stream()
                .collect(Collectors.groupingBy(agendamento -> agendamento.getSolicitante().getNome()));

        for (Map.Entry<String, List<AgendamentoDTO>> solicitateAgendamentosMap : agendamentosPorSolicitante.entrySet()) {
            ConsultaAgendamentoSolicitanteWrapper wrapper = new ConsultaAgendamentoSolicitanteWrapper();
            wrapper.setSolicitanteNome(solicitateAgendamentosMap.getKey());
            wrapper.setTotalAgendamentos(solicitateAgendamentosMap.getValue().size());
            int quantidadeVagas = vagasDoIntervalo.stream().filter(vaga ->
                    !solicitateAgendamentosMap.getValue().get(0).getData().isBefore(vaga.getInicio()) &&
                            !solicitateAgendamentosMap.getValue().get(0).getData().isAfter(vaga.getFim())).findFirst().get().getQuantidade();
            wrapper.setQuantidadeVagas(quantidadeVagas);
            double percentual = ((double) wrapper.getTotalAgendamentos() / quantidadeVagas) * 100;
            wrapper.setPercentual(percentual);
            agendamentosTratados.add(wrapper);
        }

        return agendamentosTratados;
    }

    /**
     * Método otimizado para a validação, 3 etapas, verifica vagas, verificar total vagas está dentro da regra e
     * depois regra dos 25% com tipo de veiculo
     * @param agendamentoDTO
     * @return
     */
    public String validarCadastroAgendamento(AgendamentoDTO agendamentoDTO) {
        List<VagasDTO> vagas = vagasService.findVagasByDataAndTipo(agendamentoDTO.getData(), agendamentoDTO.getTipoVeiculo());

        if (vagas == null || vagas.isEmpty()) {
            return "Não foram encontradas vagas para o agendamento";
        }

        Set<AgendamentoDTO> agendamentos = vagas.stream()
                .flatMap(vaga -> agendamentoRepository.findByDataBetweenAndSolicitanteIdAndTipo(vaga.getInicio(), vaga.getFim(), null, vaga.getTipoVeiculo()).stream())
                .map(agendamentoMapper::toDto)
                .collect(Collectors.toSet());

        Map<Long, Integer> vagasAgendamentoCount = new HashMap<>();
        for (AgendamentoDTO agendamento : agendamentos) {
            for (VagasDTO vaga : vagas) {
                if (!agendamento.getData().isBefore(vaga.getInicio()) && !agendamento.getData().isAfter(vaga.getFim())) {
                    vagasAgendamentoCount.merge(vaga.getId(), 1, Integer::sum);
                    if (vagasAgendamentoCount.get(vaga.getId()) >= vaga.getQuantidade()) {
                        return "Os agendamentos estão esgotados para o número de vagas.";
                    }
                }
            }
        }

        Map<Long, Map<Long, Integer>> vagasSolicitanteAgendamentoCount = new HashMap<>();
        for (AgendamentoDTO agendamento : agendamentos) {
            for (VagasDTO vaga : vagas) {
                if (!agendamento.getData().isBefore(vaga.getInicio()) && !agendamento.getData().isAfter(vaga.getFim()) &&
                        agendamento.getTipoVeiculo().equals(vaga.getTipoVeiculo())) {
                    vagasSolicitanteAgendamentoCount
                            .computeIfAbsent(vaga.getId(), k -> new HashMap<>())
                            .merge(agendamento.getSolicitanteId(), 1, Integer::sum);

                    int maxAgendamentos = (int) Math.floor(vaga.getQuantidade() * 0.25);
                    if (vagasSolicitanteAgendamentoCount.get(vaga.getId()).get(agendamento.getSolicitanteId()) >= maxAgendamentos) {
                        if (agendamentoDTO.getSolicitanteId().equals(agendamento.getSolicitanteId())) {
                            return "O número de agendamentos por solicitante não pode ultrapassar 25% da quantidade total de vagas para o tipo: " + vaga.getTipoVeiculo().name() + ".";
                        }
                    }
                }
            }
        }
        return null;
    }
}
