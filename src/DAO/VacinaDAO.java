package DAO;
import MODEL.Vacina;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class VacinaDAO {

    // ==========================================================
    // INSERIR
    // ==========================================================
    public boolean inserir(Vacina vacina) {

        String sql = """
                INSERT INTO vacina
                (nome, fabricante, lote, validade,
                 quantidade_estoque, estoque_minimo)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try (Connection con = ConexaoMySQL.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, vacina.getNome());
            stmt.setString(2, vacina.getFabricante());
            stmt.setString(3, vacina.getLote());
            stmt.setDate(4, Date.valueOf(vacina.getValidade()));
            stmt.setInt(5, vacina.getQuantidadeEstoque());
            stmt.setInt(6, vacina.getEstoqueMinimo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir vacina.");
            e.printStackTrace();
            return false;
        }
    }

    // ==========================================================
    // LISTAR TODAS
    // ==========================================================
    public List<Vacina> listarTodas() {
        List<Vacina> lista = new ArrayList<>();

        String sql = """
                SELECT *
                FROM vacina
                ORDER BY nome
                """;
        try (Connection con = ConexaoMySQL.conectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vacina vacina = new Vacina();
                vacina.setId(rs.getInt("id"));
                vacina.setNome(rs.getString("nome"));
                vacina.setFabricante(rs.getString("fabricante"));
                vacina.setLote(rs.getString("lote"));
                vacina.setValidade(rs.getDate("validade").toLocalDate());
                vacina.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                vacina.setEstoqueMinimo(rs.getInt("estoque_minimo"));
                lista.add(vacina);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar vacinas.");
            e.printStackTrace();
        }
        return lista;
    }

    // ==========================================================
    // BUSCAR POR ID
    // ==========================================================
    public Vacina buscarPorId(int id) {

        String sql = """
                SELECT *
                FROM vacina
                WHERE id = ?
                """;
        try (Connection con = ConexaoMySQL.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Vacina vacina = new Vacina();
                vacina.setId(rs.getInt("id"));
                vacina.setNome(rs.getString("nome"));
                vacina.setFabricante(rs.getString("fabricante"));
                vacina.setLote(rs.getString("lote"));
                vacina.setValidade(rs.getDate("validade").toLocalDate());
                vacina.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                vacina.setEstoqueMinimo(rs.getInt("estoque_minimo"));
                return vacina;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar vacina.");
            e.printStackTrace();
        }
        return null;
    }

    // ==========================================================
    // BUSCAR POR NOME
    // ==========================================================
    public List<Vacina> buscarPorNome(String nome) {
        List<Vacina> lista = new ArrayList<>();
        String sql = """
                SELECT *
                FROM vacina
                WHERE nome LIKE ?
                ORDER BY nome
                """;
        try (Connection con = ConexaoMySQL.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vacina vacina = new Vacina();
                vacina.setId(rs.getInt("id"));
                vacina.setNome(rs.getString("nome"));
                vacina.setFabricante(rs.getString("fabricante"));
                vacina.setLote(rs.getString("lote"));
                vacina.setValidade(rs.getDate("validade").toLocalDate());
                vacina.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                vacina.setEstoqueMinimo(rs.getInt("estoque_minimo"));
                lista.add(vacina);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar vacina.");
            e.printStackTrace();
        }
        return lista;
    }

    // ==========================================================
    // ATUALIZAR
    // ==========================================================
    public boolean atualizar(Vacina vacina) {

        String sql = """
                UPDATE vacina
                SET
                    nome = ?,
                    fabricante = ?,
                    lote = ?,
                    validade = ?,
                    quantidade_estoque = ?,
                    estoque_minimo = ?
                WHERE id = ?
                """;
        try (Connection con = ConexaoMySQL.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, vacina.getNome());
            stmt.setString(2, vacina.getFabricante());
            stmt.setString(3, vacina.getLote());
            stmt.setDate(4, Date.valueOf(vacina.getValidade()));
            stmt.setInt(5, vacina.getQuantidadeEstoque());
            stmt.setInt(6, vacina.getEstoqueMinimo());
            stmt.setInt(7, vacina.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar vacina.");
            e.printStackTrace();
            return false;
        }
    }

    // ==========================================================
    // EXCLUIR
    // ==========================================================
    public boolean excluir(int id) {

        String sql = """
                DELETE FROM vacina
                WHERE id = ?
                """;
        try (Connection con = ConexaoMySQL.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir vacina.");
            e.printStackTrace();
            return false;
        }
    }

    // ==========================================================
    // ALTERAR ESTOQUE
    // ==========================================================
    public boolean atualizarEstoque(int id, int novaQuantidade) {

        String sql = """
                UPDATE vacina
                SET quantidade_estoque = ?
                WHERE id = ?
                """;
        try (Connection con = ConexaoMySQL.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, novaQuantidade);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar estoque.");
            e.printStackTrace();
            return false;
        }
    }
}