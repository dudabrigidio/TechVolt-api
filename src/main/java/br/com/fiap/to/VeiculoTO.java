package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class VeiculoTO {


    private int idVeiculo;
    private int  idUsuario;
    @NotNull
    private double distancia;
    @NotBlank
    private String combustivel;
    private double  emissoes;
    private LocalDate data;


    public VeiculoTO() {
    }

    public VeiculoTO(int idVeiculo, int idUsuario, @NotNull double distancia, @NotBlank String combustivel, double emissoes, LocalDate data) {
        this.idVeiculo = idVeiculo;
        this.idUsuario = idUsuario;
        this.distancia = distancia;
        this.combustivel = combustivel;
        this.emissoes = emissoes;
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public double getEmissoes() {
        return emissoes;
    }

    public void setEmissoes(double emissoes) {
        this.emissoes = emissoes;
    }

    public double calcularEmissoes(){
        double gasto_combustivel = 0;
        if (getCombustivel().equalsIgnoreCase("gasolina")){
            gasto_combustivel = 0.21;
        } else if (getCombustivel().equalsIgnoreCase("diesel")) {
            gasto_combustivel = 0.25;
        } else if (getCombustivel().equalsIgnoreCase("etanol")) {
            gasto_combustivel = 0.15;
        }
        return getDistancia() * gasto_combustivel;
    }
}
