package calculator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CircleCalculator {
    private final List<String> savedData = new ArrayList<>();
    private double radius;
    private double result;
    public static final double PIE = Math.PI;  // 상수임을 명시하기 위해 대문자로 명명
    // static:클래스 로딩 시 한 번만 메모리에 올라감. 객체 생성 없이도 클래스 이름으로 접근 가능 -> public으로 외부접근 허용
    // final: 한번 정의 된 후 변하지 않는 값(파이 값은 변하지 않으므로)
    public CircleCalculator(){};

    public CircleCalculator(double radius) {
        this.radius = radius;
    }

    public double circleExtent(Scanner sc) {
        result = radius * radius * PIE ;
        DecimalFormat df = new DecimalFormat("#,###.####");
        System.out.println("원의 넓이는 "+df.format(result)+" 입니다.");

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

    public void save(double num) {
        DecimalFormat df = new DecimalFormat("#,###.####");
        String formatted = df.format(num);
        savedData.add(formatted);
        System.out.println("계산한 원 넓이가 저장되었습니다.");
    }

    public Double readyCircle(Scanner sc) {
        System.out.println("입력하신 반지름의 원 넓이를 구합니다.");

        while(true){
            System.out.print("계산하고 싶은 원의 반지름을 입력해주세요.(소수 가능) : ");
            String input = sc.nextLine().trim();

            if(input.equalsIgnoreCase("exit")){
                System.out.println("계산을 종료하고 메인메뉴로 돌아갑니다.");
                System.out.println();
                return null;
            }
            if(input.trim().isEmpty()){
                System.out.print("입력값이 없습니다. 다시 입력해주세요: ");
                continue;
            }
            try{
                radius = Double.parseDouble(input);
                break;
            } catch(NumberFormatException e){
                System.out.println("잘못 입력하였습니다. 정수를 입력해주세요");
            }
        }
        return radius;
    }

    public void finishCircle(Scanner sc) {
        while(true){
            System.out.println();
            System.out.println("저장된 연산결과를 조회하시겠습니까? (inquiry 입력 시 조회): ");
            System.out.println("가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제): ");
            System.out.println("현재 저장되어있는 모든값을 삭제 하시겠습니까? ('clear'입력)");
            System.out.println();
            System.out.println("더 계산하길 원하시면 'enter키'를, 종료를 원하시면 'exit'을 입력해주세요: ");

            String answer = sc.nextLine().trim();

            if(answer.equalsIgnoreCase("remove")) {
                if(!this.getData().isEmpty()){
                    String first = this.getData().remove(0);
                    System.out.println("저장된 첫 번째 "+first+"이 삭제됩니다.");
                    System.out.println("현재 저장된 값은 "+String.join(" ", this.getData())+" 입니다.");
                } else {
                    System.out.println("삭제할 값이 없습니다.");
                }
            } else if(answer.equalsIgnoreCase("inquiry")){
                System.out.print("현재 계산기에 저장된 값은 ");
                if(!this.getData().isEmpty()){
                    System.out.println(String.join(" ", this.getData()));
                    System.out.println();
                }else {
                    System.out.println("없습니다.");
                }
            } else if (answer.equalsIgnoreCase("clear")) {
                if(!this.getData().isEmpty()){
                    System.out.println("계산기에 저장된 모든 값을 삭제합니다.");
                    this.getData().clear();
                } else {
                    System.out.println("삭제할 값이 존재하지 않습니다.");
                }

            } else if ( answer.equalsIgnoreCase("exit")){
                System.out.println("계산을 종료하고 메인메뉴로 돌아갑니다.");
                System.out.println();
                return;
            } else if (answer.isBlank()){
                Double input = this.readyCircle(sc);
                if(input == null){  // <- exit 를 입력한 경우
                    System.out.println("계산을 종료하고 메인메뉴로 돌아갑니다.");
                    System.out.println();
                    return;
                }
                this.setValues(input);
                this.circleExtent(sc);
            }else {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    // getter 메서드
    public List<String> getData() {
        return savedData;
    }

    public Double getRadius(){
        return this.radius;
    }

    //setter 메서드
    public void setValues(double radius) {
        this.radius = radius;
    }

}