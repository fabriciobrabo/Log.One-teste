package com.teste.pratico.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class SolicitanteDTO implements Serializable {

    private Long id;

    private String nome;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SolicitanteDTO)) {
            return false;
        }

        SolicitanteDTO solicitanteDTO = (SolicitanteDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, solicitanteDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "SolicitanteDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
