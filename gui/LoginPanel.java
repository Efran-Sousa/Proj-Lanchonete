package gui;

import javax.swing.*;
import java.awt.*;
import model.Vendedor;
import service.VendedorService;

public class LoginPanel extends JPanel {
    private MainFrame mainFrame;
    private VendedorService vendedorService;
    
    private JTextField txtNome;
    private JTextField txtSalario;
    private JButton btnEntrar;
    
    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.vendedorService = new VendedorService();
        
        setLayout(new BorderLayout());
        
        // Painel de título
        JPanel titlePanel = new JPanel();
        JLabel lblTitle = new JLabel("Sistema de Lanchonete");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(lblTitle);
        
        // Painel de formulário
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Campo Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Nome do Vendedor:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        txtNome = new JTextField(20);
        formPanel.add(txtNome, gbc);
        
        // Campo Salário
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Salário Base:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        txtSalario = new JTextField(20);
        formPanel.add(txtSalario, gbc);
        
        // Botão Entrar
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        btnEntrar = new JButton("Entrar no Sistema");
        btnEntrar.addActionListener(e -> login());
        formPanel.add(btnEntrar, gbc);
        
        // Layout final
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }
    
    private void login() {
        try {
            String nome = txtNome.getText().trim();
            String salarioText = txtSalario.getText().trim();
            
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, digite o nome do vendedor.", 
                        "Campo Obrigatório", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            double salario;
            try {
                salario = Double.parseDouble(salarioText.replace(",", "."));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, digite um valor numérico válido para o salário.", 
                        "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Vendedor vendedor = vendedorService.criarVendedor(nome, salario);
            mainFrame.setVendedor(vendedor);
            
            JOptionPane.showMessageDialog(this, "Bem-vindo(a), " + nome + "!", 
                    "Login Efetuado", JOptionPane.INFORMATION_MESSAGE);
                    
            mainFrame.showPanel("menu");
            
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
