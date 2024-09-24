package appswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import regras_negocio.Fachada;

public class TelaCaixa extends JFrame {
    public TelaCaixa() {
    	setTitle("Gerenciar Contas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true); // Tornar a tela visível
        
        // Definir layout da tela
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 5, 5));  // Ajustando o espaço entre os componentes
        
        // Campos de entrada com tamanhos menores
        JTextField txtId = new JTextField(8);
        JTextField txtCpf = new JTextField(8);
        JTextField txtSenha = new JTextField(4);
        JTextField txtValor = new JTextField(8);
        JTextField txtIdDestino = new JTextField(8);
        
        // Botões para as operações
        JButton btnCreditar = new JButton("Creditar");
        JButton btnDebitar = new JButton("Debitar");
        JButton btnTransferir = new JButton("Transferir");
        
        // Adicionar campos e labels ao painel
        panel.add(new JLabel("ID Conta:"));
        panel.add(txtId);
        panel.add(new JLabel("CPF:"));
        panel.add(txtCpf);
        panel.add(new JLabel("Senha:"));
        panel.add(txtSenha);
        panel.add(new JLabel("Valor:"));
        panel.add(txtValor);
        panel.add(new JLabel("ID Conta Destino:"));
        panel.add(txtIdDestino);
        
        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnCreditar);
        buttonPanel.add(btnDebitar);
        buttonPanel.add(btnTransferir);
        
        // Adicionar espaçamento nas 4 laterais da tela
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Adicionando margem
        
        // Adicionar painéis à tela
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Adicionar o painel principal à janela
        add(mainPanel);
        
        // Ações para os botões
        btnCreditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    String cpf = txtCpf.getText();
                    String senha = txtSenha.getText();
                    double valor = Double.parseDouble(txtValor.getText());
                    
                    Fachada.creditarValor(id, cpf, senha, valor);
                    mostrarMensagem("Valor de R$ " + valor + " creditado na conta " + id + " com sucesso.");
                    
                    // Limpar campos
                    limparCampos(txtId, txtCpf, txtSenha, txtValor, txtIdDestino);
                } catch (Exception ex) {
                    mostrarMensagem(ex.getMessage());
                }
            }
        });
        
        btnDebitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    String cpf = txtCpf.getText();
                    String senha = txtSenha.getText();
                    double valor = Double.parseDouble(txtValor.getText());
                    
                    Fachada.debitarValor(id, cpf, senha, valor);
                    mostrarMensagem("Valor de R$ " + valor + " debitado da conta " + id + " com sucesso.");
                    
                    // Limpar campos
                    limparCampos(txtId, txtCpf, txtSenha, txtValor, txtIdDestino);
                } catch (Exception ex) {
                    mostrarMensagem(ex.getMessage());
                }
            }
        });
        
        btnTransferir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    String cpf = txtCpf.getText();
                    String senha = txtSenha.getText();
                    double valor = Double.parseDouble(txtValor.getText());
                    int idDestino = Integer.parseInt(txtIdDestino.getText());
                    
                    Fachada.transferirValor(id, cpf, senha, valor, idDestino);
                    mostrarMensagem("Valor de R$ " + valor + " transferido da conta " + id + " para a conta " + idDestino + " com sucesso.");
                    
                    // Limpar campos
                    limparCampos(txtId, txtCpf, txtSenha, txtValor, txtIdDestino);
                } catch (Exception ex) {
                    mostrarMensagem(ex.getMessage());
                }
            }
        });
        
        setVisible(true);
    }
    
    private void mostrarMensagem(String mensagem) {
        // Criar um painel com um JLabel para a mensagem
        JPanel panel = new JPanel();
        panel.add(new JLabel(mensagem));
        JOptionPane.showMessageDialog(this, panel, "Informação", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limparCampos(JTextField... campos) {
        for (JTextField campo : campos) {
            campo.setText("");
        }
    }
    
    public static void main(String[] args) {
        new TelaCaixa();
    }
}
