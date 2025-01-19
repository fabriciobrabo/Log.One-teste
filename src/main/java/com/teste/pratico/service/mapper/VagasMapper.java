package com.teste.pratico.service.mapper;

import com.teste.pratico.domain.Vagas;
import com.teste.pratico.service.dto.VagasDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Vagas} and its DTO {@link VagasDTO}.
 */
@Mapper(componentModel = "spring")
public interface VagasMapper extends EntityMapper<VagasDTO, Vagas> {}
