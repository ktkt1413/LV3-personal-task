package calculator.operator;

public class MultiplyOperator implements Operator<Double> {
    @Override
    public Double operate(Double a, Double b) {
        return a * b;
    }
}