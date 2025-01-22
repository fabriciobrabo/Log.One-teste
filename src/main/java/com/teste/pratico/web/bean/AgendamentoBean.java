package com.teste.pratico.web.bean;

import com.teste.pratico.domain.enumerations.TipoVeiculo;
import com.teste.pratico.service.AgendamentoService;
import com.teste.pratico.service.dto.AgendamentoDTO;
import com.teste.pratico.service.dto.wrapper.AgendamentoFiltroWrapper;
import com.teste.pratico.service.dto.wrapper.ConsultaAgendamentoSolicitanteWrapper;
import com.teste.pratico.web.util.JSFUtil;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class AgendamentoBean extends EntidadeDescritivaBean<AgendamentoDTO, AgendamentoService>{

    private AgendamentoFiltroWrapper filtroWrapper;

    private List<ConsultaAgendamentoSolicitanteWrapper> consultaEntidades;

    @PostConstruct
    public void init() {
        this.filtroWrapper = new AgendamentoFiltroWrapper();
        this.consultaEntidades = new ArrayList<>();
        super.setEntidades(service.findAllWithEagerRelationships());
    }

    public void pesquisar() {
        super.setEntidades(service.findByDateBetweenAndSolicitanteId(filtroWrapper));
        JSFUtil.adicionarMsgGlobalOperacaoSucesso();
    }

    public void consultarAgendamentosSolicitantes() {
        this.setConsultaEntidades(service.consultarAgendamentosSolicitantes(filtroWrapper));
        JSFUtil.adicionarMsgGlobalOperacaoSucesso();
    }

    @Override
    @Transactional
    public Boolean cadastrar() {
        try {
            String validacaoResposta = service.validarCadastroAgendamento(entidade);
            if (validacaoResposta != null){
                JSFUtil.setErro(validacaoResposta);
                entidade.setSolicitanteId(null);
                return Boolean.FALSE;
            }
            service.save(entidade);
            this.criarNovaInstancia();
            JSFUtil.adicionarMsgGlobalCadastroSucesso();
            return Boolean.TRUE;
        } catch (Exception e) {
            JSFUtil.adicionarMsgGlobalOperacaoErro();
            return Boolean.FALSE;
        }
    }

    @Override
    public String cancelar(){
        filtroWrapper = new AgendamentoFiltroWrapper();
        return super.cancelar();
    }

    public TipoVeiculo[] tipoVeiculoEnums(){
        return TipoVeiculo.values();
    }

    public AgendamentoFiltroWrapper getFiltroWrapper() {
        return filtroWrapper;
    }

    public void setFiltroWrapper(AgendamentoFiltroWrapper filtroWrapper) {
        this.filtroWrapper = filtroWrapper;
    }

    public List<ConsultaAgendamentoSolicitanteWrapper> getConsultaEntidades() {
        return consultaEntidades;
    }

    public void setConsultaEntidades(List<ConsultaAgendamentoSolicitanteWrapper> consultaEntidades) {
        this.consultaEntidades = consultaEntidades;
    }
}
