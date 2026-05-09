# Order API

API REST simples para gerenciamento de pedidos, construída com **Spring Boot**, **Spring Web MVC**, **Spring Data JPA** e **MySQL**.

## 📌 Visão geral

Este projeto implementa um serviço de pedidos com arquitetura em camadas:

- **Controller**: recebe requisições HTTP e expõe endpoints REST.
- **Service**: aplica regras de negócio.
- **Repository**: persiste e consulta dados usando JPA.
- **Entity**: representa o modelo de dados da tabela no banco.

Atualmente, a API oferece:

- `POST /orders` para criar um pedido.
- `GET /orders` para listar todos os pedidos.

---

## 🧱 Tecnologias utilizadas

- **Java 21**
- **Spring Boot 4.0.6**
- **Spring Web MVC**
- **Spring Data JPA**
- **MySQL Connector/J**
- **JUnit 5 + Mockito**
- **Gradle**

---

## 📂 Estrutura do projeto

```text
src/
├── main/
│   ├── java/com/leonardo/order_api/
│   │   ├── controller/
│   │   │   └── OrderController.java
│   │   ├── entity/
│   │   │   └── Order.java
│   │   ├── repository/
│   │   │   └── OrderRepository.java
│   │   ├── service/
│   │   │   └── OrderService.java
│   │   └── OrderApiApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/leonardo/order_api/
        ├── service/
        │   └── OrderServiceTest.java
        └── OrderApiApplicationTests.java
```

---

## 🗃️ Modelo de dados

A entidade `Order` é mapeada para a tabela `orders` e possui os campos:

- `id` (`Long`) — chave primária com auto incremento.
- `customer` (`String`) — nome do cliente.
- `product` (`String`) — produto comprado.
- `price` (`Double`) — valor do pedido.

---

## ⚙️ Configuração

Arquivo: `src/main/resources/application.properties`

Configuração atual:

- Banco: `orderdb`
- URL: `jdbc:mysql://localhost:3306/orderdb`
- Usuário: `root`
- Senha: `root`
- `spring.jpa.hibernate.ddl-auto=update`
- `spring.jpa.show-sql=true`

> Observação: para produção, ajuste credenciais e desative logs SQL sensíveis.

---

## ▶️ Como executar localmente

### Pré-requisitos

- Java 21 instalado
- MySQL rodando localmente
- Banco `orderdb` criado

### Passos

1. Clone o repositório.
2. Configure o `application.properties` conforme seu ambiente.
3. Execute a aplicação:

```bash
./gradlew bootRun
```

A API iniciará, por padrão, em `http://localhost:8080`.

---

## 🔌 Endpoints

### Criar pedido

- **Método:** `POST`
- **Rota:** `/orders`
- **Body (JSON):**

```json
{
  "customer": "Leonardo",
  "product": "Notebook",
  "price": 4500.00
}
```

- **Regra de negócio:** `price` deve ser maior que zero.

### Listar pedidos

- **Método:** `GET`
- **Rota:** `/orders`
- **Resposta:** lista de pedidos.

---

## 🧠 Regra de negócio atual

No `OrderService`, ao criar pedido:

- Se `price <= 0`, a aplicação lança exceção: `Price must be greater than zero`.
- Caso contrário, o pedido é persistido.

---

## 🧪 Testes

O projeto contém testes unitários para `OrderService` cobrindo:

- criação com sucesso;
- listagem de pedidos;
- falha para preço inválido.

Para executar:

```bash
./gradlew test
```

---

## 🚀 Melhorias sugeridas

Para evoluir o projeto:

1. Adicionar endpoints de atualização e exclusão (`PUT/PATCH`, `DELETE`).
2. Implementar tratamento global de erros com `@ControllerAdvice`.
3. Adotar validação com `@Valid` + Bean Validation.
4. Criar DTOs para entrada/saída.
5. Adicionar paginação e filtros na listagem.
6. Incluir documentação de API com Swagger/OpenAPI.
7. Externalizar configuração por perfis (`dev`, `test`, `prod`).

---

## 👤 Autor

Projeto base de estudos e prática com Spring Boot.
