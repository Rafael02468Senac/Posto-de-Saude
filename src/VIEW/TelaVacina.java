package VIEW;

import DAO.VacinaDAO;
import MODEL.Vacina;
import UTIL.Validacao;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.time.LocalDate;

public class TelaVacina extends JFrame {
    private JTextField campoNome, campoFabricante, campoLote, campoValidade, campoQuantidade, campoEstoqueMinimo;
    private final VacinaDAO vacinaDAO = new VacinaDAO();

    public TelaVacina(Connection conexao) { configurarTela(); }

    private void configurarTela() {
        setTitle("Cadastro de Vacinas");
        setSize(480, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel("Nome da vacina:")); campoNome = new JTextField(); add(campoNome);
        add(new JLabel("Fabricante:")); campoFabricante = new JTextField(); add(campoFabricante);
        add(new JLabel("Lote:")); campoLote = new JTextField(); add(campoLote);
        add(new JLabel("Validade (AAAA-MM-DD):")); campoValidade = new JTextField(); add(campoValidade);
        add(new JLabel("Quantidade:")); campoQuantidade = new JTextField(); add(campoQuantidade);
        add(new JLabel("Estoque mínimo:")); campoEstoqueMinimo = new JTextField(); add(campoEstoqueMinimo);
        JButton botaoSalvar = new JButton("Salvar"); add(botaoSalvar);

        Validacao.somenteNumeros(campoQuantidade, 9);
        Validacao.somenteNumeros(campoEstoqueMinimo, 9);
        botaoSalvar.addActionListener(e -> salvarVacina());
    }

    private void salvarVacina() {
        try {
            String nome = Validacao.textoObrigatorio(campoNome.getText(), "Nome da vacina");
            String fabricante = Validacao.textoObrigatorio(campoFabricante.getText(), "Fabricante");
            String lote = Validacao.textoObrigatorio(campoLote.getText(), "Lote");
            LocalDate validade = Validacao.data(campoValidade.getText(), "Validade");
            int quantidade = Validacao.inteiroNaoNegativo(campoQuantidade.getText(), "Quantidade");
            int minimo = Validacao.inteiroNaoNegativo(campoEstoqueMinimo.getText(), "Estoque mínimo");

            Vacina vacina = new Vacina(nome, fabricante, lote, validade, quantidade, minimo);
            if (!vacinaDAO.inserir(vacina)) throw new IllegalStateException("Não foi possível salvar a vacina.");

            JOptionPane.showMessageDialog(this, "Vacina cadastrada com sucesso!");
            limparCampos();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage(), "Dados inválidos", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limparCampos() {
        campoNome.setText(""); campoFabricante.setText(""); campoLote.setText(""); campoValidade.setText("");
        campoQuantidade.setText(""); campoEstoqueMinimo.setText("");
    }
}
