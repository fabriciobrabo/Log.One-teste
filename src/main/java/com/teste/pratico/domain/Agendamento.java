package com.teste.pratico.domain;

import com.teste.pratico.domain.enumerations.TipoVeiculo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "AGENDAMENTO", schema = "PUBLIC")
public class Agendamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATA")
    private LocalDateTime data;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "MOTIVO")
    private String motivo;

    @Column(name = "TIPO_VEICULO")
    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOLICITANTE_ID")
    private Solicitante solicitante;

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

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
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
        if (!(o instanceof Agendamento)) {
            return false;
        }
        return getId() != null && getId().equals(((Agendamento) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Agendamento{" +
                "id=" + id +
                ", data=" + data +
                ", numero='" + numero + '\'' +
                ", motivo='" + motivo + '\'' +
                ", tipoVeiculo='" + tipoVeiculo + '\'' +
                ", solicitante=" + solicitante +
                '}';
    }
}
