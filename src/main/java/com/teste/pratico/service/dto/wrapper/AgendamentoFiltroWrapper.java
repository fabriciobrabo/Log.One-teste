package com.teste.pratico.service.dto.wrapper;

import java.time.ZonedDateTime;

public class AgendamentoFiltroWrapper {

    private ZonedDateTime dataInicio;

    private ZonedDateTime dataFim;

    private Long solicitanteId;

    public AgendamentoFiltroWrapper() {
    }

    public AgendamentoFiltroWrapper(ZonedDateTime dataInicio, ZonedDateTime dataFim, Long solicitanteId) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.solicitanteId = solicitanteId;
    }

    public ZonedDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(ZonedDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public ZonedDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(ZonedDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public Long getSolicitanteId() {
        return solicitanteId;
    }

    public void setSolicitanteId(Long solicitanteId) {
        this.solicitanteId = solicitanteId;
    }
}
