import java.util.List;
import java.util.ArrayList;

public class Pedido {
    private String nomeCliente;
    private double taxaDeServico;
    private List<Prato> itensConsumidos;

    public Pedido(String nomeCliente, double taxaDeServico) {
        this.nomeCliente = nomeCliente;
        this.taxaDeServico = taxaDeServico;
        this.itensConsumidos = new ArrayList<>();
    }

    public void adicionarItem(Prato item) {
        itensConsumidos.add(item);
    }

    public double calcularTotal() {
        double total = 0;
        for (Prato item : itensConsumidos) {
            total += item.getPrecoVenda();
        }
        return total + taxaDeServico;
    }

    public void mostrarFatura() {
        System.out.println("------- ProgLanches -------");
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("Itens consumidos:");
        for (Prato item : itensConsumidos) {
            System.out.println("- " + item.descricao() + " (R$" + item.getPrecoVenda() + ")");
        }
        System.out.println("Taxa de serviço: R$" + taxaDeServico);
        System.out.println("Total: R$" + calcularTotal());
    }

    public double calcularTroco(double valorPago) {
        return valorPago - calcularTotal();
    }
}