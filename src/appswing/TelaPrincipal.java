package appswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Sistema Financeiro");
        setSize(600, 400); // Tamanho inicial da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Definir layout principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Usando GridBagLayout para controle preciso
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os botões
        
        // Criar botões
        JButton btnCorrentista = new JButton("Correntista");
        JButton btnConta = new JButton("Conta");
        JButton btnCaixa = new JButton("Caixa");
        
        // Definir tamanho dos botões (40% da largura da tela principal)
        Dimension buttonSize = new Dimension((int)(getWidth() * 0.4), 50);
        btnCorrentista.setPreferredSize(buttonSize);
        btnConta.setPreferredSize(buttonSize);
        btnCaixa.setPreferredSize(buttonSize);
        
        // Adicionar botões ao painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(btnCorrentista, gbc);
        
        gbc.gridy = 1;
        panel.add(btnConta, gbc);
        
        gbc.gridy = 2;
        panel.add(btnCaixa, gbc);
        
        // Adicionar painel centralizado à tela principal
        add(panel, BorderLayout.CENTER);
        
        // Ações dos botões
        btnCorrentista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre a TelaCorrentista
                new TelaCorrentista().setVisible(true); // Assegure-se de chamar setVisible aqui
            }
        });
        
        btnConta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre a TelaConta
                new TelaConta().setVisible(true); // Assegure-se de chamar setVisible aqui
            }
        });
        
        btnCaixa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre a TelaCaixa
                new TelaCaixa().setVisible(true); // Assegure-se de chamar setVisible aqui
            }
        });
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new TelaPrincipal();
    }
}
