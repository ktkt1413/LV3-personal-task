# 🧮 Java 콘솔 계산기

Java 기반 콘솔 애플리케이션으로, 사용자가 직접 입력한 값을 바탕으로 **사칙연산**과 **원의 넓이 계산**을 수행합니다.  
계산 결과는 저장하거나 삭제할 수 있으며, 람다식, Optional, 컬렉션 등을 활용하여 기능을 간결하게 구성하였습니다.

---

## 📌 기능 요약

| 기능 | 설명 |
|------|------|
| 사칙연산 계산기 | 덧셈, 뺄셈, 곱셈, 나눗셈, 나머지 연산 수행 |
| 원의 넓이 계산기 | 반지름 입력 후 넓이 계산 (πr²) |
| 저장 기능 | 결과를 메모리(List)에 저장, 조회, 삭제 가능 |
| 종료 기능 | 언제든 `exit` 입력 시 메인메뉴 혹은 프로그램 종료 |

---

## 🛠 사용 기술

- Java 11+
- 객체지향 프로그래밍 (OOP)
- 람다식 (`ifPresentOrElse`)
- Optional 활용
- 컬렉션 프레임워크 (`List`, `Map`)
- 인터페이스 기반 다형성
- `DecimalFormat` 포맷 사용

---

## 💻 실행 방법
### 1. 프로젝트 클론
git clone https://github.com/ktkt1413/LV3-personal-task.git

### 2. 컴파일
javac calculator/*.java calculator/operator/*.java

### 3. 실행
java calculator.App

# 🔗 파일 연결 구조 요약
📦 calculator  
├── App.java                    ⬅️ 실행 시작 지점 (main)  
├── ArithmeticCalculator.java   ⬅️ 사칙연산 처리  
├── CircleCalculator.java       ⬅️ 원 넓이 계산 처리  
├── ArithmeticInput.java        ⬅️ 사칙연산 입력값 저장용 DTO  
└── operator  
&nbsp;&nbsp;&nbsp;&nbsp;├── Operator.java           ⬅️ 인터페이스  
&nbsp;&nbsp;&nbsp;&nbsp;├── AddOperator.java        ⬅️ 덧셈  
&nbsp;&nbsp;&nbsp;&nbsp;├── SubtractOperator.java   ⬅️ 뺄셈  
&nbsp;&nbsp;&nbsp;&nbsp;├── MultiplyOperator.java   ⬅️ 곱셈  
&nbsp;&nbsp;&nbsp;&nbsp;├── DivideOperator.java     ⬅️ 나눗셈  
&nbsp;&nbsp;&nbsp;&nbsp;├── ModOperator.java        ⬅️ 나머지  
&nbsp;&nbsp;&nbsp;&nbsp;└── OperatorType.java       ⬅️ enum (연산자 기호 → 클래스 연결용)


---

## 🧩 주요 클래스 설명

| 클래스명                  | 역할 설명 |
|---------------------------|------------|
| `App.java`                | 메인 실행 클래스 (사용자 입력 처리 및 흐름 제어) |
| `ArithmeticCalculator.java` | 사칙연산 계산, 결과 저장/조회/삭제 처리 |
| `CircleCalculator.java`   | 원의 넓이 계산 (πr²), 결과 저장 및 관리 |
| `ArithmeticInput.java`    | 사칙연산을 위한 두 숫자와 연산자를 담는 DTO |
| `Operator.java`           | 사칙연산 클래스들의 공통 인터페이스 |
| `AddOperator.java`        | 덧셈 연산 클래스 |
| `SubtractOperator.java`   | 뺄셈 연산 클래스 |
| `MultiplyOperator.java`   | 곱셈 연산 클래스 |
| `DivideOperator.java`     | 나눗셈 연산 클래스 (0 나눗셈 예외 처리 포함) |
| `ModOperator.java`        | 나머지 연산 클래스 |
| `OperatorType.java`       | 연산자 기호를 enum으로 정의하고 해당 연산 클래스 매핑 |

---

필요하다면 이 구조를 이미지나 다이어그램으로도 시각화할 수 있어요. 추가 요청 주세요!

## 🔹 App.java

- 메인 실행 클래스  
- 메뉴 선택(1: 사칙연산, 2: 원 넓이) 처리  
- 사용자 입력에 따라 `ArithmeticCalculator`, `CircleCalculator` 실행

---

## 🔹 ArithmeticCalculator.java

- 사칙연산 계산 및 결과 저장/조회/삭제 처리  
- `OperatorType`에 따라 동적 연산 수행  

**주요 메서드**
- `readyArithmetic()` : 사용자로부터 두 수와 연산자 입력받음  
- `arithmetic()` : 연산 실행 및 결과 저장 여부 확인  
- `finishArithmetic()` : 저장 결과에 대한 조회, 삭제, 초기화 기능  

---

## 🔹 CircleCalculator.java

- 원의 넓이(πr²) 계산  

**주요 메서드**
- `readyCircle()` : 반지름 입력받기  
- `circleExtent()` : 넓이 계산 및 저장 여부 확인  
- `finishCircle()` : 결과 저장 조회/삭제/초기화 기능  

---

## 📦 연산자 구성 (`calculator.operator` 패키지)

| 인터페이스 / 클래스 | 역할 |
|---------------------|------|
| `Operator<T>`       | 모든 연산 클래스의 공통 인터페이스 |
| `AddOperator`       | 덧셈 |
| `SubtractOperator`  | 뺄셈 |
| `MultiplyOperator`  | 곱셈 |
| `DivideOperator`    | 나눗셈 (0 나누기 예외 처리 포함) |
| `ModOperator`       | 나머지 연산 |

---

## 🧠 기능 흐름 예시

1. 프로그램 실행  
2. 사용자에게 `1. 사칙연산` 또는 `2. 원 넓이` 선택 요구  
3. 연산 수행 후 결과 출력  
4. 결과 저장 여부 선택 (y/n)  
5. 이후 `inquiry`, `remove`, `clear`, `exit`, `enter` 중 선택  

---

## 📤 예외 처리

- 숫자가 아닌 값 입력 시 안내 출력  
- 연산자 잘못 입력 시 재입력 유도  
- 0으로 나눌 수 없도록 검사  
- `exit` 입력 시 언제든 종료 가능  

---

## 🙌 기여

이 프로젝트는 학습용으로 만들어졌으며, 다음과 같은 기여를 환영합니다:

- 기능 개선  
- 리팩토링 (ex. Stream, 람다 활용)  
- JUnit 테스트 코드 작성  
- 예외 처리 개선  

Pull Request는 언제든지 환영합니다! 😊

---

## 📄 License

This project is licensed under the [MIT License](LICENSE.txt).

