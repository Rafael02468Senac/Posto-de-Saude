package VIEW;

import DAO.PacienteDAO;
import MODEL.Paciente;
import UTIL.Validacao;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.time.LocalDate;

public class TelaPaciente extends JFrame {
    private JTextField campoCpf, campoNome, campoTelefone, campoNascimento;
    private final PacienteDAO pacienteDAO = new PacienteDAO();

    public TelaPaciente(Connection conexao) { configurarTela(); }

    private void configurarTela() {
        setTitle("Cadastro de Pacientes");
        setSize(440, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("CPF (11 números):")); campoCpf = new JTextField(); add(campoCpf);
        add(new JLabel("Nome:")); campoNome = new JTextField(); add(campoNome);
        add(new JLabel("Telefone (10 ou 11 números):")); campoTelefone = new JTextField(); add(campoTelefone);
        add(new JLabel("Nascimento (AAAA-MM-DD):")); campoNascimento = new JTextField(); add(campoNascimento);
        JButton botaoSalvar = new JButton("Salvar"); add(botaoSalvar);

        Validacao.somenteNumeros(campoCpf, 11);
        Validacao.somenteNumeros(campoTelefone, 11);
        botaoSalvar.addActionListener(e -> salvarPaciente());
    }

    private void salvarPaciente() {
        try {
            String cpf = Validacao.cpf(campoCpf.getText());
            String nome = Validacao.nome(campoNome.getText(), "Nome");
            String telefone = Validacao.telefone(campoTelefone.getText());
            LocalDate nascimento = Validacao.data(campoNascimento.getText(), "Data de nascimento");
            if (nascimento.isAfter(LocalDate.now())) throw new IllegalArgumentException("Data de nascimento não pode estar no futuro.");
            if (pacienteDAO.buscarPorCpf(cpf) != null) throw new IllegalArgumentException("Já existe um paciente cadastrado com este CPF.");

            if (!pacienteDAO.inserir(new Paciente(cpf, nome, telefone, nascimento)))
                throw new IllegalStateException("Não foi possível salvar o paciente.");

            JOptionPane.showMessageDialog(this, "Paciente cadastrado com sucesso!");
            limparCampos();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage(), "Dados inválidos", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limparCampos() {
        campoCpf.setText(""); campoNome.setText(""); campoTelefone.setText(""); campoNascimento.setText("");
    }
}
