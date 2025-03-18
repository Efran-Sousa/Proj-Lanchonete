package model;

import util.Validador;

public class Salgadinho extends Prato {
    private String tipo;
    private String massa;
    private String recheio;

    public Salgadinho(double precoVenda, String tipo, String massa, String recheio) {
        super(precoVenda);
        
        Validador.validarString(tipo, "Tipo não pode ser vazio");
        Validador.validarString(massa, "Massa não pode ser vazia");
        Validador.validarString(recheio, "Recheio não pode ser vazio");
        
        this.tipo = tipo;
        this.massa = massa;
        this.recheio = recheio;
    }

    // Getters
    public String getTipo() {
        return tipo;
    }

    public String getMassa() {
        return massa;
    }

    public String getRecheio() {
        return recheio;
    }

    // Setters
    public void setTipo(String tipo) {
        Validador.validarString(tipo, "Tipo não pode ser vazio");
        this.tipo = tipo;
    }

    public void setMassa(String massa) {
        Validador.validarString(massa, "Massa não pode ser vazia");
        this.massa = massa;
    }

    public void setRecheio(String recheio) {
        Validador.validarString(recheio, "Recheio não pode ser vazio");
        this.recheio = recheio;
    }

    @Override
    public String descricao() {
        return tipo + " " + massa + " com recheio de " + recheio;
    }
}