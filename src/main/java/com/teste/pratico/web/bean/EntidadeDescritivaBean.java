package com.teste.pratico.web.bean;

import com.teste.generico.bean.RestServiceRepository;
import com.teste.pratico.web.util.ContextoUtil;
import com.teste.pratico.web.util.JSFUtil;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


public abstract class EntidadeDescritivaBean<T, R extends RestServiceRepository> implements Serializable {

    public static final long serialVersionUID = 1L;

    @Autowired
    protected ContextoUtil contextoUtil;

    @Autowired
    protected R service;

    protected T entidade;
    protected T filtro;
    protected LazyDataModel<T> entidadesLazy;
    protected List<T> entidades;
    private Class<?> paramType;
    private String busca = "";

    protected Object id;
    protected String outcome;

    public String getBooleanAsString(Boolean boooool){
        if(boooool==null || boooool==false)
            return "NÃO";
        return "SIM" ;
    }

    public String getLocalDateAsString(LocalDate local) {
        if (local != null)
            return DateTimeFormatter.ofPattern("dd/MM/yy").format(local);
        else return "-";
    }

    public String getDataAsString(LocalDateTime local) {
        if (local != null)
            return DateTimeFormatter.ofPattern("dd/MM/yy HH:mm").format(local);
        else return "-";
    }

    @PostConstruct
    protected void init() {
        id = contextoUtil.popParamRequest("id", null);
        if (id != null) {
            if (id instanceof Long) {
                if (((Long) id) > 0) {
                    findOne((Long) id);
                }
            }
        }
    }

    public void findOne(Long id) {
        entidade = (T) service.findOne(id).get();
    }

    public EntidadeDescritivaBean() {
        this.paramType = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        try {
            criarNovaInstancia();
            inicializarFiltro();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public Optional<List<T>> listar() {
        return service.findAll();
    }

    @SuppressWarnings("unchecked")
    public void criarNovaInstancia() throws Exception {
        entidade = (T) this.paramType.newInstance();
    }

    @SuppressWarnings("unchecked")
    public void inicializarFiltro() throws Exception {
        filtro = (T) this.paramType.newInstance();
    }


    @Transactional
    public Boolean cadastrar() {
        try {
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

    @Transactional
    public String atualizar() {
        try {
            service.save(entidade);
            JSFUtil.adicionarMsgGlobalAlteracaoSucesso();
            this.criarNovaInstancia();
        } catch (Exception e) {
            JSFUtil.adicionarMsgGlobalOperacaoErro();
        }
        return null;
    }

    @Transactional
    public String excluir() {
        try {
            service.deleteEntity(entidade);
            JSFUtil.adicionarMsgGlobalExclusaoSucesso();
            this.criarNovaInstancia();
        } catch (Exception e) {
            JSFUtil.adicionarMsgGlobalOperacaoErro();
            e.printStackTrace();
        }
        return null;
    }

    public String cancelar() {
        try {
            this.criarNovaInstancia();
        } catch (Exception e) {
        }
        return null;
    }

    public T getEntidade() {
        return entidade;
    }

    public void setEntidade(T entidade) {
        this.entidade = entidade;
    }

    public List<T> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<T> entidades) {
        this.entidades = entidades;
    }

    public T getFiltro() {
        return filtro;
    }

    public void setFiltro(T filtro) {
        this.filtro = filtro;
    }

    public R getService() {
        return service;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

    public Double getRound(Double valor){
        if( valor==null){
            return Double.valueOf(0);
        }
        java.math.BigDecimal bd = java.math.BigDecimal.valueOf(valor);
        bd = bd.setScale(2, java.math.RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
