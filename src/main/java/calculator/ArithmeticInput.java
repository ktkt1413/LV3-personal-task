package calculator;

import calculator.operator.OperatorType;

public class ArithmeticInput {
    public double num1;
    public double num2;
    public OperatorType operator;

    public ArithmeticInput(double num1, double num2, OperatorType operator) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
    }
}