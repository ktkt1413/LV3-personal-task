package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArithmeticCalculator {
    private final List<Integer> result = new ArrayList<>();
    private int num1;
    private int num2;
    private char operator;

    public ArithmeticCalculator() {
    }

    public ArithmeticCalculator(int num1, int num2, char operator) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
    }

    public int arithmetic(Scanner sc) {
        int midResult = 0;
        while(true) {

            if(operator == '/' && num2 == 0) {
                while(num2 ==0){
                    System.out.println("0을 제외한 다른 수를 입력해주세요");
                    String input = sc.nextLine();
                    if (input.equalsIgnoreCase("exit")) {
                        System.out.println("작업이 종료됩니다.");
                        return 0;
                    }
                    try{
                        num2 = Integer.parseInt(input);
                    } catch(NumberFormatException e) {
                        System.out.println("정수를 입력해주세요");
                    }
                }
            }

            switch (operator) {
                case '+':
                    midResult = num1 + num2;
                    break;
                case '-':
                    midResult = num1 - num2;
                    break;
                case '*':
                    midResult = num1 * num2;
                    break;
                case '/':
                    midResult = num1 / num2;
                    break;
                default:
                    System.out.println("알 수 없는 오류가 발생했습니다. 다시 입력해주세요");
                    sc.nextLine();
                    continue;
            }
            System.out.println("결과: " + midResult);
            save(midResult);
            return midResult;
        }
    }
    public void save(int num){
        result.add(num);
        System.out.println("계산한 값이 저장되었습니다.");
    }

    public void clearAll(){
        result.clear();
    }

    public ArithmeticInput readyArithmetic(Scanner sc) {
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
        // 사칙연산 입력
        while (true) {
            System.out.print("사칙연산 (+, -, *, /) 기호를 한개만 입력해주세요: ");
            String opInput = sc.nextLine();
            if (opInput.equals("exit")) {
                System.out.println("작업이 종료됩니다.");
                return null;
            }
            if (opInput.length() == 1) {
                operator = opInput.charAt(0);
                if (operator == '+' || operator == '-' || operator == '*' || operator == '/') {
                    break;
                } else {
                    System.out.println("잘못된 연산자 입니다. 다시 입력해주세요.");
                }
            } else {
                System.out.println("연산자는 1개만 입력해주세요");
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
        return new ArithmeticInput(num1, num2, operator);
    }

    public void finishArithmetic(Scanner sc) {
        while (true) {
            System.out.println();
            System.out.println("가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제): ");
            System.out.println("저장된 연산결과를 조회하시겠습니까? (inquiry 입력 시 조회): ");
            System.out.println("현재 저장되어있는 값을 삭제 하시겠습니까? ('clear'입력)");
            System.out.println("더 계산하길 원하시면 'enter키'를, 종료를 원하시면 'exit'을 입력해주세요: ");


            String answer = sc.nextLine();
            List<Integer> currentResult = this.getResult();

            if (answer.equalsIgnoreCase("remove")) {
                if (!currentResult.isEmpty()) {
                    System.out.println("저장된 첫 번째 " + currentResult.get(0) + "이 삭제 되었습니다.");
                    currentResult.remove(0);
                    System.out.println("현재 저장된 값은 " + currentResult);
                } else {
                    System.out.println("삭제할 결과가 없습니다.");
                }
            } else if (answer.equalsIgnoreCase("inquiry")) {
                System.out.print("현재 계산기에 저장된 값은 ");
                if(!currentResult.isEmpty()) {
                    for (int r : currentResult) {
                        System.out.print(r + " ");
                    }
                    System.out.println();
                } else {
                    System.out.println("현재 저장되어 있는 값이 없습니다.");
                }

            }else if (answer.equalsIgnoreCase("clear")) {
                this.getResult().clear();
            } else if (answer.equalsIgnoreCase("exit")) {
                System.out.println("작업이 종료됩니다.");
                return;
            } else if (answer.isBlank()) {
                ArithmeticInput input2 = this.readyArithmetic(sc);
                if(input2 == null) {
                    System.out.println("작업이 종료됩니다.");
                    return;
                }
                this.setValues(input2.num1, input2.num2, input2.operator);
                this.arithmetic(sc);
            } else {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    //getter
    public int getNum1(){
        return num1;
    }

    public int getNum2(){
        return num2;
    }

    public char getOperator(){
        return operator;
    }

    public List<Integer> getResult(){
        return result;
    }


    //setter
    public void setValues(int num1, int num2,  char operator) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
    }
}
