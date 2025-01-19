package com.teste.pratico.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "VAGAS", schema = "PUBLIC")
public class Vagas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "INICIO")
    private ZonedDateTime inicio;

    @Column(name = "FIM")
    private ZonedDateTime fim;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getInicio() {
        return inicio;
    }

    public void setInicio(ZonedDateTime inicio) {
        this.inicio = inicio;
    }

    public ZonedDateTime getFim() {
        return fim;
    }

    public void setFim(ZonedDateTime fim) {
        this.fim = fim;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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
                '}';
    }
}
