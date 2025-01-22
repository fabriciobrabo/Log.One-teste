package com.teste.pratico.domain;


import com.teste.pratico.domain.enumerations.TipoVeiculo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "VAGAS", schema = "PUBLIC")
public class Vagas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "INICIO")
    private LocalDateTime inicio;

    @Column(name = "FIM")
    private LocalDateTime fim;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    @Column(name = "TIPO_VEICULO")
    @Enumerated(EnumType.STRING)
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

    public void setTipoVeiculo(TipoVeiculo tipo) {
        this.tipoVeiculo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vagas)) {
            return false;
        }
        return getId() != null && getId().equals(((Vagas) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Vagas{" +
                "id=" + id +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", quantidade=" + quantidade +
                ", tipoVeiculo='" + tipoVeiculo + '\'' +
                '}';
    }
}
