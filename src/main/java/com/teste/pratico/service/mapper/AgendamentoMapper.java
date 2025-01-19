package com.teste.pratico.service.mapper;

import com.teste.pratico.domain.Agendamento;
import com.teste.pratico.domain.Solicitante;
import com.teste.pratico.service.dto.AgendamentoDTO;
import com.teste.pratico.service.dto.SolicitanteDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link Agendamento} and its DTO {@link AgendamentoDTO}.
 */
@Mapper(componentModel = "spring", uses = {SolicitanteMapper.class})
public interface AgendamentoMapper extends EntityMapper<AgendamentoDTO, Agendamento> {
    @Mapping(target = "solicitante", source = "solicitanteId", qualifiedByName = "fromSolicitanteId")
    Agendamento toEntity(AgendamentoDTO agendamentoDTO);

    @Mapping(target = "solicitanteId", source = "solicitante.id")
    @Mapping(target = "solicitante", source = "solicitante")
    AgendamentoDTO toDto(Agendamento agendamento);

    @Named("fromSolicitanteId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    Solicitante fromSolicitanteId(Long id);
}
