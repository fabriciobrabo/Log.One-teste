package com.teste.pratico.service.dto.wrapper;

public class ConsultaAgendamentoSolicitanteWrapper {

    private String solicitanteNome;

    private Integer totalAgendamentos;

    private Integer quantidadeVagas;

    private double percentual;

    public String getSolicitanteNome() {
        return solicitanteNome;
    }

    public void setSolicitanteNome(String solicitanteNome) {
        this.solicitanteNome = solicitanteNome;
    }

    public Integer getTotalAgendamentos() {
        return totalAgendamentos;
    }

    public void setTotalAgendamentos(Integer totalAgendamentos) {
        this.totalAgendamentos = totalAgendamentos;
    }

    public Integer getQuantidadeVagas() {
        return quantidadeVagas;
    }

    public void setQuantidadeVagas(Integer quantidadeVagas) {
        this.quantidadeVagas = quantidadeVagas;
    }

    public double getPercentual() {
        return percentual;
    }

    public void setPercentual(double percentual) {
        this.percentual = percentual;
    }
}
