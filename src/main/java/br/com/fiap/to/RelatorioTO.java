package br.com.fiap.to;

import java.time.LocalDate;

public class RelatorioTO extends UsuarioTO{
    private int energiaKwh;
    private double energiaEmissoes;
    private int quantidadeL;
    private double  aguaEmissoes;
    private double distanciaKm;
    private String combustivel;
    private double  veiculoEmissoes;
    private double grauSustentab;
    private double somaEmissao;
    private LocalDate data;

    public RelatorioTO(double grauSustentab, double veiculoEmissoes, String combustivel, double distanciaKm, double aguaEmissoes, int quantidadeL, double energiaEmissoes, int energiaKwh, double somaEmissao, LocalDate data) {
        this.grauSustentab = grauSustentab;
        this.veiculoEmissoes = veiculoEmissoes;
        this.combustivel = combustivel;
        this.distanciaKm = distanciaKm;
        this.aguaEmissoes = aguaEmissoes;
        this.quantidadeL = quantidadeL;
        this.energiaEmissoes = energiaEmissoes;
        this.energiaKwh = energiaKwh;
        this.somaEmissao = somaEmissao;
        this.data = data;
    }

    public RelatorioTO() {
    }

    public double getSomaEmissao() {
        return somaEmissao;
    }

    public void setSomaEmissao(double somaEmissao) {
        this.somaEmissao = somaEmissao;
    }

    public int getEnergiaKwh() {
        return energiaKwh;
    }

    public void setEnergiaKwh(int energiaKwh) {
        this.energiaKwh = energiaKwh;
    }

    public double getEnergiaEmissoes() {
        return energiaEmissoes;
    }

    public void setEnergiaEmissoes(double energiaEmissoes) {
        this.energiaEmissoes = energiaEmissoes;
    }

    public int getQuantidadeL() {
        return quantidadeL;
    }

    public void setQuantidadeL(int quantidadeL) {
        this.quantidadeL = quantidadeL;
    }

    public double getAguaEmissoes() {
        return aguaEmissoes;
    }

    public void setAguaEmissoes(double aguaEmissoes) {
        this.aguaEmissoes = aguaEmissoes;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public double getVeiculoEmissoes() {
        return veiculoEmissoes;
    }

    public void setVeiculoEmissoes(double veiculoEmissoes) {
        this.veiculoEmissoes = veiculoEmissoes;
    }

    public double getGrauSustentab() {
        return grauSustentab;
    }

    public void setGrauSustentab(double grauSustentab) {
        this.grauSustentab = grauSustentab;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
