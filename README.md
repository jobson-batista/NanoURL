# ⚛️ NanoURL | Encurtador de URL

Este é um projeto simples de encurtamento de URL utilizando **Spring Boot**, **MongoDB** e um pouco de **JUnit**. O objetivo é criar um serviço RESTful que recebe uma URL longa e gera um código curto, armazenando as informações no MongoDB.

## ✅ Funcionalidades

- **Encurtar URL**: Recebe uma URL longa e retorna uma URL encurtada.
- **Redirecionar para URL Original**: Ao acessar a URL encurtada, o serviço redireciona para a URL original.
- **Obter estastísticas das URLs**: Obtém informação como total de URLs ativas e total de URLs expiradas, por exemplo.

## ✅ Tecnologias Utilizadas

- **Spring Boot 3.4.1**: Para agilizar a criação do projeto Spring.
- **MongoDB 8.0.4**: Banco de dados não relacional, baseado em collections e documentos.
- **Gradle 8.11**: Gerenciador de dependências.
- **JUnit 5**: Realizar testes automatizados.
- **Lombok**: Para diminuir a verbosidade do código.

## 🏃🏻‍♂️ Como Rodar o Projeto

### Pré-requisitos

- ☕️ Java 21 
- 🐘 Gradle 
- 🎲 MongoDB rodando localmente (ou em outro ambiente) 

### ✅ Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/jobson-batista/NanoURL.git
   ```

2. Navegue até o diretório do projeto:
   ```bash
   cd NanoURL
   ```

3. Configure o MongoDB no arquivo `src/main/resources/application-dev.properties`:
   ```properties
   spring.data.mongodb.host=localhost
   spring.data.mongodb.port=27017
   spring.data.mongodb.database=nano_url
   ```

4. Execute o projeto:
   ```bash
   ./gradlew bootRun
   ```

5. Acesse a aplicação:
   - **POST** para encurtar uma URL:
     ```
     http://localhost:8080/api/url/shorten
     ```
     Corpo da requisição (JSON):
     ```json
     {
       "originalUrl": "https://github.com/jobson-batista"
     }
     ```

   - **GET** para redirecionar para a URL original:
     ```
     http://localhost:8080/{shortCode}
     ```

## 📚 Documentação | Swagger

* Para acessar a documentação basta subir o projeto e acessar o endereço abaixo:
```http://localhost:8080/swagger-ui/index.html```
