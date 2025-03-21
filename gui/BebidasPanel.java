package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;

import model.Bebidas;
import model.ItemPedido;
import model.Pedido;

public class BebidasPanel extends JPanel {
    private MainFrame mainFrame;
    private List<ItemPedido> bebidas;
    
    public BebidasPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.bebidas = criarBebidas();
        
        setLayout(new BorderLayout());
        
        // Painel de título
        JPanel titlePanel = new JPanel();
        JLabel lblTitle = new JLabel("Bebidas");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(lblTitle);
        
        // Painel de produtos
        JPanel produtosPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        produtosPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Adiciona cada bebida como um cartão
        for (ItemPedido item : bebidas) {
            Bebidas bebida = (Bebidas) item;
            JPanel cardPanel = criarCardProduto(bebida);
            produtosPanel.add(cardPanel);
        }
        
        // Painel com scroll para os produtos
        JScrollPane scrollPane = new JScrollPane(produtosPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // Painel de botões
        JPanel buttonPanel = new JPanel();
        JButton btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.addActionListener(e -> mainFrame.showPanel("menu"));
        buttonPanel.add(btnVoltar);
        
        // Adiciona os componentes ao painel principal
        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private JPanel criarCardProduto(Bebidas bebida) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        card.setBackground(Color.WHITE);
        
        // Ícone da bebida
        JLabel lblIcon = new JLabel(getIconoBebida(bebida.getTipo()));
        lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Nome da bebida
        JLabel lblNome = new JLabel(bebida.getTipo() + " " + bebida.getSabor());
        lblNome.setFont(new Font("Arial", Font.BOLD, 14));
        lblNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Descrição da bebida
        JLabel lblDescricao = new JLabel(bebida.getTamanho());
        lblDescricao.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Preço da bebida
        JLabel lblPreco = new JLabel(String.format("R$ %.2f", bebida.getPrecoVenda()));
        lblPreco.setFont(new Font("Arial", Font.BOLD, 16));
        lblPreco.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Botão para adicionar ao pedido
        JButton btnAdicionar = new JButton("Adicionar ao Pedido");
        btnAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdicionar.addActionListener(e -> adicionarAoPedido(bebida));
        
        // Adiciona componentes ao card
        card.add(Box.createVerticalStrut(10));
        card.add(lblIcon);
        card.add(Box.createVerticalStrut(10));
        card.add(lblNome);
        card.add(Box.createVerticalStrut(5));
        card.add(lblDescricao);
        card.add(Box.createVerticalStrut(5));
        card.add(lblPreco);
        card.add(Box.createVerticalStrut(10));
        card.add(btnAdicionar);
        card.add(Box.createVerticalStrut(10));
        
        return card;
    }
    
    private ImageIcon getIconoBebida(String tipo) {
        // Aqui você poderia carregar ícones reais com base no tipo de bebida
        // Para simplificar, vamos criar ícones coloridos simples
        int size = 64;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        
        Color cor;
        switch (tipo.toLowerCase()) {
            case "suco":
                cor = new Color(255, 165, 0); // Laranja
                break;
            case "refrigerante":
                cor = new Color(139, 69, 19); // Marrom
                break;
            case "água mineral":
                cor = new Color(0, 191, 255); // Azul claro
                break;
            default:
                cor = Color.GRAY;
        }
        
        g2d.setColor(cor);
        g2d.fillRect(0, 0, size, size);
        g2d.setColor(Color.WHITE);
        g2d.drawRect(0, 0, size-1, size-1);
        g2d.dispose();
        
        return new ImageIcon(image);
    }
    
    private void adicionarAoPedido(Bebidas bebida) {
        Pedido pedido = mainFrame.getPedidoAtual();
        
        if (pedido == null) {
            JOptionPane.showMessageDialog(this, 
                    "Não há pedido em andamento. Por favor, crie um novo pedido primeiro.", 
                    "Nenhum Pedido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        pedido.adicionarItem(bebida);
        JOptionPane.showMessageDialog(this, 
                bebida.descricao() + " adicionado(a) ao pedido com sucesso!", 
                "Item Adicionado", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private List<ItemPedido> criarBebidas() {
        List<ItemPedido> lista = new ArrayList<>();
        
        // Sucos
        lista.add(new Bebidas(5.0, "Suco", "de Laranja", "300ml"));
        lista.add(new Bebidas(5.0, "Suco", "de Uva", "300ml"));
        lista.add(new Bebidas(5.0, "Suco", "de Abacaxi", "300ml"));
        lista.add(new Bebidas(5.5, "Suco", "de Maracujá", "300ml"));
        
        // Refrigerantes
        lista.add(new Bebidas(4.0, "Refrigerante", "Cola", "Lata 350ml"));
        lista.add(new Bebidas(4.0, "Refrigerante", "Guaraná", "Lata 350ml"));
        lista.add(new Bebidas(8.0, "Refrigerante", "Cola", "Garrafa 1L"));
        lista.add(new Bebidas(8.0, "Refrigerante", "Guaraná", "Garrafa 1L"));
        
        // Água
        lista.add(new Bebidas(2.0, "Água Mineral", "Natural", "Garrafa 500ml"));
        lista.add(new Bebidas(3.0, "Água Mineral", "com Gás", "Garrafa 500ml"));
        
        return lista;
    }
}
