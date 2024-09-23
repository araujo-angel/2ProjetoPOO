package appswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import repositorio.Repositorio;
import regras_negocio.Fachada;
import modelo.Conta;
import modelo.ContaEspecial;

public class TelaConta extends JFrame {
    private JPanel painelListagem;
    private JTextField campoCpfTitularSimples, campoCpfTitularEspecial, campoLimite;
    private JButton botaoCriarSimples, botaoCriarEspecial;

    public TelaConta() {
        setTitle("Gerenciar Contas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Painel de listagem de contas com título
        painelListagem = new JPanel();
        painelListagem.setLayout(new BoxLayout(painelListagem, BoxLayout.Y_AXIS));

        // Adiciona título "Lista de Contas"
        JLabel labelTitulo = new JLabel("Lista de Contas");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelListagem.add(labelTitulo);

        // Adiciona um painel scrollável para a lista de contas
        JScrollPane scroll = new JScrollPane(painelListagem);
        add(scroll, BorderLayout.CENTER);

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
        painelListagem.removeAll(); // Limpar a lista antes de atualizar
        painelListagem.add(Box.createVerticalStrut(10)); // Adicionando espaçamento inicial
        try {
            ArrayList<Conta> contas = Fachada.listarContas();
            for (Conta conta : contas) {
                JPanel painelConta = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));  // Layout horizontal com espaçamento reduzido
                painelConta.setAlignmentX(Component.LEFT_ALIGNMENT);  // Justificando o painel à esquerda

                String info;
                if (conta instanceof ContaEspecial) {
                    info = "Conta Especial | ID: " + conta.getId() + " | Saldo: " + conta.getSaldo() + " | Limite: " + ((ContaEspecial) conta).getLimite();
                } else {
                    info = "Conta Simples | ID: " + conta.getId() + " | Saldo: " + conta.getSaldo();
                }

                JLabel labelConta = new JLabel(info);
                painelConta.add(labelConta);

                // Botão adicionar cotitular
                JButton botaoAdicionarCotitular = new JButton("Adicionar Cotitular");
                botaoAdicionarCotitular.setPreferredSize(new Dimension(160, 25)); // Ajustando o tamanho dos botões
                botaoAdicionarCotitular.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        adicionarCotitular(conta);
                    }
                });
                painelConta.add(botaoAdicionarCotitular);

                // Botão remover cotitular
                JButton botaoRemoverCotitular = new JButton("Remover Cotitular");
                botaoRemoverCotitular.setPreferredSize(new Dimension(160, 25)); // Ajustando o tamanho dos botões
                botaoRemoverCotitular.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        removerCotitular(conta);
                    }
                });
                painelConta.add(botaoRemoverCotitular);

                // Botão apagar conta
                JButton botaoApagarConta = new JButton("Apagar Conta");
                botaoApagarConta.setPreferredSize(new Dimension(160, 25)); // Ajustando o tamanho dos botões
                botaoApagarConta.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        apagarConta(conta);
                    }
                });
                painelConta.add(botaoApagarConta);

                painelListagem.add(painelConta);  // Adicionando o painel da conta à listagem
                painelListagem.add(Box.createVerticalStrut(60));  // Diminuindo o espaço entre as contas
            }
            painelListagem.revalidate();
            painelListagem.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao listar contas: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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

    // Método para adicionar cotitular
    private void adicionarCotitular(Conta conta) {
        String cpf = JOptionPane.showInputDialog(this, "Digite o CPF do cotitular:");
        if (cpf != null && !cpf.isEmpty()) {
            try {
                Fachada.inserirCorrentistaConta(conta.getId(), cpf);
                atualizarListagemContas();
                JOptionPane.showMessageDialog(this, "Cotitular adicionado com sucesso!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para remover cotitular
    private void removerCotitular(Conta conta) {
        String cpf = JOptionPane.showInputDialog(this, "Digite o CPF do cotitular a ser removido:");
        if (cpf != null && !cpf.isEmpty()) {
            try {
                Fachada.removerCorrentistaConta(conta.getId(), cpf);
                atualizarListagemContas();
                JOptionPane.showMessageDialog(this, "Cotitular removido com sucesso!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para apagar conta
    private void apagarConta(Conta conta) {
        int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja apagar esta conta?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            try {
                Fachada.apagarConta(conta.getId());
                atualizarListagemContas();
                JOptionPane.showMessageDialog(this, "Conta apagada com sucesso!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        TelaConta tela = new TelaConta();
        tela.setVisible(true);
    }
}
