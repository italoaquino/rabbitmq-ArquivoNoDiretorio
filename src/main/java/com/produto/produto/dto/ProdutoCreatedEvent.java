package com.produto.produto.dto;

import java.io.Serializable;

public class ProdutoCreatedEvent implements Serializable {

    private Long id;

    private String nome;

    private Double price;

    public ProdutoCreatedEvent(){
    }

    public ProdutoCreatedEvent(Long id, String nome, Double price) {
        this.id = id;
        this.nome = nome;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
