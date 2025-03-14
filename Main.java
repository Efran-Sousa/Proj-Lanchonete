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
                        adicionarLanche();
                        break;
                    case 2:
                        adicionarSalgadinho();
                        break;
                    case 3:
                        if (pedidoAtual != null) {
                            pedidoAtual.mostrarFatura();
                        }
                        break;
                    case 4:
                        finalizarPedido();
                        break;
                    case 0:
                        continuar = false;
                        System.out.println("Sistema encerrado!");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
            
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void iniciarNovoPedido() {
        System.out.println("\n=== Novo Pedido ===");
        System.out.println("Digite o nome do cliente:");
        String nomeCliente = scanner.nextLine();
        
        pedidoAtual = new Pedido(nomeCliente);
    }

    private static void adicionarLanche() {
        Lanche lanche = Lanche.chamarLanche(scanner);
        if (lanche != null) {
            pedidoAtual.adicionarItem(lanche);
            System.out.println("Lanche adicionado com sucesso!");
        }
    }

    private static void adicionarSalgadinho() {
        Salgadinho salgadinho = Salgadinho.chamarSalgadinho(scanner);
        if (salgadinho != null) {
            pedidoAtual.adicionarItem(salgadinho);
            System.out.println("Salgadinho adicionado com sucesso!");
        }
    }

    private static void finalizarPedido() {
        if (pedidoAtual != null) {
            if (pedidoAtual.calcularTotal() == 0) {
                System.out.println("Nenhum pedido realizado!");
                pedidoAtual = null; // Limpa o pedido atual
                iniciarNovoPedido(); // Volta para a função de digitar o nome do cliente
            } else {
                pedidoAtual.mostrarFatura();
                
                System.out.println("\nDigite o valor pago pelo cliente:");
                double valorPago = scanner.nextDouble();
                scanner.nextLine(); // Limpar buffer
                double troco = pedidoAtual.calcularTroco(valorPago);
                System.out.printf("Troco: %s%n", formatarMoeda(troco));
                
                double bonus = vendedor.calcularBonus(pedidoAtual.calcularTotal());
                System.out.printf("Bônus do vendedor: %s%n", formatarMoeda(bonus));
                
                pedidoAtual = null; // Limpa o pedido atual
            }
        } else {
            System.out.println("Não há pedido em andamento!");
        }
    }

    private static String formatarMoeda(double valor) {
        return String.format("R$ %.2f", valor);
    }

    private static void mostrarMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Adicionar Lanche");
        System.out.println("2. Adicionar Salgadinho");
        System.out.println("3. Mostrar Fatura");
        System.out.println("4. Finalizar Pedido");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
}