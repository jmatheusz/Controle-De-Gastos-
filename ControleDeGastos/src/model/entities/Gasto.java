package model.entities;

import model.enums.Categoria;

import java.time.LocalDate;
import java.util.Date;

public class Gasto {
    private String descricao;
    private Double valor;
    private LocalDate data;
    private Categoria categoria;

    public Gasto(){
    }
    public Gasto(LocalDate data, String descricao, Double valor, Categoria categoria) {
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValor() {
        return valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
