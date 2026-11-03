# Arquitetura do Sistema

## Visão Geral
O sistema é dividido em camadas para garantir a separação de responsabilidades:
- **DAO**: Gerencia a persistência de dados no banco MySQL.
- **MODEL**: Representa as entidades do sistema.
- **SERVICE**: Contém a lógica de negócios.
- **VIEW**: Interface com o usuário.

## Fluxo de Dados
1. O usuário interage com a interface (VIEW).
2. A lógica de negócios é processada na camada SERVICE.
3. Os dados são persistidos ou recuperados pela camada DAO.

## Tecnologias
- **Frontend**: HTML, CSS, JavaScript.
- **Backend**: Java com JDBC.
- **Banco de Dados**: MySQL.