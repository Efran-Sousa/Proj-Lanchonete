package model;

import util.Validador;

public class Vendedor {
    private String nome;
    private double salario;
    private double bonus;
    private static final double TAXA_BONUS = 0.02; // 2% de comissão

    public Vendedor(String nome, double salario) {
        Validador.validarString(nome, "Nome do vendedor não pode ser vazio");
        Validador.validarNumero(salario, 0, "Salário não pode ser negativo");
        
        this.nome = nome;
        this.salario = salario;
        this.bonus = 0;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public double getSalario() {
        return salario;
    }

    public double getBonus() {
        return bonus;
    }

    // Setters
    public void setNome(String nome) {
        Validador.validarString(nome, "Nome do vendedor não pode ser vazio");
        this.nome = nome;
    }

    public void setSalario(double salario) {
        Validador.validarNumero(salario, 0, "Salário não pode ser negativo");
        this.salario = salario;
    }

    public double calcularBonus(double valorVendido) {
        double bonusAtual = valorVendido * TAXA_BONUS;
        bonus += bonusAtual;
        return bonusAtual;
    }
}

