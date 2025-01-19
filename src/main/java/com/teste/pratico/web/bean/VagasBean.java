package com.teste.pratico.web.bean;

import com.teste.pratico.service.VagasService;
import com.teste.pratico.service.dto.VagasDTO;
import com.teste.pratico.web.util.JSFUtil;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@Named
@ViewScoped
public class VagasBean extends EntidadeDescritivaBean<VagasDTO, VagasService>{

    @PostConstruct
    public void init() {
        super.setEntidades(service.findAll().orElse(new ArrayList<>()));
    }

    @Override
    @Transactional
    public Boolean cadastrar() {
        try {
            if (!validarDatas()){
                return Boolean.FALSE;
            }
            service.save(entidade);
            this.criarNovaInstancia();
            JSFUtil.adicionarMsgGlobalCadastroSucesso();
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            JSFUtil.adicionarMsgGlobalOperacaoErro();
            return Boolean.FALSE;
        }
    }

    private boolean validarDatas() {
        ZonedDateTime dataInicio = entidade.getInicio();
        ZonedDateTime dataFim = entidade.getFim();
        ZonedDateTime dataAtual = ZonedDateTime.now();

        if (dataInicio.isBefore(dataAtual)) {
            JSFUtil.setErro("A data de início não pode ser menor que a data atual.");
            return false;
        }

        if (dataFim.isBefore(dataInicio)) {
            JSFUtil.setErro("A data fim não pode ser menor que a data de início.");
            return false;
        }
        return true;
    }
}
