package gui;

import javax.swing.*;
import java.awt.*;
import model.Pedido;
import model.Vendedor;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    
    // Painéis da aplicação
    private LoginPanel loginPanel;
    private MenuPanel menuPanel;
    private LanchePanel lanchePanel;
    private SalgadinhoPanel salgadinhoPanel;
    private BebidasPanel bebidasPanel;
    private PedidoPanel pedidoPanel;
    
    // Dados da aplicação
    private Vendedor vendedor;
    private Pedido pedidoAtual;
    
    public MainFrame() {
        // Configurações básicas da janela
        setTitle("Sistema de Lanchonete");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centraliza a janela
        
        // Configuração do CardLayout para navegação entre telas
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        // Inicialização dos painéis
        loginPanel = new LoginPanel(this);
        menuPanel = new MenuPanel(this);
        lanchePanel = new LanchePanel(this);
        salgadinhoPanel = new SalgadinhoPanel(this);
        bebidasPanel = new BebidasPanel(this);
        pedidoPanel = new PedidoPanel(this);
        
        // Adiciona painéis ao cardLayout
        cardPanel.add(loginPanel, "login");
        cardPanel.add(menuPanel, "menu");
        cardPanel.add(lanchePanel, "lanche");
        cardPanel.add(salgadinhoPanel, "salgadinho");
        cardPanel.add(bebidasPanel, "bebida");
        cardPanel.add(pedidoPanel, "pedido");
        
        // Adiciona o painel principal ao frame
        add(cardPanel);
        
        // Inicia com o painel de login
        showPanel("login");
    }
    
    // Métodos para navegação
    public void showPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
        
        // Atualiza o painel se necessário
        if (panelName.equals("pedido") && pedidoPanel != null) {
            pedidoPanel.atualizarTela();
        }
    }
    
    // Getters e setters
    public Vendedor getVendedor() {
        return vendedor;
    }
    
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    public Pedido getPedidoAtual() {
        return pedidoAtual;
    }
    
    public void setPedidoAtual(Pedido pedidoAtual) {
        this.pedidoAtual = pedidoAtual;
    }
}
