# 🏢 SmartBooking

**SmartBooking** é um sistema de gerenciamento de reservas de espaços corporativos desenvolvido em **Java** com **Spring Boot**.  
O objetivo é permitir que usuários façam reservas de salas, auditórios ou outros ambientes de forma simples e organizada, garantindo controle, disponibilidade e histórico de uso.

---

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3+**
  - Spring Web
  - Spring Data JPA
  - Spring Validation
  - Spring Security (opcional)
- **Hibernate**
- **Banco de Dados:** MySQL (pode ser substituído por PostgreSQL ou H2)
- **Maven**
- **Lombok**
- **Swagger / Springdoc OpenAPI**
- **JUnit 5 / Mockito** (para testes)
---

## 📘 Entidades Principais

### 🧍 Usuário (`Usuario`)
Representa os usuários que fazem login e utilizam o sistema.

| Campo | Tipo | Descrição |
|--------|------|-----------|
| `id` | Long | Identificador único |
| `nome` | String | Nome completo do usuário |
| `email` | String | E-mail de acesso |
| `senha` | String | Senha criptografada |
| `perfil` | Enum (ADMIN, USER) | Nível de acesso do usuário |

---

### 🏢 Espaço (`Espaco`)
Representa as salas ou ambientes que podem ser reservados.

| Campo | Tipo | Descrição |
|--------|------|-----------|
| `id` | Long | Identificador único |
| `nome` | String | Nome do espaço |
| `descricao` | String | Descrição breve |
| `capacidade` | Integer | Capacidade máxima |
| `tipo de ambiente` | String |que tipo lugar é |
| `endereco` | Endereco | Informações de endereço |
| `andar` | String |andar da sala  |
| `Referencia` | String | Ponto de referência para identificaçaõ do local |
| `disponivel` | Boolean | Indica se o espaço está disponível |

---

### 📅 Reserva (`Reserva`)
Representa as reservas realizadas pelos usuários.

| Campo | Tipo | Descrição |
|--------|------|-----------|
| `id` | Long | Identificador único |
| `usuario` | Usuario | Usuário que fez a reserva |
| `espaco` | Espaco | Espaço reservado |
| `dataInicio` | LocalDateTime | Início da reserva |
| `dataFim` | LocalDateTime | Fim da reserva |
| `status` | Enum (PENDENTE, CONFIRMADA, CANCELADA) | Estado da reserva |

---

## 🧭 Regras de Negócio

- Um **usuário** pode reservar vários **espaços**, mas um **espaço** não pode estar reservado em dois horários que se sobrepõem.  
- Apenas **administradores** podem:
  - Criar, editar ou excluir espaços.
  - Gerenciar todas as reservas.
- **Usuários comuns** podem:
  - Criar e cancelar suas próprias reservas.
  - Visualizar a disponibilidade dos espaços.
---

## ⚙️ Endpoints Principais

| Método | Endpoint | Descrição |
|---------|-----------|------------|
| `POST` | `/auth/register` | Registrar novo usuário |
| `POST` | `/auth/login` | Autenticar e obter token JWT |
| `GET` | `/espacos` | Listar todos os espaços |
| `POST` | `/espacos` | Criar espaço (ADMIN) |
| `PUT` | `/espacos/{id}` | Atualizar espaço (ADMIN) |
| `DELETE` | `/espacos/{id}` | Remover espaço (ADMIN) |
| `POST` | `/reservas` | Criar reserva |
| `GET` | `/reservas` | Listar reservas do usuário autenticado |
| `GET` | `/reservas/espaco/{id}` | Listar reservas de um espaço específico |
| `PUT` | `/reservas/{id}/cancelar` | Cancelar reserva |
---

## 🧰 Configuração do Banco de Dados

Arquivo `application.properties`:

```properties
 
pring.datasource.url=jdbc:mysql://localhost:3306/${DB_NAME3}?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&characterEncoding=UTF-8
spring.datasource.username=${DB_USARIO}
spring.datasource.password=${DB_SENHA} 
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.show-sql=true

```
| Variável | Descrição |
|-----------|------------|
| `${DB_NAME3}` | Nome do banco de dados |
| `${DB_SENHA}` | Senha do banco de dados |
| `${DB_USUARIO}` | Nome de usuário |

---

## 🧪 Testes

Executar testes unitários:

```bash
mvn test
```

---

## 📦 Executando o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/isrraelantonio/SmartBooking.git
   ```
2. Acesse o diretório:
   ```bash
   cd Smartbooking
   ```
3. Compile e execute:
   ```bash
   mvn spring-boot:run
   ```
4. Acesse no navegador:
   ```
   http://localhost:8080
   ```

---

## 📖 Documentação da API (Swagger)

Após rodar o projeto, acesse:
```
http://localhost:8080/swagger-ui.html
```

---

## 👨‍💻 Autor

**Seu Nome**  
Desenvolvedor Java | Spring Boot | REST APIs  
📧 israelsilvawr@gmail.com  
🔗 [LinkedIn](https://www.linkedin.com/in/israelantonio-backend) | [GitHub](https://github.com/isrraelantonio)

---

## 📝 Licença

Este projeto é distribuído sob a licença **MIT**.  
Sinta-se livre para usar, modificar e distribuir conforme necessário.

