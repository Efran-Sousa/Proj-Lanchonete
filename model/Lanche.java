package model;

import util.Validador;

public class Lanche extends Prato {
    private String pao;
    private String recheio;
    private String molho;

    public Lanche(double precoVenda, String pao, String recheio, String molho) {
        super(precoVenda);
        
        Validador.validarString(pao, "Tipo de pão não pode ser vazio");
        Validador.validarString(recheio, "Recheio não pode ser vazio");
        Validador.validarString(molho, "Molho não pode ser vazio");
        
        this.pao = pao;
        this.recheio = recheio;
        this.molho = molho;
    }

    // Getters
    public String getPao() {
        return pao;
    }

    public String getRecheio() {
        return recheio;
    }

    public String getMolho() {
        return molho;
    }

    // Setters
    public void setPao(String pao) {
        Validador.validarString(pao, "Tipo de pão não pode ser vazio");
        this.pao = pao;
    }

    public void setRecheio(String recheio) {
        Validador.validarString(recheio, "Recheio não pode ser vazio");
        this.recheio = recheio;
    }

    public void setMolho(String molho) {
        Validador.validarString(molho, "Molho não pode ser vazio");
        this.molho = molho;
    }

    @Override
    public String descricao() {
        return recheio + " no pão " + pao + " com molho " + molho;
    }
}