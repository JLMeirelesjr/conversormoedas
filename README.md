# 💱 Conversor de Moedas API

API RESTful desenvolvida com Spring Boot 3 para conversão de moedas em tempo real. O sistema consome taxas de câmbio da **AwesomeAPI**, executa o cálculo da conversão e armazena o histórico das transações em um banco de dados **PostgreSQL**.

---

**🚀 Tecnologias**
* **Java 17**
* **Spring Boot 3** (Spring Web, Spring Data JPA)
* **PostgreSQL**
* **RestClient** (Consumo de API HTTP externa)
* **Java Records** (DTOs para transferência de dados)
* **Lombok** & **Jackson**

---

**📌 Endpoints da API**

| Método | Endpoint | Descrição | Exemplo de Parâmetros |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/v1/cambio/converter` | Converte o valor entre duas moedas | `de=USD&para=BRL&valor=100` |
| `GET` | `/api/v1/cambio/historico` | Lista todas as conversões salvas no banco | N/A |

---

**💡 Tratamento de Erros**
A aplicação conta com um `@RestControllerAdvice` centralizado para interceptar requisições com moedas inválidas ou falhas na comunicação externa, retornando respostas padronizadas em formato JSON (`400 Bad Request`).

---

**🛠️ Como Executar o Projeto**

1. Clone o repositório:
   ```bash
   git clone [https://github.com/seu-usuario/conversor-moedas.git](https://github.com/seu-usuario/conversor-moedas.git)