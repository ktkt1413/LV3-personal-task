package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> result = new ArrayList<>();

        while (true) {

            System.out.println("사칙연산을 시작합니다. (종료를 원하시면 'exit' 입력)");
            int num1=0;
            int num2=0;
            char operator = ' ';

//           while(true){
//                System.out.print("두 번째 숫자를 입력하세요: ");
//                if(sc.hasNextInt()){
//                    num2 = sc.nextInt();
//                    sc.nextLine();
//                    break;
//                }else{
//                    System.out.println("잘못 입력하셨습니다. 숫자를 다시 입력해주세요");
//                    sc.nextLine();
//                }
//            }

            while(true) {
                System.out.print("첫 번째 숫자를 입력하세요: ");
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("작업이 종료됩니다.");
                    sc.close();
                    return;
                }
                try {
                    num1 = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("잘못 입력하였습니다. 정수를 입력해주세요.");
                }
            }

            while(true) {
                System.out.print("두 번째 숫자를 입력하세요: ");
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("작업이 종료됩니다.");
                    sc.close();
                    return;
                }
                try {
                    num2 = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("잘못 입력하였습니다. 정수를 입력해주세요.");
                }
            }

            while(true) {
                System.out.print("사칙연산 기호를 입력하세요: "); // + , - , * , /
                // charAt(index)은 매개변수로 char 타입으로 반환하고자 하는 문자열의 위치(index)를 받는다
                String opInput = sc.nextLine();
                if (opInput.equalsIgnoreCase("exit")) {
                    System.out.println("작업이 종료됩니다.");
                    sc.close();
                    return;
                }
                if (opInput.length() == 1) {
                    operator = opInput.charAt(0);
                    if (operator == '+' || operator == '-' || operator == '*' || operator == '/') {
                        break;
                    }
                }
                System.out.println("잘못된 연산자 입니다. 다시 입력해주세요.");
            }
//            while (!(operator == '+' || operator == '-' || operator == '*' || operator == '/')) {
//                System.out.println("+,-,*,/ 중에 하나를 입력해주세요");
//                operator = sc.next().charAt(0);
//                sc.nextLine();
//                if (operator == '+' || operator == '-' || operator == '*' || operator == '/') {
//                    break;
//                } else {
//                    System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
//                }
//            }

            int midResult;
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
//                    while (num2 == 0) {
//                        System.out.println("0으로 나눌 수 없습니다. 다시 입력해주세요");
//                        num2 = sc.nextInt();
//                    }
                    while(num2 == 0){
                        System.out.println("0을 제외한 숫자를 입력해주세요");
                        String input = sc.nextLine();
                        if (input.equalsIgnoreCase("exit")) {
                            System.out.println("작업이 종료됩니다.");
                            sc.close();
                            return;
                        }
                        try{
                            num2 = Integer.parseInt(input);
                            if(num2 == 0){
                                System.out.println("0으로 나눌 수 없습니다. 정수를 입력해주세요");
                            }
                        } catch (NumberFormatException e){
                            System.out.println("잘못된 입력입니다. 정수를 다시 입력해주세요.");
                        }
//                        if(sc.hasNextInt()){
//                            num2 = sc.nextInt();
//                            sc.nextLine();
//                            if(num2 !=0){
//                                bufferCleared = true;
//                                break;
//                            }
//                        } else{
//                            System.out.println("잘못 누르셨습니다.");
//                            sc.nextLine();
//                        }
                    }
                    midResult = num1/num2;
                    break;
                default:
                    System.out.println("알 수 없는 오류가 발생했습니다. 다시 입력해주세요");
                    sc.nextLine();
                    continue;
            }

            result.add(midResult);

            System.out.println("결과: " + midResult);


            System.out.println("가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제)");
            System.out.println("저장된 연산결과를 조회하시겠습니까? (inquiry 입력 시 조회)");
            System.out.println("더 계산하길 원하시면 'enter키'를, 종료를 원하시면 'exit'을 입력해주세요");

            String answer = sc.nextLine();

            if (answer.equalsIgnoreCase("remove")) {
                if (!result.isEmpty()) {
                    System.out.println("저장된 첫 번째 " + result.get(0) + "이 삭제 되었습니다.");
                    result.remove(0);
                    System.out.println("현재 저장된 값은 " + result);
                } else {
                    System.out.println("삭제할 결과가 없습니다.");
                }
            } else if (answer.equalsIgnoreCase("inquiry")) {
                for (int r : result) {
                    System.out.print(r + " ");
                }
                System.out.println();
            } else if (answer.equalsIgnoreCase("exit")) {
                System.out.println("작업이 종료됩니다.");
                sc.close();
                return;
            }else if(answer.isBlank()){
                continue;
            }else {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }
}
