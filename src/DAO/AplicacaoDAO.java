package DAO;
import MODEL.Aplicacao;
import MODEL.Paciente;
import MODEL.Vacina;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class AplicacaoDAO {

    // ==========================================================
    // INSERIR APLICAÇÃO
    // ==========================================================
    public boolean inserir(Aplicacao aplicacao) {

        String sql = """
                INSERT INTO aplicacao
                (paciente_id,
                 vacina_id,
                 data_aplicacao,
                 profissional,
                 observacao)

                VALUES (?, ?, ?, ?, ?)
                """;
        try (Connection con = ConexaoMySQL.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, aplicacao.getPaciente().getId());
            stmt.setInt(2, aplicacao.getVacina().getId());
            stmt.setDate(3, Date.valueOf(aplicacao.getDataAplicacao()));
            stmt.setString(4, aplicacao.getProfissional());
            stmt.setString(5, aplicacao.getObservacao());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao registrar aplicação.");
            e.printStackTrace();
            return false;
        }
    }

    // ==========================================================
    // LISTAR TODAS AS APLICAÇÕES
    // ==========================================================

    public List<Aplicacao> listarTodas() {
        List<Aplicacao> lista = new ArrayList<>();

        String sql = """
                SELECT
                    a.id,
                    a.data_aplicacao,
                    a.profissional,
                    a.observacao,

                    p.id AS paciente_id,
                    p.nome AS paciente_nome,

                    v.id AS vacina_id,
                    v.nome AS vacina_nome

                FROM aplicacao a

                INNER JOIN paciente p
                    ON p.id = a.paciente_id

                INNER JOIN vacina v
                    ON v.id = a.vacina_id

                ORDER BY a.data_aplicacao DESC
                """;

        try (Connection con = ConexaoMySQL.conectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("paciente_id"));
                paciente.setNome(rs.getString("paciente_nome"));
                Vacina vacina = new Vacina();
                vacina.setId(rs.getInt("vacina_id"));
                vacina.setNome(rs.getString("vacina_nome"));
                Aplicacao aplicacao = new Aplicacao();
                aplicacao.setId(rs.getInt("id"));
                aplicacao.setPaciente(paciente);
                aplicacao.setVacina(vacina);
                aplicacao.setDataAplicacao(rs.getDate("data_aplicacao").toLocalDate());
                aplicacao.setProfissional(rs.getString("profissional"));
                aplicacao.setObservacao(rs.getString("observacao"));
                lista.add(aplicacao);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar aplicações.");
            e.printStackTrace();
        }
        return lista;
    }

    // ==========================================================
    // BUSCAR POR ID
    // ==========================================================
    public Aplicacao buscarPorId(int id) {

        String sql = """
                SELECT
                    a.id,
                    a.data_aplicacao,
                    a.profissional,
                    a.observacao,

                    p.id AS paciente_id,
                    p.nome AS paciente_nome,

                    v.id AS vacina_id,
                    v.nome AS vacina_nome

                FROM aplicacao a

                INNER JOIN paciente p
                    ON p.id = a.paciente_id

                INNER JOIN vacina v
                    ON v.id = a.vacina_id

                WHERE a.id = ?
                """;

        try (Connection con = ConexaoMySQL.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("paciente_id"));
                paciente.setNome(rs.getString("paciente_nome"));
                Vacina vacina = new Vacina();
                vacina.setId(rs.getInt("vacina_id"));
                vacina.setNome(rs.getString("vacina_nome"));
                Aplicacao aplicacao = new Aplicacao();
                aplicacao.setId(rs.getInt("id"));
                aplicacao.setPaciente(paciente);
                aplicacao.setVacina(vacina);
                aplicacao.setDataAplicacao(rs.getDate("data_aplicacao").toLocalDate());
                aplicacao.setProfissional(rs.getString("profissional"));
                aplicacao.setObservacao(rs.getString("observacao"));
                return aplicacao;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar aplicação.");
            e.printStackTrace();
        }
        return null;
    }

    // ==========================================================
    // EXCLUIR
    // ==========================================================
    public boolean excluir(int id) {

        String sql = """
                DELETE FROM aplicacao
                WHERE id = ?
                """;
        try (Connection con = ConexaoMySQL.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir aplicação.");
            e.printStackTrace();
            return false;
        }
    }
}