# Guia de Desenvolvimento

## Configuração do Ambiente
1. Certifique-se de ter o Java JDK instalado.
2. Configure o banco de dados MySQL.
3. Crie um arquivo `.env` com base no `.env.example`.

## Estrutura do Projeto
- **DAO**: Persistência de dados.
- **MODEL**: Entidades do sistema.
- **SERVICE**: Lógica de negócios.
- **VIEW**: Interface com o usuário.

## Boas Práticas
- Siga os princípios de Clean Code.
- Evite duplicação de código.
- Escreva testes para novas funcionalidades.

## Contribuição
1. Crie um branch para sua funcionalidade:
   ```bash
   git checkout -b feature/nome-da-funcionalidade