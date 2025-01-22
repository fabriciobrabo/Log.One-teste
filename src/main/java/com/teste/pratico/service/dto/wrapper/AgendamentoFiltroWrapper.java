package com.teste.pratico.service.dto.wrapper;

import java.time.LocalDateTime;

public class AgendamentoFiltroWrapper {

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private Long solicitanteId;

    public AgendamentoFiltroWrapper() {
    }

    public AgendamentoFiltroWrapper(LocalDateTime dataInicio, LocalDateTime dataFim, Long solicitanteId) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.solicitanteId = solicitanteId;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public Long getSolicitanteId() {
        return solicitanteId;
    }

    public void setSolicitanteId(Long solicitanteId) {
        this.solicitanteId = solicitanteId;
    }
}
