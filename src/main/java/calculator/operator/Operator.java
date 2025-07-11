package calculator.operator;

public interface Operator<T> {
    T operate(T a, T b);
}