package calculator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("계산기 ON.(종료를 원하시면 언제든 'exit'을 입력하세요.)");
            System.out.print("계산하고 싶은 번호를 선택해주세요.(1.사칙연산 , 2.원넓이): ");

            String input = sc.nextLine().trim();
            int pressButton=0;

            if(input.equalsIgnoreCase("exit")){
                System.out.println("작업을 종료합니다. ");
                sc.close();
                return;
            } else{
                try{
                    pressButton = Integer.parseInt(input);
                    if(pressButton != 1 && pressButton != 2){
                        System.out.println("1 또는 2만 입력 가능합니다. 다시 입력해주세요.");
                        continue;
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("잘못 입력하셨습니다. 숫자 '1' 또는 '2'를 입력해주세요.");
                }
                if(pressButton == 1){
                    ArithmeticCalculator calc = new ArithmeticCalculator();
                    ArithmeticInput values = calc.readyArithmetic(sc);
                    if(values == null){
                        continue;
                    }else {
                        calc.setValues(values.num1, values.num2, values.operator);
                        double resultNum = calc.arithmetic(sc);  // 반환값이 있어서 resultNum을 만들긴 했는데 쓸데가 없다..
                        if(resultNum == -99999.0){
                            continue;
                        }
                        calc.finishArithmetic(sc);               // 마찬가지로 삭제할 수 없다. 계산이 안 된다.
                    }
                } else if (pressButton ==2){
                    CircleCalculator calc = new CircleCalculator();
                    Double value = calc.readyCircle(sc);
                    if(value == null){
                        continue;
                    } else {
                        calc.setValues(value);
                        double resultNum = calc.circleExtent(sc);  // 마찬가지 쓸데가 없다..그런데 삭제면 계산이 안되는듯
                        if(resultNum == 0){
                            continue;
                        }
                        calc.finishCircle(sc);
                    }
                }
            }



        }



    }
}