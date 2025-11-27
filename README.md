# 🏫 Sistema de Gestão Escolar - Spring Data JPA

<div align="center">

![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0%2B-brightgreen?style=for-the-badge&logo=springboot)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-3.0%2B-green?style=for-the-badge&logo=spring)
![Hibernate](https://img.shields.io/badge/Hibernate-6.0%2B-blue?style=for-the-badge&logo=hibernate)

**Projeto demonstrativo das principais funcionalidades do Spring Data JPA**

</div>

## 📋 Índice

- [📊 Visão Geral](#-visão-geral)
- [🏗️ Arquitetura do Projeto](#️-arquitetura-do-projeto)
- [🔍 Spring Data JPA em Ação](#-spring-data-jpa-em-ação)
  - [1. Derived Queries](#1-derived-queries)
  - [2. Consultas JPQL](#2-consultas-jpql)
  - [3. Native Queries](#3-native-queries)
  - [4. Paginação e Ordenação](#4-paginação-e-ordenanção)
  - [5. Trabalhando com Projeções](#5-trabalhando-com-projeções)
  - [6. Consultas Dinâmicas](#6-consultas-dinâmicas)
- [🚀 Como Executar](#-como-executar)
- [📚 Entidades e Relacionamentos](#-entidades-e-relacionamentos)
- [💡 Funcionalidades Principais](#-funcionalidades-principais)

## 📊 Visão Geral

Este projeto é uma aplicação completa de gestão escolar desenvolvida para demonstrar as principais funcionalidades do **Spring Data JPA**, incluindo diferentes tipos de consultas, paginação, projeções e consultas dinâmicas.

## 🔍 Spring Data JPA em Ação

### 1. Derived Queries

**Consulta derivada de métodos no `AlunoRepository`:**

```java
// 📍 Busca exata por nome
List<Aluno> findByNome(String nome);

// 🔍 Busca por fragmento de nome (LIKE)
List<Aluno> findByNomeContaining(String nome);

// 📅 Busca por data posterior
List<Aluno> findByDataNascimentoAfter(LocalDate data);
```
**Exemplo de uso:**
```
// Busca alunos com "Silva" no nome
List<Aluno> alunos = alunoRepository.findByNomeContaining("Silva");

// Busca alunos nascidos após 2000
List<Aluno> alunos = alunoRepository.findByDataNascimentoAfter(
    LocalDate.of(2000, 1, 1)
);
```
## 2. Consultas JPQL

Consultas personalizadas com JPQL no AlunoRepository:
```java
// 👥 Busca por nome e CPF com parâmetros nomeados
@Query("SELECT a FROM Aluno a WHERE a.nome = :nome AND a.cpf = :cpf")
List<Aluno> findNomeCPF(@Param("nome") String nome, @Param("cpf") String cpf);

// 🎓 Busca de alunos aprovados com JOIN
@Query("""
    SELECT DISTINCT a 
    FROM Aluno a 
    JOIN Avaliacao av ON a = av.aluno 
    WHERE av.nota >= 7 AND av.frequencia >= 20
""")
List<Aluno> findAlunosAprovados();
```

### Características:

✅ **Type-safe** - Verificação em tempo de compilação

✅ **Parâmetros nomeados** - Maior segurança e legibilidade

✅ **JOIN explícito** - Controle total sobre os relacionamentos

## 3. Native Queries

**Consultas SQL nativas para casos específicos:**

## Vantagens:

🚀 **Performance** - SQL otimizado para o banco específico

🔧 **Flexibilidade** - Sintaxe SQL completa

📊 **Projeções eficientes** - Seleção apenas de colunas necessárias

## 4. Paginação e Ordenação

**Implementação no AlunoRepository:**


// 📄 Herda de PagingAndSortingRepository para paginação automática
```
public interface AlunoRepository extends 
    PagingAndSortingRepository<Aluno, Long>,
    CrudRepository<Aluno, Long>,
    JpaSpecificationExecutor<Aluno> {
    
    // Métodos automaticamente pagináveis
}
```
**Exemplo de uso com paginação**

```
java
// Paginação: página 0, 10 registros por página, ordenado por nome

Pageable pageable = PageRequest.of(0, 10, Sort.by("nome"));
Page<Aluno> paginaAlunos = alunoRepository.findAll(pageable);

// Acessando dados paginados
List<Aluno> alunos = paginaAlunos.getContent();

int totalPaginas = paginaAlunos.getTotalPages();
long totalElementos = paginaAlunos.getTotalElements();
```

## 5. Trabalhando com Projeções

**Interface-based Projection (AlunoProjecao.java):**

```
public interface AlunoProjecao {
    Long getId();
    String getNome();
    String getEmail();
}
```

**Uso em consultas:**

// 🎯 Consulta que retorna apenas dados necessários
List<AlunoProjecao> projecoes = alunoRepository.findAlunoEmail();

**// Acesso aos dados projetados**

```
projecoes.forEach(projecao -> {
    System.out.println("ID: " + projecao.getId());
    System.out.println("Nome: " + projecao.getNome());
    System.out.println("Email: " + projecao.getEmail());
}); 
```

## Benefícios das Projeções:

⚡ **Performance melhorada** - Menos dados transferidos

🎯 **Consulta específica** - Apenas campos necessários

🔒 **Imutabilidade** - Dados somente leitura

## 6. Consultas Dinâmicas

**Implementação com JpaSpecificationExecutor:**

**// 🎛️ Suporte a specifications dinâmicas**

```
java
public interface AlunoRepository extends JpaSpecificationExecutor<Aluno> {
    // Permite construir queries dinâmicas
}
```
**Exemplo de Specification dinâmica:**

```
java
public class AlunoSpecifications {
    
    public static Specification<Aluno> comNome(String nome) {
        return (root, query, criteriaBuilder) -> 
            nome == null ? null : criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }
    
    public static Specification<Aluno> comDataNascimentoAfter(LocalDate data) {
        return (root, query, criteriaBuilder) -> 
            data == null ? null : criteriaBuilder.greaterThan(root.get("dataNascimento"), data);
    }
}

// Uso combinado de specifications
Specification<Aluno> spec = Specification
    .where(AlunoSpecifications.comNome("João"))
    .and(AlunoSpecifications.comDataNascimentoAfter(LocalDate.of(2000, 1, 1)));

List<Aluno> alunos = alunoRepository.findAll(spec);

```
## 🚀 Como Executar

## Pré-requisitos

- Java 17+

- Maven 3.6+

- Banco de dados configurado (H2, MySQL, PostgreSQL)

## Configuração

1. Clone o repositório

2. Configure o banco de dados no application.properties:

```
spring.datasource.url=jdbc:mysql://localhost:3306/escola
spring.datasource.username=usuario
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
```

### Diagrama de Relacionamentos

```ascii
Aluno (N) ←──────→ (N) Curso
  ↑                     ↑
  │                     │
 (N)                   (1)
  │                     │
Avaliacao           Disciplina
  ↑                     ↑
  │                     │
 (N)                   (1)
  │                     │
Disciplina ←──────→ Professor

```

## 💡 Funcionalidades Principais

### 💻 Menu Interativo

| Opção | Funcionalidade | Descrição |
|-------|----------------|-----------|
| **1** | **Aluno** 👨‍🎓 | Gestão de estudantes |
| **2** | **Professor** 👨‍🏫 | Gestão de docentes |
| **3** | **Avaliação** 📊 | Notas e frequência |
| **4** | **Relatórios** 📋 | Relatórios estáticos |
| **5** | **Relatórios Dinâmicos** 🎚️ | Consultas dinâmicas |
| **0** | **Sair** 🚪 | Finalizar sistema |


## Principais Anotações JPA Utilizadas

| Anotação | Uso | Exemplo |
|----------|-----|---------|
| `@Entity` | Define entidade JPA | `@Entity public class Aluno` |
| `@Id` + `@GeneratedValue` | Chave primária | `@Id @GeneratedValue(strategy = IDENTITY)` |
| `@ManyToOne` | Relacionamento N-1 | `@ManyToOne private Disciplina disciplina` |
| `@OneToMany` | Relacionamento 1-N | `@OneToMany(mappedBy = "aluno")` |
| `@ManyToMany` | Relacionamento N-N | `@ManyToMany @JoinTable` |
| `@Enumerated` | Enumeração | `@Enumerated(EnumType.STRING)` |


## Destaques Técnicos

✅ Derived Queries - Consultas automáticas a partir dos nomes dos métodos

✅ JPQL - Consultas type-safe com linguagem orientada a objetos

✅ Native Queries - SQL nativo para casos específicos

✅ Paginação - Controle eficiente de grandes volumes de dados

✅ Projeções - Otimização de performance com interfaces

✅ Specifications - Consultas dinâmicas type-safe

✅ Relacionamentos - Mapeamento completo de entidades


