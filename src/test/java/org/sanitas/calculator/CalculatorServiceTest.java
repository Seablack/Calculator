package org.sanitas.calculator;

import org.junit.jupiter.api.Test;
import org.sanitas.calculator.application.CalculatorServiceImpl;
import org.sanitas.calculator.domain.CalculatorService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CalculatorServiceImpl.class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class CalculatorServiceTest {

    private final CalculatorService calculatorService;

    public CalculatorServiceTest(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @Test
    public void add_ShouldReturnCorrectSum_TwoNumbers(){
        assertEquals(3.4, calculatorService.add(2.2,1.2), 0.001);
    }

    @Test
    public void substract_ShouldReturnCorrectDifference_TwoNumbers(){
        assertEquals(-0.5, calculatorService.subtract(2.7,3.2), 0.001);
    }
}
