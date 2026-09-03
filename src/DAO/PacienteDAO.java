package DAO;
import MODEL.Paciente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class PacienteDAO {

    // ==========================================================
    // INSERIR PACIENTE
    // ==========================================================
    public boolean inserir(Paciente paciente) {

        String sql = """
                INSERT INTO paciente
                (cpf, nome, telefone, data_nascimento)
                VALUES (?, ?, ?, ?)
                """;
        try (Connection con = ConexaoMySQL.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, paciente.getCpf());
            stmt.setString(2, paciente.getNome());
            stmt.setString(3, paciente.getTelefone());
            stmt.setDate(4, Date.valueOf(paciente.getDataNascimento()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir paciente.");
            e.printStackTrace();
            return false;
        }
    }

    // ==========================================================
    // LISTAR TODOS
    // ==========================================================
    public List<Paciente> listarTodos() {
        List<Paciente> lista = new ArrayList<>();

        String sql = """
                SELECT *
                FROM paciente
                ORDER BY nome
                """;
        try (Connection con = ConexaoMySQL.conectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setNome(rs.getString("nome"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                lista.add(paciente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pacientes.");
            e.printStackTrace();
        }
        return lista;
    }

    // ==========================================================
    // BUSCAR POR ID
    // ==========================================================
    public Paciente buscarPorId(int id) {
        String sql = """
                SELECT *
                FROM paciente
                WHERE id = ?
                """;
        try (Connection con = ConexaoMySQL.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setNome(rs.getString("nome"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                return paciente;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar paciente.");
            e.printStackTrace();
        }
        return null;
    }

    // ==========================================================
    // BUSCAR POR CPF
    // ==========================================================
    public Paciente buscarPorCpf(String cpf) {

        String sql = """
                SELECT *
                FROM paciente
                WHERE cpf = ?
                """;
        try (Connection con = ConexaoMySQL.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setNome(rs.getString("nome"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                return paciente;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar CPF.");
            e.printStackTrace();
        }
        return null;
    }

    // ==========================================================
    // ATUALIZAR
    // ==========================================================
    public boolean atualizar(Paciente paciente) {

        String sql = """
                UPDATE paciente
                SET
                    cpf = ?,
                    nome = ?,
                    telefone = ?,
                    data_nascimento = ?
                WHERE id = ?
                """;
        try (Connection con = ConexaoMySQL.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, paciente.getCpf());
            stmt.setString(2, paciente.getNome());
            stmt.setString(3, paciente.getTelefone());
            stmt.setDate(4, Date.valueOf(paciente.getDataNascimento()));
            stmt.setInt(5, paciente.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar paciente.");
            e.printStackTrace();
            return false;
        }
    }

    // ==========================================================
    // EXCLUIR
    // ==========================================================
    public boolean excluir(int id) {

        String sql = """
                DELETE FROM paciente
                WHERE id = ?
                """;
        try (Connection con = ConexaoMySQL.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir paciente.");
            e.printStackTrace();
            return false;
        }
    }
}