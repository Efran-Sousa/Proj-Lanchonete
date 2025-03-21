package gui;

import javax.swing.*;
import java.awt.*;
import model.Pedido;
import service.PedidoService;
import service.VendedorService;
import util.FormatadorMoeda;

public class MenuPanel extends JPanel {
    private MainFrame mainFrame;
    private PedidoService pedidoService;
    private VendedorService vendedorService;
    
    public MenuPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.pedidoService = new PedidoService();
        this.vendedorService = new VendedorService();
        
        setLayout(new BorderLayout());
        
        // Painel de boas-vindas
        JPanel welcomePanel = new JPanel();
        JLabel lblWelcome = new JLabel("Menu Principal");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 24));
        welcomePanel.add(lblWelcome);
        
        // Painel de botões de categoria
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Botão Novo Pedido
        JButton btnNovoPedido = criarBotao("Novo Pedido", e -> novoPedido());
        buttonPanel.add(btnNovoPedido);
        
        // Botão Lanches
        JButton btnLanches = criarBotao("Lanches", e -> mainFrame.showPanel("lanche"));
        buttonPanel.add(btnLanches);
        
        // Botão Salgadinhos
        JButton btnSalgadinhos = criarBotao("Salgadinhos", e -> mainFrame.showPanel("salgadinho"));
        buttonPanel.add(btnSalgadinhos);
        
        // Botão Bebidas
        JButton btnBebidas = criarBotao("Bebidas", e -> mainFrame.showPanel("bebida"));
        buttonPanel.add(btnBebidas);
        
        // Botão Ver Pedido
        JButton btnVerPedido = criarBotao("Ver Pedido Atual", e -> mainFrame.showPanel("pedido"));
        buttonPanel.add(btnVerPedido);
        
        // Botão Ver Bônus
        JButton btnBonus = criarBotao("Ver Bônus", e -> mostrarBonus());
        buttonPanel.add(btnBonus);
        
        // Painel inferior
        JPanel bottomPanel = new JPanel();
        JButton btnLogout = new JButton("Encerrar Turno e Sair");
        btnLogout.addActionListener(e -> encerrarTurno());
        bottomPanel.add(btnLogout);
        
        // Montagem final
        add(welcomePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private JButton criarBotao(String texto, java.awt.event.ActionListener listener) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.PLAIN, 16));
        btn.addActionListener(listener);
        return btn;
    }
    
    private void novoPedido() {
        String nomeCliente = JOptionPane.showInputDialog(this, 
                "Nome do cliente:", 
                "Novo Pedido", 
                JOptionPane.QUESTION_MESSAGE);
        
        if (nomeCliente != null && !nomeCliente.trim().isEmpty()) {
            Pedido pedido = pedidoService.criarPedido(nomeCliente);
            mainFrame.setPedidoAtual(pedido);
            
            JOptionPane.showMessageDialog(this, 
                    "Pedido criado para: " + nomeCliente, 
                    "Pedido Iniciado", 
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void mostrarBonus() {
        if (mainFrame.getVendedor() == null) {
            JOptionPane.showMessageDialog(this, 
                    "Erro ao acessar informações do vendedor.", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        double salario = mainFrame.getVendedor().getSalario();
        double bonus = mainFrame.getVendedor().getBonus();
        
        JOptionPane.showMessageDialog(this, 
                "Vendedor: " + mainFrame.getVendedor().getNome() + "\n" +
                "Salário Base: " + FormatadorMoeda.formatar(salario) + "\n" +
                "Bônus Acumulado: " + FormatadorMoeda.formatar(bonus) + "\n" +
                "Total a Receber: " + FormatadorMoeda.formatar(salario + bonus),
                "Informações do Vendedor", 
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void encerrarTurno() {
        if (mainFrame.getVendedor() == null) {
            JOptionPane.showMessageDialog(this, 
                    "Erro ao acessar informações do vendedor.", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirmacao = JOptionPane.showConfirmDialog(this, 
                "Deseja realmente encerrar o turno?", 
                "Confirmar Saída", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirmacao == JOptionPane.YES_OPTION) {
            vendedorService.mostrarResumoFinalTurno(mainFrame.getVendedor());
            
            double salario = mainFrame.getVendedor().getSalario();
            double bonus = mainFrame.getVendedor().getBonus();
            
            JOptionPane.showMessageDialog(this, 
                    "==== RESUMO FINAL DO TURNO ====\n\n" +
                    "Vendedor: " + mainFrame.getVendedor().getNome() + "\n" +
                    "Salário Base: " + FormatadorMoeda.formatar(salario) + "\n" +
                    "Bônus Acumulado: " + FormatadorMoeda.formatar(bonus) + "\n" +
                    "Total a Receber: " + FormatadorMoeda.formatar(salario + bonus) + "\n\n" +
                    "Volte sempre!",
                    "Turno Encerrado", 
                    JOptionPane.INFORMATION_MESSAGE);
            
            System.exit(0);
        }
    }
}
