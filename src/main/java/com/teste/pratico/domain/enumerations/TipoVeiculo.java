package com.teste.pratico.domain.enumerations;

/**
 * The Tipo Veículo enumeration.
 */
public enum TipoVeiculo {
    LEVE("Veículo Leve"),
    PESADO("Veículo Pesado");

    private String name;

    TipoVeiculo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
