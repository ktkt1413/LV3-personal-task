package calculator;

import calculator.operator.*;

import java.util.*;

public class ArithmeticCalculator {
    private final List<Integer> savedData = new ArrayList<>();
    private final Map<Character, Operator> operators = new HashMap<>();
    private int num1;
    private int num2;
    private char operator;


    public ArithmeticCalculator(int num1, int num2, char operator) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
    }

    public ArithmeticCalculator() {
        operators.put('+', new AddOperator());
        operators.put('-', new SubtractOperator());
        operators.put('*', new MultiplyOperator());
        operators.put('/', new DivideOperator());
        operators.put('%', new ModOperator());
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
            }          //.get(operator) 여기서 operator은 사칙연산 클래스 중 하나이다. 입력한 연산 클래스의 객체가 op이다.
            Operator op = operators.get(operator); //다형성(Operator인터페이스타입의 변수 op)을 통해
            if(op == null){                        //같은 메서드이름(operator())으로 다른 기능 구현
                throw new IllegalArgumentException("지원하지 않는 연산자 입니다.");
            }
            midResult = op.operate(num1, num2);
            System.out.println("결과: " + midResult);
            this.save(midResult);
            return midResult;  // 연산결과 반환 후 while문 종료
        }
    }

    public void save(int num){
        savedData.add(num);
        System.out.println("계산한 값이 저장되었습니다.");
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
            System.out.print("사칙연산 (+, -, *, /, %) 기호를 한개만 입력해주세요: ");
            String opInput = sc.nextLine();
            if (opInput.equals("exit")) {
                System.out.println("작업이 종료됩니다.");
                return null;
            }
            if (opInput.length() == 1) {
                operator = opInput.charAt(0);
                if (operator == '+' || operator == '-' || operator == '*' || operator == '/'|| operator == '%') {
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
            System.out.println("저장된 연산결과를 조회하시겠습니까? (inquiry 입력 시 조회): ");
            System.out.println("가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제): ");
            System.out.println("현재 저장되어있는 모든값을 삭제 하시겠습니까? ('clear'입력)");
            System.out.println();
            System.out.println("더 계산하길 원하시면 'enter키'를, 종료를 원하시면 'exit'을 입력해주세요: ");


            String answer = sc.nextLine();

            if (answer.equalsIgnoreCase("remove")) {
                if (!this.getData().isEmpty()) {
                    System.out.println("저장된 첫 번째 " + this.getData().get(0) + "이 삭제 되었습니다.");
                    this.getData().remove(0);
                    System.out.println("현재 저장된 값은 " + this.getData() + " 입니다.");
                } else {
                    System.out.println("삭제할 값이 없습니다.");
                }
            } else if (answer.equalsIgnoreCase("inquiry")) {
                System.out.print("현재 계산기에 저장된 값은 ");
                if(!this.getData().isEmpty()) {
                    for (int r : this.getData()) {
                        System.out.print(r + " ");
                    }
                    System.out.println();
                } else {
                    System.out.println("현재 저장되어 있는 값이 없습니다.");
                }

            }else if (answer.equalsIgnoreCase("clear")) {
                System.out.println("계산기에 저장된 모든 값을 삭제하였습니다.");
                this.getData().clear();
            } else if (answer.equalsIgnoreCase("exit")) {
                System.out.println("작업이 종료됩니다.");
                return;
            } else if (answer.isBlank()) {
                ArithmeticInput input2 = this.readyArithmetic(sc);
                if(input2 == null) {  // <- exit 를 입력한 경우
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

    public List<Integer> getData(){
        return savedData;
    }


    //setter
    public void setValues(int num1, int num2,  char operator) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
    }
}
