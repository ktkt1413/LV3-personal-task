package calculator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArithmeticCalculator calc = new ArithmeticCalculator();

        ArithmeticInput values = calc.readyArithmetic(sc);
        if(values == null){
            sc.close();
            return;
        }

        calc.setValues(values.num1, values.num2, values.operator);
        int resultNum = calc.arithmetic(sc);

        calc.finishArithmetic(sc);

    }
}

