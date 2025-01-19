package com.teste.pratico.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SOLICITANTE", schema = "PUBLIC")
public class Solicitante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
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
        if (!(o instanceof Solicitante)) {
            return false;
        }
        return getId() != null && getId().equals(((Solicitante) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Solicitante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
