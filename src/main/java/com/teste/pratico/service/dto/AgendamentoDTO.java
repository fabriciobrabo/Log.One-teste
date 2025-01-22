package com.teste.pratico.service.dto;

import com.teste.pratico.domain.enumerations.TipoVeiculo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class AgendamentoDTO implements Serializable {

    private Long id;

    private LocalDateTime data;

    private String numero;

    private String motivo;

    private SolicitanteDTO solicitante;

    private TipoVeiculo tipoVeiculo;

    private Long solicitanteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public SolicitanteDTO getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(SolicitanteDTO solicitante) {
        this.solicitante = solicitante;
    }

    public Long getSolicitanteId() {
        return solicitanteId;
    }

    public void setSolicitanteId(Long solicitanteId) {
        this.solicitanteId = solicitanteId;
    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AgendamentoDTO)) {
            return false;
        }

        AgendamentoDTO agendamentoDTO = (AgendamentoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, agendamentoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "AgendamentoDTO{" +
                "id=" + id +
                ", data=" + data +
                ", numero='" + numero + '\'' +
                ", motivo='" + motivo + '\'' +
                ", solicitante=" + solicitante +
                ", tipoVeiculo=" + tipoVeiculo +
                ", solicitanteId=" + solicitanteId +
                '}';
    }
}
