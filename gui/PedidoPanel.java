package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import model.ItemPedido;
import model.Pedido;
import service.PedidoService;
import service.VendedorService;

public class PedidoPanel extends JPanel {
    private MainFrame mainFrame;
    private PedidoService pedidoService;
    private VendedorService vendedorService;
    
    private DefaultTableModel tableModel;
    private JTable tblItens;
    private JLabel lblTotal;
    private JTextField txtValorPago;
    
    public PedidoPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.pedidoService = new PedidoService();
        this.vendedorService = new VendedorService();
        
        setLayout(new BorderLayout());
        
        // Painel do título
        JPanel titlePanel = new JPanel();
        JLabel lblTitle = new JLabel("Pedido Atual");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(lblTitle);
        
        // Painel para exibição do cliente
        JPanel clientePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clientePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        JLabel lblCliente = new JLabel("Cliente: [Nenhum pedido iniciado]");
        lblCliente.setFont(new Font("Arial", Font.PLAIN, 14));
        clientePanel.add(lblCliente);
        
        // Tabela de itens
        String[] colunas = {"Item", "Descrição", "Preço (R$)"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Impede edição das células
            }
        };
        tblItens = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblItens);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        
        // Painel para o total
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblTotal = new JLabel("Total: R$ 0,00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        totalPanel.add(lblTotal);
        
        // Painel para pagamento
        JPanel pagamentoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pagamentoPanel.add(new JLabel("Valor Pago: R$ "));
        txtValorPago = new JTextField(10);
        pagamentoPanel.add(txtValorPago);
        
        JButton btnFinalizar = new JButton("Finalizar Pedido");
        btnFinalizar.addActionListener(e -> finalizarPedido());
        pagamentoPanel.add(btnFinalizar);
        
        // Painel de botões
        JPanel buttonPanel = new JPanel();
        JButton btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.addActionListener(e -> {
            mainFrame.showPanel("menu");
            atualizarTela(); // Para quando voltar, os dados serem recarregados
        });
        buttonPanel.add(btnVoltar);
        
        // Painel central
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(clientePanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(totalPanel, BorderLayout.SOUTH);
        
        // Painel inferior combinado (pagamento + botões)
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(pagamentoPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Adiciona os painéis ao layout
        add(titlePanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    // Método para atualizar a exibição do pedido
    public void atualizarTela() {
        // Limpa a tabela
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        
        Pedido pedido = mainFrame.getPedidoAtual();
        double total = 0;
        
        if (pedido != null) {
            // Atualiza o nome do cliente
            JPanel clientePanel = (JPanel) ((JPanel) getComponent(1)).getComponent(0);
            JLabel lblCliente = (JLabel) clientePanel.getComponent(0);
            lblCliente.setText("Cliente: " + pedido.getNomeCliente());
            
            // Preenche a tabela com os itens
            int i = 1;
            for (ItemPedido item : pedido.getItensConsumidos()) {
                Object[] row = {
                    "Item " + i,
                    item.descricao(),
                    String.format("%.2f", item.getPrecoVenda())
                };
                tableModel.addRow(row);
                total += item.getPrecoVenda();
                i++;
            }
            
            // Atualiza o total
            lblTotal.setText(String.format("Total: R$ %.2f", total));
        } else {
            lblTotal.setText("Total: R$ 0,00");
        }
    }
    
    private void finalizarPedido() {
        Pedido pedido = mainFrame.getPedidoAtual();
        
        if (pedido == null) {
            JOptionPane.showMessageDialog(this, 
                "Não há pedido em andamento.",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (pedido.getItensConsumidos().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "O pedido não possui itens.",
                "Pedido Vazio", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            double valorPago = Double.parseDouble(txtValorPago.getText().replace(",", "."));
            double total = pedido.calcularTotal();
            
            if (valorPago < total) {
                JOptionPane.showMessageDialog(this,
                    "O valor pago é insuficiente. Total: R$ " + String.format("%.2f", total),
                    "Valor Insuficiente", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Calcular o troco e o bônus do vendedor
            double troco = valorPago - total;
            double bonus = mainFrame.getVendedor().calcularBonus(total);
            
            // Mostrar resultado
            JOptionPane.showMessageDialog(this,
                "Pedido finalizado com sucesso!\n\n" +
                "Total: R$ " + String.format("%.2f", total) + "\n" +
                "Valor pago: R$ " + String.format("%.2f", valorPago) + "\n" +
                "Troco: R$ " + String.format("%.2f", troco) + "\n" +
                "Bônus do vendedor: R$ " + String.format("%.2f", bonus),
                "Pedido Finalizado", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpar o pedido atual
            mainFrame.setPedidoAtual(null);
            atualizarTela();
            txtValorPago.setText("");
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Por favor, digite um valor numérico válido.",
                "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }
}
