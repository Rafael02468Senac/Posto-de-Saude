package SERVICE;

import DAO.AplicacaoDAO;
import DAO.PacienteDAO;
import DAO.VacinaDAO;
import MODEL.Aplicacao;
import MODEL.Paciente;
import MODEL.Vacina;

/**
 * Serviço responsável pelas regras de negócio relacionadas
 * às aplicações de vacinas.
 *
 * Autor: Rafael da Silva Joaquim
 */
public class AplicacaoService {

    private final AplicacaoDAO aplicacaoDAO;
    private final PacienteDAO pacienteDAO;
    private final VacinaDAO vacinaDAO;
    private final EstoqueService estoqueService;

    /**
     * Construtor.
     */
    public AplicacaoService() {

        aplicacaoDAO = new AplicacaoDAO();
        pacienteDAO = new PacienteDAO();
        vacinaDAO = new VacinaDAO();
        estoqueService = new EstoqueService();
    }

    // ==========================================================
    // REGISTRAR APLICAÇÃO
    // ==========================================================
    /**
     * Registra uma aplicação de vacina.
     */
    public boolean registrarAplicacao(Aplicacao aplicacao) {

        // Verifica paciente
        Paciente paciente = pacienteDAO.buscarPorId(aplicacao.getPaciente().getId());
        if (paciente == null) {
            System.out.println("Paciente não encontrado.");
            return false;
        }

        // Verifica vacina
        Vacina vacina = vacinaDAO.buscarPorId(aplicacao.getVacina().getId());
        if (vacina == null) {
            System.out.println("Vacina não encontrada.");
            return false;
        }

        // Verifica validade
        if (vacina.estaVencida()) {
            System.out.println("Vacina vencida.");
            return false;
        }

        // Verifica estoque
        if (vacina.getQuantidadeEstoque() <= 0) {
            System.out.println("Estoque insuficiente.");
            return false;
        }

        // Atualiza os objetos
        aplicacao.setPaciente(paciente);
        aplicacao.setVacina(vacina);

        // Salva aplicação
        boolean salvou = aplicacaoDAO.inserir(aplicacao);
        if (!salvou) {
            return false;
        }

        // Baixa uma dose
        estoqueService.retirarEstoque(vacina.getId(), 1);
        return true;
    }

    // ==========================================================
    // CANCELAR APLICAÇÃO
    // ==========================================================
    /**
     * Remove uma aplicação e devolve a dose ao estoque.
     */
    public boolean cancelarAplicacao(int idAplicacao) {
        Aplicacao aplicacao = aplicacaoDAO.buscarPorId(idAplicacao);

        if (aplicacao == null) {
            return false;
        }

        // Devolve uma dose ao estoque
        estoqueService.adicionarEstoque(aplicacao.getVacina().getId(), 1);
        return aplicacaoDAO.excluir(idAplicacao);

    }
}