package com.teste.pratico.web.bean;

import com.teste.pratico.service.SolicitanteService;
import com.teste.pratico.service.dto.SolicitanteDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class AutocompleteBean {

    @Autowired
    protected SolicitanteService solicitanteService;

    @PostConstruct
    public void init() {
    }

    public List<SolicitanteDTO> autocompleteSolicitante(String busca){
        return solicitanteService.autocomplete(busca);
    }
}
