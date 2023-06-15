package org.sanitas.calculator.application.Impl;


import io.corp.calculator.TracerImpl;
import org.sanitas.calculator.domain.CalculatorService;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    private TracerImpl tracer;

    public CalculatorServiceImpl (TracerImpl tracer){
        this.tracer = tracer;
    }
    @Override
    public double add(double a, double b) {
        double result = a + b;
        tracer.trace(result);
        return result;
    }

    @Override
    public double subtract(double a, double b) {
        double result = a - b;
        tracer.trace(result);
        return result;
    }
}
