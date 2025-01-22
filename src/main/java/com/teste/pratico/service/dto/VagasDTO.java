package com.teste.pratico.service.dto;

import com.teste.pratico.domain.enumerations.TipoVeiculo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class VagasDTO implements Serializable {

    private Long id;

    private LocalDateTime inicio;

    private LocalDateTime fim;

    private Integer quantidade;

    private TipoVeiculo tipoVeiculo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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
        if (!(o instanceof VagasDTO)) {
            return false;
        }

        VagasDTO vagasDTO = (VagasDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, vagasDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "VagasDTO{" +
                "id=" + id +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", quantidade=" + quantidade +
                ", tipoVeiculo=" + tipoVeiculo +
                '}';
    }
}
