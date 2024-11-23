package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class EnergiaTO {

    private int idEnergia;
    private int  idUsuario;
    @NotNull
    private int kwh;
    private double  emissoes;
    private LocalDate data;

    public EnergiaTO() {
    }

    public EnergiaTO(int idEnergia, int idUsuario, @NotNull int kwh, double emissoes, LocalDate data) {
        this.idEnergia = idEnergia;
        this.idUsuario = idUsuario;
        this.kwh = kwh;
        this.emissoes = emissoes;
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getIdEnergia() {
        return idEnergia;
    }

    public void setIdEnergia(int idEnergia) {
        this.idEnergia = idEnergia;
    }

    public int getKwh() {
        return kwh;
    }

    public void setKwh(int kwh) {
        this.kwh = kwh;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getEmissoes() {
        return emissoes;
    }

    public void setEmissoes(double emissoes) {
        this.emissoes = emissoes;
    }

    public double calcularEmissoes(){
        return getKwh() * 0.4;
    }
}
