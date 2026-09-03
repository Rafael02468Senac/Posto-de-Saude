package UTIL;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.time.LocalDate;
import java.util.regex.Pattern;

public final class Validacao {
    private static final Pattern NOME = Pattern.compile("[\\p{L} .'-]+");

    private Validacao() {}

    public static void somenteNumeros(JTextField campo, int maximo) {
        ((AbstractDocument) campo.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
                    throws BadLocationException {
                substituir(fb, offset, 0, text, attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                substituir(fb, offset, length, text, attrs);
            }

            private void substituir(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                String atual = fb.getDocument().getText(0, fb.getDocument().getLength());
                String novo = atual.substring(0, offset) + (text == null ? "" : text) + atual.substring(offset + length);
                if (novo.matches("\\d{0," + maximo + "}")) {
                    fb.replace(offset, length, text, attrs);
                }
            }
        });
    }

    public static String textoObrigatorio(String valor, String campo) {
        String texto = valor == null ? "" : valor.trim();
        if (texto.isEmpty()) throw new IllegalArgumentException(campo + " é obrigatório.");
        return texto;
    }

    public static String cpf(String valor) {
        String cpf = textoObrigatorio(valor, "CPF");
        if (!cpf.matches("\\d{11}")) throw new IllegalArgumentException("CPF deve conter exatamente 11 números.");
        return cpf;
    }

    public static String telefone(String valor) {
        String telefone = textoObrigatorio(valor, "Telefone");
        if (!telefone.matches("\\d{10,11}")) throw new IllegalArgumentException("Telefone deve conter 10 ou 11 números.");
        return telefone;
    }

    public static String nome(String valor, String campo) {
        String nome = textoObrigatorio(valor, campo);
        if (!NOME.matcher(nome).matches()) throw new IllegalArgumentException(campo + " deve conter apenas letras e espaços.");
        return nome;
    }

    public static LocalDate data(String valor, String campo) {
        try {
            return LocalDate.parse(textoObrigatorio(valor, campo));
        } catch (Exception e) {
            throw new IllegalArgumentException(campo + " deve estar no formato AAAA-MM-DD.");
        }
    }

    public static int inteiroNaoNegativo(String valor, String campo) {
        try {
            int numero = Integer.parseInt(textoObrigatorio(valor, campo));
            if (numero < 0) throw new NumberFormatException();
            return numero;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(campo + " deve ser um número inteiro igual ou maior que zero.");
        }
    }
}
