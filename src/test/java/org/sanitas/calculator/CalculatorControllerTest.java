package org.sanitas.calculator;


import io.corp.calculator.TracerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.TestConstructor;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class CalculatorControllerTest {
    @LocalServerPort
    private int port;

    private RestTemplate restTemplate = new RestTemplate();

    @TestConfiguration
    public static class TestConfig {

        @Bean
        public TracerImpl testTracer() {
            return new TracerImpl();
        }
    }

    @Test
    public void add_ShouldReturnCorrectSum_TwoNumbers_Rest() {
        String url = "http://localhost:" + port + "/add?num1=2.0&num2=3.0";
        ResponseEntity<Double> response = restTemplate.getForEntity(url, Double.class);
        assertEquals(5.0, response.getBody(), 0.001);
    }

    @Test
    public void add_ShouldReturnError_WhenNonNumericValues_Rest() {
        String url = "http://localhost:" + port + "/add?num1=2.0&num2=abc";
        try {
            restTemplate.getForEntity(url, String.class);
            fail("Ambos argumentos deben ser números válidos");
        } catch (HttpClientErrorException ex) {
            assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
            assertTrue(ex.getResponseBodyAsString().contains("Ambos argumentos deben ser números válidos."));
        }
    }

    @Test
    public void add_ShouldReturnError_WhenResultIsInfinite_Rest() {
        String url = "http://localhost:" + port + "/add?num1=" + Double.POSITIVE_INFINITY + "&num2=1.0";
        try {
            restTemplate.getForEntity(url, String.class);
            fail("El resultado es infinito");
        } catch (HttpClientErrorException.BadRequest ex) {
            assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
            assertTrue(ex.getResponseBodyAsString().contains("Error en la operación de suma: El resultado es infinito."));
        }

    }

    @Test
    public void subtract_ShouldReturnCorrectDifference_TwoNumbers_Rest() {
        String url = "http://localhost:" + port + "/subtract?num1=2.0&num2=3.0";
        ResponseEntity<Double> response = restTemplate.getForEntity(url, Double.class);
        assertEquals(-1.0, response.getBody(), 0.001);
    }

    @Test
    public void subtract_ShouldReturnError_WhenNonNumericValues_Rest() {
        String url = "http://localhost:" + port + "/subtract?num1=2.0&num2=abc";
        try {
            restTemplate.getForEntity(url, String.class);
            fail("Ambos argumentos deben ser números válidos");
        } catch (HttpClientErrorException ex) {
            assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
            assertTrue(ex.getResponseBodyAsString().contains("Ambos argumentos deben ser números válidos."));
        }
    }

    @Test
    public void subtract_ShouldReturnError_WhenResultIsInfinite_Rest() {
        String url = "http://localhost:" + port + "/subtract?num1=" + Double.NEGATIVE_INFINITY + "&num2=1.0";
        try {
            restTemplate.getForEntity(url, String.class);
            fail("El resultado es infinito");
        } catch (HttpClientErrorException.BadRequest ex) {
            assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
            assertTrue(ex.getResponseBodyAsString().contains("Error en la operación de resta: El resultado es infinito."));
        }

    }


}
