package org.sanitas.calculator.application.Impl;



import io.corp.calculator.TracerImpl;
import org.sanitas.calculator.domain.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    private final TracerImpl tracer;

    @Autowired
    public CalculatorServiceImpl(TracerImpl tracer) {
        this.tracer = tracer;
    }

    @Override
    public double add(double a, double b) {
        double result = a + b;
        comprobarResultInfinito(result);
        return result;
    }

    private void comprobarResultInfinito(double result) {
        if (Double.isInfinite(result)) {
            tracer.trace("El resultado es infinito.");
        } else {
            tracer.trace(result);
        }
    }

    @Override
    public double subtract(double a, double b) {
        double result = a - b;
        comprobarResultInfinito(result);
        return result;
    }
}
