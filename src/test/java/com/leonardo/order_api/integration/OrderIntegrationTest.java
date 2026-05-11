package com.leonardo.order_api.integration;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

@Testcontainers //Enables automatic container lifecycle management.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //Starts the real application.
public class OrderIntegrationTest {

    @Container //Defines a Docker container used during the test
    static MySQLContainer<?> mysql =
            new MySQLContainer<>("mysql:8.0")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");

    @LocalServerPort //Provides the random port used by the application.
    int port;

    @DynamicPropertySource //Injects the container connection properties into Spring Boot.
    static void configureProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Test
    void shouldCreateOrderSuccessfully() {
        RestAssured.given()
                .port(port)
                .contentType(JSON)
                .body("""
                      {
                        "customer": "Leonardo",
                        "product": "MacBook Pro",
                        "price": 15000
                      }
                      """)
                .when()
                .post("/orders")
                .then()
                .statusCode(200)
                .body("customer", equalTo("Leonardo"))
                .body("product", equalTo("MacBook Pro"));
    }

    //TODO - In future new negatives tests should be implementation
}
