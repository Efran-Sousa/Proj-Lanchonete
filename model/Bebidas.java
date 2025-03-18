package model;

import util.Validador;

public class Bebidas extends Prato {
    private String tipo;
    private String sabor;
    private String tamanho;

    public Bebidas(double precoVenda, String tipo, String sabor, String tamanho) {
        super(precoVenda);
        
        Validador.validarString(tipo, "Tipo não pode ser vazio");
        Validador.validarString(sabor, "Sabor não pode ser vazio");
        Validador.validarString(tamanho, "Tamanho não pode ser vazio");
        
        this.tipo = tipo;
        this.sabor = sabor;
        this.tamanho = tamanho;
    }

    // Getters
    public String getTipo() {
        return tipo;
    }

    public String getSabor() {
        return sabor;
    }

    public String getTamanho() {
        return tamanho;
    }

    // Setters
    public void setTipo(String tipo) {
        Validador.validarString(tipo, "Tipo não pode ser vazio");
        this.tipo = tipo;
    }

    public void setSabor(String sabor) {
        Validador.validarString(sabor, "Sabor não pode ser vazio");
        this.sabor = sabor;
    }

    public void setTamanho(String tamanho) {
        Validador.validarString(tamanho, "Tamanho não pode ser vazio");
        this.tamanho = tamanho;
    }

    @Override
    public String descricao() {
        return tipo + " " + sabor + " " + tamanho;
    }
}