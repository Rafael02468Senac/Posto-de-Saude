package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável pela conexão com o banco de dados MySQL.
 *
 * Sistema: Controle de Vacinas
 * Autor: Rafael da Silva Joaquim
 */
public class ConexaoMySQL {

    // ==========================================================
    // CONFIGURAÇÕES DO BANCO
    // ==========================================================
    private static final String URL = "jdbc:mysql://localhost:3307/loja_teste";
    private static final String USUARIO = "root";
    private static final String SENHA = "senacrs";

    // ==========================================================
    // CONECTAR
    // ==========================================================
    /**
     * Retorna uma conexão com o banco de dados.
     */
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    // ==========================================================
    // TESTAR CONEXÃO
    // ==========================================================
    /**
     * Método para testar a conexão com o banco.
     */
    public static boolean testarConexao() {
        try (Connection con = conectar()) {
            System.out.println("Conexão realizada com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco.");
            e.printStackTrace();
            return false;
        }
    }
}