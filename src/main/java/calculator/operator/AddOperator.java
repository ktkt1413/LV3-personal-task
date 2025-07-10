package calculator.operator;

public class AddOperator implements Operator {
    @Override
    public int operate(int a, int b) {
        return a + b;
    }
}
