Com certeza! Aqui está a Versão Final e Completa do README.md.

Esta versão é a "vencedora" porque inclui:

Destaques Técnicos: Explica as chaves compostas e FKs (para garantir a nota de modelagem).

Regras de Negócio: "Vende" a lógica de geração de código e validação de idade.

Testes Automatizados: Menciona os testes unitários que acabamos de consertar.

Diagrama e Exemplos: Tudo que o professor pediu.

Crie um arquivo chamado README.md na raiz do seu projeto (junto com o pom.xml) e cole este conteúdo:

Markdown

# ⚽ Backend - Sistema de Gerenciamento de Escolinha de Futebol

Este projeto consiste em uma API RESTful desenvolvida com Spring Boot para o gerenciamento administrativo e pedagógico de uma escolinha de futebol. O sistema permite o controle de alunos, matrículas, turmas, professores e campeonatos, com acesso seguro via autenticação JWT.

O foco principal é a **modelagem avançada de dados**, a implementação de **regras de negócio complexas** e a garantia de qualidade através de **testes automatizados**.

---

## 🏆 Destaques Técnicos (Atendimento aos Requisitos)

### 1. Modelagem Relacional Avançada (9 Entidades)
* **Chave Primária Composta (`@EmbeddedId`):** Implementada na entidade `InscricaoCampeonato`. A chave é formada pela união de `aluno_id` + `campeonato_id`, garantindo unicidade na inscrição.
* **Chave Estrangeira como Chave Primária (`@MapsId`):** Implementada na entidade `FichaMedica`. Garante dependência estrita 1:1 entre Aluno e sua Ficha.
* **Relacionamentos N:N:** Gerenciados via entidade associativa `Matricula` com atributos extras (data, status).

### 2. Regras de Negócio (Business Logic)
* **Geração Automática de Código:** Uso de **Native Query** para gerar códigos sequenciais baseados no ano corrente (ex: `2025-0042`).
* **Validação de Lotação e Idade:** Serviços que calculam a idade exata do aluno e verificam a capacidade da turma antes de efetivar a matrícula.
* **Soft Delete:** Implementação de exclusão lógica para preservação de histórico.

### 3. Testes Automatizados (`src/test/java`)
O projeto implementa testes unitários para a camada de Controladores (`Controllers`) utilizando:
* **@WebMvcTest:** Para testar os endpoints de forma isolada.
* **Mockito:** Para simular o comportamento dos Serviços (`Services`).
* **MockMvc:** Para realizar requisições HTTP simuladas e validar os JSONs de resposta.
* **Isolamento de Segurança:** Utilização de `@AutoConfigureMockMvc(addFilters = false)` para focar os testes puramente na lógica de negócio e validação de dados.

---

## 📋 Descrição do Domínio

1.  **Funcionario (`/auth`):** Usuários do sistema (Login/Security).
2.  **Aluno (`/alunos`):** Cliente final com código gerado automaticamente.
3.  **FichaMedica:** Dados de saúde (1:1 Forte).
4.  **Professor (`/professores`):** Responsável pedagógico.
5.  **Turma (`/turmas`):** Agrupamento lógico com regras de negócio.
6.  **Matricula (`/matriculas`):** Vínculo Aluno-Turma (ATIVA, TRANCADA).
7.  **Aula (`/aulas`):** Grade horária.
8.  **Campeonato & Inscrição:** Gestão de eventos (Chave Composta).

---

## 📊 Diagrama de Entidades (Lógico)

```mermaid
classDiagram
    class Funcionario {
        +Long id
        +String email
        +String role
    }
    class Professor {
        +Long id
        +String cpf
    }
    class Turma {
        +Long id
        +Integer limiteAlunos
    }
    class Aluno {
        +Long id
        +String codigoAluno
        +LocalDate dataNascimento
    }
    class FichaMedica {
        +Long aluno_id (PK/FK)
        +String alergias
    }
    class Matricula {
        +Long id
        +StatusMatricula status
    }
    class InscricaoCampeonato {
        +InscricaoId id (Composite)
        +Boolean taxaPaga
    }

    Professor "1" -- "N" Turma : ministra
    Turma "1" -- "N" Aula : possui
    Turma "1" -- "N" Matricula : contem
    Aluno "1" -- "N" Matricula : possui
    Aluno "1" -- "1" FichaMedica : tem
    Aluno "1" -- "N" InscricaoCampeonato : inscreve
▶️ Como Executar
Pré-requisitos
Java 17 ou superior.

Maven.

Passo a Passo
Clone o repositório.

Execute a classe principal FutebolBackendApplication.

O servidor iniciará na porta 8080.

Acessando o Banco de Dados (H2 Console)
Para verificar a criação das tabelas e chaves:

URL: http://localhost:8080/h2-console

Driver Class: org.h2.Driver

JDBC URL: jdbc:h2:mem:futeboldb

User: sa

Password: password

Documentação da API (Swagger UI)
Para testar os endpoints visualmente:

URL: http://localhost:8080/swagger-ui/index.html

Autenticação: Faça login na rota /auth/login, copie o token e use no botão "Authorize" (formato: SEU_TOKEN_AQUI, sem prefixos).

🧪 Exemplos de Uso (JSON)
1. Login (Obter Token)
POST /auth/login

JSON

{
  "email": "admin@escolinha.com",
  "senha": "123456"
}
2. Criar Professor (Valida CPF único)
POST /api/professores

JSON

{
  "nome": "Professor Tite",
  "cpf": "12345678900"
}
3. Criar Turma (Define regras)
POST /api/turmas

JSON

{
  "nome": "Sub-15 Manhã",
  "faixaEtariaMinima": 10,
  "faixaEtariaMaxima": 15,
  "limiteAlunos": 20,
  "professorId": 1
}
4. Criar Aluno (Gera código automático)
POST /api/alunos

JSON

{
  "nome": "Neymar Junior",
  "dataNascimento": "2010-05-15",
  "nomeResponsavel": "Pai do Neymar",
  "cpfResponsavel": "11122233344",
  "emailResponsavel": "pai@neymar.com",
  "telefoneResponsavel": "11999999999"
}
5. Matricular (Valida Idade e Lotação)
POST /api/matriculas

JSON

{
  "alunoId": 1,
  "turmaId": 1
}
🛠️ Tecnologias Utilizadas
Java 17 & Spring Boot 3

Spring Data JPA (Hibernate)

Spring Security (JWT Authentication)

H2 Database (Memória)

Lombok & Bean Validation

JUnit 5 & Mockito