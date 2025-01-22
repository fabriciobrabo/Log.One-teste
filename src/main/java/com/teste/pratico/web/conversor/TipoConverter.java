package com.teste.pratico.web.conversor;

import com.teste.pratico.domain.enumerations.TipoVeiculo;

import javax.enterprise.context.RequestScoped;
import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;


@Named
@RequestScoped
@FacesConverter(forClass = TipoVeiculo.class,value = "tipoConverter")
public class TipoConverter extends EnumConverter {

    public TipoConverter() {
         super(TipoVeiculo.class);
    }

}
