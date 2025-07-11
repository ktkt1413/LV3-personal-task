
package calculator;

import calculator.operator.*;

import java.text.DecimalFormat;
import java.util.*;

public class ArithmeticCalculator {
    private final List<String> savedData = new ArrayList<>();
    private final Map<OperatorType, Operator<Double>> operators = new HashMap<>();
    private double num1;
    private double num2;
    private OperatorType operator;


    public ArithmeticCalculator(double num1, double num2, OperatorType operator) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
        initOperators();
    }

    private void initOperators() {
        operators.put(OperatorType.ADD, new AddOperator());
        operators.put(OperatorType.SUBTRACT, new SubtractOperator());
        operators.put(OperatorType.MULTIPLY, new MultiplyOperator());
        operators.put(OperatorType.DIVIDE, new DivideOperator());
        operators.put(OperatorType.MOD, new ModOperator());
    }

    public ArithmeticCalculator() {
        initOperators();
    }

    public double arithmetic(Scanner sc) {
        while(true) {
            if(operator == OperatorType.DIVIDE && num2 == 0.0) {
                while(num2 == 0.0){
                    System.out.println("0을 제외한 다른 수를 입력해주세요");
                    String input = sc.nextLine().trim();
                    if (input.equalsIgnoreCase("exit")) {
                        System.out.println("계산을 종료하고 메인메뉴로 돌아갑니다.");
                        System.out.println();
                        return 0;
                    }
                    try{
                        num2 = Double.parseDouble(input);
                    } catch(NumberFormatException e) {
                        System.out.println("숫자를 입력해주세요");
                    }
                }
            }          //.get(operator) 여기서 operator은 사칙연산 클래스 중 하나이다. 입력한 연산 클래스의 객체가 op이다.
            Operator<Double> op = operators.get(operator); //다형성(Operator인터페이스타입의 변수 op)을 통해
            if(op == null){                        //같은 메서드이름(operator())으로 다른 기능 구현
                throw new IllegalArgumentException("지원하지 않는 연산자 입니다.");
            }
            double result = op.operate(num1, num2);

            DecimalFormat df = new DecimalFormat("#,###.####");
            System.out.println("결과: " + df.format(result));
            char saveAnswer;
            while(true){
                System.out.println("이 결과를 저장하시겠습니까? (y/n)");
                String saveInput = sc.nextLine().trim();
                if (saveInput.equalsIgnoreCase("exit")) {
                    System.out.println("계산을 종료하고 메인메뉴로 돌아갑니다.");
                    System.out.println();
                    return 0;
                }else if(saveInput.length() == 1){
                    saveAnswer = saveInput.charAt(0);
                    if(saveAnswer == 'y'||saveAnswer == 'Y'){
                        this.save(result);
                    } else if (saveAnswer == 'n'||saveAnswer == 'N') {
                        System.out.println("결과를 저장하지 않습니다.");
                    }else {
                        System.out.println("잘못 입력하셨습니다. y 또는 n 중 하나만 입력하세요");
                        System.out.println();
                        continue;
                    }
                } return result;
            }
        }
    }

    public void save(double num){
        DecimalFormat df = new DecimalFormat("#,###.####");
        String formatted = df.format(num);
        savedData.add(formatted);
        System.out.println("계산한 값이 저장되었습니다.");
    }
    public ArithmeticInput readyArithmetic(Scanner sc) {
        System.out.println("사칙연산을 시작합니다. (메인메뉴로 돌아가길 원하시면 'exit' 입력)");
        System.out.println();

        // 첫 번째 숫자 입력
        while (true) {
            System.out.print("첫 번째 숫자를 입력하세요: ");
            String input = sc.nextLine().trim();
            if (input.equals("exit")) {
                System.out.println("계산을 종료하고 메인메뉴로 돌아갑니다.");
                System.out.println();
                return null;
            }
            try {
                num1 = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("잘못 입력하였습니다. 숫자를 입력해주세요.");
            }
        }
        // 사칙연산 입력
        while (true) {
            System.out.print("사칙연산 (+, -, *, /, %) 기호를 한개만 입력해주세요: ");
            String opInput = sc.nextLine().trim();
            if (opInput.equals("exit")) {
                System.out.println("계산을 종료하고 메인메뉴로 돌아갑니다.");
                System.out.println();
                return null;
            }
            if (opInput.length() == 1) {
                Optional<OperatorType> op = OperatorType.fromChar(opInput.charAt(0));
                if (op.isPresent()) {
                    operator =op.get();
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
            String input = sc.nextLine().trim();
            if (input.equals("exit")) {
                System.out.println("계산을 종료하고 메인메뉴로 돌아갑니다.");
                System.out.println();
                return null;
            }
            try {
                num2 = Double.parseDouble(input);
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
            System.out.println("더 계산하길 원하시면 'enter키'를, 메인메뉴로 돌아가기를 원하시면 'exit'을 입력해주세요: ");


            String answer = sc.nextLine().trim();

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
                    for (String r : this.getData()) {
                        System.out.print(r + " ");
                    }
                    System.out.println();
                } else {
                    System.out.println("없습니다.");
                }

            }else if (answer.equalsIgnoreCase("clear")) {
                if(!this.getData().isEmpty()){
                    System.out.println("계산기에 저장된 모든 값을 삭제합니다.");
                    this.getData().clear();
                } else {
                    System.out.println("삭제할 값이 존재하지 않습니다.");
                }
            } else if (answer.equalsIgnoreCase("exit")) {
                System.out.println("계산을 종료하고 메인메뉴로 돌아갑니다.");
                System.out.println();
                return;
            } else if (answer.isBlank()) {
                ArithmeticInput input2 = this.readyArithmetic(sc);
                if(input2 == null) {  // <- exit 를 입력한 경우
                    System.out.println("계산을 종료하고 메인메뉴로 돌아갑니다.");
                    System.out.println();
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
    public double getNum1(){
        return num1;
    }

    public double getNum2(){
        return num2;
    }

    public OperatorType getOperator(){
        return operator;
    }

    public List<String> getData(){
        return savedData;
    }


    //setter
    public void setValues(double num1, double num2,  OperatorType operator) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
    }
}
