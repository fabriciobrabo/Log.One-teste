package com.teste.pratico.service.mapper;

import com.teste.pratico.domain.Solicitante;
import com.teste.pratico.service.dto.SolicitanteDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Solicitante} and its DTO {@link SolicitanteDTO}.
 */
@Mapper(componentModel = "spring")
public interface SolicitanteMapper extends EntityMapper<SolicitanteDTO, Solicitante> {

}
