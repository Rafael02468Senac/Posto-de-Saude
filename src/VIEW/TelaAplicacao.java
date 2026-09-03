package VIEW;

import DAO.PacienteDAO;
import DAO.VacinaDAO;
import MODEL.Aplicacao;
import MODEL.Paciente;
import MODEL.Vacina;
import SERVICE.AplicacaoService;
import UTIL.Validacao;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class TelaAplicacao extends JFrame {
    private JTextField campoCpfPaciente, campoNomeVacina, campoProfissional, campoObservacao;
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final VacinaDAO vacinaDAO = new VacinaDAO();
    private final AplicacaoService aplicacaoService = new AplicacaoService();

    public TelaAplicacao(Connection conexao) { configurarTela(); }

    private void configurarTela() {
        setTitle("Aplicação de Vacina");
        setSize(500, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("CPF do paciente:")); campoCpfPaciente = new JTextField(); add(campoCpfPaciente);
        add(new JLabel("Nome da vacina:")); campoNomeVacina = new JTextField(); add(campoNomeVacina);
        add(new JLabel("Profissional:")); campoProfissional = new JTextField(); add(campoProfissional);
        add(new JLabel("Observação:")); campoObservacao = new JTextField(); add(campoObservacao);
        JButton botaoAplicar = new JButton("Aplicar Vacina"); add(botaoAplicar);

        Validacao.somenteNumeros(campoCpfPaciente, 11);
        botaoAplicar.addActionListener(e -> aplicarVacina());
    }

    private void aplicarVacina() {
        try {
            String cpf = Validacao.cpf(campoCpfPaciente.getText());
            Paciente paciente = pacienteDAO.buscarPorCpf(cpf);
            if (paciente == null) throw new IllegalArgumentException("Paciente não encontrado.");

            String nomeVacina = Validacao.textoObrigatorio(campoNomeVacina.getText(), "Nome da vacina");
            List<Vacina> vacinas = vacinaDAO.buscarPorNome(nomeVacina);
            Vacina vacina = vacinas.stream().filter(v -> v.getNome().equalsIgnoreCase(nomeVacina)).findFirst()
                    .orElse(vacinas.size() == 1 ? vacinas.get(0) : null);
            if (vacina == null) throw new IllegalArgumentException("Vacina não encontrada ou nome ambíguo.");

            String profissional = Validacao.nome(campoProfissional.getText(), "Profissional");
            Aplicacao aplicacao = new Aplicacao(paciente, vacina, LocalDate.now(), profissional, campoObservacao.getText().trim());
            if (!aplicacaoService.registrarAplicacao(aplicacao))
                throw new IllegalStateException("Aplicação não realizada. Verifique validade e estoque da vacina.");

            JOptionPane.showMessageDialog(this, "Vacina aplicada com sucesso!");
            limparCampos();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage(), "Não foi possível aplicar", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limparCampos() {
        campoCpfPaciente.setText(""); campoNomeVacina.setText(""); campoProfissional.setText(""); campoObservacao.setText("");
    }
}
