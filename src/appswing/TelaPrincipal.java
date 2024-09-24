package appswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {
    public TelaPrincipal() {
        // Configuração da janela principal
        setTitle("Sistema Financeiro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10)); // Layout de grade com 4 linhas

        // Botão para gerenciar contas
        JButton btnGerenciarContas = new JButton("Gerenciar Contas");
        btnGerenciarContas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaConta(); // Chama a tela de gerenciamento de contas
            }
        });

        // Botão para gerenciar correntistas
        JButton btnGerenciarCorrentistas = new JButton("Gerenciar Correntistas");
        btnGerenciarCorrentistas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCorrentista(); // Chama a tela de gerenciamento de correntistas
            }
        });

        // Botão para abrir a tela de operações de caixa
        JButton btnOperacoesCaixa = new JButton("Operações de Caixa");
        btnOperacoesCaixa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCaixa(); // Abre a tela de operações de caixa
            }
        });

        // Botão para sair do sistema
        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Fecha o aplicativo
            }
        });

        // Adicionar os botões à tela
        add(btnGerenciarContas);
        add(btnGerenciarCorrentistas); // Adicionando o botão de gerenciar correntistas
        add(btnOperacoesCaixa);
        add(btnSair);

        // Tornar a tela visível
        setVisible(true);
    }

    public static void main(String[] args) {
        // Executa a tela principal
        SwingUtilities.invokeLater(() -> new TelaPrincipal());
    }
}
