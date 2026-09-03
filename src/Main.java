import DAO.ConexaoMySQL;
import VIEW.TelaPrincipal;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

/** Classe principal do Sistema Controle de Vacinas. */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Connection conexao = ConexaoMySQL.conectar();
                TelaPrincipal tela = new TelaPrincipal(conexao);
                tela.setVisible(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Não foi possível conectar ao banco de dados.\n" + e.getMessage(),
                        "Erro de conexão", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
