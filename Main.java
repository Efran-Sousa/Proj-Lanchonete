
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Pedido pedidoAtual = null;
    private static Vendedor vendedor = null;

    public static void main(String[] args) {
        try {
            System.out.println("=== Iniciando Sistema da Lanchonete ===");
            
            // Criando vendedor
            System.out.println("Digite o nome do vendedor:");
            String nomeVendedor = scanner.nextLine();
            System.out.println("Digite o salário base do vendedor:");
            double salarioVendedor = scanner.nextDouble();
            scanner.nextLine(); // Limpar buffer
            vendedor = new Vendedor(nomeVendedor, salarioVendedor);

            boolean continuar = true;
            while (continuar) {
                if (pedidoAtual == null) {
                    iniciarNovoPedido();
                }
                
                mostrarMenu();
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer

                switch (opcao) {
                    case 1:
                        adicionarPizza();
                        break;
                    case 2:
                        adicionarLanche();
                        break;
                    case 3:
                        adicionarSalgadinho();
                        break;
                    case 4:
                        if (pedidoAtual != null) {
                            pedidoAtual.mostrarFatura();
                        }
                        break;
                    case 5:
                        finalizarPedido();
                        break;
                    case 0:
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
            
            scanner.close();
            
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void iniciarNovoPedido() {
        System.out.println("\n=== Novo Pedido ===");
        System.out.println("Digite o nome do cliente:");
        String nomeCliente = scanner.nextLine();
        System.out.println("Digite a taxa de serviço:");
        double taxaServico = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer
        
        pedidoAtual = new Pedido(nomeCliente, taxaServico);
    }

    private static void adicionarPizza() {
        System.out.println("\n=== Adicionar Pizza ===");
        System.out.println("Digite o preço da pizza:");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer
        
        System.out.println("Digite o recheio:");
        String recheio = scanner.nextLine();
        
        System.out.println("Digite o tipo de borda:");
        String borda = scanner.nextLine();
        
        System.out.println("Digite o tipo de molho:");
        String molho = scanner.nextLine();
        
        Pizza pizza = new Pizza(preco, new Date(), 0.8, recheio, borda, molho);
        pedidoAtual.adicionarItem(pizza);
        System.out.println("Pizza adicionada com sucesso!");
    }

    private static void adicionarLanche() {
        System.out.println("\n=== Adicionar Lanche ===");
        System.out.println("Digite o preço do lanche:");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer
        
        System.out.println("Digite o tipo de pão:");
        String pao = scanner.nextLine();
        
        System.out.println("Digite o recheio:");
        String recheio = scanner.nextLine();
        
        System.out.println("Digite o tipo de molho:");
        String molho = scanner.nextLine();
        
        Lanche lanche = new Lanche(preco, new Date(), 0.3, pao, recheio, molho);
        pedidoAtual.adicionarItem(lanche);
        System.out.println("Lanche adicionado com sucesso!");
    }

    private static void adicionarSalgadinho() {
        System.out.println("\n=== Adicionar Salgadinho ===");
        System.out.println("Digite o preço do salgadinho:");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer
        
        System.out.println("Digite o tipo de salgadinho:");
        String tipo = scanner.nextLine();
        
        System.out.println("Digite o tipo de massa:");
        String massa = scanner.nextLine();
        
        System.out.println("Digite o recheio:");
        String recheio = scanner.nextLine();
        
        Salgadinho salgadinho = new Salgadinho(preco, new Date(), 0.1, tipo, massa, recheio);
        pedidoAtual.adicionarItem(salgadinho);
        System.out.println("Salgadinho adicionado com sucesso!");
    }

    private static void finalizarPedido() {
        if (pedidoAtual != null) {
            pedidoAtual.mostrarFatura();
            
            System.out.println("\nDigite o valor pago pelo cliente:");
            double valorPago = scanner.nextDouble();
            double troco = pedidoAtual.calcularTroco(valorPago);
            System.out.printf("Troco: %s%n", formatarMoeda(troco));
            
            double bonus = vendedor.calcularBonus(pedidoAtual.calcularTotal());
            System.out.printf("Bônus do vendedor: %s%n", formatarMoeda(bonus));
            
            pedidoAtual = null; // Limpa o pedido atual
        } else {
            System.out.println("Não há pedido em andamento!");
        }
    }

    private static String formatarMoeda(double valor) {
        return String.format("R$ %.2f", valor);
    }

    private static void mostrarMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Adicionar Pizza");
        System.out.println("2. Adicionar Lanche");
        System.out.println("3. Adicionar Salgadinho");
        System.out.println("4. Mostrar Fatura");
        System.out.println("5. Finalizar Pedido");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
}