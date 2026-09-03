package SERVICE;

import DAO.VacinaDAO;
import MODEL.Vacina;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Serviço responsável pelo controle de estoque das vacinas.
 *
 * Autor: Rafael da Silva Joaquim
 */
public class EstoqueService {

    private final VacinaDAO vacinaDAO;

    /**
     * Construtor.
     */
    public EstoqueService() {
        vacinaDAO = new VacinaDAO();
    }

    // ==========================================================
    // ENTRADA DE ESTOQUE
    // ==========================================================

    /**
     * Adiciona novas doses ao estoque.
     */
    public boolean adicionarEstoque(int idVacina, int quantidade) {

        if (quantidade <= 0) return false;

        Vacina vacina = vacinaDAO.buscarPorId(idVacina);

        if (vacina == null) {
            return false;
        }

        vacina.setQuantidadeEstoque(
                vacina.getQuantidadeEstoque() + quantidade
        );

        return vacinaDAO.atualizar(vacina);
    }

    // ==========================================================
    // SAÍDA DE ESTOQUE
    // ==========================================================

    /**
     * Remove doses do estoque.
     */
    public boolean retirarEstoque(int idVacina, int quantidade) {

        if (quantidade <= 0) return false;

        Vacina vacina = vacinaDAO.buscarPorId(idVacina);

        if (vacina == null) {
            return false;
        }

        if (vacina.getQuantidadeEstoque() < quantidade) {
            return false;
        }

        vacina.setQuantidadeEstoque(
                vacina.getQuantidadeEstoque() - quantidade
        );

        return vacinaDAO.atualizar(vacina);
    }

    // ==========================================================
    // CONSULTAR ESTOQUE
    // ==========================================================

    /**
     * Retorna a quantidade disponível em estoque.
     */
    public int consultarEstoque(int idVacina) {

        Vacina vacina = vacinaDAO.buscarPorId(idVacina);

        if (vacina == null) {
            return 0;
        }
        return vacina.getQuantidadeEstoque();
    }

    // ==========================================================
    // ESTOQUE BAIXO
    // ==========================================================

    /**
     * Verifica se a vacina está abaixo do estoque mínimo.
     */
    public boolean estoqueBaixo(int idVacina) {

        Vacina vacina = vacinaDAO.buscarPorId(idVacina);

        if (vacina == null) {
            return false;
        }
        return vacina.getQuantidadeEstoque() <= vacina.getEstoqueMinimo();
    }

    // ==========================================================
    // VACINA VENCIDA
    // ==========================================================
    /**
     * Verifica se a vacina está vencida.
     */
    public boolean vacinaVencida(int idVacina) {
        Vacina vacina = vacinaDAO.buscarPorId(idVacina);
        if (vacina == null) {
            return false;
        }
        return vacina.getValidade().isBefore(LocalDate.now());
    }

    // ==========================================================
    // PRÓXIMA DO VENCIMENTO
    // ==========================================================

    /**
     * Verifica se a vacina vence nos próximos 30 dias.
     */
    public boolean proximaDoVencimento(int idVacina) {
        Vacina vacina = vacinaDAO.buscarPorId(idVacina);
        if (vacina == null) {
            return false;
        }
        long dias = ChronoUnit.DAYS.between(LocalDate.now(), vacina.getValidade());
        return dias >= 0 && dias <= 30;
    }
}