package appswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import regras_negocio.Fachada;
import modelo.Correntista;
import modelo.Conta;

public class TelaCorrentista extends JFrame {
    private JPanel painelListagem;
    private JTextField campoCpf, campoNome, campoSenha;
    private JButton botaoCriar;

    public TelaCorrentista() {
        setTitle("Gerenciar Correntistas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Painel de listagem de correntistas
        painelListagem = new JPanel();
        painelListagem.setLayout(new BoxLayout(painelListagem, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(painelListagem);
        add(scroll, BorderLayout.NORTH);

        // Painel de cadastro de correntista
        JPanel painelCadastro = new JPanel(new GridLayout(4, 2, 10, 10));
        painelCadastro.setBorder(BorderFactory.createTitledBorder("Cadastre Correntista"));

        JLabel labelCpf = new JLabel("CPF:");
        painelCadastro.add(labelCpf);
        campoCpf = new JTextField(20);
        painelCadastro.add(campoCpf);

        JLabel labelNome = new JLabel("Nome:");
        painelCadastro.add(labelNome);
        campoNome = new JTextField(20);
        painelCadastro.add(campoNome);

        JLabel labelSenha = new JLabel("Senha:");
        painelCadastro.add(labelSenha);
        campoSenha = new JTextField(20);
        painelCadastro.add(campoSenha);

        botaoCriar = new JButton("Criar Correntista");
        painelCadastro.add(new JLabel());
        painelCadastro.add(botaoCriar);
        add(painelCadastro, BorderLayout.CENTER);

        // Ação do botão criar
        botaoCriar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarCorrentista();
            }
        });

        // Atualizar a listagem de correntistas
        atualizarListagemCorrentistas();
    }

    // Método para atualizar a listagem de correntistas
    public void atualizarListagemCorrentistas() {
        painelListagem.removeAll();  // Limpar listagem anterior

        try {
            ArrayList<Correntista> correntistas = Fachada.listarCorrentistas();
            for (Correntista correntista : correntistas) {
                JPanel painelCorrentista = new JPanel(new BorderLayout(10, 10));
                JLabel labelCorrentista = new JLabel("CPF: " + correntista.getCpf() + " | Nome: " + correntista.getNome());
                painelCorrentista.add(labelCorrentista, BorderLayout.CENTER);

                JButton botaoListarContas = new JButton("Listar Contas");
                botaoListarContas.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        listarContasCorrentista(correntista);
                    }
                });
                painelCorrentista.add(botaoListarContas, BorderLayout.EAST);

                painelListagem.add(painelCorrentista);
            }
        } catch (Exception e) {
            JLabel erro = new JLabel("Erro ao listar correntistas: " + e.getMessage());
            painelListagem.add(erro);
        }

        painelListagem.revalidate();
        painelListagem.repaint();
    }

 // Método para listar as contas de um correntista específico
    private void listarContasCorrentista(Correntista correntista) {
        try {
         
            ArrayList<Conta> contas = Fachada.listarContas();
            StringBuilder contasListagem = new StringBuilder();
            contasListagem.append("Contas do Correntista ").append(correntista.getNome()).append(":\n");

            // Iterar pelas contas e verificar se o correntista está vinculado à conta
            for (Conta conta : contas) {
                if (conta.getCorrentistas().contains(correntista)) {
                    contasListagem.append("Conta ID: ").append(conta.getId())
                        .append(" | Saldo: ").append(conta.getSaldo()).append("\n");
                }
            }

            // Caso o correntista não tenha contas
            if (contasListagem.length() == 0) {
                contasListagem.append("Nenhuma conta encontrada para o correntista.");
            }

            // Exibir as contas vinculadas ao correntista em uma janela de diálogo
            JOptionPane.showMessageDialog(this, contasListagem.toString(), "Contas do Correntista", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao listar contas: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Simulação de criação de correntista
    private void criarCorrentista() {
        String cpf = campoCpf.getText();
        String nome = campoNome.getText();
        String senha = campoSenha.getText();

        try {
            Fachada.criarCorrentista(cpf, nome, senha);
            atualizarListagemCorrentistas();  // Atualizar a listagem após criar o correntista

            // Limpar os campos de entrada
            campoCpf.setText("");
            campoNome.setText("");
            campoSenha.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(true);
    }

    public static void main(String[] args) {
        TelaCorrentista tela = new TelaCorrentista();
        tela.setVisible(true);
    }
}
