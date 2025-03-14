// Classe abstrata Prato
public abstract class Prato implements ItemPedido {
    private double precoVenda;

    public Prato(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    @Override
    public double getPrecoVenda() {
        return precoVenda;
    }

    @Override
    public abstract String descricao();
}