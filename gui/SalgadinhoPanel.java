package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import model.Salgadinho;
import model.ItemPedido;

public class SalgadinhoPanel extends JPanel {
    private MainFrame mainFrame;
    private JList<String> listSalgadinhos;
    private List<Salgadinho> salgadinhos;
    private String[] nomesSalgadinhos = {
        "Coxinha de Carne",
        "Coxinha de Frango",
        "Pastel Frito de Carne",
        "Pastel Frito de Frango",
        "Pastel Assado de Carne",
        "Pastel Assado de Frango",
        "Empanado de Frango"
    };
    
    public SalgadinhoPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        
        setLayout(new BorderLayout());
        
        // Painel do título
        JPanel titlePanel = new JPanel();
        JLabel lblTitle = new JLabel("Escolha um Salgadinho");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(lblTitle);
        
        // Criar lista de salgadinhos
        salgadinhos = criarSalgadinhos();
        
        // Converter para array de strings para exibição
        String[] opcoes = new String[salgadinhos.size()];
        for (int i = 0; i < salgadinhos.size(); i++) {
            Salgadinho s = salgadinhos.get(i);
            // Use nomes armazenados em vez do método getNome() que não existe
            opcoes[i] = nomesSalgadinhos[i] + " - R$ " + String.format("%.2f", s.getPrecoVenda());
        }
        
        listSalgadinhos = new JList<>(opcoes);
        listSalgadinhos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listSalgadinhos);
        
        // Botões
        JPanel buttonPanel = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar ao Pedido");
        JButton btnVoltar = new JButton("Voltar ao Menu");
        
        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnVoltar);
        
        // Adicionar ao layout
        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Implementar ações
        btnVoltar.addActionListener(e -> mainFrame.showPanel("menu"));
        btnAdicionar.addActionListener(e -> adicionarSalgadinho());
    }
    
    private List<Salgadinho> criarSalgadinhos() {
        List<Salgadinho> lista = new ArrayList<>();
        
        // Cria objetos Salgadinho correspondentes às opções do menu
        lista.add(new Salgadinho(3.0, "Massa de Batata", "Carne", "Frito"));
        lista.add(new Salgadinho(3.0, "Massa de Batata", "Frango", "Frito"));
        lista.add(new Salgadinho(4.0, "Massa Tradicional", "Carne", "Frito"));
        lista.add(new Salgadinho(4.0, "Massa Tradicional", "Frango", "Frito"));
        lista.add(new Salgadinho(5.0, "Massa Folhada", "Carne", "Assado"));
        lista.add(new Salgadinho(5.0, "Massa Folhada", "Frango", "Assado"));
        lista.add(new Salgadinho(6.0, "Massa Crocante", "Frango", "Frito"));
        
        return lista;
    }
    
    private void adicionarSalgadinho() {
        int selectedIndex = listSalgadinhos.getSelectedIndex();
        
        if (selectedIndex != -1) {
            // Verifica se existe um pedido ativo
            if (mainFrame.getPedidoAtual() == null) {
                JOptionPane.showMessageDialog(this, 
                        "Você deve iniciar um novo pedido primeiro.", 
                        "Pedido não iniciado", JOptionPane.WARNING_MESSAGE);
                mainFrame.showPanel("menu");
                return;
            }
            
            // Pega o salgadinho selecionado
            Salgadinho salgadinhoSelecionado = salgadinhos.get(selectedIndex);
            
            // Adiciona ao pedido
            mainFrame.getPedidoAtual().adicionarItem(salgadinhoSelecionado);
            
            JOptionPane.showMessageDialog(this, 
                    nomesSalgadinhos[selectedIndex] + " adicionado ao pedido com sucesso!", 
                    "Item Adicionado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, 
                    "Selecione um salgadinho primeiro.", 
                    "Nenhuma Seleção", JOptionPane.WARNING_MESSAGE);
        }
    }
}
