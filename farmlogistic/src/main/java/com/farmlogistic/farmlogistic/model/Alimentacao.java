package com.farmlogistic.farmlogistic.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo que representa a alimentação de um animal")
public class Alimentacao {
    @Schema(description = "Tipo de alimentação fornecido ao animal", example = "Ração Seca")
    private String tipoAlimentacao;
    
    @Schema(description = "Quantidade de alimento fornecido em quilos", example = "2.5")
    private double quantidade;
    
    @Schema(description = "Período de alimentação (ex: manhã, tarde, noite)", example = "Manhã")
    private String periodo;

    // Construtores
    public Alimentacao(String tipoAlimentacao, double quantidade, String periodo) {
        this.tipoAlimentacao = tipoAlimentacao;
        this.quantidade = quantidade;
        this.periodo = periodo;
    }

    // Getters e Setters
    public String getTipoAlimentacao() {
        return tipoAlimentacao;
    }

    public void setTipoAlimentacao(String tipoAlimentacao) {
        this.tipoAlimentacao = tipoAlimentacao;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

}
