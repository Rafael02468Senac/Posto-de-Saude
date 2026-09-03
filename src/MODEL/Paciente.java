package MODEL;
import java.time.LocalDate;
/**
 * Classe que representa um paciente do sistema de Controle de Vacinas.
 *
 * Autor: Rafael da Silva Joaquim
 */
public class Paciente {

    // ==========================
    // ATRIBUTOS
    // ==========================
    private int id;
    private String cpf;
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;

    // ==========================
    // CONSTRUTORES
    // ==========================
    /**
    * Construtor vazio
    */
    public Paciente() {
    }

    /**
     * Construtor sem ID.
     * Utilizado para novos cadastros.
     */
    public Paciente(String cpf,
                    String nome,
                    String telefone,
                    LocalDate dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    /**
    * Construtor completo.
    */
    public Paciente(int id,
                    String cpf,
                    String nome,
                    String telefone,
                    LocalDate dataNascimento) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    // =========================
    // TO STRING (IMPORTANTE PARA TESTES)
    // =========================
    @Override
    public String toString() {
        return "\nPaciente{ID: " + id + " | CPF: " + cpf + " | Nome: " + nome + " | Telefone: " + telefone + " | Data de Nascimento: " + dataNascimento +"}";
    }
}