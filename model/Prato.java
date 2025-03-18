package model;

import util.Validador;

public abstract class Prato implements ItemPedido {
    private double precoVenda;

    public Prato(double precoVenda) {
        Validador.validarNumero(precoVenda, 0, "Preço não pode ser negativo");
        this.precoVenda = precoVenda;
    }

    @Override
    public double getPrecoVenda() {
        return precoVenda;
    }

    @Override
    public abstract String descricao();
}