package MODEL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Classe que representa uma vacina cadastrada no sistema.
 *
 * Autor: Rafael da Silva Joaquim
 */
public class Vacina {

    // ==========================================================
    // ATRIBUTOS
    // ==========================================================
    private int id;
    private String nome;
    private String fabricante;
    private String lote;
    private LocalDate validade;
    private int quantidadeEstoque;
    private int estoqueMinimo;

    // ==========================================================
    // CONSTRUTORES
    // ==========================================================

    /**
     * Construtor vazio.
     */
    public Vacina() {
    }

    /**
     * Construtor sem ID.
     * Utilizado para novos cadastros.
     */
    public Vacina(String nome,
                  String fabricante,
                  String lote,
                  LocalDate validade,
                  int quantidadeEstoque,
                  int estoqueMinimo) {

        this.nome = nome;
        this.fabricante = fabricante;
        this.lote = lote;
        this.validade = validade;
        this.quantidadeEstoque = quantidadeEstoque;
        this.estoqueMinimo = estoqueMinimo;
    }

    /**
    * Construtor completo (leitura do banco).
    */
    public Vacina(int id,
                  String nome,
                  String fabricante,
                  String lote,
                  LocalDate validade,
                  int quantidadeEstoque,
                  int estoqueMinimo) {

        this.id = id;
        this.nome = nome;
        this.fabricante = fabricante;
        this.lote = lote;
        this.validade = validade;
        this.quantidadeEstoque = quantidadeEstoque;
        this.estoqueMinimo = estoqueMinimo;
    }

    // ==========================================================
    // GETTERS E SETTERS
    // ==========================================================
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    // ==========================================================
    // MÉTODOS AUXILIARES
    // ==========================================================
    /**
     * Verifica se o estoque está abaixo do mínimo.
     */
    public boolean estoqueBaixo() {
        return quantidadeEstoque <= estoqueMinimo;
    }

    /**
     * Verifica se a vacina está vencida.
     */
    public boolean estaVencida() {
        return validade.isBefore(LocalDate.now());
    }

    /**
     * Retorna a quantidade de dias restantes até o vencimento.
     */
    public long diasParaVencer() {
        return ChronoUnit.DAYS.between(LocalDate.now(), validade);
    }

    // =========================
    // TO STRING
    // =========================
    @Override
    public String toString() {
        return "\nVacina{ID: " + id + " | Nome: " + nome + " | Fabricante: " + fabricante + " | Lote: " + lote + " | Validade: " + validade + " | Quantidade de Estoque: " + quantidadeEstoque + " | Estoque Minimo: " + estoqueMinimo + "}";
    }
}