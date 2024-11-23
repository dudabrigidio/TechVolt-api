package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class AguaTO {

    private int idAgua;
    private int  idUsuario;
    @NotNull
    private int quantidade;
    private double  emissoes;
    private LocalDate data;

    public AguaTO() {
    }

    public AguaTO(int idAgua, int idUsuario, @NotNull int quantidade, double emissoes, LocalDate data) {
        this.idAgua = idAgua;
        this.idUsuario = idUsuario;
        this.quantidade = quantidade;
        this.emissoes = emissoes;
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getIdAgua() {
        return idAgua;
    }

    public void setIdAgua(int idAgua) {
        this.idAgua = idAgua;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getEmissoes() {
        return emissoes;
    }

    public void setEmissoes(double emissoes) {
        this.emissoes = emissoes;
    }

    public double calcularEmissoes(){
        return getQuantidade() * 0.001;
    }
}
