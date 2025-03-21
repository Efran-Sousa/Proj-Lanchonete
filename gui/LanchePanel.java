package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import model.ItemPedido;
import model.Lanche;
import factory.LancheFactory;
import factory.ItemFactory;

public class LanchePanel extends JPanel {
    private MainFrame mainFrame;
    private List<Lanche> lanches;
    private JList<String> listLanches;
    private DefaultListModel<String> listModel;
    private ItemFactory lancheFactory;
    
    // Definição das opções de lanches e seus nomes para o display
    private static final String[] OPCOES_LANCHES = {
        "1", // Misto
        "2", // Hambúrguer
        "3", // X-Burger
        "4", // X-Eggs
        "5", // Duplo
        "6", // X-Calabresa
        "7", // X-Eggs Calabresa
        "8", // X-Bacon
        "9", // X-Eggs Bacon
        "10", // X-Duplo com bacon
        "11", // X-Bacon com Calabresa
        "12"  // X-Tudo
    };
    
    private static final String[] NOMES_LANCHES = {
        "Misto",
        "Hambúrguer",
        "X-Burger",
        "X-Eggs",
        "Duplo",
        "X-Calabresa",
        "X-Eggs Calabresa",
        "X-Bacon",
        "X-Eggs Bacon",
        "X-Duplo com bacon",
        "X-Bacon com Calabresa",
        "X-Tudo"
    };
    
    public LanchePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.lancheFactory = new LancheFactory();
        
        setLayout(new BorderLayout());
        
        // Painel do título
        JPanel titlePanel = new JPanel();
        JLabel lblTitle = new JLabel("Escolha um Lanche");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(lblTitle);
        
        // Cria a lista de lanches utilizando a LancheFactory
        lanches = criarLanchesComFactory();
        
        // Cria o modelo de lista
        listModel = new DefaultListModel<>();
        for (int i = 0; i < lanches.size(); i++) {
            Lanche lanche = lanches.get(i);
            // Formato: Nome - Preço
            listModel.addElement(NOMES_LANCHES[i] + " - R$ " + 
                    String.format("%.2f", lanche.getPrecoVenda()));
        }
        
        listLanches = new JList<>(listModel);
        listLanches.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(listLanches);
        
        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnAdicionar = new JButton("Adicionar ao Pedido");
        JButton btnVoltar = new JButton("Voltar ao Menu");
        
        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnVoltar);
        
        // Adiciona os painéis ao layout
        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Configura ações dos botões
        btnAdicionar.addActionListener(e -> adicionarLanche());
        btnVoltar.addActionListener(e -> mainFrame.showPanel("menu"));
    }
    
    private List<Lanche> criarLanchesComFactory() {
        List<Lanche> listaLanches = new ArrayList<>();
        
        // Para cada opção, cria um lanche usando a factory
        for (String opcao : OPCOES_LANCHES) {
            // Simula a entrada do usuário para a factory
            String input = opcao + "\n";
            Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
            
            // Usa a factory para criar o item
            ItemPedido item = lancheFactory.criarItem(scanner);
            
            if (item instanceof Lanche) {
                listaLanches.add((Lanche) item);
            }
            
            scanner.close();
        }
        
        return listaLanches;
    }
    
    private void adicionarLanche() {
        int selectedIndex = listLanches.getSelectedIndex();
        
        if (selectedIndex != -1) {
            if (mainFrame.getPedidoAtual() == null) {
                JOptionPane.showMessageDialog(this, 
                        "Você deve iniciar um novo pedido primeiro.", 
                        "Pedido não iniciado", JOptionPane.WARNING_MESSAGE);
                mainFrame.showPanel("menu");
                return;
            }
            
            // Pega o lanche diretamente da lista
            Lanche lancheSelecionado = lanches.get(selectedIndex);
            
            // Adiciona ao pedido
            mainFrame.getPedidoAtual().adicionarItem(lancheSelecionado);
            
            // Usa o nome do array ao invés de derivar do lanche
            JOptionPane.showMessageDialog(this, 
                    NOMES_LANCHES[selectedIndex] + " adicionado ao pedido com sucesso!", 
                    "Item Adicionado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, 
                    "Selecione um lanche primeiro.", 
                    "Nenhuma Seleção", JOptionPane.WARNING_MESSAGE);
        }
    }
}
