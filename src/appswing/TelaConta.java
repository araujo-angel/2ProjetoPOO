package appswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import regras_negocio.Fachada;
import modelo.Conta;
import modelo.ContaEspecial;

public class TelaConta extends JFrame {
    private JTextArea areaListagem;
    private JTextField campoCpfTitularSimples, campoCpfTitularEspecial, campoLimite;
    private JButton botaoCriarSimples, botaoCriarEspecial;

    public TelaConta() {
        setTitle("Gerenciar Contas");
        setSize(800, 400);  // Diminuindo a altura da tela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Painel de listagem de contas
        JPanel painelListagem = new JPanel(new BorderLayout());
        JLabel labelListagem = new JLabel("Lista das Contas");
        labelListagem.setHorizontalAlignment(SwingConstants.LEFT);
        painelListagem.add(labelListagem, BorderLayout.NORTH);

        areaListagem = new JTextArea(5, 50);  // Diminuindo a altura
        areaListagem.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaListagem);
        painelListagem.add(scroll, BorderLayout.CENTER);
        add(painelListagem, BorderLayout.CENTER);

        // Painel de cadastro de conta
        JPanel painelCadastro = new JPanel(new GridLayout(1, 2, 10, 10));
        painelCadastro.setBorder(BorderFactory.createTitledBorder("Cadastre Conta"));

        // Subpainel para Conta Simples
        JPanel painelSimples = new JPanel(new GridBagLayout());
        GridBagConstraints gbcSimples = new GridBagConstraints();
        gbcSimples.fill = GridBagConstraints.HORIZONTAL;

        gbcSimples.gridx = 0;
        gbcSimples.gridy = 0;
        painelSimples.add(new JLabel("CPF do Titular:"), gbcSimples);

        gbcSimples.gridx = 1;
        campoCpfTitularSimples = new JTextField(15);
        painelSimples.add(campoCpfTitularSimples, gbcSimples);

        gbcSimples.gridx = 0;
        gbcSimples.gridy = 1;
        gbcSimples.gridwidth = 2;
        botaoCriarSimples = new JButton("Criar Conta Simples");
        painelSimples.add(botaoCriarSimples, gbcSimples);

        // Subpainel para Conta Especial
        JPanel painelEspecial = new JPanel(new GridBagLayout());
        GridBagConstraints gbcEspecial = new GridBagConstraints();
        gbcEspecial.fill = GridBagConstraints.HORIZONTAL;

        gbcEspecial.gridx = 0;
        gbcEspecial.gridy = 0;
        painelEspecial.add(new JLabel("CPF do Titular:"), gbcEspecial);

        gbcEspecial.gridx = 1;
        campoCpfTitularEspecial = new JTextField(15);
        painelEspecial.add(campoCpfTitularEspecial, gbcEspecial);

        gbcEspecial.gridx = 0;
        gbcEspecial.gridy = 1;
        painelEspecial.add(new JLabel("Limite:"), gbcEspecial);

        gbcEspecial.gridx = 1;
        campoLimite = new JTextField(15);
        painelEspecial.add(campoLimite, gbcEspecial);

        gbcEspecial.gridx = 0;
        gbcEspecial.gridy = 2;
        gbcEspecial.gridwidth = 2;
        botaoCriarEspecial = new JButton("Criar Conta Especial");
        painelEspecial.add(botaoCriarEspecial, gbcEspecial);

        // Painel horizontal para unir os subpainéis
        JPanel painelCompleto = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1;
        painelCompleto.add(painelSimples, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0;
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        painelCompleto.add(separator, gbc);

        gbc.gridx = 2;
        gbc.weightx = 1;
        painelCompleto.add(painelEspecial, gbc);

        // Adicionando os painéis ao painel de cadastro
        painelCadastro.add(painelCompleto);
        add(painelCadastro, BorderLayout.SOUTH);

        // Ações dos botões de criar
        botaoCriarSimples.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarContaSimples();
            }
        });

        botaoCriarEspecial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarContaEspecial();
            }
        });

        // Atualizar a listagem de contas
        atualizarListagemContas();
    }

    // Método para atualizar a listagem de contas
    public void atualizarListagemContas() {
        try {
            ArrayList<Conta> contas = Fachada.listarContas();
            areaListagem.setText("");
            for (Conta conta : contas) {
                String info = "ID: " + conta.getId() + " | Saldo: " + conta.getSaldo();
                if (conta instanceof ContaEspecial) { // Verificando se é Conta Especial
                    info += " | Limite: " + ((ContaEspecial) conta).getLimite();
                }
                areaListagem.append(info + "\n");
            }
        } catch (Exception e) {
            areaListagem.setText("Erro ao listar contas: " + e.getMessage());
        }
    }

    // Método para criar conta simples
    private void criarContaSimples() {
        String cpfTitular = campoCpfTitularSimples.getText();

        try {
            Fachada.criarConta(cpfTitular);
            atualizarListagemContas();
            campoCpfTitularSimples.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(true);
    }

    // Método para criar conta especial
    private void criarContaEspecial() {
        String cpfTitular = campoCpfTitularEspecial.getText();
        double limite;

        try {
            limite = Double.parseDouble(campoLimite.getText());
            Fachada.criarContaEspecial(cpfTitular, limite);
            atualizarListagemContas();
            campoCpfTitularEspecial.setText("");
            campoLimite.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Limite inválido. Insira um valor numérico.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        TelaConta tela = new TelaConta();
        tela.setVisible(true);
    }
}
