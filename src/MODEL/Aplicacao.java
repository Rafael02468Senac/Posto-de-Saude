package MODEL;
import java.time.LocalDate;

/**
 * Classe que representa uma aplicação de vacina realizada em um paciente.
 *
 * Autor: Rafael da Silva Joaquim
 */
public class Aplicacao {
    // ==========================================================
    // ATRIBUTOS
    // ==========================================================
    private int id;
    private Paciente paciente;
    private Vacina vacina;
    private LocalDate dataAplicacao;
    private String profissional;
    private String observacao;

    // ==========================================================
    // CONSTRUTORES
    // ==========================================================
    /**
     * Construtor vazio.
     */
    public Aplicacao() {
    }

    /**
     * Construtor sem ID.
     */
    public Aplicacao(Paciente paciente,
                     Vacina vacina,
                     LocalDate dataAplicacao,
                     String profissional,
                     String observacao) {

        this.paciente = paciente;
        this.vacina = vacina;
        this.dataAplicacao = dataAplicacao;
        this.profissional = profissional;
        this.observacao = observacao;
    }

    /**
    * Construtor completo (vindo do banco).
    */
     public Aplicacao(int id,
                     Paciente paciente,
                     Vacina vacina,
                     LocalDate dataAplicacao,
                     String profissional,
                     String observacao){

        this.id = id;
        this.paciente = paciente;
        this.vacina = vacina;
        this.dataAplicacao = dataAplicacao;
        this.profissional = profissional;
        this.observacao = observacao;
    }

    // =========================
    // GETTERS E SETTERS
    // =========================
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Vacina getVacina() {
        return vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(LocalDate dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public String getProfissional() {
        return profissional;
    }

    public void setProfissional(String profissional) {
        this.profissional = profissional;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    // ==========================================================
    // MÉTODOS AUXILIARES
    // ==========================================================
    /**
     * Verifica se a aplicação foi realizada na data atual.
     */
    public boolean aplicadaHoje() {
        return dataAplicacao.equals(LocalDate.now());
    }

    /**
     * Retorna o nome do paciente.
     */
    public String getNomePaciente() {
        return paciente != null ? paciente.getNome() : "";
    }

    /**
     * Retorna o nome da vacina.
     */
    public String getNomeVacina() {
        return vacina != null ? vacina.getNome() : "";
    }

    // =========================
    // TO STRING
    // =========================
    @Override
    public String toString() {
        return "\nAplicação{ID: " + id + " | Paciente: " + (paciente != null ? paciente.getNome() : "N/A") + " | Vacina: " + (vacina != null ? vacina.getNome() : "N/A") + " | Data de Aplicação: " + dataAplicacao + " | Profissional de Saúde: " + profissional + " | Observação: " + observacao +"}";
    }
}