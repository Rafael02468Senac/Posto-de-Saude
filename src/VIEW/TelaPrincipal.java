package VIEW;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

/**
 * Tela principal do Sistema Controle de Vacinas.
 */
public class TelaPrincipal extends JFrame {

    private JButton botaoPaciente;

    private JButton botaoVacina;

    private JButton botaoAplicacao;

    private Connection conexao;

    public TelaPrincipal(Connection conexao) {
        this.conexao = conexao;
        configurarTela();
    }

    private void configurarTela() {
        setTitle("Controle das Vacinas");

        setSize(400, 300);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(4,1,10,10));

        JLabel titulo = new JLabel("Sistema Controle de Vacinas", SwingConstants.CENTER);
        add(titulo);

        botaoPaciente = new JButton("Cadastro de Pacientes");
        add(botaoPaciente);

        botaoVacina = new JButton("Cadastro de Vacinas");
        add(botaoVacina);

        botaoAplicacao = new JButton("Aplicação de Vacina");
        add(botaoAplicacao);

        // Eventos dos botões:
        botaoPaciente.addActionListener(e -> abrirPaciente());
        botaoVacina.addActionListener(e -> abrirVacina());
        botaoAplicacao.addActionListener(e -> abrirAplicacao());
    }

    private void abrirPaciente() {
        TelaPaciente tela = new TelaPaciente(conexao);
        tela.setVisible(true);
    }

    private void abrirVacina() {
        TelaVacina tela = new TelaVacina(conexao);
        tela.setVisible(true);
    }

    private void abrirAplicacao() {
        TelaAplicacao tela = new TelaAplicacao(conexao);
        tela.setVisible(true);
    }
}