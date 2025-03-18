package model;

import java.util.List;
import java.util.ArrayList;
import util.Validador;
import util.FormatadorMoeda;

public class Pedido {
    private String nomeCliente;
    private List<ItemPedido> itensConsumidos;

    public Pedido(String nomeCliente) {
        Validador.validarString(nomeCliente, "Nome do cliente não pode ser vazio");
        this.nomeCliente = nomeCliente;
        this.itensConsumidos = new ArrayList<>();
    }

    public void adicionarItem(ItemPedido item) {
        if (item != null) {
            itensConsumidos.add(item);
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido item : itensConsumidos) {
            total += item.getPrecoVenda();
        }
        return total;
    }

    public void mostrarFatura() {
        System.out.println("------- ProgLanches -------");
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("Itens consumidos:");
        for (ItemPedido item : itensConsumidos) {
            System.out.println("- " + item.descricao() + " (" + FormatadorMoeda.formatar(item.getPrecoVenda()) + ")");
        }
        System.out.println("Total: " + FormatadorMoeda.formatar(calcularTotal()));
    }

    public double calcularTroco(double valorPago) {
        return valorPago - calcularTotal();
    }
    
    public String getNomeCliente() {
        return nomeCliente;
    }
}