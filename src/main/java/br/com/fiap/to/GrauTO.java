package br.com.fiap.to;

import java.time.LocalDate;

public class GrauTO {
    private double porcentagem;
    private LocalDate data;
    private int idUsuario;
    private double soma;
    private double emissaoEnergia;
    private double emissaoAgua;
    private double emissaoVeiculo;



    public GrauTO() {
    }


    public double getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(double porcentagem) {
        this.porcentagem = porcentagem;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getEmissaoEnergia() {
        return emissaoEnergia;
    }

    public void setEmissaoEnergia(double emissaoEnergia) {
        this.emissaoEnergia = emissaoEnergia;
    }

    public double getEmissaoAgua() {
        return emissaoAgua;
    }

    public void setEmissaoAgua(double emissaoAgua) {
        this.emissaoAgua = emissaoAgua;
    }

    public double getEmissaoVeiculo() {
        return emissaoVeiculo;
    }

    public void setEmissaoVeiculo(double emissaoVeiculo) {
        this.emissaoVeiculo = emissaoVeiculo;
    }

    public double getSoma() {
        return soma;
    }

    public void setSoma(double soma) {
        this.soma = soma;
    }

    public double soma() {
        return getEmissaoEnergia() + getEmissaoAgua() + getEmissaoVeiculo();
    }
    public double calcularPorcentagem(double sum) {
        return (167/ sum)*100;
    }

}
