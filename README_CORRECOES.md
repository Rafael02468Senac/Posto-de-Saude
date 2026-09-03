# Correções e interface web

## Problemas encontrados e corrigidos no Java
- `Main` chamava `ConexaoMySQL.conectar()` sem tratar `SQLException`.
- As telas tentavam criar `PacienteDAO`, `VacinaDAO` e `AplicacaoDAO` passando `Connection`, mas esses construtores não existiam.
- As telas chamavam métodos inexistentes (`salvar`, `aplicarVacina`).
- `VacinaDAO.buscarPorNome()` retorna uma lista, mas a tela tratava como um único objeto.
- Cadastro de vacina ignorava os campos `fabricante` e `estoqueMinimo` exigidos pelo model/DAO.
- CPF, telefone e quantidade aceitavam entradas incompatíveis com o tipo esperado.
- Foram adicionadas validações de CPF, telefone, nome, data e inteiros não negativos.
- Campos numéricos de CPF, telefone e quantidades agora bloqueiam letras na interface Swing.
- Data de nascimento futura é bloqueada.
- CPF duplicado é bloqueado pela interface antes de inserir.
- Aplicação valida paciente, vacina, profissional, validade e estoque.
- Entrada/saída de estoque rejeita quantidades zero ou negativas.
- `Vacina.diasParaVencer()` passou a usar `ChronoUnit.DAYS`, evitando cálculo incorreto entre meses/anos.
- O projeto foi recompilado com `javac` após as alterações.

## Interface web
A pasta `web/` contém:
- `index.html`
- `styles.css`
- `script.js`

A interface reproduz Pacientes, Vacinas/Estoque e Aplicações. Por ser somente HTML/CSS/JS, ela usa `localStorage` para demonstração no navegador. Para usar o mesmo MySQL do Java em produção, é necessário adicionar uma API/backend entre o navegador e o banco.
