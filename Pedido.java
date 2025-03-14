import java.util.List;
import java.util.ArrayList;

public class Pedido {
    private String nomeCliente;
    private List<ItemPedido> itensConsumidos;

    public Pedido(String nomeCliente) {
        this.nomeCliente = nomeCliente;
        this.itensConsumidos = new ArrayList<>();
    }

    public void adicionarItem(ItemPedido item) {
        itensConsumidos.add(item);
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
            System.out.println("- " + item.descricao() + " (R$" + item.getPrecoVenda() + ")");
        }
        System.out.println("Total: R$" + calcularTotal());
    }

    public double calcularTroco(double valorPago) {
        return valorPago - calcularTotal();
    }
}