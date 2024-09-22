package appswing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import regras_negocio.Fachada;
import modelo.Correntista;

public class TelaCorrentista extends JFrame {
    private JTextArea areaListagem;
    private JTextField campoCpf, campoNome, campoSenha;
    private JButton botaoCriar;

    public TelaCorrentista() {
        setTitle("Gerenciar Correntistas");
        setSize(600, 400);  // Tamanho da tela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Painel de listagem de correntistas
        JPanel painelListagem = new JPanel(new BorderLayout());
        JLabel labelListagem = new JLabel("Lista dos Correntistas");
        labelListagem.setHorizontalAlignment(SwingConstants.LEFT);
        painelListagem.add(labelListagem, BorderLayout.NORTH);

        areaListagem = new JTextArea(10, 50);  // Aumentando o tamanho da área de texto
        areaListagem.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaListagem);
        painelListagem.add(scroll, BorderLayout.CENTER);
        add(painelListagem, BorderLayout.NORTH);

        // Painel de cadastro de correntista
        JPanel painelCadastro = new JPanel(new GridLayout(4, 2, 10, 10));
        painelCadastro.setBorder(BorderFactory.createTitledBorder("Cadastre Correntista"));

        JLabel labelCpf = new JLabel("CPF:");
        painelCadastro.add(labelCpf);
        campoCpf = new JTextField(20);  // Aumentando o campo de entrada
        painelCadastro.add(campoCpf);

        JLabel labelNome = new JLabel("Nome:");
        painelCadastro.add(labelNome);
        campoNome = new JTextField(20);  // Aumentando o campo de entrada
        painelCadastro.add(campoNome);

        JLabel labelSenha = new JLabel("Senha:");
        painelCadastro.add(labelSenha);
        campoSenha = new JTextField(20);  // Aumentando o campo de entrada
        painelCadastro.add(campoSenha);

        botaoCriar = new JButton("Criar Correntista");
        painelCadastro.add(new JLabel());  // Preenchendo o espaço vazio
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

    // Método para atualizar a listagem de correntistas usando o método da Fachada
    public void atualizarListagemCorrentistas() {
        try {
            ArrayList<Correntista> correntistas = Fachada.listarCorrentistas();
            areaListagem.setText("");
            for (Correntista correntista : correntistas) {
                areaListagem.append("CPF: " + correntista.getCpf() + " | Nome: " + correntista.getNome() + "\n");
            }
        } catch (Exception e) {
            areaListagem.setText("Erro ao listar correntistas: " + e.getMessage());
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
