# ⚛️ NanoURL | URL Shortener  

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
