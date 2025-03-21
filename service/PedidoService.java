package service;

import model.ItemPedido;
import model.Pedido;
import model.Vendedor;
import util.FormatadorMoeda;

public class PedidoService {
    public Pedido criarPedido(String nomeCliente) {
        return new Pedido(nomeCliente);
    }
    
    public void adicionarItem(Pedido pedido, ItemPedido item) {
        if (item != null) {
            pedido.adicionarItem(item);
        }
    }
    
    public boolean pedidoVazio(Pedido pedido) {
        return pedido.calcularTotal() == 0;
    }
    
    public void mostrarFatura(Pedido pedido) {
        pedido.mostrarFatura();
    }
    
    public double finalizarPedido(Pedido pedido, double valorPago, Vendedor vendedor) {
        double total = pedido.calcularTotal();
        double troco = valorPago - total;
        double bonus = vendedor.calcularBonus(total);
        
        System.out.printf("Troco: %s%n", FormatadorMoeda.formatar(troco));
        System.out.printf("Bônus do vendedor nesta venda: %s%n", 
                FormatadorMoeda.formatar(bonus));
        
        return troco;
    }
}