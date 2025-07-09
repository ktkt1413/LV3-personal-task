package calculator;

import java.util.Scanner;

public class InputFunc {
    public static Object[] getnInputs(Scanner sc) {
        int num1 = 0;
        int num2 = 0;
        char operator = '+';

        System.out.println("사칙연산을 시작합니다. (종료를 원하시면 'exit' 입력)");

        // 첫 번째 숫자 입력
        while (true) {
            System.out.print("첫 번째 숫자를 입력하세요: ");
            String input = sc.nextLine();
            if (input.equals("exit")) {
                System.out.println("작업이 종료됩니다.");
                return null;
            }
            try {
                num1 = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("잘못 입력하였습니다. 정수를 입력해주세요.");
            }
        }

        // 두 번째 숫자 입력
        while (true) {
            System.out.print("두 번째 숫자를 입력하세요: ");
            String input = sc.nextLine();
            if (input.equals("exit")) {
                System.out.println("작업이 종료됩니다.");
                return null;
            }
            try {
                num2 = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("잘못 입력하였습니다. 정수를 입력해주세요.");
            }
        }

        // 사칙연산 입력

        while (true) {
            System.out.println("사칙연산 (+, -, *, /) 기호를 한개만 입력해주세요");
            String opInput = sc.nextLine();
            if (opInput.equals("exit")) {
                System.out.println("작업이 종료됩니다.");
                return null;
            }
            if (opInput.length() == 1) {
                operator = opInput.charAt(0);
                if (operator == '+' || operator == '-' || operator == '*' || operator == '/') {
                    break;
                }
                else {
                    System.out.println("잘못된 연산자 입니다. 다시 입력해주세요.");
                }
            } else {
                System.out.println("연산자는 1개만 입력해주세요");
            }
        }
        return new Object[]{num1, num2, operator};
    }
}
