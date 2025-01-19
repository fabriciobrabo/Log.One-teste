package com.teste.pratico.web.bean;

import com.teste.pratico.service.SolicitanteService;
import com.teste.pratico.service.dto.SolicitanteDTO;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;

@Named
@ViewScoped
public class SolicitanteBean extends EntidadeDescritivaBean<SolicitanteDTO, SolicitanteService>{

    @PostConstruct
    public void init() {
        super.setEntidades(service.findAll().orElse(new ArrayList<>()));
    }


}
