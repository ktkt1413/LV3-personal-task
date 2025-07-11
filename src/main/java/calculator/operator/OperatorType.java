package calculator.operator;

import java.util.Optional;

public enum OperatorType {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/'),
    MOD('%');

    private final char symbol;

    OperatorType(char symbol){
        this.symbol = symbol;
    }

    public char getSymbol(){
        return symbol;
    }

    public static Optional<OperatorType> fromChar (char ch){
        for (OperatorType op : OperatorType.values()){
            if(op.symbol == ch){
                return Optional.of(op);
            }
        }return Optional.empty();
    }

}
