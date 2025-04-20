_[English Version](#nanourl--url-shortener-en-)_

_[Versão em Português](#nanourl--encurtador-de-url-pt-br-)_

---

# ⚛️ NanoURL | Encurtador de URL PT-BR 🇧🇷

Este é um projeto simples de encurtamento de URLs utilizando **Spring Boot**, **MongoDB** e um pouco de **JUnit**. O objetivo é criar um serviço RESTful que receba uma URL longa e gere um código curto, armazenando as informações no MongoDB.  

## ✅ Funcionalidades  

- **Encurtar URL**: Recebe uma URL longa e retorna uma URL encurtada.  
- **Redirecionar para URL Original**: Ao acessar a URL encurtada, o serviço redireciona para a URL original.  
- **Recuperar Estatísticas de URLs**: Exibe informações como o total de URLs ativas e expiradas, por exemplo.  

## ✅ Tecnologias Utilizadas  

- **Spring Boot 3.4.1**: Para facilitar o desenvolvimento com o framework Spring.  
- **MongoDB 8.0.4**: Banco de dados não relacional baseado em coleções e documentos.  
- **Gradle 8.11**: Gerenciador de dependências e builds.  
- **JUnit 5**: Para testes automatizados.  
- **Lombok**: Para reduzir a verbosidade do código.  

## 🏃🏻‍♂️ Como Executar o Projeto  

### Pré-requisitos  

- ☕️ Java 21  
- 🐘 Gradle  
- 🎲 MongoDB rodando localmente (ou em outro ambiente)  

### ✅ Passos  

1. Clone o repositório:  
   ```bash
   git clone https://github.com/jobson-batista/NanoURL.git
   ```  

2. Acesse o diretório do projeto:  
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

5. Utilize a aplicação:  
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

* Para acessar a documentação da API, basta iniciar o projeto e acessar a URL abaixo:  
```http://localhost:8080/swagger-ui/index.html```  

----

# ⚛️ NanoURL | URL Shortener EN 🇺🇸

This is a simple URL shortening project using **Spring Boot**, **MongoDB**, and a bit of **JUnit**. The goal is to create a RESTful service that receives a long URL and generates a short code, storing the information in MongoDB.  

## ✅ Features  

- **Shorten URL**: Receives a long URL and returns a shortened URL.  
- **Redirect to Original URL**: When accessing the shortened URL, the service redirects to the original URL.  
- **Retrieve URL Statistics**: Gets information such as the total number of active and expired URLs, for example.  

## ✅ Technologies Used  

- **Spring Boot 3.4.1**: To streamline the Spring project development.  
- **MongoDB 8.0.4**: A non-relational database based on collections and documents.  
- **Gradle 8.11**: Dependency manager.  
- **JUnit 5**: For automated testing.  
- **Lombok**: To reduce code verbosity.  

## 🏃🏻‍♂️ How to Run the Project  

### Prerequisites  

- ☕️ Java 21  
- 🐘 Gradle  
- 🎲 MongoDB running locally (or in another environment)  

### ✅ Steps  

1. Clone the repository:  
   ```bash
   git clone https://github.com/jobson-batista/NanoURL.git
   ```  

2. Navigate to the project directory:  
   ```bash
   cd NanoURL
   ```  

3. Configure MongoDB in the `src/main/resources/application-dev.properties` file:  
   ```properties
   spring.data.mongodb.host=localhost
   spring.data.mongodb.port=27017
   spring.data.mongodb.database=nano_url
   ```  

4. Run the project:  
   ```bash
   ./gradlew bootRun
   ```  

5. Access the application:  
   - **POST** to shorten a URL:  
     ```
     http://localhost:8080/api/url/shorten
     ```
     Request body (JSON):  
     ```json
     {
       "originalUrl": "https://github.com/jobson-batista"
     }
     ```  

   - **GET** to redirect to the original URL:  
     ```
     http://localhost:8080/{shortCode}
     ```  

## 📚 Documentation | Swagger  

* To access the documentation, simply start the project and visit the URL below:  
```http://localhost:8080/swagger-ui/index.html```  
