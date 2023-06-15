package org.sanitas.calculator;


import org.junit.jupiter.api.Test;
import org.sanitas.calculator.infrastructure.CalculatorControllerImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestConstructor;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class CalculatorControllerTest {

    @LocalServerPort
    private int port;

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void add_ShouldReturnCorrectSum_TwoNumbers_Rest(){
        String url = "http://localhost:" + port + "/add?num1=2.0&num2=3.0";
        ResponseEntity<Double> response = restTemplate.getForEntity(url, Double.class);
        assertEquals(5.0, response.getBody(), 0.001);
    }

    @Test
    public void subtract_ShouldReturnCorrectDifference_TwoNumbers_Rest(){
        String url = "http://localhost:" + port + "/subtract?num1=3.0&num2=2.0";
        ResponseEntity<Double> response = restTemplate.getForEntity(url, Double.class);
        assertEquals(1.0, response.getBody(), 0.001);
    }

}
