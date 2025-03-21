package ui;

import java.util.Scanner;
import factory.ItemFactory;
import factory.LancheFactory;
import factory.SalgadinhoFactory;
import factory.BebidasFactory;
import model.ItemPedido;
import model.Pedido;
import model.Vendedor;
import service.PedidoService;
import service.VendedorService;
import util.MenuBuilder;

public class MenuController {
    private Scanner scanner;
    private PedidoService pedidoService;
    private VendedorService vendedorService;
    private ItemFactory lancheFactory;
    private ItemFactory salgadinhoFactory;
    private ItemFactory bebidasFactory;
    private Pedido pedidoAtual;
    private Vendedor vendedor;
    
    public MenuController() {
        this.scanner = new Scanner(System.in);
        this.pedidoService = new PedidoService();
        this.vendedorService = new VendedorService();
        this.lancheFactory = new LancheFactory();
        this.salgadinhoFactory = new SalgadinhoFactory();
        this.bebidasFactory = new BebidasFactory();
    }
    
    public void iniciar() {
        try {
            System.out.println("=== Iniciando Sistema da Lanchonete ===");
            inicializarVendedor();
            executarMenuPrincipal();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    private void inicializarVendedor() {
        System.out.println("Digite o nome do vendedor:");
        String nomeVendedor = scanner.nextLine();
        
        System.out.println("Digite o salário base do vendedor:");
        double salarioVendedor = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer
        
        vendedor = vendedorService.criarVendedor(nomeVendedor, salarioVendedor);
    }
    
    private void executarMenuPrincipal() {
        boolean continuar = true;
        
        while (continuar) {
            if (pedidoAtual == null) {
                iniciarNovoPedido();
            }
            
            mostrarMenuPrincipal();
            int opcao = MenuBuilder.lerOpcao(scanner, 0, 6);
            
            switch (opcao) {
                case 1:
                    adicionarLanche();
                    break;
                case 2:
                    adicionarSalgadinho();
                    break;
                case 3:
                    adicionarBebida();
                    break;
                case 4:
                    if (pedidoAtual != null) {
                        pedidoService.mostrarFatura(pedidoAtual);
                    }
                    break;
                case 5:
                    finalizarPedido();
                    break;
                case 6:
                    vendedorService.mostrarInformacoes(vendedor);
                    break;
                case 0:
                    vendedorService.mostrarResumoFinalTurno(vendedor);
                    continuar = false;
                    System.out.println("Sistema encerrado!");
                    break;
            }
        }
    }
    
    private void mostrarMenuPrincipal() {
        String[] opcoes = {
            "Adicionar Lanche",
            "Adicionar Salgadinho",
            "Adicionar Bebida",
            "Mostrar Fatura",
            "Finalizar Pedido",
            "Mostrar Bônus do Vendedor"
        };
        MenuBuilder.exibirMenu("MENU", opcoes, true);
    }
    
    private void iniciarNovoPedido() {
        System.out.println("\n=== Novo Pedido ===");
        System.out.println("Digite o nome do cliente:");
        String nomeCliente = scanner.nextLine();
        
        pedidoAtual = pedidoService.criarPedido(nomeCliente);
    }
    
    private void adicionarLanche() {
        ItemPedido lanche = lancheFactory.criarItem(scanner);
        if (lanche != null) {
            pedidoService.adicionarItem(pedidoAtual, lanche);
            System.out.println("Lanche adicionado com sucesso!");
        }
    }
    
    private void adicionarSalgadinho() {
        ItemPedido salgadinho = salgadinhoFactory.criarItem(scanner);
        if (salgadinho != null) {
            pedidoService.adicionarItem(pedidoAtual, salgadinho);
            System.out.println("Salgadinho adicionado com sucesso!");
        }
    }
    
    private void adicionarBebida() {
        ItemPedido bebida = bebidasFactory.criarItem(scanner);
        if (bebida != null) {
            pedidoService.adicionarItem(pedidoAtual, bebida);
            System.out.println("Bebida adicionada com sucesso!");
        }
    }
    
    private void finalizarPedido() {
        if (pedidoAtual != null) {
            if (pedidoService.pedidoVazio(pedidoAtual)) {
                System.out.println("Nenhum pedido realizado!");
                pedidoAtual = null;
                iniciarNovoPedido();
            } else {
                pedidoService.mostrarFatura(pedidoAtual);
                
                System.out.println("\nDigite o valor pago pelo cliente:");
                double valorPago = scanner.nextDouble();
                scanner.nextLine(); // Limpar buffer
                
                pedidoService.finalizarPedido(pedidoAtual, valorPago, vendedor);
                
                pedidoAtual = null; // Limpa o pedido atual
            }
        } else {
            System.out.println("Não há pedido em andamento!");
        }
    }
}